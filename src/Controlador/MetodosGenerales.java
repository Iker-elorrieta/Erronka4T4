package Controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Excepciones.DniInvalidoException;
import Modelo.Adopcion;
import Modelo.ClinicaVeterinaria;
import Modelo.Consulta;
import Modelo.Gestion;
import Modelo.GestionAnimal;
import Modelo.ObjetosComprables;
import Modelo.Pedido;
import Modelo.Producto;
import ModeloAnimal.Animal;
import ModeloAnimal.Mascota;
import ModeloPerfil.Cliente;
import ModeloPerfil.Empleado;

public class MetodosGenerales extends ManagerAbstract {

	MetodosConsulta metodosConsulta = new MetodosConsulta();
	MetodosAnimal metodosAnimal = new MetodosAnimal();
	MetodosCliente metodosCliente = new MetodosCliente();
	MetodosProducto metodosProducto = new MetodosProducto();
	MetodosGestion metodosGestion = new MetodosGestion();
	MetodosPedido metodosPedido = new MetodosPedido();
	MetodosMascota metodoMascota = new MetodosMascota();
	MetodosGestionAnimal metodosGestionAnimal = new MetodosGestionAnimal();
	MetodosAdopcion metodosAdopcion = new MetodosAdopcion();

	private final String precio = "Precio";
	private final String stock = "Stock";
	private final String especie = "Especie";
	private final String nombreProducto = "Producto";
	private final String mascota = "Mascota";
	private final String cantidad = "Cantidad";
	private final String fecha = "Fecha";
	private final String hora = "Hora";

	Connection conexion;

	public void LogIn(String dni, String contra) throws SQLException, DniInvalidoException, NumberFormatException {
		boolean errorLogIN = true;
		MetodosCliente metodosCliente = new MetodosCliente();
		MetodosGenerales.comprobarDni(dni);

		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		listaClientes = metodosCliente.recogerClienteYSusAnimales();
		for (Cliente cliente : listaClientes) {
			if (dni.equals(cliente.getDni()) && contra.equals(cliente.getContrasenya())) {
				errorLogIN = false;
			}
		}
		if (errorLogIN == true) {
			JOptionPane.showMessageDialog(null, "DNI o contraseña incorrecta");
		} else {
			JOptionPane.showMessageDialog(null, "Bienvenido :)");
		}
	}

	public static boolean comprobarDni(String dni) throws DniInvalidoException, NumberFormatException {
		boolean dniCorrecto = true;
		final int longitudDNI = 9;
		final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

		if (dni.length() != longitudDNI) {
			dniCorrecto = false;
		} else {

			String numerosDni = dni.substring(0, 8);
			char letraDni = dni.charAt(8);

			int numeros = Integer.parseInt(numerosDni);
			int resto = numeros % 23;
			char letraCalculada = LETRAS_DNI.charAt(resto);

			if (letraCalculada != letraDni) {
				dniCorrecto = false;
			}
		}

		return dniCorrecto;
	}

	public void AnadirStockProducto(Producto producto, Gestion gestion, Empleado empleado) throws SQLException {

		String cantidad = String.valueOf(gestion.getCantidad() + producto.getStock());

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosGestion.insertarGestion(gestion, empleado.getDni(), producto.getCodProducto());
		metodosProducto.updateProducto(column, cantidadNueva, producto);

	}

	public void EliminarStockProducto(Producto producto, Pedido pedido, Cliente cliente) throws SQLException {

		String cantidad = String.valueOf(producto.getStock() - pedido.getCantidadProducto());

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosPedido.insertarPedido(pedido, cliente.getDni(), producto.getCodProducto(), 0);
		metodosProducto.updateProducto(column, cantidadNueva, producto);
	}
	
	public void EliminarGestion(Gestion gestion, int numero) throws SQLException {

		String cantidad = String.valueOf(numero);

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosGestion.updateGestion(column, cantidadNueva, gestion);
	}

	public void AnadirStockAnimal(Mascota mascota, GestionAnimal gestionanimal, Empleado empleado) throws SQLException {

		String cantidad = String.valueOf(gestionanimal.getCantidad() + mascota.getStock());

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosGestionAnimal.insertarGestion(gestionanimal, empleado.getDni(), mascota.getCodMascota());
		metodoMascota.updateMascota(column, cantidadNueva, mascota);

	}

	public void AnadirEdadAnimal(Animal animal, int numero) throws SQLException {

		String edad = String.valueOf(numero);

		String[] edadNueva = { edad };
		String column[] = { "Edad" };

		metodosAnimal.updateAnimal(column, edadNueva, animal);

	}

	public void EditarCantidadGestion(Gestion gestion, int numero) throws SQLException {

		String cantidad = String.valueOf(numero);

		String[] cantidadNueva = { cantidad };
		String column[] = { "Cantidad" };

		metodosGestion.updateGestion(column, cantidadNueva, gestion);

	}
	
	public void EditarCantidadPedido(Pedido pedido, int numero) throws SQLException {

		String cantidad = String.valueOf(numero);

		String[] cantidadNueva = { cantidad };
		String column[] = { "CantidadProductos" };

		metodosPedido.updatePedido(column, cantidadNueva, pedido);

	}
	
	public void EditarCantidadAdopcion(Adopcion adopcion, int numero) throws SQLException {

		String cantidad = String.valueOf(numero);

		String[] cantidadNueva = { cantidad };
		String column[] = { "CantidadProductos" };

		metodosAdopcion.updateAnimalADoptado(column, cantidadNueva, adopcion);

	}

	public void EditarCantidadGestionAnimal(GestionAnimal gestionAnimal, int numero) throws SQLException {

		String cantidad = String.valueOf(numero);

		String[] cantidadNueva = { cantidad };
		String column[] = { "Cantidad" };

		metodosGestionAnimal.updateGestion(column, cantidadNueva, gestionAnimal);

	}

	public void EliminarStockAnimal(Mascota mascota, Adopcion adopcion, Cliente cliente) throws SQLException {

		String cantidad = String.valueOf(mascota.getStock() - 1);

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosAdopcion.insertarAnimalAdoptado(adopcion, cliente.getDni(), mascota.getCodMascota());
		metodoMascota.updateMascota(column, cantidadNueva, mascota);

	}

	public Object[][] generarTablaUbicaciones() throws SQLException {
		MetodosClinicaVeterinaria metodosClinicaveterinaria = new MetodosClinicaVeterinaria();
		ArrayList<ClinicaVeterinaria> listaClinicaVeterinaria = metodosClinicaveterinaria.recogerClinicaVeterinaria();
		String[] titulos = new String[] { "Ubicacion" };
		Object[][] txt = new String[listaClinicaVeterinaria.size()][titulos.length];
		int cont = 0;
		for (ClinicaVeterinaria clini : listaClinicaVeterinaria) {
			txt[cont][0] = clini.getUbicacion();
			cont++;
		}
		return txt;
	}

	public Object[][] generarTablaTiendas() throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;
		titulos = new String[] { "Tienda" };
		txt = new String[2][titulos.length];
		txt[0][0] = "Mascotas";
		txt[1][0] = "Productos";

		return txt;
	}

	public JTable generarTablaSeleccionMascotas(ArrayList<ObjetosComprables> listaMascota) throws SQLException {
		String[] titulos = new String[] { mascota, precio, stock };
		Object[][] txt = new String[listaMascota.size()][titulos.length];
		int cont = 0;
		for (ObjetosComprables objetosComprables : listaMascota) {
			if (objetosComprables instanceof Mascota) {
				Mascota mascota = (Mascota) objetosComprables;
				txt[cont][0] = mascota.getEspecie();
				txt[cont][1] = String.valueOf(mascota.getPrecio());
				txt[cont][2] = String.valueOf(mascota.getStock());
				cont++;
			}
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);

		return table;
	}

	public JTable generarTablaSeleccionProductos(ArrayList<ObjetosComprables> listaProducto) throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;
		titulos = new String[] { nombreProducto, precio, stock };
		txt = new String[listaProducto.size()][titulos.length];
		int cont = 0;

		for (ObjetosComprables objetosComprables : listaProducto) {
			if (objetosComprables instanceof Producto) {
				Producto producto = (Producto) objetosComprables;
				txt[cont][0] = producto.getNombreProducto();
				txt[cont][1] = String.valueOf(producto.getPrecio());
				txt[cont][2] = String.valueOf(producto.getStock());
				cont++;
			}
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);

		return table;
	}

	public JTable generarTablasALL() throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;
		MetodosEmpleado metodosEmpleado = new MetodosEmpleado();
		ArrayList<Empleado> listaEmpleados = metodosEmpleado.recogerEmpleado();
		titulos = new String[] { "DNI", "Nombre", "Apellidos", "Contraseña", "Dirección", "Salario", "Antigüedad",
				"Especialización" };
		txt = new Object[listaEmpleados.size()][titulos.length];
		int cont = 0;
		for (int i = 0; i < listaEmpleados.size(); i++) {
			txt[cont][0] = listaEmpleados.get(i).getDni();
			txt[cont][1] = listaEmpleados.get(i).getNombre();
			txt[cont][2] = listaEmpleados.get(i).getApellido();
			txt[cont][3] = listaEmpleados.get(i).getContrasenya();
			txt[cont][4] = listaEmpleados.get(i).getDireccion();
			txt[cont][5] = listaEmpleados.get(i).getSalario();
			txt[cont][6] = listaEmpleados.get(i).getAntiguedad();
			txt[cont][7] = listaEmpleados.get(i).getEspecializacion();
			cont++;
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		return table;
	}

	public JTable generarTablaConfiguracionAnimalEmpleado() throws SQLException {
		String[] titulos = { "Cliente", "Nombre de su mascota", "Especie", "Edad", "Sexo" };
		ArrayList<Cliente> listClienteConAnimales = metodosCliente.recogerClienteYSusAnimales();
		ArrayList<Animal> listAnimal = metodosAnimal.recogerAnimal();
		Object[][] txt = new Object[listClienteConAnimales.size()][titulos.length];
		int cont = 0;
		for (int i = 0; i < listClienteConAnimales.size(); i++) {
			Cliente cliente = listClienteConAnimales.get(i);
			for (int j = 0; j < listAnimal.size(); j++) {
				txt[cont][0] = cliente.getNombre() + " " + cliente.getApellido();
				txt[cont][1] = cliente.getAnimal().get(j).getNombreAnimal();
				txt[cont][2] = cliente.getAnimal().get(j).getEspecie();
				txt[cont][3] = cliente.getAnimal().get(j).getEdad();
				txt[cont][4] = cliente.getAnimal().get(j).getSexo();
				cont++;
			}
		}
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		return table;
	}

	public void modificarSalarioAntiguedad(String dni, int antiguedad) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement modificarSalario = conexion.createStatement();
		String sql = "call ModificarSalarioAntiguedad ('" + dni + "', '" + antiguedad + "');";
		ResultSet resul = modificarSalario.executeQuery(sql);
		while (resul.next()) {
		}
	}

	public void asignarSalario(String dni) throws SQLException {
		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement asignarSalario = conexion.createStatement();
			asignarSalario.executeUpdate("call AsignarSalario ('" + dni + "');");
		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}

	}

	public void ordenarPorPrecioDesc(ArrayList<ObjetosComprables> listaObjetocomprablesPrecio, boolean precio) {

		if (precio == true) {
			Collections.sort(listaObjetocomprablesPrecio, new Comparator<ObjetosComprables>() {

				@Override
				public int compare(ObjetosComprables oc1, ObjetosComprables oc2) {

					return Double.compare(oc2.getPrecio(), oc1.getPrecio());
				}
			});
		} else {
			Collections.sort(listaObjetocomprablesPrecio, new Comparator<ObjetosComprables>() {

				@Override
				public int compare(ObjetosComprables oc1, ObjetosComprables oc2) {

					return Double.compare(oc1.getPrecio(), oc2.getPrecio());
				}
			});
		}
	}

	public void ordenarPorStockDesc(ArrayList<ObjetosComprables> listaObjetocomprablesStock, boolean stock) {
		if (stock == true) {
			Collections.sort(listaObjetocomprablesStock, new Comparator<ObjetosComprables>() {
				@Override
				public int compare(ObjetosComprables oc1, ObjetosComprables oc2) {
					return Double.compare(oc2.getStock(), oc1.getStock());
				}
			});
		} else {
			Collections.sort(listaObjetocomprablesStock, new Comparator<ObjetosComprables>() {

				@Override
				public int compare(ObjetosComprables oc1, ObjetosComprables oc2) {

					return Double.compare(oc1.getStock(), oc2.getStock());
				}
			});
		}
	}

	public JTable generarTablaGestionarAnimalCliente(Cliente cliente, ArrayList<Cliente> clienteRecibido)
			throws SQLException {

		String[] titulos = null;
		Object[][] txt = null;
		titulos = new String[] { "Nombre", "Edad", "Especie", "Sexo" };

		if (!(cliente == null)) {
			int pos = clienteRecibido.indexOf(cliente);
			ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();

			txt = new String[listaCliente.get(pos).getAnimal().size()][titulos.length];
			int cont = 0;
			for (Cliente clienteTabla : listaCliente) {

				if (clienteTabla.getDni().equals(cliente.getDni())) {

					for (int i = 0; i < clienteTabla.getAnimal().size(); i++) {
						txt[cont][0] = clienteTabla.getAnimal().get(i).getNombreAnimal();
						txt[cont][1] = String.valueOf(clienteTabla.getAnimal().get(i).getEdad());
						txt[cont][2] = clienteTabla.getAnimal().get(i).getEspecie();
						txt[cont][3] = clienteTabla.getAnimal().get(i).getSexo();
						cont++;
					}
					break;
				}
			}
		}
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		return table;

	}

	public ArrayList<Consulta> ArrayListTxt(Consulta consulta, Cliente cliente) throws SQLException {
		MetodosConsulta metodosConsulta = new MetodosConsulta();
		ArrayList<Consulta> lista = metodosConsulta.recogerConsulta();
		String nombreArchivo = cliente.getNombre() + ".txt";
		try {
			FileWriter escritor = new FileWriter(nombreArchivo);
			String txtx = "Te atendera " + consulta.getEmpleado().getNombre() + ", el dia =" + consulta.getFecha()
					+ "a las =" + consulta.getHora() + "." + " La mascota es " + consulta.getAnimal().getNombreAnimal()
					+ ", y el coste será " + consulta.getPrecio() + ".$" + " Un saludo " + cliente.getNombre() + " "
					+ cliente.getApellido();
			escritor.write(txtx + "\n");
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public JTable generarTablaSeleccionMascotasTiendaEmpleado(ArrayList<Mascota> listaObjetosMascota)
			throws SQLException {

		String[] titulos = new String[] { especie, stock, precio };
		Object[][] txt = new String[listaObjetosMascota.size()][titulos.length];

		int cont = 0;
		for (ObjetosComprables objetosComprables : listaObjetosMascota) {
			if (objetosComprables instanceof Mascota) {
				Mascota mascota = (Mascota) objetosComprables;
				txt[cont][0] = mascota.getEspecie();
				txt[cont][1] = String.valueOf(mascota.getStock());
				txt[cont][2] = String.valueOf(mascota.getPrecio());
				cont++;
			}
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		return table;
	}

	public JTable generarTablaSeleccionProductosTiendaEmpleado(ArrayList<Producto> listaObjetosComprables)
			throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;

		titulos = new String[] { nombreProducto, stock, precio };
		txt = new String[listaObjetosComprables.size()][titulos.length];

		int cont = 0;

		for (ObjetosComprables objetosComprables : listaObjetosComprables) {
			if (objetosComprables instanceof Producto) {
				Producto producto = (Producto) objetosComprables;
				txt[cont][0] = producto.getNombreProducto();
				txt[cont][1] = String.valueOf(producto.getStock());
				txt[cont][2] = String.valueOf(producto.getPrecio());
				cont++;
			}
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		return table;
	}

	public JTable generarTablaSeleccionGestionTiendaEmpleado(ArrayList<Gestion> listaGestiones) throws SQLException {

		String[] titulos = new String[] { nombreProducto, cantidad, fecha };
		Object[][] txt = new String[listaGestiones.size()][titulos.length];

		int cont = 0;

		for (Gestion gestion : listaGestiones) {

			txt[cont][0] = String.valueOf(gestion.getCodGestion());
			txt[cont][1] = String.valueOf(gestion.getCantidad());
			txt[cont][2] = String.valueOf(gestion.getFecha());
			cont++;

		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		return table;
	}
	

	public JTable generarTablaSeleccionPedidoCliente(ArrayList<Pedido> listaPedido) throws SQLException {

		String[] titulos = new String[] { nombreProducto, cantidad, fecha , hora , precio};
		Object[][] txt = new String[listaPedido.size()][titulos.length];

		int cont = 0;

		for (Pedido pedido : listaPedido) {

			txt[cont][0] = String.valueOf(pedido.getCodPedido());
			txt[cont][1] = String.valueOf(pedido.getCantidadProducto());
			txt[cont][2] = String.valueOf(pedido.getFecha());
			txt[cont][3] = String.valueOf(pedido.getHora());
			txt[cont][4] = String.valueOf(pedido.getPreciototal());
			cont++;

		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));
		table.setCellSelectionEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		return table;
	}
	
	public Object[][] generarTablaSeleccionGestionTAnimaliendaEmpleado(ArrayList<GestionAnimal> listaGestionAnimal) throws SQLException {

		String[] titulos = new String[] { nombreProducto, cantidad, fecha };
		Object[][] txt = new String[listaGestionAnimal.size()][titulos.length];

		int cont = 0;

		for (GestionAnimal gestionAnimal : listaGestionAnimal) {

			txt[cont][0] = String.valueOf(gestionAnimal.getCodGestionAnimal());
			txt[cont][1] = String.valueOf(gestionAnimal.getCantidad());
			txt[cont][2] = String.valueOf(gestionAnimal.getFecha());
			cont++;

		}
		return txt;
	}
	
	public Object[][] generarTablaSeleccionAdopcionCliente(ArrayList<Adopcion> listaAdopcion) throws SQLException {

		String[] titulos = new String[] { nombreProducto, fecha , precio};
		Object[][] txt = new String[listaAdopcion.size()][titulos.length];

		int cont = 0;

		for (Adopcion adopcion : listaAdopcion) {
			
			txt[cont][0] = String.valueOf(adopcion.getCodAdopcion());
			txt[cont][1] = String.valueOf(adopcion.getFecha());
			txt[cont][2] = String.valueOf(adopcion.getPrecioTotal());
			cont++;
		}

		return txt;
	}
	
	public Object[][] generarTablaClienteAnimal(Cliente cliente, ArrayList<Cliente> cs) throws SQLException {
		MetodosCliente mc = new MetodosCliente();
		int pos = cs.indexOf(cliente);
		ArrayList<Cliente> cl = mc.recogerClienteYSusAnimales();
		String[] titulos = new String[] { "Nombre", "Edad", "Especie", "Sexo" };
		Object[][] txt = new String[cl.get(pos).getAnimal().size()][titulos.length];
		int cont = 0;
		for (Cliente cli : cl) {

			if (cli.getDni().equals(cliente.getDni())) {

				int i = 0;
				while (i < cl.size()) {
					cli = cl.get(i);
					if (cli.getDni().equals(cliente.getDni())) {
						int j = 0;
						while (j < cli.getAnimal().size()) {
							txt[cont][0] = cli.getAnimal().get(j).getNombreAnimal();
							txt[cont][1] = String.valueOf(cli.getAnimal().get(j).getEdad());
							txt[cont][2] = cli.getAnimal().get(j).getEspecie();
							txt[cont][3] = cli.getAnimal().get(j).getSexo();
							cont++;
							j++;
						}
						i++;
					} else {
						i++;
					}
				}
			}
		}
		return txt ;
	}
}
