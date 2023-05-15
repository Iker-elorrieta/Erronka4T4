package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosEmpleado;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class ventanaEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JLabel lblFrase;
	private JLabel lblNombreApellido;
	
	private JButton btnTienda;
	private JButton btnGestionarAnimales;
	private JButton btnCerrarSesion;
	private JButton btnConfigurarPerfil;
	private JButton btnGestionarEmpleados;
	
	PedirCita ventanaPedirCita;
	TiendaEmpleado ventanaTiendaEmpleado ;
	ConfigurarPerfil ventanaConfigurarPerfil;
	MostrarEmple ventanaMostrarEmple;
	ConfiguracionAnimalEmpledo ventanaConfiguracionAnimalEmpleado;
	ConfigurarGestiones ventanaConfigurarGestiones;
	
	LogIn ventanaLogIn;
	String nombreLogIn = "";
	String apellidoLogIn = "";
	String texto = "";
	Especialidad especializacionLogIn;

	/**
	 * Create the frame.
	 */
	public ventanaEmpleado(Empleado empleadoLogIn) {

		nombreLogIn = empleadoLogIn.getNombre();
		apellidoLogIn = empleadoLogIn.getApellido();
		texto = nombreLogIn + " " + apellidoLogIn;

		especializacionLogIn = empleadoLogIn.getEspecializacion();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFrase = new JLabel("Elige una de estas opciones:");
		lblFrase.setBounds(21, 27, 191, 14);
		contentPane.add(lblFrase);

		btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaTiendaEmpleado = new TiendaEmpleado(empleadoLogIn);
				ventanaTiendaEmpleado.setVisible(true);
				dispose();
			}
		});
		if (especializacionLogIn.equals(Especialidad.Perros) || especializacionLogIn.equals(Especialidad.limpieza) || especializacionLogIn.equals(Especialidad.Pez) || especializacionLogIn.equals(Especialidad.Gatos) || especializacionLogIn.equals(Especialidad.Loros)) {
			btnTienda.setEnabled(false);
		}
		btnTienda.setBounds(258, 64, 203, 118);
		contentPane.add(btnTienda);

		btnGestionarAnimales = new JButton("Gestionar animales");
		btnGestionarAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ventanaConfiguracionAnimalEmpleado = new ConfiguracionAnimalEmpledo(empleadoLogIn);
					ventanaConfiguracionAnimalEmpleado.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		if (especializacionLogIn.equals(Especialidad.ventas) || especializacionLogIn.equals(Especialidad.limpieza)) {
			btnGestionarAnimales.setEnabled(false);
		}
		
		btnGestionarAnimales.setBounds(497, 64, 203, 118);
		contentPane.add(btnGestionarAnimales);

		lblNombreApellido = new JLabel(texto);
		lblNombreApellido.setBounds(497, 27, 203, 14);
		contentPane.add(lblNombreApellido);

		btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ventanaLogIn = new LogIn();
					ventanaLogIn.setVisible(true);
					dispose();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnCerrarSesion.setBounds(21, 213, 203, 118);
		contentPane.add(btnCerrarSesion);

		btnConfigurarPerfil = new JButton("Configurar perfil");
		btnConfigurarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ventanaConfigurarPerfil = new ConfigurarPerfil(null, empleadoLogIn);
					ventanaConfigurarPerfil.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnConfigurarPerfil.setBounds(258, 213, 203, 118);
		contentPane.add(btnConfigurarPerfil);
		
		btnGestionarEmpleados = new JButton("Gestionar empleados");
		btnGestionarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosEmpleado me = new MetodosEmpleado();
				ArrayList<Empleado> mer;
				try {
					mer = me.recogerEmpleado();
					ventanaMostrarEmple = new MostrarEmple(mer, empleadoLogIn);
					ventanaMostrarEmple.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		if (especializacionLogIn.equals(Especialidad.Perros) || especializacionLogIn.equals(Especialidad.limpieza) || especializacionLogIn.equals(Especialidad.Pez) || especializacionLogIn.equals(Especialidad.Gatos) || especializacionLogIn.equals(Especialidad.Loros) || especializacionLogIn.equals(Especialidad.ventas)) {
			btnGestionarEmpleados.setEnabled(false);
		}
		btnGestionarEmpleados.setBounds(21, 64, 203, 118);
		contentPane.add(btnGestionarEmpleados);
		
		JButton btnConfigurarGestiones = new JButton("Configurar gestiones");
		btnConfigurarGestiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaConfigurarGestiones = new ConfigurarGestiones(empleadoLogIn);
				ventanaConfigurarGestiones.setVisible(true);
				dispose();
			}
		});
		btnConfigurarGestiones.setBounds(497, 213, 203, 118);
		contentPane.add(btnConfigurarGestiones);
		ImageIcon img1 = new ImageIcon("imgReto2/ll.jpg");
		img1 = new ImageIcon(img1.getImage().getScaledInstance(743, 410, Image.SCALE_DEFAULT));

		contentPane.setLayout(null);

		JLabel lblIMG1 = new JLabel();
		lblIMG1.setBounds(0, 0, 743, 410);
		lblIMG1.setIcon(img1);
		contentPane.add(lblIMG1);
	}
}
