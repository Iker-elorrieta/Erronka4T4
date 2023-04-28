package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtContra;
	private JTextField txtDni;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblContra;
	private JLabel lblDni;
	private JLabel lblFrase;
	LogIn ventanaLogIn;
	
	/**
	 * Create the frame.
	 */
	public RegistroCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(148, 45, 244, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(148, 89, 244, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtContra = new JTextField();
		txtContra.setBounds(148, 133, 244, 20);
		contentPane.add(txtContra);
		txtContra.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaLogIn = new LogIn();
				
				ventanaLogIn.setVisible(true);
				dispose();
			}
		});
		btnAceptar.setBounds(335, 227, 89, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaLogIn = new LogIn();
				
				ventanaLogIn.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(10, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(44, 48, 46, 14);
		contentPane.add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(44, 92, 94, 14);
		contentPane.add(lblApellidos);
		
		lblContra = new JLabel("Contraseña");
		lblContra.setBounds(44, 136, 94, 14);
		contentPane.add(lblContra);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(44, 182, 46, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(148, 179, 244, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		lblFrase = new JLabel("Rellena los datos para el registro:");
		lblFrase.setBounds(24, 23, 368, 14);
		contentPane.add(lblFrase);
	}

}
