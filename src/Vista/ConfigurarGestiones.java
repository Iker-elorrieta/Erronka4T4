package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosGenerales;
import Controlador.MetodosGestion;
import Controlador.MetodosGestionAnimal;
import Modelo.Gestion;
import Modelo.GestionAnimal;

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
import java.util.ArrayList;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class ConfigurarGestiones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPaneTabla;
	private JButton btnEliminar;

	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosGestion metodosGestion = new MetodosGestion();
	MetodosGestionAnimal metodosGestionAnimal = new MetodosGestionAnimal();

	Gestion gestion = new Gestion();
	GestionAnimal gestionAnimal = new GestionAnimal();

	ArrayList<Gestion> listaGestion = new ArrayList<Gestion>();
	ArrayList<GestionAnimal> listaGestionAnimal = new ArrayList<GestionAnimal>();

	boolean isGestion = true;

	ventanaEmpleado ventanaEmpleado;

	private final String precio = "Precio";
	private final String nombreProducto = "Producto";
	private final String fecha = "Fecha";
	private final String cantidad = "Cantidad";
	private final String especie = "Especie";


	/**
	 * Create the frame.
	 */
	public ConfigurarGestiones(Empleado empleadoLogIn) {
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
						if (isGestion == true) {
							gestion = (Gestion) listaGestion.get(filaseleccionada);
							int codGestion = gestion.getCodGestion();
							gestion.setCodGestion(codGestion);

							metodosGenerales.EditarCantidadGestion(gestion, numero);

						} else {
							gestionAnimal = (GestionAnimal) listaGestionAnimal.get(filaseleccionada);
							int codGestionAnimal = gestion.getCodGestion();
							gestion.setCodGestion(codGestionAnimal);

							metodosGenerales.EditarCantidadGestionAnimal(gestionAnimal, numero);
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
		btnConfigurar.setBounds(531, 337, 186, 23);
		contentPane.add(btnConfigurar);

		JButton btnProductos = new JButton("Mostrar gestiones productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					listaGestion = metodosGestion.recogerGestion();
					Object[][] gestionEmpleado = metodosGenerales.generarTablaSeleccionGestionTiendaEmpleado(listaGestion);
					String[] titulos = new String[] { nombreProducto, cantidad, fecha };
					table.setModel(new DefaultTableModel(gestionEmpleado, titulos));
					table.setCellSelectionEnabled(false);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setDefaultEditor(Object.class, null);
					scrollPaneTabla.setViewportView(table);

					btnConfigurar.setEnabled(true);
					btnEliminar.setEnabled(true);

					isGestion = true;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnProductos.setBounds(85, 74, 233, 23);
		contentPane.add(btnProductos);

		JButton btnAnimales = new JButton("Mostrar gestiones animales");
		btnAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					listaGestionAnimal = metodosGestionAnimal.recogerGestionAnimal();
					Object[][] gestionAnimalesEmpleado = metodosGenerales
							.generarTablaSeleccionGestionTAnimaliendaEmpleado(listaGestionAnimal);
					String[] titulos = new String[] { especie, fecha, precio };
					table.setModel(new DefaultTableModel(gestionAnimalesEmpleado, titulos));
					table.setCellSelectionEnabled(false);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setDefaultEditor(Object.class, null);
					scrollPaneTabla.setViewportView(table);

					btnConfigurar.setEnabled(true);
					btnEliminar.setEnabled(true);

					isGestion = false;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAnimales.setBounds(412, 74, 242, 23);
		contentPane.add(btnAnimales);

		// -----------------------------Labels-----------------------------//

		JLabel lblTitulo = new JLabel("Seleccione que stock configurar:");
		lblTitulo.setBounds(85, 49, 210, 14);
		contentPane.add(lblTitulo);

		// -----------------------------CreaciónTabla-----------------------------//

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(85, 117, 569, 192);
		contentPane.add(scrollPaneTabla);

		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		scrollPaneTabla.add(table);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isGestion == true) {
					DefaultTableModel modelEditar;
					if (table.getSelectedRow() != -1) {
						modelEditar = (DefaultTableModel) table.getModel();
						int filaseleccionada = table.getSelectedRow();
						int id = listaGestion.get(filaseleccionada).getCodGestion();
						try {
							metodosGestion.eliminarGestion(id);
							modelEditar.removeRow(table.getSelectedRow());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona una gestion de producto");
					}
				} else {
					DefaultTableModel modelEditar;
					if (table.getSelectedRow() != -1) {
						modelEditar = (DefaultTableModel) table.getModel();
						int filaseleccionada = table.getSelectedRow();
						int id = listaGestionAnimal.get(filaseleccionada).getCodGestionAnimal();
						try {
							metodosGestionAnimal.eliminarGestionAnimal(id);
							modelEditar.removeRow(table.getSelectedRow());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona una gestion de mascota");
					}
				}

			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(215, 337, 186, 23);
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
