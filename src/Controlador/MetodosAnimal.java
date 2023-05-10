package Controlador;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import ModeloAnimal.Animal;
import ModeloAnimal.Gato;
import ModeloAnimal.Loro;
import ModeloAnimal.Perro;
import ModeloAnimal.Pez;
import ModeloPerfil.Cliente;

public class MetodosAnimal extends ManagerAbstract {

	String especie = "";
	final String perro = "Perros";
	final String gato = "Gatos";
	final String loro = "Loros";
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

		String nombreAnimal = "";
		int idAnimal = 0;
		int edad = 0;
		String especie = "";
		String sexo = "";

		Animal animal = animalNuevo;
		nombreAnimal = animal.getNombreAnimal();
		idAnimal = animal.getIdAnimal();
		edad = animal.getEdad();
		especie = animal.getEspecie();
		sexo = animal.getSexo();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ANIMAL + "`"
				+ "(`IdAnimal`, `Nombre`, `Edad`, `Especie`, `Sexo`, `DNI`) " + "VALUES( '" + idAnimal + "' , '"
				+ nombreAnimal + "' , '" + edad + "' , '" + especie + "' , '" + sexo + "' , '" + dni + "');");

	}

	public void eliminarAnimal(int idAnimal) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_ANIMAL + "`" + "WHERE animal.IdAnimal = '" + idAnimal + "';");

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
	
	public JTable generarTablaClienteAnimal(Cliente cliente, ArrayList<Cliente> cs) throws SQLException {
		// TODO Auto-generated method stub
		String[] titulos = null;
		Object[][] txt = null;
		MetodosCliente mc = new MetodosCliente();
		int pos = cs.indexOf(cliente);
		ArrayList<Cliente> cl = mc.recogerClienteYSusAnimales();
		titulos = new String[] { "Nombre", "Edad", "Especie", "Sexo" ,"ID"};
		txt = new String[cl.get(pos).getAnimal().size()][titulos.length];
		int cont = 0;
		for (Cliente cli : cl) {
			
			if (cli.getDni().equals(cliente.getDni())) {

				for (int i = 0; i < cli.getAnimal().size(); i++) {
					txt[cont][0] = cli.getAnimal().get(i).getNombreAnimal();
					txt[cont][1] = String.valueOf(cli.getAnimal().get(i).getEdad());
					txt[cont][2] = cli.getAnimal().get(i).getEspecie();
					txt[cont][3] = cli.getAnimal().get(i).getSexo();
					txt[cont][4] = String.valueOf(cli.getAnimal().get(i).getIdAnimal());					
					cont++;
				}
				break;
			}
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(txt, titulos));

		return table;
	}
}
