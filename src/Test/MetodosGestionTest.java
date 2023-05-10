package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosGestion;
import Modelo.Gestion;

class MetodosGestionTest extends ManagerAbstract {

	MetodosGestion metodosGestion = new MetodosGestion();
	// -------------------------------------------//
	final String codGestion = "CodGestion";
	final String cantidad = "Cantidad";
	final String fecha = "Fecha";
	final String hora = "Hora";
	final String empleado = "Empleado";
	final String productos = "Productos";
	// -------------------------------------------//
	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";
	// -------------------------------------------//
	final String dni = "DNI";

	@Test
	void recogerGestionTest() {

		String fechaDate = "2023-02-13";

		String dniNuevo = "13240462Y";
		int codProductoNuevo = 2;
		int codGestionNueva = 900;
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNueva = 5;

		ArrayList<Gestion> listaGestion;
		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadNueva + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");

			listaGestion = metodosGestion.recogerGestion();

			String resultado = "";

			for (Gestion gestion : listaGestion) {
				if (gestion.getCodGestion() == codGestionNueva) {
					int pos = listaGestion.indexOf(gestion);
					resultado = listaGestion.get(pos).toString();
				}
			}
			assertEquals(resultado, "Gestion [codGestion=900, cantidad=5, fecha=2023-02-13, empleado=Empleado [antiguedad=2, salario=1500.0, especializacion=Ventas, nombre=Francisco-Jose , apellido=Campo, dni=13240462Y, direccion=Praza Girón, 3, 4º, contrasenya=21], productos=[Producto [nombreProducto=Pienso para perros, precio=5.0, stock=15, codProducto=2], Producto [nombreProducto=Pienso para perros, precio=5.0, stock=15, codProducto=2]]]");			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_GESTION + "`" + "WHERE gestion.CodGestion = '"
					+ codGestionNueva + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarGestionTest() {

		String fechaDate = "2023-02-13";

		String dniNuevo = "13240462Y";
		int codProductoNuevo = 2;

		int codGestionNueva = 900;
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNueva = 5;

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(cantidadNueva);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);

		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadNueva + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");
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
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_GESTION + "`" + "WHERE gestion.CodGestion = '"
					+ codGestionNueva + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarGestionTest() {

		String fechaDate = "2023-02-13";

		String dniNuevo = "13240462Y";
		int codProductoNuevo = 2;

		int codGestionNueva = 900;
		Date fechaNueva = Date.valueOf(fechaDate);
		int cantidadNueva = 5;

		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadNueva + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");

			metodosGestion.eliminarGestion(codGestionNueva);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where gestion.CodGestion = '"
					+ codGestionNueva + "';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul2.isBeforeFirst(), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateGestionTest() {

		String[] cantidadNueva = { "15" };
		int cantidadUpdateada = 15;
		int cantidadUpdatear = 2654;

		String fechaDate = "2023-02-13";

		String dniNuevo = "13240462Y";
		int codProductoNuevo = 2;

		int codGestionNueva = 900;
		Date fechaNueva = Date.valueOf(fechaDate);

		String column[] = { "Cantidad" };

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(2654);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);

		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadUpdatear + "' , '" + codProductoNuevo + "' , '"
					+ dniNuevo + "');");

			metodosGestion.updateGestion(column, cantidadNueva, gestionNueva);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where CodGestion = '" + codGestionNueva
					+ " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(cantidadUpdateada, resul2.getInt(cantidad));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_GESTION + "`" + "WHERE gestion.CodGestion = '"
					+ codGestionNueva + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
