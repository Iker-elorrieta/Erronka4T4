package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		setBounds(100, 100, 450, 300);
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
		btnAtras.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtras);
		
		scrollpanelUbicacion = new JScrollPane();
		scrollpanelUbicacion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpanelUbicacion.setBounds(25, 55, 150, 73);
		contentPane.add(scrollpanelUbicacion);
		
		tableUbicacion = new JTable();
		try {
			tableUbicacion = metodosGenerales.generarTablaUbicaciones();
			scrollpanelUbicacion.setViewportView(tableUbicacion);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		scrollpanelTienda = new JScrollPane();
		scrollpanelTienda.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpanelTienda.setBounds(249, 55, 150, 55);
		contentPane.add(scrollpanelTienda);
		
		tableTienda = new JTable();
		try {
			tableTienda = metodosGenerales.generarTablaTiendas();
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
		btnAceptar.setBounds(335, 227, 89, 23);
		contentPane.add(btnAceptar);
		
		lblUbicacion = new JLabel("Selecciona una ubicacion:");
		lblUbicacion.setBounds(25, 30, 150, 14);
		contentPane.add(lblUbicacion);
		
		lblElemento = new JLabel("Selecciona una elemento:");
		lblElemento.setBounds(249, 30, 150, 14);
		contentPane.add(lblElemento);
	}
}
