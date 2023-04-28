package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Inicio extends JFrame implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBienvenida;
	private JPanel panel;
	LogIn ventanaLogIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBienvenida = new JLabel("Bienvenida");
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBienvenida.setBounds(131, 91, 159, 66);
		contentPane.add(lblBienvenida);
		
		panel = new JPanel();
		panel.addMouseListener((MouseListener) this);
			
	
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ventanaLogIn = new LogIn();
		
		ventanaLogIn.setVisible(true);
		dispose();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
