package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.Producto;

public class MetodosProducto extends ManagerAbstract {

	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";

	public ArrayList<Producto> recogerProducto() throws SQLException {

		ArrayList<Producto> listaProducto = new ArrayList<Producto>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaProducto = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_PRODUCTOS;
		ResultSet resul = sacaProducto.executeQuery(sql);
		while (resul.next()) {
			Producto producto = new Producto();
			producto.setNombreProducto(resul.getString(nombreProducto));
			producto.setPrecio(resul.getFloat(precio));
			producto.setStock(resul.getInt(stock));
			producto.setCodProducto(resul.getInt(codProducto));
			listaProducto.add(producto);
		}
		return listaProducto;

	}

	public void insertarProducto(Producto productoNuevo) throws SQLException {

		String nombreProducto = "";
		float precio = 0;
		int stock = 0;
		int codProducto = 0;

		Producto producto = productoNuevo;

		nombreProducto = producto.getNombreProducto();
		precio = producto.getPrecio();
		stock = producto.getStock();
		codProducto = producto.getCodProducto();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
				+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProducto + "' , '" + nombreProducto
				+ "' , '" + stock + "' , '" + precio + "');");
	}

	public void eliminarProducto(int codProducto) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '" + codProducto + "';");

	}

	public void updateProducto(String[] nombreColumna, String[] UpdateColumna, Producto producto) throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE productos set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where CodProducto = " + "'" + producto.getCodProducto() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}
	
	public ArrayList<Producto> recogerProductoTienda(String valorUbicacion) throws SQLException {

		ArrayList<Producto> listaProducto = new ArrayList<Producto>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaProducto = conexion.createStatement();
		String sql = "select p.Nombre, p.Precio, p.Stock from " + ManagerAbstract.TABLE_PRODUCTOS + " p join " + ManagerAbstract.TABLE_ALMACEN +
				" a on p.CodProducto = a.CodProducto join " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " c on a.CodClinica = c.CodClinica " +
				" WHERE c.Ubicacion = '" + valorUbicacion + "';";
		ResultSet resul = sacaProducto.executeQuery(sql);
		while (resul.next()) {
			Producto producto = new Producto ();
			producto.setNombreProducto(resul.getString(nombreProducto));
			producto.setPrecio(resul.getFloat(precio));
			producto.setStock(resul.getInt(stock));
			listaProducto.add(producto);
		}
		return listaProducto;
	}
}
