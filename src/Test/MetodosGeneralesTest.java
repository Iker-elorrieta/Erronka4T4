package Test;

import Excepciones.DniInvalidoException;

import Modelo.Adopcion;
import Modelo.Gestion;
import Modelo.GestionAnimal;
import Modelo.Pedido;
import Modelo.Producto;
import ModeloAnimal.Mascota;
import ModeloPerfil.Cliente;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

import org.junit.Test;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosCliente;
import Controlador.MetodosConsulta;
import Controlador.MetodosGenerales;
import Controlador.MetodosProducto;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class MetodosGeneralesTest extends ManagerAbstract {

	MetodosGenerales metodosgenerales = new MetodosGenerales();
	MetodosConsulta metodosConsulta = new MetodosConsulta();
	MetodosProducto metodosProducto = new MetodosProducto();
	MetodosCliente metodosCliente = new MetodosCliente();
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
	MetodosGenerales MetodosGenerales= new MetodosGenerales();

	@Test
	public void testComprobarDni() {

		String dniValido = "45894650J";

		try {
			
			MetodosGenerales.comprobarDni(dniValido);
			assertTrue(true);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block

		} catch (DniInvalidoException e1) {
			// TODO Auto-generated catch block

		}

		String dniLetraIncorrecta = "12345678X";
		try {
			MetodosGenerales.comprobarDni(dniLetraIncorrecta);
			assertTrue(false);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block

		} catch (DniInvalidoException e) {
			// TODO Auto-generated catch block

		}

		String dniLongitudIncorrecta = "1234567X";
		try {
			MetodosGenerales.comprobarDni(dniLongitudIncorrecta);
			assertTrue(false);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block

		} catch (DniInvalidoException e) {
			// TODO Auto-generated catch block

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

		int codGestionNueva = 900;
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
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			metodosgenerales.AnadirStockProducto(producto, gestionNueva, empleadoNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul3 = sacaCliente.executeQuery(sql2);
			while (resul3.next()) {

				assertEquals(stockNuevoUpdateado[0].toString(), String.valueOf(resul3.getInt(stock)));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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
		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
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

		String dniNuevo = "22761890D";

		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 20;

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

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevoCliente
					+ "' , '" + nombreNuevoCliente + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
					+ direccionNuevo + "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			metodosgenerales.EliminarStockProducto(producto, pedidoNueva, clienteNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where DNI = '" + dniNuevo + "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			while (resul3.next()) {

				assertEquals(fechaNueva, resul3.getDate(fecha));
				assertEquals(horaNueva, resul3.getTime(hora));
				assertEquals(precioTotalNuevo, resul3.getFloat(precio));
				assertEquals(cantidadProductosNuevo, resul3.getInt(cantidadProducto));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul4 = sacaCliente.executeQuery(sql2);
			while (resul4.next()) {
				assertEquals(stockNuevoUpdateado[0], String.valueOf(resul4.getInt(stock)));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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
		int codMascotaNuevo = 900;
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

		Connection conexion;
		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			metodosgenerales.AnadirStockAnimal(mascota, gestionAnimal, empleadoNuevo);

			Connection conexions;
			conexions = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexions.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTIONANIMAL
					+ " order by CodGestionAnimal DESC limit 1 ;";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);
			while (resul2.next()) {
				
				assertEquals(fechaNueva, resul2.getDate(cFecha));
				assertEquals(stockMascotaNuevo, resul2.getInt(cCantidad));
				assertEquals(codMascotaNuevo, resul2.getInt(cCodMascota));
				assertEquals(dniEmpleado, resul2.getString(dni));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_MASCOTA + " order by CodMascota DESC limit 1 ;";
			ResultSet resul3 = sacaCliente.executeQuery(sql2);
			while (resul3.next()) {

				assertEquals(stockNuevoUpdateado[0].toString(), String.valueOf(resul3.getInt(stock)));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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

		int codAdopcionNuevo = 651651;
		float precioTotalNuevo = 416;
		Date fechaNuevo = Date.valueOf(fechaDate);
		String dniNuevo = "X7650931G";

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

		Connection conexion;
		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			metodosgenerales.EliminarStockAnimal(mascota, adopcion, clienteNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " order by CodAdopcion DESC limit 1 ;";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			while (resul3.next()) {

				assertEquals(fechaNuevo, resul3.getDate(cFecha));
				assertEquals(precioTotalNuevo, resul3.getFloat(cPrecioTotal));
				assertEquals(codMascotaNuevo, resul3.getInt(cCodMascota));
				assertEquals(dniNuevo, resul3.getString(cDNI));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql2 = "select * from " + ManagerAbstract.TABLE_MASCOTA + " where CodMascota = '"
					+ codMascotaNuevo + " ';";
			ResultSet resul4 = sacaCliente.executeQuery(sql2);
			while (resul4.next()) {
				assertEquals(stockNuevoUpdateado[0], String.valueOf(resul4.getInt(stock)));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul5 = conexion.createStatement();

			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul6 = conexion.createStatement();

			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
