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
import Modelo.GestionAnimal;
import ModeloAnimal.Mascota;
import ModeloPerfil.Empleado;
/** 
 * *MetodosGestionAnimal esta clase contiene los metodos crud de la clase GestionAnimal
 */
public class MetodosGestionAnimal extends ManagerAbstract {
	final String cCodGestionAnimal = "CodGestionAnimal";
	final String cFecha = "Fecha";
	final String cCantidad = "Cantidad";
	final String cCodMascota = "CodMascota";
	final String cDNI = "DNI";
// -----------------------------------------
	MetodosEmpleado me = new MetodosEmpleado();
	MetodosMascota mm = new MetodosMascota();

	Connection conexion;
	/** 
	 * *recogerGestion recoge los datos de las gestionesanimales de la bd en un arraylist y lo devuelve
	 */
	public ArrayList<GestionAnimal> recogerGestionAnimal() throws SQLException {
		ArrayList<Empleado> re = me.recogerEmpleado();
		ArrayList<GestionAnimal> listaGestion = new ArrayList<GestionAnimal>();
		ArrayList<Mascota> listaGestionNueva = new ArrayList<Mascota>();
		ArrayList<Mascota> lm = mm.recogerMascota();

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaGestion = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_GESTIONANIMAL;
		ResultSet resul = sacaGestion.executeQuery(sql);
		while (resul.next()) {
			GestionAnimal gestion = new GestionAnimal();
			gestion.setCantidad(resul.getInt(cCantidad));
			gestion.setCodGestionAnimal(resul.getInt(cCodGestionAnimal));
			String codEmple = resul.getString(cDNI);
			for (Empleado empleado2 : re) {
				if (codEmple.equals(empleado2.getDni())) {
					gestion.setEmpleado(empleado2);
				}
			}
			gestion.setFecha(resul.getDate(cFecha));
			int codMascota = resul.getInt(cCodMascota);
			for (int i = 0; i < lm.size(); i++) {
				if (codMascota == lm.get(i).getCodMascota()) {
					Mascota mas = lm.get(i);
					listaGestionNueva.add(mas);
				}
			}
			gestion.setListaMAscota(lm);
			listaGestion.add(gestion);

		}
		return listaGestion;
	}
	/** 
	 * *insertarGestion inserta una gestion en la bd
	 */
	public void insertarGestion(GestionAnimal gestionNueva, String dni, int codMascota) throws SQLException {

		MetodosMascota met = new MetodosMascota();

		int codGestionAnimal = 0;
		Date fecha = null;
		int cantidad = 0;

		GestionAnimal gestion = gestionNueva;

		fecha = gestion.getFecha();
		cantidad = gestion.getCantidad();

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		ArrayList<Mascota> lista = met.recogerMascota();

		for (Mascota mas : lista) {
			if (mas.getCodMascota() == codMascota) {
				resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTIONANIMAL + "`"
						+ "(`CodGestionAnimal`, `Fecha`, `Cantidad`, `CodMascota`, `DNI`) " + "VALUES( '"
						+ codGestionAnimal + "' , '" + fecha + "' , '" + cantidad + "' , '" + codMascota + "' , '" + dni
						+ "');");
			}
		}
	}
	/** 
	 * *eliminarGestionAnimal inserta una gestionanimal en la bd
	 */
	public void eliminarGestionAnimal(int codGestion) throws SQLException {

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_GESTIONANIMAL + "`" + "WHERE CodGestionAnimal = '"
				+ codGestion + "';");
	}
	/** 
	 * *updateGestion renueva una gestionanimal en la bd
	 */
	public void updateGestion(String[] nombreColumna, String[] UpdateColumna, GestionAnimal gestion)
			throws SQLException {

		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE gestionanimal set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where CodGestionAnimal = " + "'" + gestion.getCodGestionAnimal() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}
}
