package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controlador.MetodosEmpleado;
import Controlador.MetodosGenerales;
import ModeloPerfil.Empleado;

public class MostrarEmple extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosEmpleado metodosEmpleado = new MetodosEmpleado();
	
	String recogerDNI="";
	Empleado empleado;
	
	/**
	 * Create the frame.
	 * 
	 * @param mer
	 */
	public MostrarEmple(ArrayList<Empleado> mer,Empleado empleadoLogIn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		JButton btnEliminarEmpleado = new JButton("Eliminar empleado");
		btnEliminarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelEditar;
				if (table.getSelectedRow() != -1) {
					modelEditar = (DefaultTableModel) table.getModel();
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					recogerDNI = String.valueOf(tm.getValueAt(table.getSelectedRow(), 0));
					try {
						metodosEmpleado.eliminarEmpleado(recogerDNI);
						modelEditar.removeRow(table.getSelectedRow());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un empleado");
				}

			}
		});
		contentPane.setLayout(null);
		btnEliminarEmpleado.setBounds(348, 337, 155, 23);
		getContentPane().add(btnEliminarEmpleado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 707, 262);
		getContentPane().add(scrollPane);

		table = new JTable();
		try {
			table = metodosGenerales.generarTablasALL();
			scrollPane.setViewportView(table);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		JButton btnAnadirEmpleado = new JButton("Añadir empleado");
		btnAnadirEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnyadirEmple ae = new AnyadirEmple(empleadoLogIn);
				ae.setVisible(true);
				dispose();

			}
		});
		btnAnadirEmpleado.setBounds(562, 337, 155, 23);
		contentPane.add(btnAnadirEmpleado);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaEmpleado ve = new ventanaEmpleado(empleadoLogIn);
				ve.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(10, 337, 115, 23);
		contentPane.add(btnAtras);
		
		JButton btnAumentarAntiguedad = new JButton("Aumerntar antiguedad");
		btnAumentarAntiguedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				recogerDNI = String.valueOf(tm.getValueAt(table.getSelectedRow(), 0));
				try {
					ArrayList<Empleado> lista = metodosEmpleado.recogerEmpleado();
					for(int i  =0;i<lista.size();i++) {
						if(recogerDNI.equals(lista.get(i).getDni())) {
							empleado = lista.get(i);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String dni = empleado.getDni();
				int antiguedad = empleado.getAntiguedad();
				try {
					metodosGenerales.modificarSalarioAntiguedad(dni, antiguedad);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAumentarAntiguedad.setBounds(151, 337, 171, 23);
		contentPane.add(btnAumentarAntiguedad);
	}
}
