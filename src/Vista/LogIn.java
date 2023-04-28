package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	RegistroCliente ventanaRegistroCliente;
	Cliente ventanaCliente;
	//Empleado ventanaEmpleado;
	
	/**
	 * Create the frame.
	 */
	public LogIn() {
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
				ventanaCliente = new Cliente();
				ventanaCliente.setVisible(true);
				dispose();
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
	}
}
