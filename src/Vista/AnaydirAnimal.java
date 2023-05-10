package Vista;

import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosAnimal;
import Controlador.MetodosCliente;
import ModeloAnimal.Gato;
import ModeloAnimal.Loro;
import ModeloAnimal.Perro;
import ModeloAnimal.Pez;
import ModeloPerfil.Cliente;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AnaydirAnimal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String[] especies = { "Perro", "Gato", "loro", "pez" };
	private String[] sexo = { "H", "M" };
	private JLabel lblNewLabel_5, lblNewLabel_6;
	final String perro = "Perro";
	final String gato = "Gato";
	final String loro = "Loro";
	final String pez = "Pez";
	MetodosAnimal ma = new MetodosAnimal();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public AnaydirAnimal(Cliente cliente) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Añade tu mascota");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 23, 296, 41);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 86, 59, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Edad:");
		lblNewLabel_2.setBounds(10, 121, 59, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Especie:");
		lblNewLabel_3.setBounds(10, 165, 59, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setBounds(10, 204, 59, 14);
		contentPane.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setBounds(68, 83, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(68, 118, 59, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(68, 161, 106, 22);
		contentPane.add(comboBox);
		for (int i = 0; i < especies.length; i++) {
			comboBox.addItem(especies[i]);
		}

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(68, 200, 45, 22);
		contentPane.add(comboBox_1);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean bien = true;
				if (textField.getText().length() <= 2) {
					lblNewLabel_5.setVisible(true);
					bien = false;

				}
				if (textField_1.getText().length() == 0) {
					lblNewLabel_6.setVisible(true);
					bien = false;
				}
				String especie = (String) comboBox.getSelectedItem();
				String sexo = (String) comboBox_1.getSelectedItem();
				if (bien) {
					if (especie.equalsIgnoreCase(perro)) {
						Perro perro = new Perro();
						perro.setIdAnimal(0);
						perro.setNombreAnimal(textField.getText());
						perro.setEdad(Integer.valueOf(textField_1.getText()));
						perro.setEspecie(especie);
						perro.setSexo(sexo);
						perro.setCliente(cliente);
						try {
							ma.insertarAnimal(perro, cliente.getDni());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else if (especie.equalsIgnoreCase(gato)) {
						Gato gato = new Gato();
						gato.setIdAnimal(0);
						gato.setNombreAnimal(textField.getText());
						gato.setEdad(Integer.valueOf(textField_1.getText()));
						gato.setEspecie(especie);
						gato.setSexo(sexo);
						try {
							ma.insertarAnimal(gato, cliente.getDni());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						;

					} else if (especie.equalsIgnoreCase(loro)) {
						Loro loro = new Loro();
						loro.setIdAnimal(0);
						loro.setNombreAnimal(textField.getText());
						loro.setEdad(Integer.valueOf(textField_1.getText()));
						loro.setEspecie(especie);
						loro.setSexo(sexo);
						try {
							ma.insertarAnimal(loro, cliente.getDni());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						Pez pez = new Pez();
						pez.setIdAnimal(0);
						pez.setNombreAnimal(textField.getText());
						pez.setEdad(Integer.valueOf(textField_1.getText()));
						pez.setEspecie(especie);
						pez.setSexo(sexo);
						try {
							ma.insertarAnimal(pez, cliente.getDni());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				if (bien) {
					MetodosCliente cl = new MetodosCliente();
					try {
						ArrayList<Cliente> cs = cl.recogerClienteYSusAnimales();
						PedirCita sa = new PedirCita(cliente, cs);
						sa.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnNewButton.setBounds(309, 204, 106, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Atras");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_5.setVisible(false);
				lblNewLabel_6.setVisible(false);
				textField.setText("");
				textField_1.setText("");
				MetodosCliente cl = new MetodosCliente();
				try {
					ArrayList<Cliente> cs = cl.recogerClienteYSusAnimales();
					PedirCita sa = new PedirCita(cliente, cs);
					sa.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(309, 11, 106, 23);
		contentPane.add(btnNewButton_1);

		lblNewLabel_5 = new JLabel("Pon un nombre");
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(241, 86, 121, 14);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);

		lblNewLabel_6 = new JLabel("Pon una edad");
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setBounds(139, 121, 92, 14);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);
		for (int i = 0; i < sexo.length; i++) {
			comboBox_1.addItem(sexo[i]);
		}
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
	}
}
