package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosCliente;
import ModeloPerfil.Cliente;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Image;
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
		btnTienda.setBounds(31, 88, 203, 111);
		contentPane.add(btnTienda);

		btnPedirCita = new JButton("Pedir cita");
		btnPedirCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosCliente metodosCliente = new MetodosCliente();
				
				try {
					listaCliente = metodosCliente.recogerClienteYSusAnimales();
					

						ventanaPedirCita = new PedirCita(clienteLogIn, listaCliente);
						ventanaPedirCita.setVisible(true);
						dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnPedirCita.setBounds(258, 88, 203, 111);
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
		btnCerrarSesion.setBounds(125, 225, 203, 111);
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
		btnConfigurarPerfil.setBounds(372, 225, 203, 111);
		contentPane.add(btnConfigurarPerfil);
		
		btnNewButton = new JButton("Carrito");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCarrito = new Carrito(clienteLogIn);
				ventanaCarrito.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(481, 88, 203, 111);
		contentPane.add(btnNewButton);
		
		ImageIcon img1 = new ImageIcon("imgReto2/ll.jpg");
		img1 = new ImageIcon(img1.getImage().getScaledInstance(743, 410, Image.SCALE_DEFAULT));

		contentPane.setLayout(null);

		JLabel lblIMG1 = new JLabel();
		lblIMG1.setBounds(0, 0, 743, 410);
		lblIMG1.setIcon(img1);
		contentPane.add(lblIMG1);
		
		
	}
}
