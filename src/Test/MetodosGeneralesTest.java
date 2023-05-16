package Test;

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
import ModeloAnimal.Perro;
import ModeloPerfil.Cliente;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

import org.junit.Test;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosCliente;
import Controlador.MetodosClinicaVeterinaria;
import Controlador.MetodosConsulta;
import Controlador.MetodosGenerales;
import Controlador.MetodosPedido;
import Controlador.MetodosProducto;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class MetodosGeneralesTest extends ManagerAbstract {

	MetodosGenerales metodosGenerales = new MetodosGenerales();
	MetodosConsulta metodosConsulta = new MetodosConsulta();
	MetodosProducto metodosProducto = new MetodosProducto();
	MetodosCliente metodosCliente = new MetodosCliente();
	MetodosPedido metodosPedido = new MetodosPedido();
	MetodosClinicaVeterinaria metodosClinicaVeterinaria = new MetodosClinicaVeterinaria();
	// ----------------------------------------------//
	final String codConsulta = "CodConsulta";
	final String precio = "PrecioTotal";
	final String idAnimal = "IdAnimal";
	final String dni = "DNI";
	final String fecha = "Fecha";
	final String hora = "Hora";
	final String codGestion = "CodGestion";
	final String cantidad = "Cantidad";
	final String empleado = "Empleado";
	final String codProducto = "CodProducto";
	final String codPedido = "CodPedido";
	final String cantidadProducto = "CantidadProductos";
	final String stock = "Stock";
	final String cCodGestionAnimal = "CodGestionAnimal";
	final String cCantidad = "Cantidad";
	final String cCodAdopcion = "CodAdopcion";
	final String cPrecioTotal = "PrecioTotal";
	final String cCodgestionAnimal = "IdAnimal";
	final String cFecha = "Fecha";
	final String cCodMascota = "CodMascota";
	final String cDNI = "DNI";
	final String edad = "Edad";
	final String antiguedad = "Antiguedad";

	Connection conexion;

	@Test
	public void testLogin() {
		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "22761890D";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";
		String dniNuevo2 = "20304812K";

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			try {
				metodosGenerales.LogIn(dniNuevo, contrasenyaNuevo);
				metodosGenerales.LogIn(dniNuevo2, contrasenyaNuevo);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DniInvalidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testComprobarDni() {

		String dniCorrecto = "12345678Z";
		String dniLongitudIncorrecta = "1234567Z";
		String dniLetraIncorrecta = "12345678X";
		String dniFormatoIncorrecto = "12345A78Z";

		try {
			assertTrue(MetodosGenerales.comprobarDni(dniCorrecto));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DniInvalidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			assertFalse(MetodosGenerales.comprobarDni(dniLongitudIncorrecta));
		} catch (DniInvalidoException e) {

		}

		try {
			assertFalse(MetodosGenerales.comprobarDni(dniLetraIncorrecta));
		} catch (DniInvalidoException e) {

		}

		try {
			assertTrue(MetodosGenerales.comprobarDni(dniFormatoIncorrecto));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		} catch (DniInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void AnadirStockProductoTest() {

		// -------------Producto-------------//
		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 18;
		String[] stockNuevoUpdateado = { "21" };

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(stockNuevo);

		// -------------Gestion-------------//
		String fechaDate = "2023-02-13";

		String dniNuevo = "13240462Y";

		int codGestionNueva = 546;
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNueva = 5;

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(cantidadNueva);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);

		// -------------Empleado-------------//
		String nombreEmpleado = "Juan";
		String apellidosNuevo = "Calvo";
		String dniEmpleado = "13240462Y";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;

		Empleado empleadoNuevo = new Empleado();

		empleadoNuevo.setDni(dniEmpleado);
		empleadoNuevo.setAntiguedad(antiguedadNuevo);
		empleadoNuevo.setApellido(apellidosNuevo);
		empleadoNuevo.setContrasenya(contrasenyaNuevo);
		empleadoNuevo.setDireccion(direccionNuevo);
		empleadoNuevo.setEspecializacion(especializacionNuevo);
		empleadoNuevo.setNombre(nombreEmpleado);
		empleadoNuevo.setSalario(salarioNuevo);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			metodosGenerales.AnadirStockProducto(producto, gestionNueva, empleadoNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where gestion.CodGestion = '"
					+ codGestionNueva + "';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(codGestionNueva, resul2.getInt(codGestion));
				assertEquals(fechaNueva, resul2.getDate(fecha));
				assertEquals(cantidadNueva, resul2.getInt(cantidad));
				assertEquals(codProductoNuevo, resul2.getInt(codProducto));
				assertEquals(dniNuevo, resul2.getString(dni));
			}

			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul3 = sacaCliente.executeQuery(sql2);
			while (resul3.next()) {

				assertEquals(stockNuevoUpdateado[0].toString(), String.valueOf(resul3.getInt(stock)));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void EliminarStockProductoTest() {

		// -------------Producto-------------//
		int codProductoNuevo = 5546;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 20;
		float precioNuevo = 18;
		String[] stockNuevoUpdateado = { "4" };

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(stockNuevo);

		// -------------Pedido-------------//
		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 16;

		Pedido pedidoNueva = new Pedido();

		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setCodPedido(codPedidoNuevo);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);

		// -------------Cliente-------------//
		String nombreNuevoCliente = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevoCliente = "22761890D";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevoCliente);
		clienteNuevo.setNombre(nombreNuevoCliente);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevoCliente
					+ "' , '" + nombreNuevoCliente + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
					+ direccionNuevo + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			metodosGenerales.EliminarStockProducto(producto, pedidoNueva, clienteNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where DNI = '" + dniNuevoCliente + "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			while (resul3.next()) {

				assertEquals(fechaNueva, resul3.getDate(fecha));
				assertEquals(horaNueva, resul3.getTime(hora));
				assertEquals(precioTotalNuevo, resul3.getFloat(precio));
				assertEquals(cantidadProductosNuevo, resul3.getInt(cantidadProducto));
			}

			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul4 = sacaCliente.executeQuery(sql2);
			while (resul4.next()) {
				assertEquals(stockNuevoUpdateado[0], String.valueOf(resul4.getInt(stock)));
			}

			Statement resul5 = conexion.createStatement();

			resul5.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '"
					+ dniNuevoCliente + "';");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul6 = conexion.createStatement();

			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void AnadirStockAnimalTest() {

		// -------------Mascota-------------//
		int codMascotaNuevo = 561;
		String especieMascotaNuevo = "Perro";
		int stockMascotaNuevo = 65;
		float precioMascotaNuevo = 50;
		String[] stockNuevoUpdateado = { "130" };

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		// -------------GestionAnimal-------------//
		int codGestionAnimalNuevo = 900;
		String fechaDate = "2023-02-13";
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNuevo = 65;

		GestionAnimal gestionAnimal = new GestionAnimal();

		gestionAnimal.setCantidad(cantidadNuevo);
		gestionAnimal.setCodGestionAnimal(codGestionAnimalNuevo);
		gestionAnimal.setFecha(fechaNueva);

		// -------------Empleado-------------//
		String nombreEmpleado = "Juan";
		String apellidosNuevo = "Calvo";
		String dniEmpleado = "13240462Y";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;

		Empleado empleadoNuevo = new Empleado();

		empleadoNuevo.setDni(dniEmpleado);
		empleadoNuevo.setAntiguedad(antiguedadNuevo);
		empleadoNuevo.setApellido(apellidosNuevo);
		empleadoNuevo.setContrasenya(contrasenyaNuevo);
		empleadoNuevo.setDireccion(direccionNuevo);
		empleadoNuevo.setEspecializacion(especializacionNuevo);
		empleadoNuevo.setNombre(nombreEmpleado);
		empleadoNuevo.setSalario(salarioNuevo);

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			metodosGenerales.AnadirStockAnimal(mascota, gestionAnimal, empleadoNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTIONANIMAL
					+ " order by CodGestionAnimal DESC limit 1 ;";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);
			while (resul2.next()) {

				assertEquals(fechaNueva, resul2.getDate(cFecha));
				assertEquals(stockMascotaNuevo, resul2.getInt(cCantidad));
				assertEquals(codMascotaNuevo, resul2.getInt(cCodMascota));
				assertEquals(dniEmpleado, resul2.getString(dni));
			}

			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_MASCOTA + " order by CodMascota DESC limit 1 ;";
			ResultSet resul3 = sacaCliente.executeQuery(sql2);
			while (resul3.next()) {

				assertEquals(stockNuevoUpdateado[0].toString(), String.valueOf(resul3.getInt(stock)));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void EliminarStockAnimalTest() {
		// -------------Mascota-------------//
		int codMascotaNuevo = 900;
		String especieMascotaNuevo = "Perro";
		int stockMascotaNuevo = 10;
		float precioMascotaNuevo = 50;
		String[] stockNuevoUpdateado = { "9" };

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		// -------------Adopcion-------------//
		String fechaDate = "2023-02-13";

		int codAdopcionNuevo = 900;
		float precioTotalNuevo = 416;
		Date fechaNuevo = Date.valueOf(fechaDate);
		String dniNuevo = "7650931G";

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		Adopcion adopcion = new Adopcion();

		adopcion.setCodAdopcion(codAdopcionNuevo);
		adopcion.setFecha(fechaNuevo);
		adopcion.setPrecioTotal(precioTotalNuevo);

		// -------------Cliente-------------//

		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			metodosGenerales.EliminarStockAnimal(mascota, adopcion, clienteNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " order by CodAdopcion DESC limit 1 ;";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			while (resul3.next()) {

				assertEquals(fechaNuevo, resul3.getDate(cFecha));
				assertEquals(precioTotalNuevo, resul3.getFloat(cPrecioTotal));
				assertEquals(codMascotaNuevo, resul3.getInt(cCodMascota));
				assertEquals(dniNuevo, resul3.getString(cDNI));
			}

			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_MASCOTA + " where CodMascota = '" + codMascotaNuevo
					+ " ';";
			ResultSet resul4 = sacaCliente.executeQuery(sql2);
			while (resul4.next()) {
				assertEquals(stockNuevoUpdateado[0], String.valueOf(resul4.getInt(stock)));
			}

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testArrayListTxt() {
		ArrayList<Pedido> listaPedido = new ArrayList<>();
		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 15;

		Pedido pedidoNueva = new Pedido();
		pedidoNueva.setCliente(null);
		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setCodPedido(codPedidoNuevo);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);

		listaPedido.add(pedidoNueva);

		ArrayList<Pedido> listaPedidotxt = null;
		try {
			listaPedidotxt = metodosPedido.ArrayListTxt(listaPedido);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertNotNull(listaPedidotxt);
		assertEquals(listaPedido.size(), listaPedidotxt.size());

		try (BufferedReader lector = new BufferedReader(new FileReader("lista.txt"))) {
			String linea;
			int index = 0;
			while ((linea = lector.readLine()) != null) {
				assertEquals(listaPedido.get(index).toString(), linea);
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void AnadirEdadAnimalTest() {

		String nombreAnimalNuevo = "Anuk";
		int idAnimalNuevo = 900;
		int edadNuevo = 2;
		int masEdad = 3;
		String especieNuevo = "Perro";
		String sexoNuevo = "M";

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "22761890D";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		Perro perroNuevo = new Perro();

		perroNuevo.setNombreAnimal(nombreAnimalNuevo);
		perroNuevo.setEdad(edadNuevo);
		perroNuevo.setEspecie(especieNuevo);
		perroNuevo.setIdAnimal(idAnimalNuevo);
		perroNuevo.setSexo(sexoNuevo);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");

			metodosGenerales.AnadirEdadAnimal(perroNuevo, masEdad);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL + " where IdAnimal = '" + idAnimalNuevo
					+ " ';";
			ResultSet resul3 = sacaCliente.executeQuery(sql);
			while (resul3.next()) {
				assertEquals(masEdad, resul3.getInt(edad));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void EditarCantidadGestionTest() {

		String fechaDate = "2023-02-13";
		String dniNuevo = "26285531G";
		int codGestionNueva = 900;
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNueva = 5;
		int masCastidad = 8;

		int codProductoNuevo = 900;
		String nombreNuevoProducto = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 18;

		String nombreNuevoEmpleado = "Juan";
		String apellidosNuevo = "Calvo";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;
		int codClinicaNuevo = 900;
		String ubicacionNueva = "Murcia";

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(2654);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevoProducto + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resu3 = conexion.createStatement();
			resu3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevoEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadNueva + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");

			metodosGenerales.EditarCantidadGestion(gestionNueva, masCastidad);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where CodGestion = '" + codGestionNueva
					+ " ';";
			ResultSet resul5 = sacaCliente.executeQuery(sql);
			while (resul5.next()) {
				assertEquals(masCastidad, resul5.getInt(cantidad));
			}

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul7 = conexion.createStatement();
			resul7.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

			Statement resul8 = conexion.createStatement();
			resul8.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void EditarCantidadPedidoTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 15;
		int mascantidadProductosNuevo = 56;

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "22761890D";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		int codProductoNuevo = 900;
		String nombreNuevoProducto = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 18;

		Pedido pedidoNueva = new Pedido();

		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setCodPedido(codPedidoNuevo);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevoProducto + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
					+ direccionNuevo + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PEDIDO + "`"
					+ "(`CodPedido`, `PrecioTotal`, `Fecha`, `Hora`, `CantidadProductos`, `CodProducto`, `DNI`) "
					+ "VALUES( '" + codPedidoNuevo + "' , '" + precioTotalNuevo + "' , '" + fechaNueva + "' , '"
					+ horaNueva + "' , '" + cantidadProductosNuevo + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");

			metodosGenerales.EditarCantidadPedido(pedidoNueva, mascantidadProductosNuevo);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '" + codPedidoNuevo
					+ " ';";
			ResultSet resul4 = sacaCliente.executeQuery(sql);
			while (resul4.next()) {
				assertEquals(mascantidadProductosNuevo, resul4.getInt(cantidadProducto));
			}

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void EditarCantidadGestionAnimalTest() {

		int codGestionAnimalNuevo = 900;
		String fechaDate = "2023-02-13";
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNuevo = 65;
		int mascantidadNuevo = 345;

		// --------------------------------------------//
		int codMascotaNuevo = 900;
		String especieMascotaNuevo = "Perro";
		int stockMascotaNuevo = 10;
		float precioMascotaNuevo = 50;
		// --------------------------------------------//

		String nombreEmpleado = "Juan";
		String apellidosNuevo = "Calvo";
		String dniEmpleado = "27446177R";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		int codClinica = 2;
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		GestionAnimal gestionAnimal = new GestionAnimal();

		gestionAnimal.setCantidad(65);
		gestionAnimal.setCodGestionAnimal(codGestionAnimalNuevo);
		gestionAnimal.setFecha(fechaNueva);

		Empleado empleadoNuevo = new Empleado();

		empleadoNuevo.setDni(dniEmpleado);
		empleadoNuevo.setAntiguedad(antiguedadNuevo);
		empleadoNuevo.setApellido(apellidosNuevo);
		empleadoNuevo.setContrasenya(contrasenyaNuevo);
		empleadoNuevo.setDireccion(direccionNuevo);
		empleadoNuevo.setEspecializacion(especializacionNuevo);
		empleadoNuevo.setNombre(nombreEmpleado);
		empleadoNuevo.setSalario(salarioNuevo);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniEmpleado + "' , '" + nombreEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinica + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTIONANIMAL + "`"
					+ "(`CodGestionAnimal`, `Fecha`, `Cantidad`, `CodMascota`, `DNI`) " + "VALUES( '"
					+ codGestionAnimalNuevo + "' , '" + fechaNueva + "' , '" + cantidadNuevo + "' , '" + codMascotaNuevo
					+ "' , '" + dniEmpleado + "');");

			metodosGenerales.EditarCantidadGestionAnimal(gestionAnimal, mascantidadNuevo);

			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTIONANIMAL + " WHERE CodGestionAnimal = '"
					+ codGestionAnimalNuevo + " ';";
			ResultSet resul4 = sacaAnimal.executeQuery(sql);
			while (resul4.next()) {
				assertEquals(mascantidadNuevo, resul4.getInt(cCantidad));
			}

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE DNI = '" + dniEmpleado + "';");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void OrdenarPorPrecioDescTest() {

		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 651;

		int codProductoNuevo2 = 541;
		String nombreNuevo2 = "Bolsas";
		int stockNuevo2 = 16;
		float precioNuevo2 = 321;

		int codProductoNuevo3 = 956;
		String nombreNuevo3 = "Bolsas";
		int stockNuevo3 = 16;
		float precioNuevo3 = 87;

		int codClinicaNueva = 900;
		String ubicacionNueva = "Murcia";

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionNueva + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate(
					"Insert into `" + ManagerAbstract.TABLE_ALMACEN + "`" + "(`CodProducto`, `CodClinica`) "
							+ "VALUES( '" + codProductoNuevo + "' , '" + codClinicaNueva + "');");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo2 + "' , '"
					+ nombreNuevo2 + "' , '" + stockNuevo2 + "' , '" + precioNuevo2 + "');");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"Insert into `" + ManagerAbstract.TABLE_ALMACEN + "`" + "(`CodProducto`, `CodClinica`) "
							+ "VALUES( '" + codProductoNuevo2 + "' , '" + codClinicaNueva + "');");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo3 + "' , '"
					+ nombreNuevo3 + "' , '" + stockNuevo3 + "' , '" + precioNuevo3 + "');");

			Statement resul7 = conexion.createStatement();
			resul7.executeUpdate(
					"Insert into `" + ManagerAbstract.TABLE_ALMACEN + "`" + "(`CodProducto`, `CodClinica`) "
							+ "VALUES( '" + codProductoNuevo3 + "' , '" + codClinicaNueva + "');");

			ArrayList<ObjetosComprables> listaProducto = metodosProducto.recogerProductoTienda(ubicacionNueva);

			metodosGenerales.ordenarPorPrecioDesc(listaProducto, true);

			assertEquals(651.0, listaProducto.get(0).getPrecio(), 0.001);
			assertEquals(321.0, listaProducto.get(1).getPrecio(), 0.001);
			assertEquals(87.0, listaProducto.get(2).getPrecio(), 0.001);

			metodosGenerales.ordenarPorPrecioDesc(listaProducto, false);

			assertEquals(87.0, listaProducto.get(0).getPrecio(), 0.001);
			assertEquals(321.0, listaProducto.get(1).getPrecio(), 0.001);
			assertEquals(651.0, listaProducto.get(2).getPrecio(), 0.001);

			Statement resul8 = conexion.createStatement();
			resul8.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");

			Statement resul9 = conexion.createStatement();
			resul9.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

			Statement resul10 = conexion.createStatement();
			resul10.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo2 + "';");

			Statement resul1 = conexion.createStatement();
			resul1.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo3 + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void ordenarPorStockDescTest() {

		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 12;
		float precioNuevo = 651;

		int codProductoNuevo2 = 541;
		String nombreNuevo2 = "Bolsas";
		int stockNuevo2 = 15;
		float precioNuevo2 = 321;

		int codProductoNuevo3 = 956;
		String nombreNuevo3 = "Bolsas";
		int stockNuevo3 = 154;
		float precioNuevo3 = 87;

		int codClinicaNueva = 900;
		String ubicacionNueva = "Murcia";

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionNueva + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate(
					"Insert into `" + ManagerAbstract.TABLE_ALMACEN + "`" + "(`CodProducto`, `CodClinica`) "
							+ "VALUES( '" + codProductoNuevo + "' , '" + codClinicaNueva + "');");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo2 + "' , '"
					+ nombreNuevo2 + "' , '" + stockNuevo2 + "' , '" + precioNuevo2 + "');");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"Insert into `" + ManagerAbstract.TABLE_ALMACEN + "`" + "(`CodProducto`, `CodClinica`) "
							+ "VALUES( '" + codProductoNuevo2 + "' , '" + codClinicaNueva + "');");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo3 + "' , '"
					+ nombreNuevo3 + "' , '" + stockNuevo3 + "' , '" + precioNuevo3 + "');");

			Statement resul7 = conexion.createStatement();
			resul7.executeUpdate(
					"Insert into `" + ManagerAbstract.TABLE_ALMACEN + "`" + "(`CodProducto`, `CodClinica`) "
							+ "VALUES( '" + codProductoNuevo3 + "' , '" + codClinicaNueva + "');");

			ArrayList<ObjetosComprables> listaProducto = metodosProducto.recogerProductoTienda(ubicacionNueva);

			metodosGenerales.ordenarPorStockDesc(listaProducto, true);

			assertEquals(154, listaProducto.get(0).getStock());
			assertEquals(15, listaProducto.get(1).getStock());
			assertEquals(12, listaProducto.get(2).getStock());

			metodosGenerales.ordenarPorStockDesc(listaProducto, false);

			assertEquals(12, listaProducto.get(0).getStock());
			assertEquals(15, listaProducto.get(1).getStock());
			assertEquals(154, listaProducto.get(2).getStock());

			Statement resul8 = conexion.createStatement();
			resul8.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");

			Statement resul9 = conexion.createStatement();
			resul9.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

			Statement resul10 = conexion.createStatement();
			resul10.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo2 + "';");

			Statement resul1 = conexion.createStatement();
			resul1.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo3 + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testArrayListTxtConsulta() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		int codConsultaNuevo = 900;
		float precioNuevo = 51;
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "22761890D";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		String nombreAnimalNuevo = "Anuk";

		ArrayList<Animal> listaAnimal = new ArrayList<Animal>();
		Perro perro = new Perro();
		perro.setNombreAnimal(nombreAnimalNuevo);
		listaAnimal.add(perro);

		String nombreNuevoEmpleado = "Juan";

		Empleado empleado = new Empleado();

		empleado.setNombre(nombreNuevoEmpleado);

		Consulta consulta = new Consulta();

		consulta.setFecha(fechaNueva);
		consulta.setHora(horaNueva);
		consulta.setIdConsulta(codConsultaNuevo);
		consulta.setPrecio(precioNuevo);
		consulta.setEmpleado(empleado);
		consulta.setAnimal(perro);

		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);

		try {
			ArrayList<Consulta> listaConsultas = metodosGenerales.ArrayListTxt(consulta, clienteNuevo);

			assertNotNull(listaConsultas);

			try (BufferedReader lector = new BufferedReader(new FileReader("Ander.txt"))) {
				String linea;
				while ((linea = lector.readLine()) != null) {
					assertEquals(
							"Te atendera Juan, el dia =2023-02-13a las =13:55:36. La mascota es Anuk, y el coste será 51.0.$ Un saludo Ander Perex",
							linea);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			File archivo = new File(
					"C:\\Users\\Trabajo\\Desktop\\Nueva carpeta\\Reto4Grupo4Definitivo (3).zip_expanded\\Reto4Ander\\Ander.txt");

			if (archivo.delete()) {
				System.out.println("El archivo ha sido eliminado.");
			} else {
				System.out.println("El archivo no se ha podido eliminar.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGenerarTablaUbicaciones() {

		int codClinicaNueva = 900;
		String ubicacionNueva = "Murcia";

		int esperadoColumnas = 1;
		int esperadoFilas = 4;

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionNueva + "');");

			ClinicaVeterinaria clinicaVeterinaria = new ClinicaVeterinaria();

			clinicaVeterinaria.setCodVeterinaria(codClinicaNueva);
			clinicaVeterinaria.setUbicacion(ubicacionNueva);

			Object[][] text = metodosGenerales.generarTablaUbicaciones();
			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(ubicacionNueva, text[text.length - 1][0]);

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void generarTablaTiendasTest() {

		try {
			Object[][] text = metodosGenerales.generarTablaTiendas();

			int esperadoColumnas = 1;
			int esperadoFilas = 2;

			String mascota = "Mascotas";
			String producto = "Productos";

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(mascota, text[0][0]);
			assertEquals(producto, text[1][0]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablaSeleccionMascotasTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 3;

		int codMascotaNuevo = 900;
		String especieMascotaNuevo = "Perro";
		int stockMascotaNuevo = 10;
		float precioMascotaNuevo = 50;
		String precioMascotaNuevoText = "50.0";
		String stockMascotaNuevoText = "10";

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		ArrayList<ObjetosComprables> listaMascota = new ArrayList<ObjetosComprables>();
		listaMascota.add(mascota);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			Object[][] text = metodosGenerales.generarTablaSeleccionMascotas(listaMascota);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(precioMascotaNuevoText, text[text.length - 1][1]);
			assertEquals(stockMascotaNuevoText, text[text.length - 1][2]);
			assertEquals(especieMascotaNuevo, text[text.length - 1][0]);

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablaSeleccionProductosTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 3;

		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		String stockProductoText = "16";
		float precioNuevo = 18;
		String precioNuevoText = "18.0";

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(stockNuevo);

		ArrayList<ObjetosComprables> listaProducto = new ArrayList<ObjetosComprables>();
		listaProducto.add(producto);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Object[][] text = metodosGenerales.generarTablaSeleccionProductos(listaProducto);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(precioNuevoText, text[text.length - 1][1]);
			assertEquals(stockProductoText, text[text.length - 1][2]);
			assertEquals(nombreNuevo, text[text.length - 1][0]);

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablasALL() {

		int esperadoFilas = 22;
		int esperadoColumnas = 8;

		String nombreNuevo = "Juan";
		String apellidosNuevo = "Calvo";
		String dniNuevo = "99999999Z";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		float salarioNuevo = 5615;
		String salarioNuevoText = "5615.0";
		int antiguedadNuevo = 25;
		String antiguedadNuevoText = "25";
		Especialidad especializacionNuevo = Especialidad.Perros;
		int codClinicaNuevo = 900;
		String ubicacionNueva = "Murcia";

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resu2 = conexion.createStatement();
			resu2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			Object[][] text = metodosGenerales.generarTablasALL();

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(dniNuevo, text[text.length - 1][0]);
			assertEquals(nombreNuevo, text[text.length - 1][1]);
			assertEquals(apellidosNuevo, text[text.length - 1][2]);
			assertEquals(contrasenyaNuevo, text[text.length - 1][3]);
			assertEquals(direccionNuevo, text[text.length - 1][4]);
			assertEquals(salarioNuevoText, text[text.length - 1][5].toString());
			assertEquals(antiguedadNuevoText, text[text.length - 1][6].toString());
			assertEquals(especializacionNuevo, text[text.length - 1][7]);

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void generarTablaGestionarAnimalCliente() {

		int esperadoFilas = 21;
		int esperadoColumnas = 4;

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "99999999Z";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		String nombreAnimalNuevo = "Anuk";
		int idAnimalNuevo = 900;
		int edadNuevo = 2;
		String edadNuevoText = "2";
		String especieNuevo = "perro";
		String sexoNuevo = "M";

		Perro perro = new Perro();
		perro.setNombreAnimal(nombreAnimalNuevo);
		perro.setEdad(edadNuevo);
		perro.setEspecie(especieNuevo);
		perro.setIdAnimal(idAnimalNuevo);
		perro.setSexo(sexoNuevo);

		ArrayList<Animal> listaAnimal = new ArrayList<Animal>();
		listaAnimal.add(perro);

		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);
		clienteNuevo.setAnimal(listaAnimal);

		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
		listaCliente.add(clienteNuevo);

		Connection conexion;
		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");

			Object[][] text = metodosGenerales.generarTablaGestionarAnimalCliente(clienteNuevo, listaCliente);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(nombreAnimalNuevo, text[0][0]);
			assertEquals(edadNuevoText, text[0][1]);
			assertEquals(especieNuevo, text[0][2]);
			assertEquals(sexoNuevo, text[0][3]);

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablaSeleccionMascotasTiendaEmpleadoTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 3;

		int codMascotaNuevo = 900;
		String especieMascotaNuevo = "Perro";
		int stockMascotaNuevo = 10;
		String stockMascotaNuevoText = "10";
		float precioMascotaNuevo = 50;
		String precioMascotaNuevoText = "50.0";

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		ArrayList<Mascota> listaMascota = new ArrayList<Mascota>();
		listaMascota.add(mascota);

		try {
			Object[][] text = metodosGenerales.generarTablaSeleccionMascotasTiendaEmpleado(listaMascota);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(especieMascotaNuevo, text[0][0]);
			assertEquals(stockMascotaNuevoText, text[0][1]);
			assertEquals(precioMascotaNuevoText, text[0][2]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void generarTablaSeleccionProductosTiendaEmpleadoTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 3;

		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		String stockNuevoText = "16";
		float precioNuevo = 18;
		String precioNuevoText = "18.0";

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(stockNuevo);

		ArrayList<Producto> listaProducto = new ArrayList<Producto>();
		listaProducto.add(producto);

		try {
			Object[][] text = metodosGenerales.generarTablaSeleccionProductosTiendaEmpleado(listaProducto);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(nombreNuevo, text[0][0]);
			assertEquals(stockNuevoText, text[0][1]);
			assertEquals(precioNuevoText, text[0][2]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablaSeleccionGestionTiendaEmpleadoTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 3;

		String fechaDate = "2023-02-13";
		String dniEmpleado = "45894650J";
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNueva = 5;
		String cantidadNuevaText = "5";

		Empleado empleado = new Empleado();
		empleado.setDni(dniEmpleado);
		
		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(cantidadNueva);
		gestionNueva.setEmpleado(empleado);
		gestionNueva.setFecha(fechaNueva);

		ArrayList<Gestion> listaGestion = new ArrayList<Gestion>();
		listaGestion.add(gestionNueva);

		try {
			Object[][] text = metodosGenerales.generarTablaSeleccionGestionTiendaEmpleado(listaGestion);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(dniEmpleado, text[0][0]);
			assertEquals(cantidadNuevaText, text[0][1]);
			assertEquals(fechaDate, text[0][2]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablaSeleccionPedidoClienteTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 5;

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		float precioTotalNuevo = 16813;
		String precioTotalNuevoText = "16813.0";
		int cantidadProductosNuevo = 15;
		String cantidadProductosNuevoText = "15";
		String nombreProducto = "Timulo";
		
		
		Producto producto = new Producto();
		producto.setNombreProducto(nombreProducto);
		
		ArrayList<Producto> lostaProducto = new ArrayList<Producto>();
		lostaProducto.add(producto);
		
		Pedido pedidoNueva = new Pedido();

		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setProductos(lostaProducto);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);

		ArrayList<Pedido> listaPedido = new ArrayList<Pedido>();
		listaPedido.add(pedidoNueva);

		try {
			Object[][] text = metodosGenerales.generarTablaSeleccionPedidoCliente(listaPedido);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(nombreProducto, text[0][0]);
			assertEquals(cantidadProductosNuevoText, text[0][1]);
			assertEquals(fechaDate, text[0][2]);
			assertEquals(horaTime, text[0][3]);
			assertEquals(precioTotalNuevoText, text[0][4]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void generarTablaSeleccionGestionTAnimaliendaEmpleadoTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 3;

		String fechaDate = "2023-02-13";
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNuevo = 65;
		String cantidadNuevoText = "65";
		String especieMascotaNuevo = "Perro";

		Mascota mascota = new Mascota();
		mascota.setEspecie(especieMascotaNuevo);

		ArrayList<Mascota> listaMascota = new ArrayList<Mascota>();
		listaMascota.add(mascota);

		GestionAnimal gestionAnimal = new GestionAnimal();

		gestionAnimal.setCantidad(cantidadNuevo);
		gestionAnimal.setListaMAscota(listaMascota);
		gestionAnimal.setFecha(fechaNueva);

		ArrayList<GestionAnimal> listaGestionAnimal = new ArrayList<GestionAnimal>();
		listaGestionAnimal.add(gestionAnimal);

		try {
			Object[][] text = metodosGenerales.generarTablaSeleccionGestionTAnimaliendaEmpleado(listaGestionAnimal);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(especieMascotaNuevo, text[0][0]);
			assertEquals(cantidadNuevoText, text[0][1]);
			assertEquals(fechaDate, text[0][2]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablaSeleccionAdopcionClienteTest() {

		int esperadoFilas = 1;
		int esperadoColumnas = 3;

		String especieMascotaNuevo = "Perro";

		String fechaDate = "2023-02-13";

		float precioTotalNuevo = 416;
		String precioTotalNuevoText = "416.0";
		Date fechaNuevo = Date.valueOf(fechaDate);

		Mascota mascota = new Mascota();
		mascota.setEspecie(especieMascotaNuevo);

		ArrayList<Mascota> listaMascota = new ArrayList<Mascota>();
		listaMascota.add(mascota);

		Adopcion adopcion = new Adopcion();

		adopcion.setListaMascota(listaMascota);
		adopcion.setFecha(fechaNuevo);
		adopcion.setPrecioTotal(precioTotalNuevo);

		ArrayList<Adopcion> listaAdopcion = new ArrayList<Adopcion>();
		listaAdopcion.add(adopcion);

		try {
			Object[][] text = metodosGenerales.generarTablaSeleccionAdopcionCliente(listaAdopcion);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(especieMascotaNuevo, text[0][0]);
			assertEquals(fechaDate, text[0][1]);
			assertEquals(precioTotalNuevoText, text[0][2]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generarTablaClienteAnimalTest() {

		int esperadoFilas = 21;
		int esperadoColumnas = 4;

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "99999999Z";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		String nombreAnimalNuevo = "Anuk";
		int idAnimalNuevo = 900;
		int edadNuevo = 2;
		String edadNuevoText = "2";
		String especieNuevo = "perro";
		String sexoNuevo = "M";

		Perro perro = new Perro();
		perro.setNombreAnimal(nombreAnimalNuevo);
		perro.setEdad(edadNuevo);
		perro.setEspecie(especieNuevo);
		perro.setIdAnimal(idAnimalNuevo);
		perro.setSexo(sexoNuevo);

		ArrayList<Animal> listaAnimal = new ArrayList<Animal>();
		listaAnimal.add(perro);

		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);
		clienteNuevo.setAnimal(listaAnimal);

		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
		listaCliente.add(clienteNuevo);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");

			Object[][] text = metodosGenerales.generarTablaClienteAnimal(clienteNuevo, listaCliente);

			assertEquals(esperadoFilas, text.length);
			assertEquals(esperadoColumnas, text[0].length);

			assertEquals(nombreAnimalNuevo, text[0][0]);
			assertEquals(edadNuevoText, text[0][1]);
			assertEquals(especieNuevo, text[0][2]);
			assertEquals(sexoNuevo, text[0][3]);

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
