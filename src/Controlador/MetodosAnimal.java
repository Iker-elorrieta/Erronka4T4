package Controlador;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import ModeloAnimal.Animal;
import ModeloAnimal.Gato;
import ModeloAnimal.Loro;
import ModeloAnimal.Perro;
import ModeloAnimal.Pez;

public class MetodosAnimal extends ManagerAbstract {

	String especie = "";
	final String perro = "Perro";
	final String gato = "Gato";
	final String loro = "Loro";
	final String pez = "Pez";
	// -------------------------------------------//
	final String idAnimal = "IdAnimal";
	final String nombreAnimal = "Nombre";
	final String edad = "Edad";
	final String especieTabla = "especie";
	final String sexo = "Sexo";
	// -------------------------------------------//
	final String dniClienteTabla = "DNI";
	String dniCliente = "";

	public ArrayList<Animal> recogerAnimal() throws SQLException {
		ArrayList<Animal> listaAnimales = new ArrayList<Animal>();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaAnimal = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_ANIMAL;
		ResultSet resul = sacaAnimal.executeQuery(sql);
		while (resul.next()) {
			especie = resul.getString(especieTabla);

			if (especie.equalsIgnoreCase(perro)) {
				Perro perro = new Perro();
				perro.setIdAnimal(resul.getInt(idAnimal));
				perro.setNombreAnimal(resul.getString(nombreAnimal));
				perro.setEdad(resul.getInt(edad));
				perro.setEspecie(especie);
				perro.setSexo(resul.getString(sexo));
				listaAnimales.add(perro);

			} else if (especie.equalsIgnoreCase(gato)) {
				Gato gato = new Gato();
				gato.setIdAnimal(resul.getInt(idAnimal));
				gato.setNombreAnimal(resul.getString(nombreAnimal));
				gato.setEdad(resul.getInt(edad));
				gato.setEspecie(especie);
				gato.setSexo(resul.getString(sexo));
				listaAnimales.add(gato);

			} else if (especie.equalsIgnoreCase(loro)) {
				Loro loro = new Loro();
				loro.setIdAnimal(resul.getInt(idAnimal));
				loro.setNombreAnimal(resul.getString(nombreAnimal));
				loro.setEdad(resul.getInt(edad));
				loro.setEspecie(especie);
				loro.setSexo(resul.getString(sexo));
				listaAnimales.add(loro);
			} else {
				Pez pez = new Pez();
				pez.setIdAnimal(resul.getInt(idAnimal));
				pez.setNombreAnimal(resul.getString(nombreAnimal));
				pez.setEdad(resul.getInt(edad));
				pez.setEspecie(especie);
				pez.setSexo(resul.getString(sexo));
				listaAnimales.add(pez);
			}
		}
		return listaAnimales;
	}

	public void insertarAnimal(Animal animalNuevo, String dni) throws SQLException {
		
		String nombreAnimal = "a";
		int idAnimal = 0;
		int edad = 0;
		String especie = "a";
		String sexo = "a";
		
		Animal animal = animalNuevo;
		nombreAnimal = animal.getNombreAnimal();
		idAnimal = animal.getIdAnimal();
		edad = animal.getEdad();
		especie = animal.getEspecie();
		sexo = animal.getSexo();
		
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL+ "`"
				+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimal
				+ "' , '" + nombreAnimal + "' , '" + edad + "' , '" + especie + "' , '" + sexo + "' , '"
				+ dni + "');");

	}
	
	public void eliminarAnimal(ArrayList<Animal> listaAnimal, int idAnimal) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		ArrayList<Animal> listaAnimalEliminar = listaAnimal;

		for (Animal animal : listaAnimalEliminar) {
			if (animal.getIdAnimal() == (idAnimal)) {
				resul.executeUpdate(
						"DELETE FROM `" + ManagerAbstract.TABLE_ANIMAL + "`" + "WHERE animal.IdAnimal = '" + idAnimal + "';");
			}
		}
	}
	
	public void updateAnimal(String[] nombreColumna, String[] UpdateColumna, Animal animal) throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE animal set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where IdAnimal = " + "'" + animal.getIdAnimal() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}
}
