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

	@Test
	void recogerPedidoTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String dniNuevo = "45894650J";
		int codProductoNuevo = 3;

		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 15;

		ArrayList<Pedido> listaPedido;

		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PEDIDO + "`"
					+ "(`CodPedido`, `PrecioTotal`, `Fecha`, `Hora`, `CantidadProductos`, `CodProducto`, `DNI`) "
					+ "VALUES( '" + codPedidoNuevo + "' , '" + precioTotalNuevo + "' , '" + fechaNueva + "' , '" + horaNueva
					+ "' , '" + cantidadProductosNuevo + "' , '" + codProductoNuevo + "' , '" + dniNuevo + "');");
		
			listaPedido = metodosPedido.recogerPedido();
			
			String resultado = "";
			
			for (Pedido pedido : listaPedido) {
				if(pedido.getCodPedido() == codPedidoNuevo) {
					int pos = listaPedido.indexOf(pedido);
					resultado = listaPedido.get(pos).toString();
				}
			}
			
			assertEquals(resultado, "Pedido [productos=[Producto [nombreProducto=Pienso para gatos, precio=5.0, stock=3, codProducto=3]], fecha=2023-02-13, hora=13:55:36, cantidadProducto=15, codPedido=900, cliente=null, preciototal=16813.0]");
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PEDIDO + "`" + "WHERE pedidos.CodPedido = '"
					+ codPedidoNuevo + "';");
			
			
		}catch(

	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	@Test
	void insertarPedidoTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";
		
		String dniNuevo = "45894650J";
		int codProductoNuevo = 3;

		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 15;

		Pedido pedidoNueva = new Pedido();

		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setCodPedido(codPedidoNuevo);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);
		try {
			metodosPedido.insertarPedido(pedidoNueva, dniNuevo, codProductoNuevo, codPedidoNuevo);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '" + codPedidoNuevo
					+ "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);
			while (resul.next()) {
				assertEquals(codPedidoNuevo, resul.getInt(codPedido));
				assertEquals(fechaNueva, resul.getDate(fecha));
				assertEquals(precioTotalNuevo, resul.getFloat(preciototal));
				assertEquals(cantidadProductosNuevo, resul.getInt(cantidadProducto));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PEDIDO + "`" + "WHERE pedidos.CodPedido = '"
					+ codPedidoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarPedidoTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String dniNuevo = "45894650J";
		int codProductoNuevo = 3;

		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 15;

		

		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PEDIDO + "`"
					+ "(`CodPedido`, `PrecioTotal`, `Fecha`, `Hora`, `CantidadProductos`, `CodProducto`, `DNI`) "
					+ "VALUES( '" + codPedidoNuevo + "' , '" + precioTotalNuevo + "' , '" + fechaNueva + "' , '" + horaNueva
					+ "' , '" + cantidadProductosNuevo + "' , '" + codProductoNuevo + "' , '" + dniNuevo + "');");

			metodosPedido.eliminarPedido(codPedidoNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '" + codPedidoNuevo
					+ "';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);
			assertEquals(resul2.isBeforeFirst(), false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void UpdatePedidoTest() {

		String[] precioTotalNuevo = { "15" };
		float precioTotalUpdateado = 15;
		float precioTotalUpdatear = 1532;
		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String dniNuevo = "45894650J";
		int codProductoNuevo = 3;

		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 900;
		int cantidadProductosNuevo = 15;

		String column[] = { "PrecioTotal" };

		Pedido pedidoNuevo = new Pedido();

		pedidoNuevo.setCantidadProducto(cantidadProductosNuevo);
		pedidoNuevo.setCodPedido(codPedidoNuevo);
		pedidoNuevo.setPreciototal(1532);
		pedidoNuevo.setFecha(fechaNueva);
		pedidoNuevo.setHora(horaNueva);

		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PEDIDO + "`"
					+ "(`CodPedido`, `PrecioTotal`, `Fecha`, `Hora`, `CantidadProductos`, `CodProducto`, `DNI`) "
					+ "VALUES( '" + codPedidoNuevo + "' , '" + precioTotalUpdatear + "' , '" + fechaNueva + "' , '" + horaNueva
					+ "' , '" + cantidadProductosNuevo + "' , '" + codProductoNuevo + "' , '" + dniNuevo + "');");

			metodosPedido.updatePedido(column, precioTotalNuevo, pedidoNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '" + codPedidoNuevo
					+ " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(precioTotalUpdateado, resul2.getFloat(preciototal));
			}
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PEDIDO + "`" + "WHERE pedidos.CodPedido = '"
					+ codPedidoNuevo + "';");
			
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

}
