package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

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
	//Tienda ventanaTienda;
	/**
	 * Create the frame.
	 */
	public Cliente() {
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
			}
		});
		btnTienda.setBounds(10, 36, 191, 170);
		contentPane.add(btnTienda);
		
		btnPedirCita = new JButton("Pedir cita");
		btnPedirCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPedirCita = new PedirCita();
				
				ventanaPedirCita.setVisible(true);
				dispose();
			}
			
		});
		btnPedirCita.setBounds(223, 36, 201, 170);
		contentPane.add(btnPedirCita);
		
		lblNombreApellido = new JLabel("NombreApellido");
		lblNombreApellido.setBounds(221, 11, 203, 14);
		contentPane.add(lblNombreApellido);
		
		btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaLogIn = new LogIn();
				
				ventanaLogIn.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setBounds(20, 227, 117, 23);
		contentPane.add(btnCerrarSesion);
		
		btnConfigurarPerfil = new JButton("Configurar perfil");
		btnConfigurarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfigurarPerfil.setBounds(223, 227, 201, 23);
		contentPane.add(btnConfigurarPerfil);
	}
}
