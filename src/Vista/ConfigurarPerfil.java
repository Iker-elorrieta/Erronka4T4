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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Image;

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
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

// --------------------------LabelVentanaPerfil--------------------------//

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(63, 63, 78, 62);
		contentPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(63, 115, 78, 62);
		contentPane.add(lblApellidos);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(63, 169, 78, 62);
		contentPane.add(lblDireccion);

		JLabel lblContrasenya = new JLabel("Contraseña:");
		lblContrasenya.setBounds(63, 229, 78, 62);
		contentPane.add(lblContrasenya);

		JLabel lblTextoPerfil = new JLabel("Los datos mostrados son los actuales, cambialos para actualizar tu perfil");
		lblTextoPerfil.setBounds(52, 41, 437, 32);
		contentPane.add(lblTextoPerfil);

// --------------------------TextVentanaPerfil--------------------------//

		textNombre = new JTextField();
		textNombre.setBounds(166, 84, 352, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		textApellidos = new JTextField();
		textApellidos.setBounds(166, 136, 352, 20);
		contentPane.add(textApellidos);
		textApellidos.setColumns(10);

		textDireccion = new JTextField();
		textDireccion.setBounds(166, 190, 352, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);

		textContrasenya = new JTextField();
		textContrasenya.setBounds(166, 250, 352, 20);
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
		btnVolver.setBounds(52, 337, 89, 23);
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
		btnDarseBaja.setBounds(286, 337, 122, 23);
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
						e1.printStackTrace();
					}

				}

			}
		});
		btnGuardarCambios.setBounds(577, 337, 140, 23);
		contentPane.add(btnGuardarCambios);
		
		JLabel lblNombreYApellidoPerfil = new JLabel("");
		lblNombreYApellidoPerfil.setBounds(535, 24, 182, 14);
		contentPane.add(lblNombreYApellidoPerfil);
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
				
				ImageIcon img1 = new ImageIcon("imgReto2/ll.jpg");
				img1 = new ImageIcon(img1.getImage().getScaledInstance(743, 410, Image.SCALE_DEFAULT));

				contentPane.setLayout(null);

				JLabel lblIMG1 = new JLabel();
				lblIMG1.setBounds(0, 0, 743, 410);
				lblIMG1.setIcon(img1);
				contentPane.add(lblIMG1);
	}
}