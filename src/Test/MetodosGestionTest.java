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
		ArrayList<Gestion> listaGestion;
		try {
			listaGestion = metodosGestion.recogerGestion();
			String resultado = listaGestion.get(0).toString();
			System.out.println(resultado);
			assertEquals(resultado,
					"Gestion [idGestion=1, cantidad=3, fecha=2023-04-24, hora=02:55:36, empleado=Empleado [antiguedad=7, salario=2000.0, especializacion=ventas, nombre=Gorka, apellido=De la Iglesia Perex, dni=22761891X, direccion=Carrer Marcos, 0, 6º A, contrasenya=3], productos=[Producto [nombreProducto=Correa, precio=2.0, stock=34, codProducto=1]]]");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarGestionTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String dniNuevo = "13240462Y";
		int codProductoNuevo = 2;

		int codGestionNueva = 2;
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int cantidadNueva = 5;

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(cantidadNueva);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);
		gestionNueva.setHora(horaNueva);

		try {
			metodosGestion.insertarGestion(gestionNueva, dniNuevo, codProductoNuevo);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where gestion.CodGestion = '"
					+ codGestionNueva + "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);
			while (resul.next()) {
				assertEquals(codGestionNueva, resul.getInt(codGestion));
				assertEquals(fechaNueva, resul.getDate(fecha));
				assertEquals(horaNueva, resul.getTime(hora));
				assertEquals(cantidadNueva, resul.getInt(cantidad));
				assertEquals(codProductoNuevo, resul.getInt(codProducto));
				assertEquals(dniNuevo, resul.getString(dni));
			}

			ArrayList<Gestion> listaGestion = metodosGestion.recogerGestion();
			metodosGestion.eliminarGestion(listaGestion, codGestionNueva);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarGestionTest()  {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String dniNuevo = "13240462Y";
		int codProductoNuevo = 2;

		int codGestionNueva = 2;
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		int cantidadNueva = 5;

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(cantidadNueva);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);
		gestionNueva.setHora(horaNueva);

		try {
			metodosGestion.insertarGestion(gestionNueva, dniNuevo, codProductoNuevo);
			
			ArrayList<Gestion> listaGestion = metodosGestion.recogerGestion();
			metodosGestion.eliminarGestion(listaGestion, codGestionNueva);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where gestion.CodGestion = '"
					+ codGestionNueva + "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);

			assertEquals(resul.isBeforeFirst(), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}

}
