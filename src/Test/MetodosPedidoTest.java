package Test;

import static org.junit.jupiter.api.Assertions.*;

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
	final String hora = "Hora";
	final String cantidadProducto = "CantidadProductos";
	final String codPedido = "CodPedido";
	final String preciototal = "PrecioTotal";
	// -------------------------------------------//
	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";
	
	@Test
	void recogerPedidoTest() throws SQLException {
		
		ArrayList<Pedido> listaPedido = metodosPedido.recogerPedido();
		String resultado = listaPedido.get(0).toString();
		System.out.println(resultado);
		assertEquals(resultado,"Pedido [productos=[Producto [nombreProducto=Pienso para perros, precio=5.0, stock=15, codProducto=2], Producto [nombreProducto=Pienso para perros, precio=5.0, stock=15, codProducto=2], Producto [nombreProducto=Pienso para perros, precio=5.0, stock=15, codProducto=2], Producto [nombreProducto=Pienso para perros, precio=5.0, stock=15, codProducto=2]], fecha=2023-04-26, hora=08:38:42, cantidadProducto=2, codPedido=1, cliente=null, preciototal=10.0]");
	}
	
	@Test
	void insertarPedidoTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String dniNuevo = "45894650J";
		int codProductoNuevo = 2;


		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 2;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 15;

		Pedido pedidoNueva = new Pedido();

		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setCodPedido(codPedidoNuevo);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);

		try {
			metodosPedido.insertarPedido(pedidoNueva, dniNuevo, codProductoNuevo);;

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '"
					+ codPedidoNuevo + "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);
			while (resul.next()) {
				assertEquals(codPedidoNuevo, resul.getInt(codPedido));
				assertEquals(fechaNueva, resul.getDate(fecha));
				assertEquals(horaNueva, resul.getTime(hora));
				assertEquals(precioTotalNuevo, resul.getFloat(preciototal));
				assertEquals(cantidadProductosNuevo, resul.getInt(cantidadProducto));
			}

			ArrayList<Pedido> listaPedido = metodosPedido.recogerPedido();
			metodosPedido.eliminarPedido(listaPedido, codPedidoNuevo);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void eliminarPedidoTest()  {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String dniNuevo = "45894650J";
		int codProductoNuevo = 2;


		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int codPedidoNuevo = 2;
		float precioTotalNuevo = 16813;
		int cantidadProductosNuevo = 15;

		Pedido pedidoNueva = new Pedido();

		pedidoNueva.setCantidadProducto(cantidadProductosNuevo);
		pedidoNueva.setCodPedido(codPedidoNuevo);
		pedidoNueva.setPreciototal(precioTotalNuevo);
		pedidoNueva.setFecha(fechaNueva);
		pedidoNueva.setHora(horaNueva);

		try {
			metodosPedido.insertarPedido(pedidoNueva, dniNuevo, codProductoNuevo);
			
			ArrayList<Pedido> listaPedido = metodosPedido.recogerPedido();
			metodosPedido.eliminarPedido(listaPedido, codPedidoNuevo);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO + " where CodPedido = '"
					+ codPedidoNuevo + "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);

			assertEquals(resul.isBeforeFirst(), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}
}
