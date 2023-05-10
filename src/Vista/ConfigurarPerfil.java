package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import Controlador.MetodosCliente;
import Controlador.MetodosEmpleado;
import ModeloPerfil.Cliente;
import ModeloPerfil.Empleado;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfigurarPerfil extends JFrame {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textDireccion;
	private JTextField textContrasenya;
	
	MetodosEmpleado metodosEmpleado = new MetodosEmpleado();
	MetodosCliente metodosCliente = new MetodosCliente();

	ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();
	ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();

	boolean empleadoBoolean = false;

	LogIn ventanaLogIn;
	ventanaCliente ventanaCliente;
	ventanaEmpleado ventanaEmpleado;

	String[] datosNuevos = new String[4];
	String[] column = { "Nombre", "Apellidos", "Contraseña", "Direccion" };

	/**
	 * Create the frame.
	 *
	 * @throws SQLException
	 */
	public ConfigurarPerfil(Cliente cliente, Empleado empleadoLogIn) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

// --------------------------LabelVentanaPerfil--------------------------//

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(63, 42, 78, 62);
		contentPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(63, 79, 78, 62);
		contentPane.add(lblApellidos);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(63, 115, 78, 62);
		contentPane.add(lblDireccion);

		JLabel lblContrasenya = new JLabel("Contraseña:");
		lblContrasenya.setBounds(63, 152, 78, 62);
		contentPane.add(lblContrasenya);

		JLabel lblNombreYApellidoPerfil = new JLabel("Datos de la persona:");
		lblNombreYApellidoPerfil.setBounds(252, 20, 182, 14);
		contentPane.add(lblNombreYApellidoPerfil);

		JLabel lblTextoPerfil = new JLabel("Configuración Perfil:");
		lblTextoPerfil.setBounds(87, 11, 163, 32);
		contentPane.add(lblTextoPerfil);

// --------------------------TextVentanaPerfil--------------------------//

		textNombre = new JTextField();
		textNombre.setBounds(146, 63, 228, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		textApellidos = new JTextField();
		textApellidos.setBounds(146, 100, 228, 20);
		contentPane.add(textApellidos);
		textApellidos.setColumns(10);

		textDireccion = new JTextField();
		textDireccion.setBounds(146, 136, 228, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);

		textContrasenya = new JTextField();
		textContrasenya.setBounds(146, 173, 228, 20);
		contentPane.add(textContrasenya);
		textContrasenya.setColumns(10);

// --------------------------ButtonVentanaPerfil--------------------------//

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (empleadoBoolean == true) {
					ventanaEmpleado = new ventanaEmpleado(empleadoLogIn);
					ventanaEmpleado.setVisible(true);
					dispose();
				} else {
					ventanaCliente = new ventanaCliente(cliente);
					ventanaCliente.setVisible(true);
					dispose();
				}

			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		contentPane.add(btnVolver);

		JButton btnDarseBaja = new JButton("Darme de baja");
		btnDarseBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int option = JOptionPane.showConfirmDialog(null, "¿Desea darse de baja?", "Confirmación",
						JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {

					try {

						if (empleadoBoolean == true) {
							metodosEmpleado.eliminarEmpleado(empleadoLogIn.getDni());
						} else {
							metodosCliente.eliminarCliente(cliente.getDni());
						}

						ventanaLogIn = new LogIn();
						ventanaLogIn.setVisible(true);
						dispose();

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}
			}
		});
		btnDarseBaja.setBounds(138, 227, 122, 23);
		contentPane.add(btnDarseBaja);

		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int option = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?", "Confirmación",
						JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {

					try {

						if (empleadoBoolean == true) {

							if (!(textNombre.getText().equals(empleadoLogIn.getNombre())
									&& textApellidos.getText().equals(empleadoLogIn.getApellido())
									&& textContrasenya.getText().equals(empleadoLogIn.getContrasenya())
									&& textDireccion.getText().equals(empleadoLogIn.getDireccion()))) {

								datosNuevos[0] = textNombre.getText();
								datosNuevos[1] = textApellidos.getText();
								datosNuevos[2] = textContrasenya.getText();
								datosNuevos[3] = textDireccion.getText();

								metodosEmpleado.updateEmpleado(column, datosNuevos, empleadoLogIn);
							}

							ventanaLogIn = new LogIn();
							ventanaLogIn.setVisible(true);
							dispose();

						} else {

							if (!(textNombre.getText().equals(cliente.getNombre())
									&& textApellidos.getText().equals(cliente.getApellido())
									&& textContrasenya.getText().equals(cliente.getContrasenya())
									&& textDireccion.getText().equals(cliente.getDireccion()))) {

								datosNuevos[0] = textNombre.getText();
								datosNuevos[1] = textApellidos.getText();
								datosNuevos[2] = textContrasenya.getText();
								datosNuevos[3] = textDireccion.getText();

								metodosCliente.updateCliente(column, datosNuevos, cliente);
							}

							ventanaLogIn = new LogIn();
							ventanaLogIn.setVisible(true);
							dispose();

						}

					} catch (SQLException e1) {
// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnGuardarCambios.setBounds(284, 227, 140, 23);
		contentPane.add(btnGuardarCambios);

// --------------------------SeteoTextVentanaPerfil--------------------------//

		if (empleadoLogIn != null) {

			lblNombreYApellidoPerfil.setText(empleadoLogIn.getNombre() + " " + empleadoLogIn.getApellido());
			textNombre.setText(empleadoLogIn.getNombre());
			textApellidos.setText(empleadoLogIn.getApellido());
			textDireccion.setText(empleadoLogIn.getDireccion());
			textContrasenya.setText(empleadoLogIn.getContrasenya());
			empleadoBoolean = true;

		} else {
			lblNombreYApellidoPerfil.setText(cliente.getNombre() + " " + cliente.getApellido());
			textNombre.setText(cliente.getNombre());
			textApellidos.setText(cliente.getApellido());
			textDireccion.setText(cliente.getDireccion());
			textContrasenya.setText(cliente.getContrasenya());
		}

	}
}