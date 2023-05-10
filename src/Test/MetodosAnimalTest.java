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

	@Test
	void recogerAnimalTest() {

		String nombreAnimalNuevo = "Anuk";
		int idAnimalNuevo = 900;
		int edadNuevo = 2;
		String especieNuevo = "Perro";
		String sexoNuevo = "M";

		String dniNuevo = "45894650J";

		Connection conexion;
		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
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
			
			assertEquals(resultado, "Pez [, nombreAnimal=Anuk, idAnimal=900, edad=2, especie=Perro, sexo=M, cliente=null]");
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_ANIMAL + "`" + "WHERE animal.IdAnimal = '"
					+ idAnimalNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarAnimalTest() {
		String nombreAnimalNuevo = "Anuk";
		int idAnimalNuevo = 900;
		int edadNuevo = 2;
		String especieNuevo = "Perro";
		String sexoNuevo = "M";

		String dniNuevo = "45894650J";

		Perro perro = new Perro();
		perro.setNombreAnimal(nombreAnimalNuevo);
		perro.setEdad(edadNuevo);
		perro.setEspecie(especieNuevo);
		perro.setIdAnimal(idAnimalNuevo);
		perro.setSexo(sexoNuevo);

		try {
			metodosAnimal.insertarAnimal(perro, dniNuevo);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL + " WHERE IdAnimal = '" + idAnimalNuevo
					+ " ';";
			ResultSet resul = sacaAnimal.executeQuery(sql);
			while (resul.next()) {
				assertEquals(nombreAnimalNuevo, resul.getString(nombreAnimal));
				assertEquals(idAnimalNuevo, resul.getInt(idAnimal));
				assertEquals(edadNuevo, resul.getInt(edad));
				assertEquals(especieNuevo.toString(), resul.getString(especieTabla));
				assertEquals(sexoNuevo, resul.getString(sexo));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_ANIMAL + "`" + "WHERE animal.IdAnimal = '"
					+ idAnimalNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarAnimalTest() {
		String nombreAnimalNuevo = "Anuk";
		int idAnimalNuevo = 900;
		int edadNuevo = 2;
		String especieNuevo = "Perro";
		String sexoNuevo = "M";

		String dniNuevo = "45894650J";

		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAnimalNuevo + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");

			metodosAnimal.eliminarAnimal(idAnimalNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL + " where IdAnimal = '" + idAnimalNuevo + "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul3.isBeforeFirst(), false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void UpdateAnimalTest() {

		String[] nombreNuevo = { "Anuk" };
		String nombreAcambiar = "Paco";
		int idAnimalNuevo = 900;
		int edadNuevo = 2;
		String especieNuevo = "Perro";
		String sexoNuevo = "M";
		String dniNuevo = "45894650J";
		String column[] = { "Nombre" };

		Perro perroNuevo = new Perro();

		perroNuevo.setNombreAnimal("Paco");
		perroNuevo.setEdad(edadNuevo);
		perroNuevo.setEspecie(especieNuevo);
		perroNuevo.setIdAnimal(idAnimalNuevo);
		perroNuevo.setSexo(sexoNuevo);

		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
					+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimalNuevo
					+ "' , '" + nombreAcambiar + "' , '" + edadNuevo + "' , '" + especieNuevo + "' , '" + sexoNuevo
					+ "' , '" + dniNuevo + "');");

			metodosAnimal.updateAnimal(column, nombreNuevo, perroNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL + " where IdAnimal = '" + idAnimalNuevo
					+ " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(nombreNuevo[0], resul2.getString(nombreAnimal));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_ANIMAL + "`" + "WHERE animal.IdAnimal = '"
					+ idAnimalNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
