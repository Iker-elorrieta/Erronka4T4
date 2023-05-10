package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosProducto;
import Modelo.Producto;

class MetodosProductoTest extends ManagerAbstract {

	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";

	MetodosProducto metodosProducto = new MetodosProducto();

	@Test
	void RecogerProductoTest() {
		
		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 18;
		
		ArrayList<Producto> listaProducto;
		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) "
					+ "VALUES( '" + codProductoNuevo + "' , '"+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");
			
			listaProducto = metodosProducto.recogerProducto();
			
			String resultado = "";
			
			for (Producto producto : listaProducto) {
				if(producto.getCodProducto() == codProductoNuevo) {
					int pos = listaProducto.indexOf(producto);
					resultado = listaProducto.get(pos).toString();
				}
			}
			
			assertEquals(resultado, "Producto [nombreProducto=Bolsas, precio=18.0, stock=16, codProducto=900]");
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '" + codProductoNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	void insertarProductoTest() {

		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 18;

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(stockNuevo);

		try {
			metodosProducto.insertarProducto(producto);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul = sacaCliente.executeQuery(sql);
			while (resul.next()) {

				assertEquals(codProductoNuevo, resul.getInt(codProducto));
				assertEquals(nombreNuevo, resul.getString(nombreProducto));
				assertEquals(stockNuevo, resul.getInt(stock));
				assertEquals(precioNuevo, resul.getFloat(precio));

			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '" + codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarProductoTest() {

		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 18;

		try {
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) "
					+ "VALUES( '" + codProductoNuevo + "' , '"+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			metodosProducto.eliminarProducto(codProductoNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);

			assertEquals(resul2.isBeforeFirst(), false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void UpdateProductoTest() {

		String[] stockNuevo = { "4" };
		int stockNuevoUpdateado = 4;
		int stockUpdatear = 0;
		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		float precioNuevo = 18;
		String[] column = { "Stock" };

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(0);

		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) "
					+ "VALUES( '" + codProductoNuevo + "' , '"+ nombreNuevo + "' , '" + stockUpdatear + "' , '" + precioNuevo + "');");
			
			metodosProducto.updateProducto(column, stockNuevo, producto);
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while(resul2.next()) {
				assertEquals(stockNuevoUpdateado, resul2.getInt(stock));
			}
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '" + codProductoNuevo + "';");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
