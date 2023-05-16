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

	// --------------------VariablesNecesariasParaElTest--------------------//
	int codClinicaNueva = 900;
	String ubicacionNueva = "Murcia";

	String column[] = { "Ubicacion" };
	String[] ubicacionNuevaUpdateado = { "Gibraltar" };
	String ubicacionUpdatear = "Valladolid";

	Connection conexion;

	@Test
	void recogerClinicaVeterinariaTest() {

		try {

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

		ClinicaVeterinaria clinicaVeterinaria = new ClinicaVeterinaria();

		clinicaVeterinaria.setCodVeterinaria(codClinicaNueva);
		clinicaVeterinaria.setUbicacion(ubicacionNueva);

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			metodosClinicaVeterinaria.insertarClinicaVeterinaria(clinicaVeterinaria);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " where CodClinica = '"
					+ codClinicaNueva + " ';";
			ResultSet resul = sacaCliente.executeQuery(sql);
			while (resul.next()) {

				assertEquals(codClinicaNueva, resul.getInt(codClinica));
				assertEquals(ubicacionNueva, resul.getString(ubicacion));
			}

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

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionNueva + "');");

			metodosClinicaVeterinaria.eliminarClinicaVeterinaria(codClinicaNueva);

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

		ClinicaVeterinaria clinicaVeterinaria = new ClinicaVeterinaria();

		clinicaVeterinaria.setCodVeterinaria(codClinicaNueva);
		clinicaVeterinaria.setUbicacion("Valladolid");

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNueva + "','" + ubicacionUpdatear + "');");

			metodosClinicaVeterinaria.updateClinicaVeterinaria(column, ubicacionNuevaUpdateado, clinicaVeterinaria);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " where CodClinica = '"
					+ codClinicaNueva + " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {

				assertEquals(ubicacionNuevaUpdateado[0], resul2.getString(ubicacion));
			}

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNueva + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
