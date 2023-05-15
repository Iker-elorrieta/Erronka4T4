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

public class Inicio extends JFrame implements MouseListener {
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
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);

		panel = new JPanel();
		panel.addMouseListener((MouseListener) this);
		ImageIcon img = new ImageIcon("imgReto2/imgBienvenida/bienvenida.png");
		img = new ImageIcon(img.getImage().getScaledInstance(609, 250, Image.SCALE_DEFAULT));
		panel.setBounds(41, 35, 498, 257);
		panel.setLayout(null);

		JLabel lblIMG = new JLabel();
		lblIMG.setBounds(-43, -34, 605, 352);
		lblIMG.setIcon(img);
		panel.add(lblIMG);

		lblBienvenida = new JLabel("Bienvenido");
		lblBienvenida.setBounds(100, 0, 126, 66);
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 26));
		contentPane.add(lblBienvenida);

		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);

		contentPane.add(panel);

		setContentPane(contentPane);
		setVisible(true);

		ImageIcon img1 = new ImageIcon("imgReto2/ll.jpg");
		img1 = new ImageIcon(img1.getImage().getScaledInstance(743, 410, Image.SCALE_DEFAULT));

		contentPane.setLayout(null);

		JLabel lblIMG1 = new JLabel();
		lblIMG1.setBounds(0, 0, 743, 410);
		lblIMG1.setIcon(img1);
		contentPane.add(lblIMG1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			ventanaLogIn = new LogIn();
			ventanaLogIn.setVisible(true);
			dispose();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
