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
import Controlador.MetodosConsulta;
import Modelo.Consulta;

class MetodosConsultaTest extends ManagerAbstract {

	MetodosConsulta metodosConsulta = new MetodosConsulta();

	final String codConsulta = "CodConsulta";
	final String precio = "Precio";
	final String idAnimal = "IdAnimal";
	final String dniEmpleado = "DNI";
	final String fecha = "Fecha";
	final String hora = "Hora";

	@Test
	void recogerConsultaTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		int codConsultaNuevo = 900;
		float precioNuevo = 51;
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		String dniNuevo = "61556765W";
		int idAnimalNuevo = 2;
		
		try {
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CONSULTA + "`"
					+ "(`CodConsulta`, `Precio`, `Fecha`, `Hora`, `DNI`, `IdAnimal`) " + "VALUES( '" + codConsultaNuevo + "' , '"
					+ precioNuevo + "' , '" + fechaNueva + "' , '" + horaNueva + "' , '" + dniNuevo + "' , '" + idAnimalNuevo + "');");
			
			ArrayList<Consulta> listaConsultas;
			listaConsultas = metodosConsulta.recogerConsulta();

			String resultado = "";

			for (Consulta consulta2 : listaConsultas) {
				if (consulta2.getIdConsulta() == codConsultaNuevo) {
					int pos = listaConsultas.indexOf(consulta2);
					resultado = listaConsultas.get(pos).toString();
				}
			}
			
			assertEquals(resultado, "Consulta [idConsulta=900, precio=51.0, animal=Gato [ nombreAnimal=Garfield, idAnimal=2, edad=12, especie=Gatos, sexo=H, cliente=null], empleado=Empleado [antiguedad=5, salario=1300.0, especializacion=Limpieza, nombre=Sandra Maria , apellido=Alcaide, dni=61556765W, direccion=Carrer Laura, 373, Bajos, contrasenya=13], fecha=2023-02-13, hora=13:55:36]");
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CONSULTA + "`" + "WHERE CodConsulta = '" + codConsultaNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarConsultaTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		int codConsultaNuevo = 900;
		float precioNuevo = 51;
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		String dniNuevo = "61556765W";
		int idAnimalNuevo = 2;

		Consulta consulta = new Consulta();

		consulta.setFecha(fechaNueva);
		consulta.setHora(horaNueva);
		consulta.setIdConsulta(codConsultaNuevo);
		consulta.setPrecio(precioNuevo);

		try {
			
			metodosConsulta.insertarConsulta(consulta, idAnimalNuevo, dniNuevo);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CONSULTA + " where CodConsulta = '" + codConsultaNuevo
					+ "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);
			while (resul.next()) {
				assertEquals(codConsultaNuevo, resul.getInt(codConsulta));
				assertEquals(precioNuevo, resul.getFloat(precio));
				assertEquals(fechaNueva, resul.getDate(fecha));
				assertEquals(horaNueva, resul.getTime(hora));
				assertEquals(dniNuevo, resul.getString(dniEmpleado));
				assertEquals(idAnimalNuevo, resul.getInt(idAnimal));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CONSULTA + "`" + "WHERE CodConsulta = '" + codConsultaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarConsultaTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		int codConsultaNuevo = 900;
		float precioNuevo = 51;
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		String dniNuevo = "61556765W";
		int idAnimalNuevo = 2;

		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CONSULTA + "`"
					+ "(`CodConsulta`, `Precio`, `Fecha`, `Hora`, `DNI`, `IdAnimal`) " + "VALUES( '" + codConsultaNuevo + "' , '"
					+ precioNuevo + "' , '" + fechaNueva + "' , '" + horaNueva + "' , '" + dniNuevo + "' , '" + idAnimalNuevo + "');");
			
			metodosConsulta.eliminarConsulta(codConsultaNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CONSULTA + " where CodConsulta = '" + codConsultaNuevo
					+ "';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);
			assertEquals(resul2.isBeforeFirst(), false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateConsultaTest() {

		String fechaDate = "2023-02-13";
		String horaTime = "13:55:36";

		String[] precioNuevo = { "20" };
		float precioUpdatear = 86;
		float precioNuevoFloat = 20;
		int codConsultaNuevo = 900;
		Date fechaNueva = Date.valueOf(fechaDate);
		Time horaNueva = Time.valueOf(horaTime);
		String dniNuevo = "61556765W";
		int idAnimalNuevo = 2;
		String[] column = { "Precio" };

		Consulta consulta = new Consulta();

		consulta.setFecha(fechaNueva);
		consulta.setHora(horaNueva);
		consulta.setIdConsulta(codConsultaNuevo);
		consulta.setPrecio(86);
		
		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CONSULTA + "`"
					+ "(`CodConsulta`, `Precio`, `Fecha`, `Hora`, `DNI`, `IdAnimal`) " + "VALUES( '" + codConsultaNuevo + "' , '"
					+ precioUpdatear + "' , '" + fechaNueva + "' , '" + horaNueva + "' , '" + dniNuevo + "' , '" + idAnimalNuevo + "');");
			
			metodosConsulta.updateConsulta(column, precioNuevo, consulta);
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CONSULTA + " where CodConsulta = '" + codConsultaNuevo
					+ "';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);
			while (resul2.next()) {
				
				assertEquals(precioNuevoFloat, resul2.getFloat(precio));
			}
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CONSULTA + "`" + "WHERE CodConsulta = '" + codConsultaNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
