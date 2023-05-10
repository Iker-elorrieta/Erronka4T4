package Vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

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
		setBounds(100, 100, 621, 392);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.addMouseListener((MouseListener) this);
		ImageIcon img = new ImageIcon("imgBienvenida/bienvenida.png");
		img = new ImageIcon(img.getImage().getScaledInstance(609, 250, Image.SCALE_DEFAULT));

	
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 605, 353);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIMG = new JLabel();
		lblIMG.setBounds(10, 60, 585, 258);
		panel.add(lblIMG);
		lblIMG.setIcon(img);
		
		lblBienvenida = new JLabel("Bienvenido");
		lblBienvenida.setBounds(226, 0, 159, 66);
		panel.add(lblBienvenida);
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 26));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			ventanaLogIn = new LogIn();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
