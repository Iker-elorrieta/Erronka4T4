package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosEmpleado;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ventanaEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFrase;
	private JButton btnTienda;
	private JButton btnGestionarAnimales;
	private JLabel lblNombreApellido;
	private JButton btnCerrarSesion;
	private JButton btnConfigurarPerfil;
	private JButton btnGestionarEmpleados;
	PedirCita ventanaPedirCita;
	TiendaEmpleado ventanaTiendaEmpleado;
	ConfigurarPerfil ventanaConfigurarPerfil;
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
				ventanaTiendaEmpleado = new TiendaEmpleado(empleadoLogIn);
				ventanaTiendaEmpleado.setVisible(true);
				dispose();
			}
		});
		if (especializacionLogIn.equals(Especialidad.Perros) || especializacionLogIn.equals(Especialidad.Limpieza)
				|| especializacionLogIn.equals(Especialidad.Pez) || especializacionLogIn.equals(Especialidad.Gatos)
				|| especializacionLogIn.equals(Especialidad.Loros)) {
			btnTienda.setEnabled(false);
		}
		btnTienda.setBounds(10, 36, 191, 87);
		contentPane.add(btnTienda);

		btnGestionarAnimales = new JButton("Gestionar animales");
		btnGestionarAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});
		if (especializacionLogIn.equals(Especialidad.Ventas) || especializacionLogIn.equals(Especialidad.Limpieza)) {
			btnGestionarAnimales.setEnabled(false);
		}

		btnGestionarAnimales.setBounds(223, 36, 201, 87);
		contentPane.add(btnGestionarAnimales);

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

					e1.printStackTrace();
				}
			}
		});
		btnCerrarSesion.setBounds(151, 232, 117, 23);
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
		btnConfigurarPerfil.setBounds(223, 134, 201, 87);
		contentPane.add(btnConfigurarPerfil);

		btnGestionarEmpleados = new JButton("Gestionar empleados");
		btnGestionarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosEmpleado me = new MetodosEmpleado();
				ArrayList<Empleado> mer;
				try {
					mer = me.recogerEmpleado();
					MostrarEmple me2 = new MostrarEmple(mer,empleadoLogIn);
					me2.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		if (especializacionLogIn.equals(Especialidad.Perros) || especializacionLogIn.equals(Especialidad.Limpieza)
				|| especializacionLogIn.equals(Especialidad.Pez) || especializacionLogIn.equals(Especialidad.Gatos)
				|| especializacionLogIn.equals(Especialidad.Loros)
				|| especializacionLogIn.equals(Especialidad.Ventas)) {
			btnGestionarEmpleados.setEnabled(false);
		}
		btnGestionarEmpleados.setBounds(10, 134, 191, 87);
		contentPane.add(btnGestionarEmpleados);
	}
}
