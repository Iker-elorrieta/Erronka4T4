package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.ObjetosComprables;
import ModeloAnimal.Mascota;
/** 
 * *MetodosMascota esta clase contiene los metodos crud de la clase Mascota
 */
public class MetodosMascota extends ManagerAbstract {

	final String especie = "Especie";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codMascota = "CodMascota";
	
	Connection conexion;
	/** 
	 * *recogerMascota recoge los datos de las mascotas de la bd en un arraylist y lo devuelve
	 */
	public ArrayList<Mascota> recogerMascota() throws SQLException {

		ArrayList<Mascota> listaMascota = new ArrayList<Mascota>();
		
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaProducto = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_MASCOTA;
		ResultSet resul = sacaProducto.executeQuery(sql);
		while (resul.next()) {
			Mascota mascota = new Mascota();
			mascota.setEspecie(resul.getString(especie));
			mascota.setPrecio(resul.getFloat(precio));
			mascota.setStock(resul.getInt(stock));
			mascota.setCodMascota(resul.getInt(codMascota));
			listaMascota.add(mascota);
		}
		return listaMascota;

	}
	/** 
	 * *insertarMascota inserta una mascota en la bd 
	 */
	public void insertarMascota(Mascota mascotaNuevo) throws SQLException {

		String especieMascota = "";
		float precio = 0;
		int stock = 0;
		int codMascota = 0;

		Mascota producto = mascotaNuevo;

		especieMascota = producto.getEspecie();
		precio = producto.getPrecio();
		stock = producto.getStock();
		codMascota = producto.getCodMascota();

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
				+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascota + "' , '" + especieMascota
				+ "' , '" + stock + "' , '" + precio + "');");
	}
	/** 
	 * *eliminarMascota elimina una mascota en la bd 
	 */
	public void eliminarMascota(int codMascota) throws SQLException {

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '" + codMascota + "';");

	}
	/** 
	 * *updateMascota renueva una mascota en la bd 
	 */
	public void updateMascota(String[] nombreColumna, String[] UpdateColumna, Mascota mascota) throws SQLException {

		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE mascotas set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where CodMascota = " + "'" + mascota.getCodMascota() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}
	/** 
	 * *recogerMascotaTienda recoge las mascotas separadas por tiendas
	 */
	public ArrayList<ObjetosComprables> recogerMascotaTienda(String valorUbicacion) throws SQLException {

		ArrayList<ObjetosComprables> listaMascota = new ArrayList<ObjetosComprables>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaProducto = conexion.createStatement();
		String sql = "select m.Especie, m.Precio, m.Stock, m.CodMascota from " + ManagerAbstract.TABLE_MASCOTA + " m join " + ManagerAbstract.TABLE_TIENE +
				" t on m.CodMascota = t.CodMascota join " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " c on t.CodClinica = c.CodClinica " +
				" WHERE c.Ubicacion = '" + valorUbicacion + "';";
		ResultSet resul = sacaProducto.executeQuery(sql);
		while (resul.next()) {
			Mascota mascota = new Mascota();
			mascota.setEspecie(resul.getString(especie));
			mascota.setPrecio(resul.getFloat(precio));
			mascota.setStock(resul.getInt(stock));
			mascota.setCodMascota(resul.getInt(stock));
			listaMascota.add(mascota);
		}
		return listaMascota;

	}
}
