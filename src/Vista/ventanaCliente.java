package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosCliente;
import ModeloPerfil.Cliente;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ventanaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFrase;
	private JButton btnTienda;
	private JButton btnPedirCita;
	private JLabel lblNombreApellido;
	private JButton btnCerrarSesion;
	private JButton btnConfigurarPerfil;
	PedirCita ventanaPedirCita;
	LogIn ventanaLogIn;
	ConfigurarPerfil ventanaConfigurarPerfil;
	TiendaCliente ventanaTiendaCliente;

	String nombreLogIn = "";
	String apellidoLogIn = "";
	String texto = "";

	/**
	 * Create the frame.
	 * 
	 * @param clienteLogIn
	 */

	public ventanaCliente(Cliente clienteLogIn) {

		nombreLogIn = clienteLogIn.getNombre();
		apellidoLogIn = clienteLogIn.getApellido();
		texto = nombreLogIn + " " + apellidoLogIn;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFrase = new JLabel("Elige una de estas opciones:");
		lblFrase.setBounds(10, 11, 191, 14);
		contentPane.add(lblFrase);

		btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaTiendaCliente = new TiendaCliente(clienteLogIn);
				ventanaTiendaCliente.setVisible(true);
				dispose();
			}
		});
		btnTienda.setBounds(10, 36, 191, 170);
		contentPane.add(btnTienda);

		btnPedirCita = new JButton("Pedir cita");
		btnPedirCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosCliente cl = new MetodosCliente();
				ArrayList<Cliente> cs;
				try {
					cs = cl.recogerClienteYSusAnimales();
					ventanaPedirCita = new PedirCita(clienteLogIn, cs);
					ventanaPedirCita.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnPedirCita.setBounds(223, 36, 201, 170);
		contentPane.add(btnPedirCita);

		lblNombreApellido = new JLabel(texto);
		lblNombreApellido.setBounds(221, 11, 203, 14);
		contentPane.add(lblNombreApellido);

		btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ventanaLogIn = new LogIn();
					ventanaLogIn.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCerrarSesion.setBounds(20, 227, 117, 23);
		contentPane.add(btnCerrarSesion);

		btnConfigurarPerfil = new JButton("Configurar perfil");
		btnConfigurarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ventanaConfigurarPerfil = new ConfigurarPerfil(clienteLogIn, null);
					ventanaConfigurarPerfil.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnConfigurarPerfil.setBounds(223, 227, 201, 23);
		contentPane.add(btnConfigurarPerfil);
	}
}
