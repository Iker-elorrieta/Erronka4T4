package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.Consulta;
import ModeloAnimal.Animal;
import ModeloPerfil.Empleado;

public class MetodosConsulta extends ManagerAbstract{

	final String codConsulta = "CodConsulta";
	final String precio = "Precio";
	final String idAnimal = "IdAnimal";
	final String dniEmpleado = "DNI";
	final String fecha = "Fecha";
	final String hora = "Hora";
	
	public ArrayList<Consulta> recogerConsulta() throws SQLException {

		ArrayList<Consulta> listaConsulta = new ArrayList<Consulta>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaConsulta = conexion.createStatement();
		String sql = "select distinct * from " + ManagerAbstract.TABLE_CONSULTA;
		ResultSet resul = sacaConsulta.executeQuery(sql);
		while (resul.next()) {
			Consulta consulta = new Consulta ();
			consulta.setIdConsulta(resul.getInt(codConsulta));
			consulta.setPrecio(resul.getFloat(precio));
			
			int idAnimalTabla = resul.getInt(idAnimal);
			
			MetodosAnimal metodosAnimal = new MetodosAnimal();
			ArrayList<Animal> listaAnimal = metodosAnimal.recogerAnimal();
			
			for (Animal animal2 : listaAnimal) {
				if(idAnimalTabla == animal2.getIdAnimal()) {
					consulta.setAnimal(animal2);
				}
			}
			
			String dniEmpleadoTabla = resul.getString(dniEmpleado);
			MetodosEmpleado metodosEmpleado = new MetodosEmpleado();
			ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();
			
			for (Empleado empleado2 : listaEmpleado) {
				if(dniEmpleadoTabla.equalsIgnoreCase(empleado2.getDni())) {
					consulta.setEmpleado(empleado2);
				}
			}
			
			consulta.setFecha(resul.getDate(fecha));
			consulta.setHora(resul.getTime(hora));
			
			listaConsulta.add(consulta);
		}
		return listaConsulta;

	}
	
}
