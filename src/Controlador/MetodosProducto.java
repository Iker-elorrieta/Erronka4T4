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

public class MetodosProducto extends ManagerAbstract{
	
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
			Producto producto = new Producto ();
			producto.setNombreProducto(resul.getString(nombreProducto));
			producto.setPrecio(resul.getFloat(precio));
			producto.setStock(resul.getInt(stock));
			producto.setCodProducto(resul.getInt(codProducto));
			listaProducto.add(producto);
		}
		return listaProducto;

	}
}
