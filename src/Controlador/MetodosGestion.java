package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.Gestion;
import Modelo.Producto;
import ModeloPerfil.Empleado;

public class MetodosGestion extends ManagerAbstract {

	final String codGestion = "CodGestion";
	final String cantidad = "Cantidad";
	final String fecha = "Fecha";
	final String empleado = "Empleado";
	final String productos = "Productos";
	// -------------------------------------------//
	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";

	public ArrayList<Gestion> recogerGestion() throws SQLException {

		ArrayList<Gestion> listaGestion = new ArrayList<Gestion>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaGestion = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_GESTION;
		ResultSet resul = sacaGestion.executeQuery(sql);
		while (resul.next()) {

			Gestion gestion = new Gestion();
			gestion.setCodGestion(resul.getInt(codGestion));
			gestion.setFecha(resul.getDate(fecha));
			gestion.setCantidad(resul.getInt(cantidad));

			String dniEmpleado = resul.getString(5);

			MetodosEmpleado metodosEmpleado = new MetodosEmpleado();
			ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();

			for (Empleado empleado2 : listaEmpleado) {
				if (dniEmpleado.equalsIgnoreCase(empleado2.getDni())) {
					gestion.setEmpleado(empleado2);
				}
			}

			ArrayList<Producto> listaProducto = new ArrayList<Producto>();
			Statement sacaProducto = conexion.createStatement();
			String sql2 = "select distinct * from " + ManagerAbstract.TABLE_PRODUCTOS + " join "
					+ ManagerAbstract.TABLE_GESTION
					+ " on productos.CodProducto = gestion.CodProducto where gestion.CodProducto = '"
					+ resul.getInt(codProducto) + "'";
			ResultSet resul2 = sacaProducto.executeQuery(sql2);
			while (resul2.next()) {
				Producto producto = new Producto();
				producto.setNombreProducto(resul2.getString(nombreProducto));
				producto.setPrecio(resul2.getFloat(precio));
				producto.setStock(resul2.getInt(stock));
				producto.setCodProducto(resul2.getInt(codProducto));
				listaProducto.add(producto);

				gestion.setProductos(listaProducto);
			}

			listaGestion.add(gestion);

		}
		return listaGestion;
	}

	public void insertarGestion(Gestion gestionNueva, String dni, int codProducto) throws SQLException {

		MetodosProducto metodosProducto = new MetodosProducto();

		int codGestion = 0;
		Date fecha = null;

		int cantidad = 0;

		Gestion gestion = gestionNueva;

		codGestion = gestion.getCodGestion();
		fecha = (Date) gestion.getFecha();
		cantidad = gestion.getCantidad();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		ArrayList<Producto> listaproducto = metodosProducto.recogerProducto();

		for (Producto producto : listaproducto) {
			if (producto.getCodProducto() == codProducto) {
				resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
						+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestion
						+ "' , '" + fecha + "' , '" + cantidad + "' , '" + codProducto + "' , '" + dni + "');");
			}
		}
	}

	public void eliminarGestion(int codGestion) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_GESTION + "`" + "WHERE gestion.CodGestion = '"
				+ codGestion + "';");

	}

	public void updateGestion(String[] nombreColumna, String[] UpdateColumna, Gestion gestion) throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE gestion set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where CodGestion = " + "'" + gestion.getCodGestion() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}
}
