package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ModeloPerfil.Empleado;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TiendaEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	ventanaEmpleado ventanaEmpleado;

	/**
	 * Create the frame.
	 */
	public TiendaEmpleado(Empleado empleadoLogIn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaEmpleado = new ventanaEmpleado(empleadoLogIn);
				ventanaEmpleado.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtras);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAceptar.setBounds(335, 227, 89, 23);
		contentPane.add(btnAceptar);
	}
}
