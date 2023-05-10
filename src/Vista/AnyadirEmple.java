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
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AnyadirEmple extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTextField textField_4;
	ArrayList<String> especilidad = new ArrayList<String>();
	ArrayList<String> ubilist = new ArrayList<String>();
	MetodosEmpleado me = new MetodosEmpleado();
	MetodosClinicaVeterinaria mcv = new MetodosClinicaVeterinaria();
	Especialidad es;
	JLabel lblNewLabel_9;
	MetodosGenerales mg = new MetodosGenerales();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AnyadirEmple(Empleado empleadoLogIn) {
		try {
			ArrayList<ClinicaVeterinaria> listcli = mcv.recogerClinicaVeterinaria();
			for (int i = 0; i < listcli.size(); i++) {
				String ubi = String.valueOf(listcli.get(i).getUbicacion());
				ubilist.add(ubi);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("añadir empleado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 22, 226, 21);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(103, 54, 176, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("nombre:");
		lblNewLabel_1.setBounds(30, 54, 63, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("apellido:");
		lblNewLabel_2.setBounds(30, 85, 88, 14);
		contentPane.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(103, 85, 181, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Dni");
		lblNewLabel_3.setBounds(30, 124, 46, 14);
		contentPane.add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.setBounds(103, 121, 155, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("contraseña:");
		lblNewLabel_4.setBounds(30, 158, 68, 14);
		contentPane.add(lblNewLabel_4);

		passwordField = new JPasswordField();
		passwordField.setBounds(103, 155, 133, 20);
		contentPane.add(passwordField);

		JLabel lblNewLabel_5 = new JLabel("direccion");
		lblNewLabel_5.setBounds(30, 188, 63, 14);
		contentPane.add(lblNewLabel_5);

		textField_3 = new JTextField();
		textField_3.setBounds(103, 183, 155, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Salario:");
		lblNewLabel_6.setBounds(30, 213, 63, 14);
		contentPane.add(lblNewLabel_6);

		textField_4 = new JTextField();
		textField_4.setBounds(103, 210, 176, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Especializacion");
		lblNewLabel_7.setBounds(173, 27, 85, 14);
		contentPane.add(lblNewLabel_7);

		JComboBox<Especializacion.Especialidad> comboBox = new JComboBox<Especializacion.Especialidad>();
		comboBox.setBounds(288, 23, 119, 22);
		contentPane.add(comboBox);

		for (Especializacion.Especialidad dia : Especializacion.Especialidad.values()) {
			comboBox.addItem(dia);
		}

		JLabel lblNewLabel_8 = new JLabel("Ubicacion");
		lblNewLabel_8.setBounds(30, 236, 46, 14);
		contentPane.add(lblNewLabel_8);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(100, 232, 106, 22);
		contentPane.add(comboBox_1);
		for (String ubilist2 : ubilist) {
			comboBox_1.addItem(ubilist2);
		}

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean bien = false;
				boolean pasar = false;
				char[] pass = passwordField.getPassword();
				String contra = String.valueOf(pass);
				if (textField.getText() != null && textField_1.getText() != null && textField_2.getText() != null
						&& textField_3.getText() != null && textField_4.getText() != null && contra.length() > 0) {
					pasar = true;
				}

				try {
					bien = mg.comprobarDni(textField_2.getText());
					if (bien && pasar) {
						Empleado emple = new Empleado();
						emple.setAntiguedad(0);
						emple.setNombre(textField.getText());
						emple.setApellido(textField_1.getText());
						emple.setContrasenya(contra);
						emple.setDireccion(textField_3.getText());
						emple.setDni(textField_2.getText());
						emple.setEspecializacion((Especialidad) comboBox.getSelectedItem());
						emple.setSalario(Float.valueOf(textField_4.getText()));
						try {
							int val = 0;
							comboBox_1.getSelectedItem();
							ArrayList<ClinicaVeterinaria> listcli = mcv.recogerClinicaVeterinaria();
							for (int x = 0; x < listcli.size(); x++) {
								if (String.valueOf(comboBox_1.getSelectedItem())
										.equals(listcli.get(x).getUbicacion())) {
									val = listcli.get(x).getCodVeterinaria();
									break;
								}
							}
							ArrayList<Empleado> comprobarEmple = me.recogerEmpleado();
							boolean meter = true;
							for (int i = 0; i < comprobarEmple.size(); i++) {
								if (textField_2.getText().equals(comprobarEmple.get(i).getDni())) {
									meter = false;
									break;
								}
							}
							if (meter) {
								me.insertarEmpleado(emple, val);
								textField.setText("");
								textField_1.setText("");
								textField_2.setText("");
								textField_3.setText("");
								textField_4.setText("");
								passwordField.setText("");
								JOptionPane.showMessageDialog(null, "Se introducio correctamente");
							} else {
								lblNewLabel_9.setVisible(true);
								textField.setText("");
								textField_1.setText("");
								textField_2.setText("");
								textField_3.setText("");
								textField_4.setText("");
								passwordField.setText("");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (DniInvalidoException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(304, 232, 120, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("atras");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Empleado> comprobarEmple;
				try {
					comprobarEmple = me.recogerEmpleado();
					MostrarEmple ve = new MostrarEmple(comprobarEmple, empleadoLogIn);
					ve.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(300, 99, 107, 23);
		contentPane.add(btnNewButton_1);

		lblNewLabel_9 = new JLabel("Ya existe");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(293, 133, 119, 55);
		contentPane.add(lblNewLabel_9);
		lblNewLabel_9.setVisible(false);

		textField_4.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
	}
}
