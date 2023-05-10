package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.Adopcion;
import ModeloAnimal.Mascota;
import ModeloPerfil.Cliente;

public class MetodosAdopcion extends ManagerAbstract {

	final String cCodAdopcion = "CodAdopcion";
	final String cPrecioTotal = "PrecioTotal";
	final String cCodgestionAnimal = "IdAnimal";
	final String cFecha = "Fecha";
	final String cCodMascota = "CodMascota";
	final String cDNI = "DNI";

	MetodosCliente mc = new MetodosCliente();
	MetodosMascota mm = new MetodosMascota();

	public ArrayList<Adopcion> recogerAnimalAdoptados() throws SQLException {
		ArrayList<Cliente> rc = mc.recogerClienteYSusAnimales();
		ArrayList<Mascota> rm = mm.recogerMascota();

		ArrayList<Adopcion> listaAnimalesAdoptados = new ArrayList<Adopcion>();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaAnimalAdoptado = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION;
		ResultSet resul = sacaAnimalAdoptado.executeQuery(sql);
		ArrayList<Mascota> mNuevas = new ArrayList<Mascota>();
		while (resul.next()) {
			Adopcion adop = new Adopcion();
			adop.setCodAdopcion(resul.getInt(cCodAdopcion));
			adop.setFecha(resul.getDate(cFecha));
			adop.setPrecioTotal(resul.getFloat(cPrecioTotal));
			String dni = resul.getString(cDNI);
			for (int i = 0; i < rc.size(); i++) {
				if (dni.equals(rc.get(i).getDni())) {
					adop.setCliente(rc.get(i));
				}
			}
			int codMascota = resul.getInt(cCodMascota);

			for (int i = 0; i < rm.size(); i++) {
				if (codMascota == rm.get(i).getCodMascota()) {
					Mascota mascota = rm.get(i);
					mNuevas.add(mascota);

				}
			}

			adop.setListaMascota(mNuevas);
			listaAnimalesAdoptados.add(adop);
			
		}
		return listaAnimalesAdoptados;
	}

	public void insertarAnimalAdoptado(Adopcion animalNuevo, String dni, int codigoMascota) throws SQLException {

		int codAdopcion = 0;
		float precioTotal = 0;
		Date fecha = null;

		Adopcion animal = animalNuevo;
		precioTotal = animal.getPrecioTotal();
		fecha = animal.getFecha();
		
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();
		resul.executeUpdate("INSERT INTO `adopcion`(`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) "
				+ "VALUES( '" + codAdopcion + "' , '" + precioTotal + "' , '" + fecha + "' , '" + codigoMascota + "' , '"
				+ dni + "');");

	}

	public void eliminarAnimalAdoptado(int codAdopcion) throws SQLException {
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_ADOPCION + "`" + "WHERE CodAdopcion = '" + codAdopcion + "';");

	}

	public void updateAnimalADoptado(String[] nombreColumna, String[] UpdateColumna, Adopcion animal)
			throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE adopcion set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where CodAdopcion = " + "'" + animal.getCodAdopcion() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}

}
