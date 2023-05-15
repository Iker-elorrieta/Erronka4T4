package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosCliente;
import Controlador.MetodosCuenta;
import Controlador.MetodosGenerales;
import Excepciones.DniInvalidoException;
import ModeloPerfil.Cliente;
import ModeloPerfil.Cuenta;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
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
	private JTextField txtDireccion;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblContra;
	private JLabel lblDni;
	private JLabel lblFrase;
	private JLabel lblDireccion;
	
	LogIn ventanaLogIn;
	
	MetodosCliente metodosCliente = new MetodosCliente();
	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosCuenta metodosCuenta = new MetodosCuenta();
	
	Cuenta cuenta = new Cuenta();
	
	/**
	 * Create the frame.
	 */
	public RegistroCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(224, 81, 244, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(224, 116, 244, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtContra = new JTextField();
		txtContra.setBounds(224, 158, 244, 20);
		contentPane.add(txtContra);
		txtContra.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				String apellido = txtApellidos.getText();
				String contra = txtContra.getText();
				String dni = txtDni.getText();
				String direccion = txtDireccion.getText();
				
				Cliente cliente = new Cliente();
				
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setDni(dni);
				cliente.setDireccion(direccion);
				cliente.setContrasenya(contra);
				
				cuenta.setCliente(cliente);
				
				if (nombre.isEmpty() || apellido.isEmpty() || contra.isEmpty() || direccion.isEmpty() || dni.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Introduce todos los datos");
				}else {
					try {	
						boolean errorDni = MetodosGenerales.comprobarDni(dni);
						if (errorDni == false) {

							JOptionPane.showMessageDialog(null, "DNI mal formado");
						} else {
							metodosCliente.insertarCliente(cliente);
							metodosCuenta.insertarCuenta(cuenta, dni);
							try {
								ventanaLogIn = new LogIn();
								ventanaLogIn.setVisible(true);
								dispose();
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}	
					} catch (SQLException | DniInvalidoException e2) {
						 System.err.println(e2.getMessage());					
					}	
				}
			}
		});
		btnAceptar.setBounds(628, 337, 89, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
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
		btnCancelar.setBounds(24, 337, 89, 23);
		contentPane.add(btnCancelar);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(120, 84, 94, 14);
		contentPane.add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(120, 119, 94, 14);
		contentPane.add(lblApellidos);
		
		lblContra = new JLabel("Contraseña");
		lblContra.setBounds(120, 161, 94, 14);
		contentPane.add(lblContra);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(120, 205, 94, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(224, 202, 244, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		lblFrase = new JLabel("Rellena los datos para el registro:");
		lblFrase.setBounds(24, 32, 368, 14);
		contentPane.add(lblFrase);
		
		lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(120, 246, 94, 14);
		contentPane.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(224, 243, 244, 20);
		contentPane.add(txtDireccion);
	}
}
