package Vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosGenerales;
import ModeloPerfil.Cliente;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class TiendaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	private JTable tableUbicacion;
	private JTable tableTienda;
	private JScrollPane scrollpanelUbicacion;
	private JScrollPane scrollpanelTienda;
	private JButton btnAceptar;
	private JLabel lblUbicacion;
	private JLabel lblElemento;
	ventanaCliente ventanaCliente;
	SeleccionTiendaProductos ventanaSeleccionTiendaProductos;
	SeleccionTiendaMascotas ventanaSeleccionTiendaMascotas;
	MetodosGenerales metodosGenerales = new MetodosGenerales();

	/**
	 * Create the frame.
	 */
	public TiendaCliente(Cliente clienteLogIn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCliente = new ventanaCliente(clienteLogIn);
				ventanaCliente.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(25, 337, 89, 23);
		contentPane.add(btnAtras);
		
		scrollpanelUbicacion = new JScrollPane();
		scrollpanelUbicacion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpanelUbicacion.setBounds(36, 73, 281, 189);
		contentPane.add(scrollpanelUbicacion);
		
		String[] titulosUbicacio = new String[] { "Ubicacion" };
		
		tableUbicacion = new JTable();
		try {
			
			Object [][] txtUbicacion = metodosGenerales.generarTablaUbicaciones();
			
			tableUbicacion = new JTable();
			tableUbicacion.setModel(new DefaultTableModel(txtUbicacion, titulosUbicacio));
			tableUbicacion.setCellSelectionEnabled(false);
			tableUbicacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableUbicacion.setDefaultEditor(Object.class, null);
			scrollpanelUbicacion.setViewportView(tableUbicacion);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		scrollpanelTienda = new JScrollPane();
		scrollpanelTienda.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpanelTienda.setBounds(355, 73, 293, 191);
		contentPane.add(scrollpanelTienda);
		
		tableTienda = new JTable();
		try {
			String[] titulosTienda = new String[] { "Tienda" };
			Object [][] txtTienda = metodosGenerales.generarTablaTiendas();
			
			tableTienda.setModel(new DefaultTableModel(txtTienda, titulosTienda));
			tableTienda.setCellSelectionEnabled(false);
			tableTienda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableTienda.setDefaultEditor(Object.class, null);
			
			scrollpanelTienda.setViewportView(tableTienda);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(tableTienda.getSelectedRow() != -1) || !(tableUbicacion.getSelectedRow() != -1)) {
					JOptionPane.showMessageDialog(null, "Selecciona una opcion en ambas tablas");
				}else {
					int valorTienda = tableTienda.getSelectedRow();
					DefaultTableModel tu = (DefaultTableModel) tableUbicacion.getModel();
					String valorUbicacion = String.valueOf(tu.getValueAt(tableUbicacion.getSelectedRow(), 0));
					if(valorTienda == 0) {
						ventanaSeleccionTiendaMascotas = new SeleccionTiendaMascotas(clienteLogIn, valorUbicacion);
						ventanaSeleccionTiendaMascotas.setVisible(true);
						dispose();
					}else {
						ventanaSeleccionTiendaProductos = new SeleccionTiendaProductos(clienteLogIn, valorUbicacion);
						ventanaSeleccionTiendaProductos.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnAceptar.setBounds(628, 337, 89, 23);
		contentPane.add(btnAceptar);
		
		lblUbicacion = new JLabel("Selecciona una ubicacion:");
		lblUbicacion.setBounds(36, 30, 150, 14);
		contentPane.add(lblUbicacion);
		
		lblElemento = new JLabel("Selecciona una elemento:");
		lblElemento.setBounds(355, 30, 150, 14);
		contentPane.add(lblElemento);
		
		ImageIcon img1 = new ImageIcon("imgReto2/ll.jpg");
		img1 = new ImageIcon(img1.getImage().getScaledInstance(743, 410, Image.SCALE_DEFAULT));

		contentPane.setLayout(null);

		JLabel lblIMG1 = new JLabel();
		lblIMG1.setBounds(0, 0, 743, 410);
		lblIMG1.setIcon(img1);
		contentPane.add(lblIMG1);
	}
}
