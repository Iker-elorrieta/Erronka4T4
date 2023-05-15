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
	private JLabel lblTexto;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public LogIn() throws SQLException {

		ArrayList<Cliente> listaClientes = metodosCliente.recogerClienteYSusAnimales();
		ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(141, 106, 46, 14);
		contentPane.add(lblDNI);

		lblContra = new JLabel("Contraseña");
		lblContra.setBounds(141, 168, 115, 14);
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
		btnLogIn.setBounds(117, 300, 163, 23);
		contentPane.add(btnLogIn);

		btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaRegistroCliente = new RegistroCliente();
				ventanaRegistroCliente.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setBounds(375, 300, 177, 23);
		contentPane.add(btnRegistro);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(313, 103, 239, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		passContra = new JPasswordField();
		passContra.setBounds(313, 165, 239, 20);
		contentPane.add(passContra);

		lblMensaje = new JLabel("El DNI o la contraseña son erroneos");
		lblMensaje.setForeground(new Color(255, 0, 0));
		lblMensaje.setBounds(239, 239, 239, 14);
		lblMensaje.setVisible(false);
		contentPane.add(lblMensaje);
		
		lblTexto = new JLabel("Introduce el DNI y la contraseña para iniciar sesion:");
		lblTexto.setBounds(51, 38, 408, 14);
		contentPane.add(lblTexto);
	}
}
