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
import Controlador.MetodosMascota;
import ModeloAnimal.Mascota;

class MetodosMascotaTest extends ManagerAbstract {

	final String especie = "Especie";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codMascota = "CodMascota";

	MetodosMascota metodosMascota = new MetodosMascota();
	
	// --------------------VariablesNecesariasParaElTest--------------------/
	int codMascotaNuevo = 900;
	String especieMascotaNuevo = "Perro";
	int stockMascotaNuevo = 10;
	float precioMascotaNuevo = 50;
	
	String[] precioNuevo = { "50" };
	float precioNuevoUpdateado = 50;
	float precioUpdatear = 54456;

	String column[] = { "Precio" };
	
	

	@Test
	void recogerMascotaTest() {
		
		try {
			ArrayList<Mascota> listaMascota;

			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			listaMascota = metodosMascota.recogerMascota();

			String resultado = "";

			for (Mascota mascota : listaMascota) {
				if (mascota.getCodMascota() == codMascotaNuevo) {
					int pos = listaMascota.indexOf(mascota);
					resultado = listaMascota.get(pos).toString();
				}
			}

			assertEquals(resultado, "Mascota [codMascota=900, especie=Perro, stock=10, precio=50.0]");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void insertarMascotaTest() {

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			metodosMascota.insertarMascota(mascota);

			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_MASCOTA + " WHERE CodMascota = '" + codMascotaNuevo
					+ " ';";
			ResultSet resul = sacaAnimal.executeQuery(sql);
			while (resul.next()) {
				assertEquals(codMascotaNuevo, resul.getInt(codMascota));
				assertEquals(especieMascotaNuevo, resul.getString(especie));
				assertEquals(stockMascotaNuevo, resul.getInt(stock));
				assertEquals(precioMascotaNuevo, resul.getFloat(precio));
			}
			
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarMascotaTest() {

		try {

			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			metodosMascota.eliminarMascota(codMascotaNuevo);

			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_MASCOTA + " WHERE CodMascota = '" + codMascotaNuevo
					+ " ';";
			ResultSet resul2 = sacaAnimal.executeQuery(sql);
			assertEquals(resul2.isBeforeFirst(), false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void UpdateMascotaTest() {
		
		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioUpdatear);
		mascota.setStock(stockMascotaNuevo);


		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioUpdatear + "');");

			metodosMascota.updateMascota(column, precioNuevo, mascota);

			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_MASCOTA + " WHERE CodMascota = '" + codMascotaNuevo
					+ " ';";
			ResultSet resul2 = sacaAnimal.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(precioNuevoUpdateado, resul2.getFloat(precio));
			}

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
