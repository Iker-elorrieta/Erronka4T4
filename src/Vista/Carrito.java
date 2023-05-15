package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosAdopcion;
import Controlador.MetodosGenerales;
import Controlador.MetodosPedido;
import Modelo.Pedido;
import Modelo.Adopcion;
import ModeloPerfil.Cliente;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class Carrito extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPaneTabla;

	private JButton btnEliminar;

	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosAdopcion metodosAdopcion = new MetodosAdopcion();
	MetodosPedido metodosPedido = new MetodosPedido();

	Pedido pedido = new Pedido();
	Adopcion adopcion = new Adopcion();

	ArrayList<Pedido> listaPedido = new ArrayList<Pedido>();
	ArrayList<Adopcion> listaAdopcion = new ArrayList<Adopcion>();

	boolean isPedido = true;

	ventanaCliente ventanaCliente;

	private final String precio = "Precio";
	private final String nombreProducto = "Producto";
	private final String fecha = "Fecha";
	private final String cantidad = "Cantidad";
	private final String hora = "Hora";
	private final String especie = "Especie";

	/**
	 * Create the frame.
	 */
	public Carrito(Cliente clienteLogIn) {
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

				ventanaCliente = new ventanaCliente(clienteLogIn);
				ventanaCliente.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(10, 337, 89, 23);
		contentPane.add(btnAtras);

		JButton btnConfigurar = new JButton("Configura Stock");
		btnConfigurar.setEnabled(false);
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int filaseleccionada = table.getSelectedRow();

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
						if (isPedido == true) {
							pedido = (Pedido) listaPedido.get(filaseleccionada);
							int codPedido = pedido.getCodPedido();
							pedido.setCodPedido(codPedido);

							metodosGenerales.EditarCantidadPedido(pedido, numero);

						} else {
							adopcion = (Adopcion) listaAdopcion.get(filaseleccionada);
							int codGestionAdopcion = adopcion.getCodAdopcion();
							adopcion.setCodAdopcion(codGestionAdopcion);

							metodosGenerales.EditarCantidadAdopcion(adopcion, numero);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Primero debe de seleccionar un articulo para cambiar su cantidad");
				}

			}
		});
		btnConfigurar.setBounds(466, 337, 186, 23);
		contentPane.add(btnConfigurar);

		JButton btnProductos = new JButton("Mostrar tus pedidos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					listaPedido = metodosPedido.recogerPedidoCliente(clienteLogIn.getDni());
					Object[][] carrito = metodosGenerales.generarTablaSeleccionPedidoCliente(listaPedido);
					String[] titulos = new String[] { nombreProducto, cantidad, fecha, hora, precio };
					table.setModel(new DefaultTableModel(carrito, titulos));
					table.setCellSelectionEnabled(false);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setDefaultEditor(Object.class, null);
					scrollPaneTabla.setViewportView(table);

					btnConfigurar.setEnabled(true);
					btnEliminar.setEnabled(true);

					isPedido = true;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnProductos.setBounds(130, 74, 197, 23);
		contentPane.add(btnProductos);

		JButton btnAnimales = new JButton("Mostrar tus mascotas");
		btnAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					listaAdopcion = metodosAdopcion.recogerAnimalAdoptados();
					Object[][] animalAdopcion = metodosGenerales.generarTablaSeleccionAdopcionCliente(listaAdopcion);
					String[] titulos = new String[] { especie, fecha, precio };
					table.setModel(new DefaultTableModel(animalAdopcion, titulos));
					table.setCellSelectionEnabled(false);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setDefaultEditor(Object.class, null);
					scrollPaneTabla.setViewportView(table);

					btnConfigurar.setEnabled(true);
					btnEliminar.setEnabled(true);

					isPedido = false;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAnimales.setBounds(399, 74, 173, 23);
		contentPane.add(btnAnimales);

		// -----------------------------Labels-----------------------------//

		JLabel lblTitulo = new JLabel("Seleccione que stock configurar:");
		lblTitulo.setBounds(85, 49, 210, 14);
		contentPane.add(lblTitulo);

		// -----------------------------CreaciónTabla-----------------------------//

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(85, 103, 569, 206);
		contentPane.add(scrollPaneTabla);

		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		scrollPaneTabla.add(table);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPedido == true) {
					DefaultTableModel modelEditar;
					if (table.getSelectedRow() != -1) {
						modelEditar = (DefaultTableModel) table.getModel();
						int filaseleccionada = table.getSelectedRow();
						int id = listaPedido.get(filaseleccionada).getCodPedido();
						try {
							metodosPedido.eliminarPedido(id);
							modelEditar.removeRow(table.getSelectedRow());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un pedido");
					}
				} else {
					DefaultTableModel modelEditar;
					if (table.getSelectedRow() != -1) {
						modelEditar = (DefaultTableModel) table.getModel();
						int filaseleccionada = table.getSelectedRow();
						int id = listaAdopcion.get(filaseleccionada).getCodAdopcion();
						try {
							metodosAdopcion.eliminarAnimalAdoptado(id);
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
		btnEliminar.setBounds(197, 337, 186, 23);
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
