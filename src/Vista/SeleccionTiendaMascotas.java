package Vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosGenerales;
import Controlador.MetodosMascota;
import Modelo.Adopcion;
import Modelo.ObjetosComprables;
import ModeloAnimal.Mascota;
import ModeloPerfil.Cliente;
import javax.swing.JLabel;

public class SeleccionTiendaMascotas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JScrollPane scrollpanelSeleccionMascotas;
	
	private JButton btnAtras;
	private JButton btnAceptar;
	
	private JTable tableSeleccionMascotas;
	
	private ImageIcon img;
	
	private JLabel lblIMG;
	
	private JPanel panelFotos;
	
	TiendaCliente ventanaTiendaCliente;
	ventanaCliente ventanaCliente;
	
	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosMascota metodosMascota = new MetodosMascota();
	
	Mascota mascota = new Mascota();
	Adopcion adopcion = new Adopcion();
	
	boolean precio = true;
	boolean stock = true;
	
	/**
	 * Create the frame.
	 */
	public SeleccionTiendaMascotas(Cliente clienteLogIn, String valorUbicacion) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		scrollpanelSeleccionMascotas = new JScrollPane();
		scrollpanelSeleccionMascotas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpanelSeleccionMascotas.setBounds(31, 98, 472, 181);
		contentPane.add(scrollpanelSeleccionMascotas);
	
		tableSeleccionMascotas = new JTable();
		try {
			ArrayList<ObjetosComprables> listaMascota = metodosMascota.recogerMascotaTienda(valorUbicacion);						
			tableSeleccionMascotas = metodosGenerales.generarTablaSeleccionMascotas(listaMascota);
			scrollpanelSeleccionMascotas.setViewportView(tableSeleccionMascotas);
			tableSeleccionMascotas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					if (tableSeleccionMascotas.getSelectedRow() != -1) {
						DefaultTableModel modelo = (DefaultTableModel) tableSeleccionMascotas.getModel();
						String dato = (String) modelo.getValueAt(tableSeleccionMascotas.getSelectedRow(), 0);
						panelFotos = new JPanel();
						panelFotos.setBackground(new Color(192, 192, 192));
						panelFotos.setBounds(555, 126, 137, 129);
						contentPane.add(panelFotos);
						panelFotos.setLayout(null);
						if (dato.equals("Perros")) {
							img = new ImageIcon("imgReto2/animales/imgPerros/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("Gatos")) {
							img = new ImageIcon("imgReto2/animales/imgGatos/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("Loros")) {
							img = new ImageIcon("imgReto2/animales/imgLoros/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("Pez")) {
							img = new ImageIcon("imgReto2/animales/imgPez/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						}
					}
				}
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaTiendaCliente = new TiendaCliente(clienteLogIn);
				ventanaTiendaCliente.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(31, 324, 89, 23);
		contentPane.add(btnAtras);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableSeleccionMascotas.getSelectedRow() != -1) {
					DefaultTableModel tu = (DefaultTableModel) tableSeleccionMascotas.getModel();
					String valorMascota = String.valueOf(tu.getValueAt(tableSeleccionMascotas.getSelectedRow(), 0));
					String valorPrecio = String.valueOf(tu.getValueAt(tableSeleccionMascotas.getSelectedRow(), 1));
					String valorStock = String.valueOf(tu.getValueAt(tableSeleccionMascotas.getSelectedRow(), 2));
					int filaseleccionada = tableSeleccionMascotas.getSelectedRow();

					ArrayList<ObjetosComprables> listaMascota = null;
					
					try {
						listaMascota = metodosMascota.recogerMascotaTienda(valorUbicacion);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					mascota = (Mascota) listaMascota.get(filaseleccionada);
					int codMascota = mascota.getCodMascota();
					
					Instant instant = Instant.now();
					long millis = instant.toEpochMilli();
					Date fechaEnMilli = new Date(millis);
					java.sql.Date fechaActual = new java.sql.Date(fechaEnMilli.getTime());

					if (Integer.valueOf(valorStock) == 0) {
						JOptionPane.showMessageDialog(null, "Cantidad seleccionada superior al stock");
					} else {
						try {
							mascota.setStock(Integer.valueOf(valorStock));
							mascota.setEspecie(valorMascota);
							mascota.setCodMascota(Integer.valueOf(codMascota));

							adopcion.setPrecioTotal(Integer.valueOf(valorPrecio));
							adopcion.setFecha(fechaActual);

							metodosGenerales.EliminarStockAnimal(mascota, adopcion, clienteLogIn);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						ventanaCliente = new ventanaCliente(clienteLogIn);
						ventanaCliente.setVisible(true);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un articulo");
				}
			}
		});
		btnAceptar.setBounds(628, 324, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel lblFrase = new JLabel("Seleccione una mascota:");
		lblFrase.setBounds(31, 47, 267, 14);
		contentPane.add(lblFrase);
		
		JButton btnOrdenarPrecio = new JButton("Ordenar por precio");
		btnOrdenarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (precio == true) {
					precio = false;
				} else {
					precio = true;
				}
				 DefaultTableModel modelo = (DefaultTableModel) tableSeleccionMascotas.getModel();
			       modelo.setRowCount(0);
					try {
						ArrayList<ObjetosComprables> listaMascotaPrecio = metodosMascota.recogerMascotaTienda(valorUbicacion);						
						metodosGenerales.ordenarPorPrecioDesc(listaMascotaPrecio, precio);
						tableSeleccionMascotas = metodosGenerales.generarTablaSeleccionMascotas(listaMascotaPrecio);
						scrollpanelSeleccionMascotas.setViewportView(tableSeleccionMascotas);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();					
      			
              }
				
			}
			
		});
		btnOrdenarPrecio.setBounds(152, 324, 183, 23);
		contentPane.add(btnOrdenarPrecio);
		
		JButton btnOrdenarStock = new JButton("Ordenar por stock");
		btnOrdenarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stock == true) {
					stock = false;
				} else {
					stock = true;
				}
				 DefaultTableModel modelo = (DefaultTableModel) tableSeleccionMascotas.getModel();
			       modelo.setRowCount(0);
					try {
						ArrayList<ObjetosComprables> listaMascotaStock = metodosMascota.recogerMascotaTienda(valorUbicacion);						
						metodosGenerales.ordenarPorStockDesc(listaMascotaStock, stock);
						tableSeleccionMascotas = metodosGenerales.generarTablaSeleccionMascotas(listaMascotaStock);
						scrollpanelSeleccionMascotas.setViewportView(tableSeleccionMascotas);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();					
    			
            }
			}
		});
		btnOrdenarStock.setBounds(377, 324, 203, 23);
		contentPane.add(btnOrdenarStock);
		
		panelFotos = new JPanel();
		panelFotos.setLayout(null);
		panelFotos.setBackground(Color.LIGHT_GRAY);
		panelFotos.setBounds(555, 126, 137, 129);
		contentPane.add(panelFotos);
		
		lblIMG = new JLabel();
		lblIMG.setBounds(0, 0, 137, 129);
		panelFotos.add(lblIMG);
		
	}
}
