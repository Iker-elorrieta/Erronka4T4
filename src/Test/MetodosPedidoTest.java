package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
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

import org.junit.jupiter.api.Test;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosPedido;
import Modelo.Pedido;

class MetodosPedidoTest extends ManagerAbstract {

	MetodosPedido metodosPedido = new MetodosPedido();
	// -------------------------------------------//
	final String productos = "CodProducto";
	final String fecha = "Fecha";
	final String cantidadProducto = "CantidadProductos";
	final String codPedido = "CodPedido";
	final String preciototal = "PrecioTotal";

	// --------------------VariablesNecesariasParaElTest--------------------/
	String fechaDate = "2023-02-13";
	String horaTime = "13:55:36";
	Date fechaNueva = Date.valueOf(fechaDate);
	Time horaNueva = Time.valueOf(horaTime);
	int codPedidoNuevo = 900;
	float precioTotalNuevo = 16813;
	int cantidadProductosNuevo = 15;
	String[] precioTotalNuevoUpdate = { "15" };
	float precioTotalUpdateado = 15;
	float precioTotalUpdatear = 1532;
	String column[] = { "PrecioTotal" };

	String nombreNuevo = "Ander";
	String apellidosNuevo = "Perex";
	String dniNuevo = "22761890D";
	String direccionNuevo = "Mi casa";
	String contrasenyaNuevo = "hola";

	int codProductoNuevo = 900;
	String nombreNuevoProducto = "Bolsas";
	int stockNuevo = 16;
	float precioNuevo = 18;

	@Test
	void recogerPedidoTest() {

		ArrayList<Pedido> listaPedido;

		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

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

			listaPedido = metodosPedido.recogerPedido();

			String resultado = "";

			for (Pedido pedido : listaPedido) {
				if (pedido.getCodPedido() == codPedidoNuevo) {
					int pos = listaPedido.indexOf(pedido);
					resultado = listaPedido.get(pos).toString();
				}
			}

			assertEquals(resultado,
					"Pedido [productos=[Producto [nombreProducto=Ander, precio=18.0, stock=16, codProducto=900]], fecha=2023-02-13, hora=13:55:36, cantidadProducto=15, codPedido=900, cliente=null, preciototal=16813.0]");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarPedidoTest() {

		Pedido pedidoNueva = new Pedido();

		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setCodPedido(codPedidoNuevo);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);
		try {

			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			metodosPedido.insertarPedido(pedidoNueva, dniNuevo, codProductoNuevo, codPedidoNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '" + codPedidoNuevo
					+ "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			while (resul3.next()) {
				assertEquals(codPedidoNuevo, resul3.getInt(codPedido));
				assertEquals(fechaNueva, resul3.getDate(fecha));
				assertEquals(precioTotalNuevo, resul3.getFloat(preciototal));
				assertEquals(cantidadProductosNuevo, resul3.getInt(cantidadProducto));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarPedidoTest() {

		try {

			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

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

			metodosPedido.eliminarPedido(codPedidoNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '" + codPedidoNuevo
					+ "';";
			ResultSet resul4 = sacaEmpleado.executeQuery(sql);
			assertEquals(resul4.isBeforeFirst(), false);
			
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
	void UpdatePedidoTest() {

		Pedido pedidoNuevo = new Pedido();

		pedidoNuevo.setCantidadProducto(cantidadProductosNuevo);
		pedidoNuevo.setCodPedido(codPedidoNuevo);
		pedidoNuevo.setPreciototal(1532);
		pedidoNuevo.setFecha(fechaNueva);
		pedidoNuevo.setHora(horaNueva);

		try {

			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

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

			metodosPedido.updatePedido(column, precioTotalNuevoUpdate, pedidoNuevo);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '" + codPedidoNuevo
					+ " ';";
			ResultSet resul4 = sacaCliente.executeQuery(sql);
			while (resul4.next()) {
				assertEquals(precioTotalUpdateado, resul4.getFloat(preciototal));
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
	public void testArrayListTxt() {
		ArrayList<Pedido> listaPedido = new ArrayList<>();

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

}
