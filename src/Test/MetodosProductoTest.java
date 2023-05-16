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
import Modelo.ObjetosComprables;
import Modelo.Producto;

class MetodosProductoTest extends ManagerAbstract {

	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";

	MetodosProducto metodosProducto = new MetodosProducto();

	// --------------------VariablesNecesariasParaElTest--------------------/
	int codProductoNuevo = 900;
	String nombreNuevo = "Bolsas";
	int stockNuevo = 16;
	float precioNuevo = 18;

	String[] stockNuevoUpdate = { "4" };
	int stockNuevoUpdateado = 4;
	int stockUpdatear = 0;
	String[] column = { "Stock" };

	Connection conexion;

	@Test
	void RecogerProductoTest() {

		ArrayList<Producto> listaProducto;
		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			listaProducto = metodosProducto.recogerProducto();

			String resultado = "";

			for (Producto producto : listaProducto) {
				if (producto.getCodProducto() == codProductoNuevo) {
					int pos = listaProducto.indexOf(producto);
					resultado = listaProducto.get(pos).toString();
				}
			}

			assertEquals(resultado, "Producto [nombreProducto=Bolsas, precio=18.0, stock=16, codProducto=900]");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void insertarProductoTest() {

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(stockNuevo);

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			metodosProducto.insertarProducto(producto);

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

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarProductoTest() {

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			metodosProducto.eliminarProducto(codProductoNuevo);

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

		Producto producto = new Producto();

		producto.setCodProducto(codProductoNuevo);
		producto.setNombreProducto(nombreNuevo);
		producto.setPrecio(precioNuevo);
		producto.setStock(0);

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevo + "' , '" + stockUpdatear + "' , '" + precioNuevo + "');");

			metodosProducto.updateProducto(column, stockNuevoUpdate, producto);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_PRODUCTOS + " where CodProducto = '"
					+ codProductoNuevo + " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(stockNuevoUpdateado, resul2.getInt(stock));
			}

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void recogerProductoTiendaTest() {

		int codProductoNuevo = 900;
		String nombreNuevo = "Bolsas";
		int stockNuevo = 16;
		float precioNuevo = 18;

		int codClinicaNueva = 900;
		String ubicacionNueva = "Murcia";

		ArrayList<ObjetosComprables> listaProducto;

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

			listaProducto = metodosProducto.recogerProductoTienda(ubicacionNueva);

			String resultado = "";

			for (ObjetosComprables objetosComprables : listaProducto) {
				resultado = objetosComprables.toString();
			}
			assertEquals(resultado, "Producto [nombreProducto=Bolsas, precio=18.0, stock=16, codProducto=900]");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
