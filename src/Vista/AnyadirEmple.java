package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosClinicaVeterinaria;
import Controlador.MetodosEmpleado;
import Controlador.MetodosGenerales;
import Excepciones.DniInvalidoException;
import Modelo.ClinicaVeterinaria;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion;
import ModeloPerfil.Especializacion.Especialidad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AnyadirEmple extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtDireccion;
	
	private JLabel lblAnyadirEmpleado;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblContra;
	private JLabel lblDireccion;
	private JLabel lblEspecializacion;
	private JLabel lblUbicacion;
	
	private JComboBox<Especializacion.Especialidad> comboBoxEspecializacion;
	private JComboBox<String> comboBoxUbicacion;

	private JButton btnAceptar;
	private JButton btnAtras;
	
	ArrayList<String> especilidad = new ArrayList<String>();
	ArrayList<String> ubilist = new ArrayList<String>();
	
	MetodosEmpleado metodosEmpleado = new MetodosEmpleado();
	MetodosClinicaVeterinaria metodosClinicaVeterinaria = new MetodosClinicaVeterinaria();
	MetodosGenerales metodosGenerales = new MetodosGenerales();
	
	Especialidad es;
	private JTextField txtContra;

	/**
	 * Create the frame.
	 */
	public AnyadirEmple(Empleado empleadoLogIn) {
		try {
			ArrayList<ClinicaVeterinaria> listcli = metodosClinicaVeterinaria.recogerClinicaVeterinaria();
			for (int i = 0; i < listcli.size(); i++) {
				String ubi = String.valueOf(listcli.get(i).getUbicacion());
				ubilist.add(ubi);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAnyadirEmpleado = new JLabel("Rellena los siguentes campos para añadir empleado:");
		lblAnyadirEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnyadirEmpleado.setBounds(30, 21, 410, 21);
		contentPane.add(lblAnyadirEmpleado);

		txtNombre = new JTextField();
		txtNombre.setBounds(198, 65, 315, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 68, 139, 14);
		contentPane.add(lblNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(30, 103, 139, 14);
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(198, 100, 315, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		lblDni = new JLabel("Dni:");
		lblDni.setBounds(30, 140, 139, 14);
		contentPane.add(lblDni);

		txtDni = new JTextField();
		txtDni.setBounds(198, 137, 315, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		lblContra = new JLabel("Contraseña:");
		lblContra.setBounds(30, 178, 139, 14);
		contentPane.add(lblContra);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(30, 256, 139, 14);
		contentPane.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(198, 253, 315, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);

		lblEspecializacion = new JLabel("Especializacion:");
		lblEspecializacion.setBounds(30, 215, 139, 14);
		contentPane.add(lblEspecializacion);

		comboBoxEspecializacion = new JComboBox<Especializacion.Especialidad>();
		comboBoxEspecializacion.setBounds(198, 211, 181, 22);
		contentPane.add(comboBoxEspecializacion);

		for (Especializacion.Especialidad dia : Especializacion.Especialidad.values()) {
			comboBoxEspecializacion.addItem(dia);
		}

		lblUbicacion = new JLabel("Ubicacion:");
		lblUbicacion.setBounds(30, 289, 139, 14);
		contentPane.add(lblUbicacion);

		comboBoxUbicacion = new JComboBox<String>();
		comboBoxUbicacion.setBounds(198, 286, 181, 21);
		contentPane.add(comboBoxUbicacion);
		for (String ubilist2 : ubilist) {
			comboBoxUbicacion.addItem(ubilist2);
		}

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String contra = txtContra.getText();
				String dni = txtDni.getText();
				String direccion = txtDireccion.getText();
				
				Empleado emple = new Empleado();
				
				emple.setAntiguedad(0);
				emple.setNombre(txtNombre.getText());
				emple.setApellido(txtApellido.getText());
				emple.setContrasenya(txtContra.getText());
				emple.setDireccion(txtDireccion.getText());
				emple.setDni(txtDni.getText());
				emple.setEspecializacion((Especialidad) comboBoxEspecializacion.getSelectedItem());
				emple.setSalario(0);
			
				if (nombre.isEmpty() || apellido.isEmpty() || contra.isEmpty() || direccion.isEmpty() || dni.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Introduce todos los datos");
				}else {
					try {	
						boolean errorDni = MetodosGenerales.comprobarDni(dni);
						if (errorDni == false) {
							JOptionPane.showMessageDialog(null, "DNI mal formado");
						} else {
							try {
								int val = 0, x = 0;;
								comboBoxUbicacion.getSelectedItem();
								ArrayList<ClinicaVeterinaria> listcli = metodosClinicaVeterinaria.recogerClinicaVeterinaria();						
								while (x < listcli.size() && !String.valueOf(comboBoxUbicacion.getSelectedItem()).equals(listcli.get(x).getUbicacion())) {
								    x++;
								}
								if (x < listcli.size()) {
								    val = listcli.get(x).getCodVeterinaria();
								}
								
								boolean meter = true;
								int i = 0;
								ArrayList<Empleado> comprobarEmple = metodosEmpleado.recogerEmpleado();
								while (i < comprobarEmple.size() && !txtDni.getText().equals(comprobarEmple.get(i).getDni())) {
								    i++;
								}
								if (i == comprobarEmple.size()) {
								    // no se encontró el elemento, hacer algo aquí si es necesario
								} else {
								    meter = false;
								}
								
								
								if (meter) {
									metodosEmpleado.insertarEmpleado(emple, val);
									metodosGenerales.asignarSalario(emple.getDni());
									JOptionPane.showMessageDialog(null, "Se introducio correctamente");
								} else {
									JOptionPane.showMessageDialog(null, "DNI en Uso");
									txtNombre.setText("");
									txtApellido.setText("");
									txtDni.setText("");
									txtDireccion.setText("");
									txtContra.setText("");
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Empleado> comprobarEmple;
							try {
								comprobarEmple = metodosEmpleado.recogerEmpleado();
								MostrarEmple mostrarEmpleado = new MostrarEmple(comprobarEmple, empleadoLogIn);
								mostrarEmpleado.setVisible(true);
								dispose();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}	
					} catch (DniInvalidoException e2) {
						 System.err.println(e2.getMessage());					
					}	

				}
			}
		});
		btnAceptar.setBounds(597, 337, 120, 23);
		contentPane.add(btnAceptar);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Empleado> comprobarEmple;
				try {
					comprobarEmple = metodosEmpleado.recogerEmpleado();
					MostrarEmple mostrarEmpleado = new MostrarEmple(comprobarEmple, empleadoLogIn);
					mostrarEmpleado.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAtras.setBounds(30, 337, 107, 23);
		contentPane.add(btnAtras);
		
		txtContra = new JTextField();
		txtContra.setColumns(10);
		txtContra.setBounds(198, 175, 315, 20);
		contentPane.add(txtContra);
	}
}
