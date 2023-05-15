package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosMascota;
import ModeloAnimal.Mascota;
import ModeloPerfil.Empleado;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AnyadirMascota extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEspecieDeLaMascota;
	private JTextField textStockInicial;
	private JTextField textPrecio;

	MetodosMascota metodoMascota = new MetodosMascota();
	
	Mascota mascota = new Mascota();
	Mascota mascotaYaAnadido = null;
	
	TiendaEmpleado ventanaTiendaEmpleado;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public AnyadirMascota(Empleado empleadoLogIn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// --------------------------LabelVentanaPerfil--------------------------//
		JLabel lblNombreProducto = new JLabel("Especie de  la mascota");
		lblNombreProducto.setBounds(63, 92, 188, 62);
		contentPane.add(lblNombreProducto);

		JLabel lblApellidos = new JLabel("Stock inicial:");
		lblApellidos.setBounds(63, 165, 78, 62);
		contentPane.add(lblApellidos);

		JLabel lblDireccion = new JLabel("Precio:");
		lblDireccion.setBounds(63, 238, 78, 62);
		contentPane.add(lblDireccion);

		JLabel lblTextoPerfil = new JLabel("Añadir nuevo producto:");
		lblTextoPerfil.setBounds(63, 38, 437, 32);
		contentPane.add(lblTextoPerfil);

		// --------------------------TextVentanaPerfil--------------------------//
		txtEspecieDeLaMascota = new JTextField();
		txtEspecieDeLaMascota.setBounds(261, 114, 239, 20);
		contentPane.add(txtEspecieDeLaMascota);
		txtEspecieDeLaMascota.setColumns(10);

		textStockInicial = new JTextField();
		textStockInicial.setColumns(10);
		textStockInicial.setBounds(261, 187, 239, 20);
		contentPane.add(textStockInicial);
		textStockInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letraIntroducida = e.getKeyChar();
				if (!((letraIntroducida >= '0' && letraIntroducida <= '9') || letraIntroducida == KeyEvent.VK_BACK_SPACE || letraIntroducida == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(261, 260, 239, 20);
		contentPane.add(textPrecio);
		textPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letraIntroducida = e.getKeyChar();
				boolean shouldConsume = false;

				String text = textPrecio.getText();

				if (!((letraIntroducida >= '0' && letraIntroducida <= '9') || letraIntroducida == KeyEvent.VK_BACK_SPACE || letraIntroducida == KeyEvent.VK_DELETE || letraIntroducida == '.')) {
					shouldConsume = true;
				}

				if (text.isEmpty() && letraIntroducida == '.') {
					shouldConsume = true;
				}

				if (letraIntroducida == '.' && text.contains(".")) {
					shouldConsume = true;
				}

				if (shouldConsume) {
					e.consume();
				}
			}
		});

		// --------------------------Botones--------------------------//
		JButton btnAnyadirMAscota = new JButton("Añadir nueva mascota");
		btnAnyadirMAscota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(txtEspecieDeLaMascota.getText() != null && textPrecio.getText() != null && textStockInicial.getText() != null && !txtEspecieDeLaMascota.getText().isEmpty() && !textPrecio.getText().isEmpty() && !textStockInicial.getText().isEmpty()) {
					
					mascota.setEspecie(txtEspecieDeLaMascota.getText());
					mascota.setPrecio(Float.parseFloat(textPrecio.getText()));
					mascota.setStock(Integer.parseInt(textStockInicial.getText()));
					
					try {
						metodoMascota.insertarMascota(mascota);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Mascota:" + txtEspecieDeLaMascota.getText() + " se ha añadido a la tienda de los productos", null, JOptionPane.INFORMATION_MESSAGE);
					
					txtEspecieDeLaMascota.setText(null);
					textPrecio.setText(null);
					textStockInicial.setText(null);
					
					mascota = mascotaYaAnadido;
					ventanaTiendaEmpleado = new TiendaEmpleado(empleadoLogIn);
					ventanaTiendaEmpleado.setVisible(true);
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "Debe de rellenar todos los campos", null, JOptionPane.INFORMATION_MESSAGE);
				}			
			}
		});
		btnAnyadirMAscota.setBounds(485, 337, 232, 23);
		contentPane.add(btnAnyadirMAscota);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaTiendaEmpleado = new TiendaEmpleado(empleadoLogIn);
				ventanaTiendaEmpleado.setVisible(true);
				dispose();
				
			}
		});
		btnAtras.setBounds(10, 337, 89, 23);
		contentPane.add(btnAtras);
	}
}
