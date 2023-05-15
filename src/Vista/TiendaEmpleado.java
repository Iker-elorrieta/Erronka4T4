package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosGenerales;
import Controlador.MetodosMascota;
import Controlador.MetodosProducto;
import Modelo.Gestion;
import Modelo.GestionAnimal;
import Modelo.Producto;
import ModeloAnimal.Mascota;
import ModeloPerfil.Empleado;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class TiendaEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPaneTabla;
	
	private JButton btnAnyadir;
	private JButton btnEliminar;

	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosProducto metodosProductos = new MetodosProducto();
	MetodosMascota metodosMascota = new MetodosMascota();

	Producto producto = new Producto();
	Gestion gestion = new Gestion();
	Mascota mascota = new Mascota();
	GestionAnimal gestionAnimal = new GestionAnimal();

	ArrayList<Producto> listaProducto = new ArrayList<Producto>();
	ArrayList<Mascota> listaMascota = new ArrayList<Mascota>();

	boolean isProducto = true;
	
	ventanaEmpleado ventanaEmpleado;
	AnyadirProducto ventanaAniadirProducto;
	AnyadirMascota ventanaAnyadirMascota;
	
	private final String precio = "Precio";
    private final String stock = "Stock";
    private final String nombreProducto = "Producto";
    private final String especie = "Especie";

	/**
	 * Create the frame.
	 */
	public TiendaEmpleado(Empleado empleadoLogIn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// -----------------------------Botones-----------------------------//

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaEmpleado = new ventanaEmpleado(empleadoLogIn);
				ventanaEmpleado.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(10, 337, 89, 23);
		contentPane.add(btnAtras);

		JButton btnConfigurar = new JButton("Configura Stock");
		btnConfigurar.setEnabled(false);
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// -------CompruebaSiNoSeASeleccionadoNinguna-------//

				if (table.getSelectedRow() != -1) {
					DefaultTableModel tu = (DefaultTableModel) table.getModel();
					String valorStock = String.valueOf(tu.getValueAt(table.getSelectedRow(), 1));
					int filaseleccionada = table.getSelectedRow();
					
					// -------CogeLaFechaActual-------//

					Instant instant = Instant.now();
					long millis = instant.toEpochMilli();
					Date fechaEnMilli = new Date(millis);
					java.sql.Date fechaActual = new java.sql.Date(fechaEnMilli.getTime());

					// -------ConfiguracionJOptionPane-------//

					int numero = 0;
					String numeroStr;
					boolean valorValido = false;
					do {
						numeroStr = JOptionPane.showInputDialog(null, "Introduce cuanta cantidad desea añadir:");
						if (numeroStr == null) {
							valorValido = false;
						} else {
							try {
								numero = Integer.parseInt(numeroStr);
								valorValido = true;
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null, "Debe introducir un valor entero.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} while (numero < 1 && valorValido);
					if (valorValido) {
						JOptionPane.showMessageDialog(null, "Se ha añadido: " + numero);
					}

					// -------EjecuciónMetodos-------//

					try {
						if (isProducto == true) {
							
							ArrayList<Producto> listaProducto = new ArrayList<Producto>();
							try {
								listaProducto = metodosProductos.recogerProducto();
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							producto = (Producto) listaProducto.get(filaseleccionada);
							int valorCodigoProducto = producto.getCodProducto();
							
							producto.setStock(Integer.valueOf(valorStock));
							producto.setCodProducto(Integer.valueOf(valorCodigoProducto));
							gestion.setCantidad(numero);
							gestion.setFecha(fechaActual);

							metodosGenerales.AnadirStockProducto(producto, gestion, empleadoLogIn);

						} else {
							
							ArrayList<Mascota> listaMascota = new ArrayList<Mascota>();
							try {
								listaMascota = metodosMascota.recogerMascota();
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							mascota = (Mascota) listaMascota.get(filaseleccionada);
							int valorCodigoMascota = mascota.getCodMascota();
							
							mascota.setStock(Integer.valueOf(valorStock));
							mascota.setCodMascota(Integer.valueOf(valorCodigoMascota));
							gestionAnimal.setCantidad(numero);
							gestionAnimal.setFecha(fechaActual);

							metodosGenerales.AnadirStockAnimal(mascota, gestionAnimal, empleadoLogIn);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Primero debe de seleccionar un articulo para cambiar su Stock");
				}

			}
		});
		btnConfigurar.setBounds(531, 337, 186, 23);
		contentPane.add(btnConfigurar);

		JButton btnProductos = new JButton("Mostrar Productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
                    listaProducto = metodosProductos.recogerProducto();
                    Object[][] Productos = metodosGenerales.generarTablaSeleccionProductosTiendaEmpleado(listaProducto);
                    String[] titulos = new String[] { nombreProducto, stock, precio };
                    table.setModel(new DefaultTableModel(Productos, titulos));
                    table.setCellSelectionEnabled(false);
                    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    table.setDefaultEditor(Object.class, null);
                    scrollPaneTabla.setViewportView(table);

                    btnConfigurar.setEnabled(true);
                    btnAnyadir.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    
                    isProducto = true;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnProductos.setBounds(317, 45, 144, 23);
		contentPane.add(btnProductos);

		JButton btnAnimales = new JButton("Mostrar Animales");
		btnAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

                    listaMascota = metodosMascota.recogerMascota();
                    Object[][] empleado = metodosGenerales.generarTablaSeleccionMascotasTiendaEmpleado(listaMascota);
                    String[] titulos = new String[] { especie, stock, precio };
                    table.setModel(new DefaultTableModel(empleado, titulos));
                    table.setCellSelectionEnabled(false);
                    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    table.setDefaultEditor(Object.class, null);
                    scrollPaneTabla.setViewportView(table);

                    btnConfigurar.setEnabled(true);
                    btnAnyadir.setEnabled(true);
                    btnEliminar.setEnabled(true);


                    isProducto = false;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAnimales.setBounds(508, 45, 144, 23);
		contentPane.add(btnAnimales);

		// -----------------------------Labels-----------------------------//

		JLabel lblTitulo = new JLabel("Seleccione que stock configurar:");
		lblTitulo.setBounds(85, 49, 210, 14);
		contentPane.add(lblTitulo);

		// -----------------------------CreaciónTabla-----------------------------//

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(85, 97, 569, 212);
		contentPane.add(scrollPaneTabla);

		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		scrollPaneTabla.add(table);
		
		btnAnyadir = new JButton("Añadir");
		btnAnyadir.setEnabled(false);
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isProducto == true) {
					ventanaAniadirProducto = new AnyadirProducto(empleadoLogIn);
					ventanaAniadirProducto.setVisible(true);
					dispose();
				} else {
					ventanaAnyadirMascota = new AnyadirMascota(empleadoLogIn);
					ventanaAnyadirMascota.setVisible(true);
					dispose();
				}

			}

		});
		btnAnyadir.setBounds(317, 338, 186, 23);
		contentPane.add(btnAnyadir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isProducto == true) {
					DefaultTableModel modelEditar;
					if (table.getSelectedRow() != -1) {
						modelEditar = (DefaultTableModel) table.getModel();
						int filaseleccionada = table.getSelectedRow();
						int id = listaProducto.get(filaseleccionada).getCodProducto();
						try {
							metodosProductos.eliminarProducto(id);
							modelEditar.removeRow(table.getSelectedRow());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un producto");
					}
				} else {
					DefaultTableModel modelEditar;
					if (table.getSelectedRow() != -1) {
						modelEditar = (DefaultTableModel) table.getModel();
						int filaseleccionada = table.getSelectedRow();
						int id = listaMascota.get(filaseleccionada).getCodMascota();
						try {
							metodosMascota.eliminarMascota(id);
							modelEditar.removeRow(table.getSelectedRow());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona una mascota");
					}
				}

			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(122, 338, 186, 23);
		contentPane.add(btnEliminar);

		ImageIcon img1 = new ImageIcon("imgReto2/ll.jpg");
		img1 = new ImageIcon(img1.getImage().getScaledInstance(743, 410, Image.SCALE_DEFAULT));

		contentPane.setLayout(null);

		JLabel lblIMG1 = new JLabel();
		lblIMG1.setBounds(0, 0, 743, 410);
		lblIMG1.setIcon(img1);
		contentPane.add(lblIMG1);
	}
}
