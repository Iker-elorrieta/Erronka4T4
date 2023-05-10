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
import Controlador.MetodosClinicaVeterinaria;
import Modelo.ClinicaVeterinaria;

class MetodosClinicaVeterinariaTest extends ManagerAbstract {

	MetodosClinicaVeterinaria metodosClinicaVeterinaria = new MetodosClinicaVeterinaria();

	final String ubicacion = "Ubicacion";
	final String codClinica = "CodClinica";

	@Test
	void recogerClinicaVeterinariaTest() {

		int codClinicaNueva = 900;
		String ubicacionNueva = "Murcia";

		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionNueva + "');");

			ArrayList<ClinicaVeterinaria> listaClinicaVeterinaria;

			listaClinicaVeterinaria = metodosClinicaVeterinaria.recogerClinicaVeterinaria();

			String resultado = "";

			for (ClinicaVeterinaria clinicaVeterinaria2 : listaClinicaVeterinaria) {
				if (clinicaVeterinaria2.getCodVeterinaria() == codClinicaNueva) {
					int pos = listaClinicaVeterinaria.indexOf(clinicaVeterinaria2);
					resultado = listaClinicaVeterinaria.get(pos).toString();
				}
			}

			assertEquals(resultado, "ClinicaVeterinaria [ubicacion=Murcia, codVeterinaria=900, empleados=[]]");
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void insertarClinicaVeterinariaTest() {

		int codClinicaNueva = 900;
		String ubicacionNueva = "Murcia";

		ClinicaVeterinaria clinicaVeterinaria = new ClinicaVeterinaria();

		clinicaVeterinaria.setCodVeterinaria(codClinicaNueva);
		clinicaVeterinaria.setUbicacion(ubicacionNueva);

		try {
			metodosClinicaVeterinaria.insertarClinicaVeterinaria(clinicaVeterinaria);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " where CodClinica = '"
					+ codClinicaNueva + " ';";
			ResultSet resul = sacaCliente.executeQuery(sql);
			while (resul.next()) {

				assertEquals(codClinicaNueva, resul.getInt(codClinica));
				assertEquals(ubicacionNueva, resul.getString(ubicacion));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarClinicaVeterinariaTest() {

		int codClinicaNueva = 900;
		String ubicacionNueva = "Murcia";

		try {
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionNueva + "');");

			metodosClinicaVeterinaria.eliminarClinicaVeterinaria(codClinicaNueva);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " where CodClinica = '"
					+ codClinicaNueva + " ';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul2.isBeforeFirst(), false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateClinicaVeterinariaTest() {

		String[] ubicacionNueva = { "Gibraltar" };
		String ubicacionUpdatear = "Valladolid";
		int codClinicaNueva = 900;
		String column[] = { "Ubicacion" };

		ClinicaVeterinaria clinicaVeterinaria = new ClinicaVeterinaria();

		clinicaVeterinaria.setCodVeterinaria(codClinicaNueva);
		clinicaVeterinaria.setUbicacion("Valladolid");

		try {
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionUpdatear + "');");
;
			metodosClinicaVeterinaria.updateClinicaVeterinaria(column, ubicacionNueva, clinicaVeterinaria);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " where CodClinica = '"
					+ codClinicaNueva + " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {
				
				assertEquals(ubicacionNueva[0], resul2.getString(ubicacion));
			}
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
