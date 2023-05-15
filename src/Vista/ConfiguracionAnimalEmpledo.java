package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosAnimal;
import Controlador.MetodosCliente;
import Controlador.MetodosGenerales;
import ModeloPerfil.Cliente;
import ModeloPerfil.Empleado;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConfiguracionAnimalEmpledo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnEliminar;
	MetodosCliente metodosCliente = new MetodosCliente();
	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosAnimal metodosAnimal = new MetodosAnimal();
	private int valor = -1;
	Cliente cliente;
	Cliente clienteSelect;
	ventanaEmpleado ventanaEmpleado;


	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ConfiguracionAnimalEmpledo(Empleado empleadoLogIn) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ---------------------RecogerDatosParaArrayList---------------------//

		ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();

		// ---------------------Botones---------------------//

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

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelEditar;
				if (table.getSelectedRow() != -1) {		
					modelEditar = (DefaultTableModel) table.getModel();
					int filaseleccionada = table.getSelectedRow();
					valor = clienteSelect.getAnimal().get(filaseleccionada).getIdAnimal();
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
		btnEliminar.setBounds(628, 337, 89, 23);
		contentPane.add(btnEliminar);

		JButton btnSeleccionarCliente = new JButton("Mirar animales de:");
		btnSeleccionarCliente.setEnabled(false);
		btnSeleccionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEliminar.setEnabled(true);
				try {

					// ---------------------Tabla---------------------//
					
					if (scrollPane != null && contentPane.getComponentCount() > 0) {
					    contentPane.remove(scrollPane);
					}
					
					scrollPane = new JScrollPane();
					scrollPane.setBounds(50, 100, 600, 200);
					contentPane.add(scrollPane);

					table = new JTable();
					table.setBounds(0, 0, 1, 1);
					table = metodosGenerales.generarTablaGestionarAnimalCliente(clienteSelect, listaCliente);
					scrollPane.setViewportView(table);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSeleccionarCliente.setBounds(10, 61, 156, 23);
		contentPane.add(btnSeleccionarCliente);

		// ---------------------ComboBox---------------------//

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(303, 61, 258, 23);
		contentPane.add(comboBox);
		
		JLabel lblMensaje = new JLabel("Selecciona un cliente para mostrar sus animales");
		lblMensaje.setBounds(10, 36, 707, 14);
		contentPane.add(lblMensaje);

		for (Cliente cliente : listaCliente) {
			if (cliente.getAnimal() != null) {
				comboBox.addItem(cliente.getNombre() + " " + cliente.getApellido() + " - " + cliente.getDni());
			}

		}

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String texto = (String) comboBox.getSelectedItem();
				String parteTexto = texto.substring(texto.indexOf(" - ") + 3);

				for (Cliente cliente : listaCliente) {
					if (parteTexto.equals(cliente.getDni())) {
						clienteSelect = cliente;
						btnSeleccionarCliente.setEnabled(true);

						if (clienteSelect != null) {
							table = null;
						}

					}
				}
			}
		});
	}
}
