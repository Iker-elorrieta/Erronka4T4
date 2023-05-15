package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controlador.MetodosGenerales;
import Controlador.MetodosProducto;
import Modelo.ObjetosComprables;
import Modelo.Pedido;
import Modelo.Producto;
import ModeloPerfil.Cliente;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;

public class SeleccionTiendaProductos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JScrollPane scrollpanelSeleccionProductos;

	private JButton btnAtras;
	private JButton btnAceptar;
	private JButton btnOrdenarPrecio;
	private JButton btnOrdenarStock;
	private JLabel lblCantidad;

	private JTable tableSeleccionProductos;

	private JLabel lblFrase;
	private JLabel lblTextoCantidad;

	boolean precio = true;
	boolean stock = true;

	TiendaCliente ventanaTiendaCliente;
	ventanaCliente ventanaCliente;
	
	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosProducto metodosProducto = new MetodosProducto();

	Producto producto = new Producto();
	Pedido pedido = new Pedido();

	private JPanel panelFotos;
	private ImageIcon img;
	private JLabel lblIMG;

	/**
	 * Create the frame.
	 */
	public SeleccionTiendaProductos(Cliente clienteLogIn, String valorUbicacion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollpanelSeleccionProductos = new JScrollPane();
		scrollpanelSeleccionProductos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpanelSeleccionProductos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanelSeleccionProductos.setBounds(10, 44, 505, 200);
		contentPane.add(scrollpanelSeleccionProductos);

		tableSeleccionProductos = new JTable();
		try {
			ArrayList<ObjetosComprables> listaProducto = metodosProducto.recogerProductoTienda(valorUbicacion);
			tableSeleccionProductos = metodosGenerales.generarTablaSeleccionProductos(listaProducto);
			scrollpanelSeleccionProductos.setViewportView(tableSeleccionProductos);
			tableSeleccionProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					if (tableSeleccionProductos.getSelectedRow() != -1) {
						DefaultTableModel modelo = (DefaultTableModel) tableSeleccionProductos.getModel();
						String dato = (String) modelo.getValueAt(tableSeleccionProductos.getSelectedRow(), 0);
						panelFotos = new JPanel();
						panelFotos.setBackground(new Color(192, 192, 192));
						panelFotos.setBounds(554, 85, 137, 129);
						contentPane.add(panelFotos);
						panelFotos.setLayout(null);
						if (dato.equals("cama")) {
							img = new ImageIcon("imgReto2/productos/ProductosGato/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("cuenco")) {
							img = new ImageIcon("imgReto2/productos/ProductosPerro/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("collar")) {
							img = new ImageIcon("imgReto2/productos/ProductosPerro/6.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("correa")) {
							img = new ImageIcon("imgReto2/productos/ProductosPerro/9.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("arnes")) {
							img = new ImageIcon("imgReto2/productos/ProductosPerro/8.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("juguete")) {
							img = new ImageIcon("imgReto2/productos/ProductosPerro/3.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("jaula")) {
							img = new ImageIcon("imgReto2/productos/ProductosLoro/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("comidaGato")) {
							img = new ImageIcon("imgReto2/productos/ProductosGato/ComidaGato/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("ComidaHasmter") || dato.equals("comidaHamster")) {
							img = new ImageIcon("imgReto2/productos/ProductosHamster/ComidaHasmter/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("ComidaLoro")) {
							img = new ImageIcon("imgReto2/productos/ProductosLoro/ComidaLoro/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else if (dato.equals("ComidaPerro")) {
							img = new ImageIcon("imgReto2/productos/ProductosPerro/ComidaPerro/1.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						} else {
							img = new ImageIcon("imgReto2/productos/desconocido.jpg");
							img = new ImageIcon(img.getImage().getScaledInstance(137, 129, Image.SCALE_DEFAULT));
							lblIMG = new JLabel();
							lblIMG.setBounds(0, 0, 137, 129);
							panelFotos.add(lblIMG);
							lblIMG.setIcon(img);
						}
					}
				}
			});
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
		btnAtras.setBounds(10, 337, 89, 23);
		contentPane.add(btnAtras);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableSeleccionProductos.getSelectedRow() != -1) {
					DefaultTableModel tu = (DefaultTableModel) tableSeleccionProductos.getModel();
					String valorProducto = String.valueOf(tu.getValueAt(tableSeleccionProductos.getSelectedRow(), 0));
					String valorPrecio = String.valueOf(tu.getValueAt(tableSeleccionProductos.getSelectedRow(), 1));
					String valorStock = String.valueOf(tu.getValueAt(tableSeleccionProductos.getSelectedRow(), 2));
					int filaseleccionada = tableSeleccionProductos.getSelectedRow();
					
					ArrayList<ObjetosComprables> listaProducto = null;
					try {
						listaProducto = metodosProducto.recogerProductoTienda(valorUbicacion);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					producto = (Producto) listaProducto.get(filaseleccionada);
					int codProducto = producto.getCodProducto();
					String cantidadString = lblCantidad.getText();
					int cantidad = Integer.parseInt(cantidadString);

					Instant instant = Instant.now();
					long millis = instant.toEpochMilli();
					Date fechaEnMilli = new Date(millis);
					java.sql.Date fechaActual = new java.sql.Date(fechaEnMilli.getTime());

					LocalTime horaActual = LocalTime.now();
					java.sql.Time horaEnTime = java.sql.Time.valueOf(horaActual);

					float precio = Float.valueOf(valorPrecio) * cantidad;

					if (Integer.valueOf(valorStock) < cantidad ) {
						JOptionPane.showMessageDialog(null, "Cantidad seleccionada superior al stock");
					} else if (Integer.valueOf(cantidad) == 0){
						JOptionPane.showMessageDialog(null, "Cantidad no determinada");
					} else {
						try {
							producto.setStock(Integer.valueOf(valorStock));
							producto.setNombreProducto(valorProducto);
							producto.setCodProducto(Integer.valueOf(codProducto));

							pedido.setPreciototal(precio);
							pedido.setCantidadProducto(cantidad);
							pedido.setFecha(fechaActual);
							pedido.setHora(horaEnTime);

							metodosGenerales.EliminarStockProducto(producto, pedido, clienteLogIn);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						ventanaCliente = new ventanaCliente(clienteLogIn);
						ventanaCliente.setVisible(true);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un articulo");
				}
			}
		});
		btnAceptar.setBounds(628, 337, 89, 23);
		contentPane.add(btnAceptar);

		lblFrase = new JLabel("Seleccione un producto:");
		lblFrase.setBounds(10, 19, 165, 14);
		contentPane.add(lblFrase);

		lblTextoCantidad = new JLabel("Determine la cantidad:");
		lblTextoCantidad.setBounds(10, 255, 153, 14);
		contentPane.add(lblTextoCantidad);

		btnOrdenarPrecio = new JButton("Ordenar por precio");
		btnOrdenarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (precio == true) {
					precio = false;
				} else {
					precio = true;
				}
				DefaultTableModel modelo = (DefaultTableModel) tableSeleccionProductos.getModel();
				modelo.setRowCount(0);
				try {
					ArrayList<ObjetosComprables> listaProductoPrecio = metodosProducto.recogerProductoTienda(valorUbicacion);
					metodosGenerales.ordenarPorPrecioDesc(listaProductoPrecio, precio);
					tableSeleccionProductos = metodosGenerales.generarTablaSeleccionProductos(listaProductoPrecio);
					scrollpanelSeleccionProductos.setViewportView(tableSeleccionProductos);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnOrdenarPrecio.setBounds(518, 255, 183, 23);
		contentPane.add(btnOrdenarPrecio);

		btnOrdenarStock = new JButton("Ordenar por stock");
		btnOrdenarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stock == true) {
					stock = false;
				} else {
					stock = true;
				}
				DefaultTableModel modelo = (DefaultTableModel) tableSeleccionProductos.getModel();
				modelo.setRowCount(0);
				try {
					ArrayList<ObjetosComprables> listaProductoStock = metodosProducto
							.recogerProductoTienda(valorUbicacion);
					metodosGenerales.ordenarPorStockDesc(listaProductoStock, stock);
					tableSeleccionProductos = metodosGenerales.generarTablaSeleccionProductos(listaProductoStock);
					scrollpanelSeleccionProductos.setViewportView(tableSeleccionProductos);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnOrdenarStock.setBounds(242, 255, 203, 23);
		contentPane.add(btnOrdenarStock);

		panelFotos = new JPanel();
		panelFotos.setLayout(null);
		panelFotos.setBackground(Color.LIGHT_GRAY);
		panelFotos.setBounds(554, 85, 137, 129);
		contentPane.add(panelFotos);

		lblIMG = new JLabel();
		lblIMG.setBounds(0, 0, 137, 129);
		panelFotos.add(lblIMG);

		JButton btnSumar = new JButton("+");
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cantidadString = lblCantidad.getText();
				int cantidad = Integer.parseInt(cantidadString);
				cantidad = cantidad + 1;
				cantidadString = String.valueOf(cantidad);
				lblCantidad.setText(cantidadString);
			}
		});
		btnSumar.setBounds(79, 280, 41, 23);
		contentPane.add(btnSumar);

		lblCantidad = new JLabel("0");
		lblCantidad.setBounds(143, 255, 46, 14);
		contentPane.add(lblCantidad);

		JButton btnRestar = new JButton("-");
		btnRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cantidadString = lblCantidad.getText();
				int cantidad = Integer.parseInt(cantidadString);
				if (cantidad > 0) {
					cantidad = cantidad - 1;
				}
				cantidadString = String.valueOf(cantidad);
				lblCantidad.setText(cantidadString);
			}
		});
		btnRestar.setBounds(10, 280, 41, 23);
		contentPane.add(btnRestar);
	}
}
