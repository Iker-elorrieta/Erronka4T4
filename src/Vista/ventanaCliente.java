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
	AnaydirAnimal ventanaAnaydirAnimal;
	Carrito ventanaCarrito;

	String nombreLogIn = "";
	String apellidoLogIn = "";
	String texto = "";
	
	ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
	private JButton btnNewButton;

	boolean atras = true;

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
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFrase = new JLabel("Elige una de estas opciones:");
		lblFrase.setBounds(20, 34, 191, 14);
		contentPane.add(lblFrase);

		btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaTiendaCliente = new TiendaCliente(clienteLogIn);
				ventanaTiendaCliente.setVisible(true);
				dispose();
			}
		});
		btnTienda.setBounds(76, 73, 212, 136);
		contentPane.add(btnTienda);

		btnPedirCita = new JButton("Pedir cita");
		btnPedirCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosCliente metodosCliente = new MetodosCliente();
				
				try {
					listaCliente = metodosCliente.recogerClienteYSusAnimales();
					
					if(clienteLogIn.getAnimal() == null) {
						atras = false;
						ventanaAnaydirAnimal = new AnaydirAnimal(clienteLogIn, atras);
						ventanaAnaydirAnimal.setVisible(true);
						dispose();
					}else {
						atras = true;
						ventanaPedirCita = new PedirCita(clienteLogIn, listaCliente, atras);
						ventanaPedirCita.setVisible(true);
						dispose();
					}
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnPedirCita.setBounds(400, 73, 246, 136);
		contentPane.add(btnPedirCita);

		lblNombreApellido = new JLabel(texto);
		lblNombreApellido.setBounds(514, 34, 203, 14);
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
		btnCerrarSesion.setBounds(76, 220, 212, 136);
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
		btnConfigurarPerfil.setBounds(400, 220, 246, 136);
		contentPane.add(btnConfigurarPerfil);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCarrito = new Carrito(clienteLogIn);
				ventanaCarrito.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(298, 208, 85, 21);
		contentPane.add(btnNewButton);
	}
}
