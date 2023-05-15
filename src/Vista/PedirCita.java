package Vista;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosAnimal;
import Controlador.MetodosGenerales;
import ModeloAnimal.Gato;
import ModeloAnimal.Loro;
import ModeloAnimal.Perro;
import ModeloAnimal.Pez;
import ModeloPerfil.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PedirCita extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	private JButton btnAnyadirAnimal;
	ventanaCliente ventanaCliente;
	private JTable table;
	private int valor = -1;
	Cliente cli = null;
	
	Gato gato = new Gato();
	Perro perro = new Perro();
	Loro loro = new Loro();
	Pez pez = new Pez();

	MetodosGenerales metodosGenerales = new MetodosGenerales();

	/**
	 * Create the frame.
	 * 
	 * @param listaCliente
	 */
	public PedirCita(Cliente cliente, ArrayList<Cliente> listaCliente, boolean atras) {
		cli = cliente;
		MetodosAnimal metodosAnimal = new MetodosAnimal();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCliente = new ventanaCliente(cliente);
				ventanaCliente.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(25, 337, 107, 23);
		contentPane.add(btnAtras);

		btnAnyadirAnimal = new JButton("Añadir animal");
		btnAnyadirAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaydirAnimal aa = new AnaydirAnimal(cliente, atras);
				aa.setVisible(true);
				dispose();

			}
		});
		btnAnyadirAnimal.setBounds(372, 337, 134, 23);
		contentPane.add(btnAnyadirAnimal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 69, 680, 204);
		contentPane.add(scrollPane);

		table = new JTable();
		try {
			Object[][] clienteAnimal =  metodosGenerales.generarTablaClienteAnimal(cliente, listaCliente);
			String[] titulos = new String[] { "Nombre", "Edad", "Especie", "Sexo" };
			table.setModel(new DefaultTableModel(clienteAnimal, titulos));
			table.setCellSelectionEnabled(false);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setDefaultEditor(Object.class, null);
			scrollPane.setViewportView(table);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton btnEliminarAnimal = new JButton("Eliminar animal");
		btnEliminarAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelEditar;
				if (table.getSelectedRow() != -1) {
					modelEditar = (DefaultTableModel) table.getModel();
					int filaseleccionada = table.getSelectedRow();
					valor = cli.getAnimal().get(filaseleccionada).getIdAnimal();
					try {
						metodosAnimal.eliminarAnimal(Integer.valueOf(valor));
						modelEditar.removeRow(table.getSelectedRow());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un animal");
				}
			}
		});
		btnEliminarAnimal.setBounds(183, 337, 144, 23);
		contentPane.add(btnEliminarAnimal);

		JButton btnPedirCita = new JButton("Pedir Cita");
		btnPedirCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int filaseleccionada = table.getSelectedRow();
					valor = cli.getAnimal().get(filaseleccionada).getIdAnimal();
					PedirCitaAnimalSeleccionado pedirCitaAnimalSeleccionado = new PedirCitaAnimalSeleccionado(cliente,
							valor, atras);
					pedirCitaAnimalSeleccionado.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un animal");
				}
			}
		});
		btnPedirCita.setBounds(571, 337, 134, 23);
		contentPane.add(btnPedirCita);

		JLabel lblNewLabel = new JLabel("En esta tabla se muestran todos tus animales registrados.");
		lblNewLabel.setBounds(25, 44, 680, 14);
		contentPane.add(lblNewLabel);

		JButton btnActualizarEdadAnimal = new JButton("Actualizar edad animal");
		btnActualizarEdadAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int filaseleccionada = table.getSelectedRow();
					String especieAnimal = cli.getAnimal().get(filaseleccionada).getEspecie();
					int idAnimal = cli.getAnimal().get(filaseleccionada).getIdAnimal();

					int numero = 0;
					String numeroStr;
					boolean valorValido = false;
					do {
						numeroStr = JOptionPane.showInputDialog(null, "Introduce la ead actual:");
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
					
					if (especieAnimal == "Gatos") {
							gato.setIdAnimal(idAnimal);
							try {
								metodosGenerales.AnadirEdadAnimal(gato, numero);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					} else if  (especieAnimal == "Perros"){
						perro.setIdAnimal(idAnimal);
						try {
							metodosGenerales.AnadirEdadAnimal(perro, numero);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if  (especieAnimal == "Loros"){
						loro.setIdAnimal(idAnimal);
						try {
							metodosGenerales.AnadirEdadAnimal(loro, numero);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						pez.setIdAnimal(idAnimal);
						try {
							metodosGenerales.AnadirEdadAnimal(pez, numero);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un animal");
				}
			}
		});
		btnActualizarEdadAnimal.setBounds(305, 293, 237, 21);
		contentPane.add(btnActualizarEdadAnimal);
	}
}
