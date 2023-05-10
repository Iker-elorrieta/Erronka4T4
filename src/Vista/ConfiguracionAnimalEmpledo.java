package Vista;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosGenerales;

public class ConfiguracionAnimalEmpledo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollpanelConfigAnimalEmple;
	private JTable tableConfigAnimalEmple;

	
	MetodosGenerales metodosGenerales = new MetodosGenerales();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfiguracionAnimalEmpledo frame = new ConfiguracionAnimalEmpledo();
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
	public ConfiguracionAnimalEmpledo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollpanelConfigAnimalEmple = new JScrollPane();
		scrollpanelConfigAnimalEmple.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpanelConfigAnimalEmple.setBounds(216, 10, 2, 2);
		contentPane.add(scrollpanelConfigAnimalEmple);

		tableConfigAnimalEmple = new JTable();
		try {
			tableConfigAnimalEmple = metodosGenerales.generarTablaConfiguracionAnimalEmpleado();
			scrollpanelConfigAnimalEmple.setViewportView(tableConfigAnimalEmple);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
