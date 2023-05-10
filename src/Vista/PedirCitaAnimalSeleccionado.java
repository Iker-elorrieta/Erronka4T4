package Vista;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Controlador.MetodosAnimal;
import Controlador.MetodosClinicaVeterinaria;
import Controlador.MetodosGenerales;
import Modelo.ClinicaVeterinaria;
import Modelo.Consulta;
import Modelo.DateLabelFormatter;
import ModeloAnimal.Animal;
import ModeloAnimal.Gato;
import ModeloAnimal.Loro;
import ModeloAnimal.Perro;
import ModeloPerfil.Cliente;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class PedirCitaAnimalSeleccionado extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MetodosClinicaVeterinaria mv = new MetodosClinicaVeterinaria();
	JDatePickerImpl datePicker;
	JSpinner spinner, spinner_1;
	JComboBox<String> comboBox_1, comboBox;
	MetodosClinicaVeterinaria metodosClinicaVeterinaria = new MetodosClinicaVeterinaria();
	Controlador.MetodosConsulta MetodosConsulta = new Controlador.MetodosConsulta();
	Controlador.MetodosCliente metodosCliente = new Controlador.MetodosCliente();
	
	Empleado empleado;
	JCheckBox chckbxNewCheckBox;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param valor
	 */
	Animal animal;

	public PedirCitaAnimalSeleccionado(Cliente cliente, int valor) {
		MetodosAnimal ma = new MetodosAnimal();
		try {
			ArrayList<Animal> an = ma.recogerAnimal();
			for (int i = 0; i < an.size(); i++) {
				if (an.get(i).getIdAnimal() == valor) {
					animal = an.get(i);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		UtilDateModel model = new UtilDateModel();
		// model.setDate(2022, 5, 6);
		Properties p = new Properties();

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(80, 83, 165, 25);
		contentPane.add(datePicker);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Pide tu cita para " + animal.getNombreAnimal());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 191, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ubicacion:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 47, 68, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fecha:");
		lblNewLabel_2.setBounds(10, 83, 60, 25);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Hora:");
		lblNewLabel_3.setBounds(10, 119, 55, 25);
		contentPane.add(lblNewLabel_3);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(82, 49, 119, 22);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date selectedUtilDate = (java.util.Date) datePicker.getModel().getValue();
				if(selectedUtilDate != null && comboBox_1.getSelectedItem()!=null) {
					float coste = 50;
					String hora = spinner.getValue().toString();
					String minuto = spinner_1.getValue().toString();
					String horaSel = hora + ":" + minuto + ":00";
					Time time = Time.valueOf(horaSel);
					Consulta consulta = new Consulta();
					consulta.setAnimal(animal);
					consulta.setEmpleado(empleado);
				
					long time2 = selectedUtilDate.getTime();
					java.sql.Date selectedSqlDate = new java.sql.Date(time2);
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					String fechaFormateada = formato.format(selectedSqlDate);
					String[] fechaAModificar = fechaFormateada.split("-");
					String anyo = fechaAModificar[0];
					String mes = fechaAModificar[1];
					String dia = fechaAModificar[2];
					String fechaFinal = anyo + "-" + mes + "-" + dia;
					Date ponerFecha = Date.valueOf(fechaFinal);
					consulta.setFecha(ponerFecha);
					consulta.setHora(time);
					consulta.setIdConsulta(0);
					if (chckbxNewCheckBox.isSelected()) {
						String especie = animal.getEspecie();
						String perro1 = "Perros";
						String gato1 = "Gatos";
						String loro1 = "Loros";
						if (especie.equalsIgnoreCase(perro1)) {
							Perro perro = new Perro();
							coste += perro.CosteVacuna(perro);

						} else if (especie.equalsIgnoreCase(gato1)) {
							Gato gato = new Gato();
							coste += gato.CosteVacuna(gato);

						} else if (especie.equalsIgnoreCase(loro1)) {
							Loro loro = new Loro();
							coste += loro.CosteVacuna(loro);
						}

					}
					consulta.setPrecio(coste);
					try {
						MetodosConsulta.insertarConsulta(consulta, animal.getIdAnimal(), empleado.getDni().toString());
						JOptionPane.showMessageDialog(null, "El coste total es " + coste);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MetodosGenerales metodosGenerales = new MetodosGenerales();
					try {
						metodosGenerales.ArrayListTxt(consulta, cliente);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBounds(289, 203, 110, 36);
		contentPane.add(btnNewButton);
		comboBox.addActionListener(e -> {
			comboBox_1.removeAllItems();
			try {
				ArrayList<ClinicaVeterinaria> listaClinicaConEmpleados = metodosClinicaVeterinaria
						.recogerClinicaVeterinaria();
				for (int i = 0; i < listaClinicaConEmpleados.size(); i++) {
					if (comboBox.getSelectedItem().equals(listaClinicaConEmpleados.get(i).getUbicacion())) {
						for (int j = 0; j < listaClinicaConEmpleados.get(i).getEmpleados().size(); j++) {
							if (animal.getEspecie().equalsIgnoreCase(listaClinicaConEmpleados.get(i).getEmpleados()
									.get(j).getEspecializacion().toString())) {
								comboBox_1.addItem(listaClinicaConEmpleados.get(i).getEmpleados().get(j).getNombre());
								empleado = listaClinicaConEmpleados.get(i).getEmpleados().get(j);

							}
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(8, 8, 18, 1));
		spinner.setBounds(53, 121, 51, 22);
		contentPane.add(spinner);

		JLabel lblNewLabel_4 = new JLabel(":");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(100, 120, 27, 24);
		contentPane.add(lblNewLabel_4);

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_1.setBounds(116, 122, 46, 22);
		contentPane.add(spinner_1);

		JLabel lblNewLabel_5 = new JLabel("Empleados disponibles:");
		lblNewLabel_5.setBounds(10, 164, 152, 16);
		contentPane.add(lblNewLabel_5);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(163, 161, 129, 22);
		contentPane.add(comboBox_1);

		chckbxNewCheckBox = new JCheckBox("Poner Vacuna");
		chckbxNewCheckBox.setBounds(10, 207, 107, 28);
		contentPane.add(chckbxNewCheckBox);

		JLabel lblNewLabel_6 = new JLabel("Coste de la consulta normal: 50$");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(208, 15, 220, 19);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("Atras");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();
					PedirCita ventanaPedirCita = new PedirCita(cliente, listaCliente);
					ventanaPedirCita.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setBounds(139, 210, 106, 29);
		contentPane.add(btnNewButton_1);
		if (animal.getEspecie().equalsIgnoreCase(Especialidad.Pez.toString())) {
			chckbxNewCheckBox.setEnabled(false);
		}

		try {
			ArrayList<ClinicaVeterinaria> cv = mv.recogerClinicaVeterinaria();
			for (int i = 0; i < cv.size(); i++) {
				comboBox.addItem(cv.get(i).getUbicacion());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

