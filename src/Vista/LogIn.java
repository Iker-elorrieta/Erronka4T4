package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosCliente;
import Controlador.MetodosEmpleado;

import ModeloPerfil.Cliente;
import ModeloPerfil.Empleado;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LogIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passContra;
	private JLabel lblDNI;
	private JLabel lblContra;
	private JButton btnLogIn;
	private JButton btnRegistro;
	private JLabel lblMensaje;
	RegistroCliente ventanaRegistroCliente;
	ventanaCliente ventanaCliente;
	ventanaEmpleado ventanaEmpleado;

	MetodosCliente metodosCliente = new MetodosCliente();
	MetodosEmpleado metodosEmpleado = new MetodosEmpleado();

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public LogIn() throws SQLException {

		ArrayList<Cliente> listaClientes = metodosCliente.recogerClienteYSusAnimales();
		ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(69, 69, 46, 14);
		contentPane.add(lblDNI);

		lblContra = new JLabel("Contraseña");
		lblContra.setBounds(69, 133, 115, 14);
		contentPane.add(lblContra);

		btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = txtUsuario.getText();
				char[] contra = passContra.getPassword();
				String contraString = String.valueOf(contra);
				boolean encontrado = false;
				boolean acceso = false;
				int cont = 0;
				int rcCont = 0;
				Cliente clienteLogIn = new Cliente();

				do {
					if (listaClientes.get(cont).getDni().equalsIgnoreCase(dni) && listaClientes.get(cont).getContrasenya().equalsIgnoreCase(contraString)) {
						encontrado = true;
						acceso = true;
						rcCont = cont;
					} else if (listaClientes.size() - 1 == cont) {
						encontrado = true;
					}
					cont++;
				} while (encontrado == false);

				if (acceso == true) {
					clienteLogIn = listaClientes.get(rcCont);
					ventanaCliente = new ventanaCliente(clienteLogIn);
					ventanaCliente.setVisible(true);
					dispose();
				}
				encontrado = false;
				acceso = false;
				cont = 0;
				rcCont = 0;
				Empleado empleadoLogIn = new Empleado();
				
				do {
					if (listaEmpleado.get(cont).getDni().equalsIgnoreCase(dni) && listaEmpleado.get(cont).getContrasenya().equals(contraString)) {
						encontrado = true;
						acceso = true;
						rcCont = cont;
					} else if (listaEmpleado.size() - 1 == cont) {
						encontrado = true;
					}
					cont++;
				} while (encontrado == false);

				if (acceso == true) {
					empleadoLogIn = listaEmpleado.get(rcCont);
					ventanaEmpleado = new ventanaEmpleado(empleadoLogIn);
					ventanaEmpleado.setVisible(true);
					dispose();
				} else {
					lblMensaje.setVisible(true);
				}

			}
		});
		btnLogIn.setBounds(95, 215, 89, 23);
		contentPane.add(btnLogIn);

		btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaRegistroCliente = new RegistroCliente();
				ventanaRegistroCliente.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setBounds(225, 215, 121, 23);
		contentPane.add(btnRegistro);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(193, 66, 163, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		passContra = new JPasswordField();
		passContra.setBounds(193, 130, 163, 20);
		contentPane.add(passContra);

		lblMensaje = new JLabel("El DNI o la contraseña son erroneos");
		lblMensaje.setForeground(new Color(255, 0, 0));
		lblMensaje.setBounds(117, 177, 239, 14);
		lblMensaje.setVisible(false);
		contentPane.add(lblMensaje);
	}
}
