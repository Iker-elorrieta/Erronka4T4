package Controlador;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Excepciones.DniInvalidoException;
import Modelo.Adopcion;
import Modelo.ClinicaVeterinaria;
import Modelo.Consulta;
import Modelo.Gestion;
import Modelo.GestionAnimal;
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

	public void LogIn(String dni, String contra) throws SQLException, DniInvalidoException, NumberFormatException {
		boolean errorLogIN = true;
		MetodosCliente metodosCliente = new MetodosCliente();
		MetodosGenerales metodosGenerales = new MetodosGenerales();
		metodosGenerales.comprobarDni(dni);

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

	public boolean comprobarDni(String dni) throws DniInvalidoException, NumberFormatException {
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

		String cantidad = String.valueOf(pedido.getCantidadProducto() - producto.getStock());

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosPedido.insertarPedido(pedido, cliente.getDni(), producto.getCodProducto(), 0);
		metodosProducto.updateProducto(column, cantidadNueva, producto);
	}

	public void AnadirStockAnimal(Mascota mascota, GestionAnimal gestionanimal, Empleado empleado) throws SQLException {

		String cantidad = String.valueOf(gestionanimal.getCantidad() + mascota.getStock());

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosGestionAnimal.insertarGestion(gestionanimal, empleado.getDni(), mascota.getCodMascota());
		metodoMascota.updateMascota(column, cantidadNueva, mascota);

	}

	public void EliminarStockAnimal(Mascota mascota, Adopcion adopcion, Cliente cliente) throws SQLException {

		String cantidad = String.valueOf(mascota.getStock() - 1);

		String[] cantidadNueva = { cantidad };
		String column[] = { "Stock" };

		metodosAdopcion.insertarAnimalAdoptado(adopcion, cliente.getDni(), mascota.getCodMascota());
		metodoMascota.updateMascota(column, cantidadNueva, mascota);

	}

	public JTable generarTablaUbicaciones() throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;
		MetodosClinicaVeterinaria metodosClinicaveterinaria = new MetodosClinicaVeterinaria();
		ArrayList<ClinicaVeterinaria> listaClinicaVeterinaria = metodosClinicaveterinaria.recogerClinicaVeterinaria();
		titulos = new String[] { "Ubicacion" };
		txt = new String[listaClinicaVeterinaria.size()][titulos.length];
		int cont = 0;
		for (ClinicaVeterinaria clini : listaClinicaVeterinaria) {
			txt[cont][0] = clini.getUbicacion();
			cont++;
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));

		return table;
	}

	public JTable generarTablaTiendas() throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;
		titulos = new String[] { "Tienda" };
		txt = new String[2][titulos.length];
		txt[0][0] = "Mascotas";
		txt[1][0] = "Productos";
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));

		return table;
	}

	public JTable generarTablaSeleccionMascotas(String valorUbicacion) throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;
		MetodosMascota metodosMascota = new MetodosMascota();
		ArrayList<Mascota> listaMascota = metodosMascota.recogerMascotaTienda(valorUbicacion);
		titulos = new String[] { "Mascotas", "Precio", "Stock" };
		txt = new String[listaMascota.size()][titulos.length];
		int cont = 0;
		for (Mascota mascota : listaMascota) {
			txt[cont][0] = mascota.getEspecie();
			txt[cont][1] = String.valueOf(mascota.getPrecio());
			txt[cont][2] = String.valueOf(mascota.getStock());
			cont++;
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));

		return table;
	}

	public JTable generarTablaSeleccionProductos(String valorUbicacion) throws SQLException {
		String[] titulos = null;
		Object[][] txt = null;
		MetodosProducto metodosProducto = new MetodosProducto();
		ArrayList<Producto> listaProducto = metodosProducto.recogerProductoTienda(valorUbicacion);
		titulos = new String[] { "Productos", "Precio", "Stock" };
		txt = new String[listaProducto.size()][titulos.length];
		int cont = 0;
		for (Producto producto : listaProducto) {
			txt[cont][0] = producto.getNombreProducto();
			txt[cont][1] = String.valueOf(producto.getPrecio());
			txt[cont][2] = String.valueOf(producto.getStock());
			cont++;
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));

		return table;
	}

	public JTable generarTablasALL() throws SQLException {
		// TODO Auto-generated method stub
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
		return table;
	}

	public void modificarSalarioAntiguedad(String dni, int antiguedad) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement modificarSalario = conexion.createStatement();
		String sql = "call ModificarSalarioAntiguedad (" + dni + ", " + antiguedad + ");";
		ResultSet resul = modificarSalario.executeQuery(sql);
		while (resul.next()) {
			System.out.println(dni);
		}
	}

	public void asignarSalario(String dni) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement modificarSalario = conexion.createStatement();
		String sql = "call AsignarSalario (" + dni + ");";
		ResultSet resul = modificarSalario.executeQuery(sql);
		while (resul.next()) {
			System.out.println(dni);
		}
	}

	public JTable generarTablasAnimalDelCliente(Cliente cliente, ArrayList<Cliente> cs) throws SQLException {
		// TODO Auto-generated method stub
		String[] titulos = null;
		Object[][] txt = null;
		MetodosCliente mc = new MetodosCliente();
		int pos = cs.indexOf(cliente);
		ArrayList<Cliente> cl = mc.recogerClienteYSusAnimales();
		titulos = new String[] { "Nombre", "Edad", "Especie", "Sexo", "ID" };
		txt = new String[cl.get(pos).getAnimal().size()][titulos.length];
		int cont = 0;
		for (Cliente cli : cl) {

			if (cli.getDni().equals(cliente.getDni())) {

				for (int i = 0; i < cli.getAnimal().size(); i++) {
					txt[cont][0] = cli.getAnimal().get(i).getNombreAnimal();
					txt[cont][1] = String.valueOf(cli.getAnimal().get(i).getEdad());
					txt[cont][2] = cli.getAnimal().get(i).getEspecie();
					txt[cont][3] = cli.getAnimal().get(i).getSexo();
					txt[cont][4] = String.valueOf(cli.getAnimal().get(i).getIdAnimal());
					cont++;
				}
				break;
			}
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));

		return table;
	}

	public ArrayList<Consulta> ArrayListTxt(Consulta consulta, Cliente cliente) throws SQLException {
		MetodosConsulta metodosConsulta = new MetodosConsulta();
		ArrayList<Consulta> lista = metodosConsulta.recogerConsulta();
		String nombreArchivo = cliente.getNombre() + ".txt";
		try {
			FileWriter escritor = new FileWriter(nombreArchivo);
			String txtx = "Te atendera " + consulta.getEmpleado().getNombre() + ", el dia =" + consulta.getFecha()
					+ "//" + "a las =" + consulta.getHora() + "." + " La mascota es "
					+ consulta.getAnimal().getNombreAnimal() + ", y el coste será " + consulta.getPrecio() + "."
					+ " Un saludo " + cliente.getNombre() + " " + cliente.getApellido();
			escritor.write(txtx + "\n");
			escritor.close();
			System.out.println("Los elementos se han guardado ");
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();

		}
		return lista;
	}

}
