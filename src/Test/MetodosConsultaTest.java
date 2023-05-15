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
	
	// --------------------VariablesNecesariasParaElTest--------------------//
	String fechaDate = "2023-02-13";
	String horaTime = "13:55:36";

	int codConsultaNuevo = 900;
	float precioNuevo = 51;
	Date fechaNueva = Date.valueOf(fechaDate);
	Time horaNueva = Time.valueOf(horaTime);
	
	String nombreAnimalNuevo = "Anuk";
	int idAnimalNuevo = 900;
	int edadNuevo = 2;
	String especieNuevo = "Perro";
	String sexoNuevo = "M";
	
	String nombreNuevo = "Ander";
	String apellidosNuevo = "Perex";
	String dniNuevo = "22761890D";
	String direccionNuevo = "Mi casa";
	String contrasenyaNuevo = "hola";
	
	String[] precioNuevoUpdatear = { "20" };
	float precioUpdatear = 86;
	String[] column = { "Precio" };
	float precioNuevoFloat = 20;
	
	@Test
	void recogerConsultaTest() {

		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");
			
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");
			
			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CONSULTA + "`"
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
			
			assertEquals(resultado, "Consulta [idConsulta=900, precio=51.0, animal=Pez [, nombreAnimal=Anuk, idAnimal=900, edad=2, especie=Perro, sexo=M, cliente=null], empleado=Empleado [antiguedad=6, salario=2400.0, especializacion=Admin, nombre=Ander, apellido=De la Iglesia Perex, dni=22761890D, direccion=Praza Mario, 6, 13º F, contrasenya=2], fecha=2023-02-13, hora=13:55:36]");
			
			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarConsultaTest() {


		Consulta consulta = new Consulta();

		consulta.setFecha(fechaNueva);
		consulta.setHora(horaNueva);
		consulta.setIdConsulta(codConsultaNuevo);
		consulta.setPrecio(precioNuevo);

		try {
			
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");
			
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");
			
			metodosConsulta.insertarConsulta(consulta, idAnimalNuevo, dniNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CONSULTA + " where CodConsulta = '" + codConsultaNuevo
					+ "';";
			ResultSet resu3 = sacaEmpleado.executeQuery(sql);
			while (resu3.next()) {
				assertEquals(codConsultaNuevo, resu3.getInt(codConsulta));
				assertEquals(precioNuevo, resu3.getFloat(precio));
				assertEquals(fechaNueva, resu3.getDate(fecha));
				assertEquals(horaNueva, resu3.getTime(hora));
				assertEquals(dniNuevo, resu3.getString(dniEmpleado));
				assertEquals(idAnimalNuevo, resu3.getInt(idAnimal));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarConsultaTest() {

		try {
			
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");
			
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");
			
			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CONSULTA + "`"
					+ "(`CodConsulta`, `Precio`, `Fecha`, `Hora`, `DNI`, `IdAnimal`) " + "VALUES( '" + codConsultaNuevo + "' , '"
					+ precioNuevo + "' , '" + fechaNueva + "' , '" + horaNueva + "' , '" + dniNuevo + "' , '" + idAnimalNuevo + "');");
			
			metodosConsulta.eliminarConsulta(codConsultaNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CONSULTA + " where CodConsulta = '" + codConsultaNuevo
					+ "';";
			ResultSet resul4 = sacaEmpleado.executeQuery(sql);
			assertEquals(resul4.isBeforeFirst(), false);
			
			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateConsultaTest() {

		Consulta consulta = new Consulta();

		consulta.setFecha(fechaNueva);
		consulta.setHora(horaNueva);
		consulta.setIdConsulta(codConsultaNuevo);
		consulta.setPrecio(86);
		
		try {
			
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");
			
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");
			
			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CONSULTA + "`"
					+ "(`CodConsulta`, `Precio`, `Fecha`, `Hora`, `DNI`, `IdAnimal`) " + "VALUES( '" + codConsultaNuevo + "' , '"
					+ precioNuevo + "' , '" + fechaNueva + "' , '" + horaNueva + "' , '" + dniNuevo + "' , '" + idAnimalNuevo + "');");
			
			metodosConsulta.updateConsulta(column, precioNuevoUpdatear, consulta);
			
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CONSULTA + " where CodConsulta = '" + codConsultaNuevo
					+ "';";
			ResultSet resul4 = sacaEmpleado.executeQuery(sql);
			while (resul4.next()) {
				
				assertEquals(precioNuevoFloat, resul4.getFloat(precio));
			}
			
			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
