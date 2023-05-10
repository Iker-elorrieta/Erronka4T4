package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Controlador.MetodosGenerales;
import ModeloPerfil.Cliente;

public class SeleccionTiendaMascotas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollpanelSeleccionMascotas;
	private JButton btnAtras;
	private JButton btnAceptar;
	private JTable tableSeleccionMascotas;
	TiendaCliente ventanaTiendaCliente;
	
	MetodosGenerales mg = new MetodosGenerales();

	/**
	 * Create the frame.
	 */
	public SeleccionTiendaMascotas(Cliente clienteLogIn, String valorUbicacion) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollpanelSeleccionMascotas = new JScrollPane();
		scrollpanelSeleccionMascotas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpanelSeleccionMascotas.setBounds(50, 22, 320, 164);
		contentPane.add(scrollpanelSeleccionMascotas);
	
		tableSeleccionMascotas = new JTable();
		try {
			tableSeleccionMascotas = mg.generarTablaSeleccionMascotas(valorUbicacion);
			scrollpanelSeleccionMascotas.setViewportView(tableSeleccionMascotas);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaTiendaCliente = new TiendaCliente(clienteLogIn);
				ventanaTiendaCliente.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtras);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableSeleccionMascotas.getSelectedRow() != -1) {
					JOptionPane.showMessageDialog(null, "Bien");
				}else {
					JOptionPane.showMessageDialog(null, "Mal");
				}
			}
		});
		btnAceptar.setBounds(335, 227, 89, 23);
		contentPane.add(btnAceptar);
	}

}
