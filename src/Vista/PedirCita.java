package Vista;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosAnimal;
import ModeloPerfil.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PedirCita extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	private JButton btnAnyadirAnimal;
	ventanaCliente ventanaCliente;
	private JTable table;
	private int valor = -1;

	/**
	 * Create the frame.
	 * 
	 * @param cs
	 */
	public PedirCita(Cliente cliente, ArrayList<Cliente> cs) {
		MetodosAnimal metodosAnimal = new MetodosAnimal();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCliente = new ventanaCliente(cliente);
				ventanaCliente.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtras);

		btnAnyadirAnimal = new JButton("Añadir animal");
		btnAnyadirAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaydirAnimal aa = new AnaydirAnimal(cliente);
				aa.setVisible(true);
				dispose();

			}
		});
		btnAnyadirAnimal.setBounds(290, 227, 134, 23);
		contentPane.add(btnAnyadirAnimal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 25, 377, 155);
		contentPane.add(scrollPane);

		table = new JTable();
		try {
			table = metodosAnimal.generarTablaClienteAnimal(cliente, cs);
			scrollPane.setViewportView(table);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton btnEliminarAnimal = new JButton("Eliminar animal");
		btnEliminarAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelEditar;
				if (table.getSelectedRow() != -1) {
					modelEditar = (DefaultTableModel) table.getModel();
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					String dato = String.valueOf(tm.getValueAt(table.getSelectedRow(), 4));
					try {
						metodosAnimal.eliminarAnimal(Integer.valueOf(dato));
						modelEditar.removeRow(table.getSelectedRow());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnEliminarAnimal.setBounds(139, 227, 115, 23);
		contentPane.add(btnEliminarAnimal);

		JButton btnPedirCita = new JButton("Pedir Cita");
		btnPedirCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					String dato = String.valueOf(tm.getValueAt(table.getSelectedRow(), 4));
					valor = Integer.valueOf(dato);

					PedirCitaAnimalSeleccionado pedirCitaAnimalSeleccionado = new PedirCitaAnimalSeleccionado(cliente,
							valor);
					pedirCitaAnimalSeleccionado.setVisible(true);
					dispose();
				}

			}
		});
		btnPedirCita.setBounds(290, 193, 134, 23);
		contentPane.add(btnPedirCita);
	}
}
