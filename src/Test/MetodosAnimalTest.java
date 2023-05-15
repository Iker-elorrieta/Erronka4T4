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
import Controlador.MetodosAnimal;
import ModeloAnimal.Animal;
import ModeloAnimal.Perro;

class MetodosAnimalTest extends ManagerAbstract {

	String especie = "";
	final String perro = "Perro";
	final String gato = "Gato";
	final String loro = "Loro";
	final String pez = "Pez";
	// -------------------------------------------//
	final String idAnimal = "IdAnimal";
	final String nombreAnimal = "Nombre";
	final String edad = "Edad";
	final String especieTabla = "Especie";
	final String sexo = "Sexo";
	// -------------------------------------------//
	final String dniClienteTabla = "DNI";
	String dniCliente = "";
	// -------------------------------------------//
	MetodosAnimal metodosAnimal = new MetodosAnimal();
	
	// --------------------VariablesNecesariasParaElTest--------------------/
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
	
	String[] nombreNuevoAnimal = { "Anuk" };
	String column[] = { "Nombre" };
	String nombreAcambiar = "Paco";
	
	@Test
	void recogerAnimalTest() {

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
			


			ArrayList<Animal> listaAnimal = metodosAnimal.recogerAnimal();

			String resultado = "";

			for (Animal animal : listaAnimal) {
				if (animal.getIdAnimal() == idAnimalNuevo) {
					int pos = listaAnimal.indexOf(animal);
					resultado = listaAnimal.get(pos).toString();
				}
			}

			assertEquals(resultado,
					"Pez [, nombreAnimal=Anuk, idAnimal=900, edad=2, especie=Perro, sexo=M, cliente=null]");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarAnimalTest() {

		Perro perro = new Perro();
		perro.setNombreAnimal(nombreAnimalNuevo);
		perro.setEdad(edadNuevo);
		perro.setEspecie(especieNuevo);
		perro.setIdAnimal(idAnimalNuevo);
		perro.setSexo(sexoNuevo);

		try {
			
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");
			
			metodosAnimal.insertarAnimal(perro, dniNuevo);

			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL + " WHERE IdAnimal = '" + idAnimalNuevo
					+ " ';";
			ResultSet resu2 = sacaAnimal.executeQuery(sql);
			while (resu2.next()) {
				assertEquals(nombreAnimalNuevo, resu2.getString(nombreAnimal));
				assertEquals(idAnimalNuevo, resu2.getInt(idAnimal));
				assertEquals(edadNuevo, resu2.getInt(edad));
				assertEquals(especieNuevo.toString(), resu2.getString(especieTabla));
				assertEquals(sexoNuevo, resu2.getString(sexo));
			}

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarAnimalTest() {

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

			metodosAnimal.eliminarAnimal(idAnimalNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL + " where IdAnimal = '" + idAnimalNuevo + "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul3.isBeforeFirst(), false);
			
			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void UpdateAnimalTest() {

		Perro perroNuevo = new Perro();

		perroNuevo.setNombreAnimal("Paco");
		perroNuevo.setEdad(edadNuevo);
		perroNuevo.setEspecie(especieNuevo);
		perroNuevo.setIdAnimal(idAnimalNuevo);
		perroNuevo.setSexo(sexoNuevo);

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


			metodosAnimal.updateAnimal(column, nombreNuevoAnimal, perroNuevo);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL + " where IdAnimal = '" + idAnimalNuevo
					+ " ';";
			ResultSet resul3 = sacaCliente.executeQuery(sql);
			while (resul3.next()) {
				assertEquals(nombreNuevoAnimal[0], resul3.getString(nombreAnimal));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
