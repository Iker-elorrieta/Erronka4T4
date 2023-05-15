package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.Consulta;
import ModeloAnimal.Animal;
import ModeloPerfil.Empleado;
/**
 * *MetodosConsulta esta clase contiene los metodos crud de la clase
 * consulta
 */
public class MetodosConsulta extends ManagerAbstract {

	final String codConsulta = "CodConsulta";
	final String precio = "Precio";
	final String idAnimal = "IdAnimal";
	final String dniEmpleado = "DNI";
	final String fecha = "Fecha";
	final String hora = "Hora";

	Connection conexion;
	/**
	 *recogerConsulta recoge los datos de las consultas de la bd en un arraylist y lo devuelve
	 */
	public ArrayList<Consulta> recogerConsulta() throws SQLException {

		ArrayList<Consulta> listaConsulta = new ArrayList<Consulta>();
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaConsulta = conexion.createStatement();
		String sql = "select distinct * from " + ManagerAbstract.TABLE_CONSULTA;
		ResultSet resul = sacaConsulta.executeQuery(sql);
		while (resul.next()) {
			Consulta consulta = new Consulta();
			consulta.setIdConsulta(resul.getInt(codConsulta));
			consulta.setPrecio(resul.getFloat(precio));

			int idAnimalTabla = resul.getInt(idAnimal);

			MetodosAnimal metodosAnimal = new MetodosAnimal();
			ArrayList<Animal> listaAnimal = metodosAnimal.recogerAnimal();

			for (Animal animal2 : listaAnimal) {
				if (idAnimalTabla == animal2.getIdAnimal()) {
					consulta.setAnimal(animal2);
				}
			}

			String dniEmpleadoTabla = resul.getString(dniEmpleado);
			MetodosEmpleado metodosEmpleado = new MetodosEmpleado();
			ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();

			for (Empleado empleado2 : listaEmpleado) {
				if (dniEmpleadoTabla.equalsIgnoreCase(empleado2.getDni())) {
					consulta.setEmpleado(empleado2);
				}
			}

			consulta.setFecha(resul.getDate(fecha));
			consulta.setHora(resul.getTime(hora));

			listaConsulta.add(consulta);
		}
		return listaConsulta;

	}
	/**
	 *insertarConsulta inserta los datos de una consulta en la bd 
	 */
	public void insertarConsulta(Consulta consultaNueva, int idAnimal, String dni) throws SQLException {

		int idConsulta = 0;
		float precio = 0;
		Date fecha = null;
		Time hora = null;

		Consulta consulta = consultaNueva;

		idConsulta = consulta.getIdConsulta();
		precio = consulta.getPrecio();
		fecha = consulta.getFecha();
		hora = consulta.getHora();

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CONSULTA + "`"
				+ "(`CodConsulta`, `Precio`, `Fecha`, `Hora`, `DNI`, `IdAnimal`) " + "VALUES( '" + idConsulta + "' , '"
				+ precio + "' , '" + fecha + "' , '" + hora + "' , '" + dni + "' , '" + idAnimal + "');");
	}
	/**
	 *eliminarConsulta elimina los datos de una consulta en la bd 
	 */
	public void eliminarConsulta(int idConsulta) throws SQLException {

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_CONSULTA + "`" + "WHERE CodConsulta = '" + idConsulta + "';");
	}
	/**
	 *updateConsulta renueva los datos de una consulta en la bd 
	 */
	public void updateConsulta(String[] nombreColumna, String[] UpdateColumna, Consulta consulta) throws SQLException {

		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE consulta set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where CodConsulta = " + "'" + consulta.getIdConsulta() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}

}
