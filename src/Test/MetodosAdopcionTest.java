package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosAdopcion;
import Modelo.Adopcion;

class MetodosAdopcionTest extends ManagerAbstract {

	MetodosAdopcion metodosAdopcion = new MetodosAdopcion();

	String cCodAdopcion = "CodAdopcion";
	String cPrecioTotal = "PrecioTotal";
	String cCodgestionAnimal = "IdAnimal";
	String cFecha = "Fecha";
	String cCodMascota = "CodMascota";
	String cDNI = "DNI";
	
	@Test
	void recogerAnimalAdoptadosTest() {

		String fechaDate = "2023-02-13";

		int codAdopcionNuevo = 900;
		float precioTotalNuevo = 416;
		Date fechaNuevo = Date.valueOf(fechaDate);
		;
		int codMascotaNuevo = 4;
		String dniNuevo = "X7650931G";

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		Connection conexion;
		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ADOPCION + "`"
					+ "(`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) " + "VALUES( '" + codAdopcionNuevo
					+ "' , '" + precioTotalNuevo + "' , '" + fechaNuevo + "' , '" + codMascotaNuevo + "' , '" + dniNuevo
					+ "');");

			ArrayList<Adopcion> listaAdopcionAnimal = metodosAdopcion.recogerAnimalAdoptados();

			String resultado = "";

			for (Adopcion adopcion : listaAdopcionAnimal) {
				if (adopcion.getCodAdopcion() == codAdopcionNuevo) {
					int pos = listaAdopcionAnimal.indexOf(adopcion);
					resultado = listaAdopcionAnimal.get(pos).toString();
				}
			}

			assertEquals(resultado, "Adopcion [codAdopcion=900, precioTotal=416.0, fecha=2023-02-13, listaMascota=[Mascota [codMascota=3, especie=Loro, stock=5, precio=35.0], Mascota [codMascota=4, especie=Pez, stock=50, precio=5.0], Mascota [codMascota=2, especie=Gato, stock=19, precio=15.0], Mascota [codMascota=4, especie=Pez, stock=50, precio=5.0]], cliente=Cliente [animal=null, nombre=Ander, apellido=Perex, dni=X7650931G, direccion=Mi casa, contrasenya=hola]]");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test
	void insertarAnimalAdoptadosTest() {
		
		String fechaDate = "2023-02-13";

		int codAdopcionNuevo = 651651;
		float precioTotalNuevo = 416;
		Date fechaNuevo = Date.valueOf(fechaDate);
		;
		int codMascotaNuevo = 4;
		String dniNuevo = "X7650931G";

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";
		
		Adopcion adopcion = new Adopcion();
		
		adopcion.setCodAdopcion(codAdopcionNuevo);
		adopcion.setFecha(fechaNuevo);
		adopcion.setPrecioTotal(precioTotalNuevo);
		
		try {
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			
			metodosAdopcion.insertarAnimalAdoptado(adopcion, dniNuevo, codMascotaNuevo);
			
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " order by CodAdopcion DESC limit 1 ;";
			ResultSet resul2 = sacaAnimal.executeQuery(sql);
			while (resul2.next()) {
				
				assertEquals(precioTotalNuevo, resul2.getFloat(cPrecioTotal));
				assertEquals(fechaNuevo, resul2.getDate(cFecha));
				assertEquals(codMascotaNuevo, resul2.getInt(cCodMascota));
				assertEquals(dniNuevo, resul2.getString(cDNI));
			}	
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void eliminarAnimalAdoptadosTest() {
		
		String fechaDate = "2023-02-13";

		int codAdopcionNuevo = 900;
		float precioTotalNuevo = 416;
		Date fechaNuevo = Date.valueOf(fechaDate);
		;
		int codMascotaNuevo = 4;
		String dniNuevo = "X7650931G";

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";
		
		Connection conexion;
		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ADOPCION + "`"
					+ "(`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) " + "VALUES( '" + codAdopcionNuevo
					+ "' , '" + precioTotalNuevo + "' , '" + fechaNuevo + "' , '" + codMascotaNuevo + "' , '" + dniNuevo
					+ "');");
			
			metodosAdopcion.eliminarAnimalAdoptado(codAdopcionNuevo);
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " where CodAdopcion = '" + codAdopcionNuevo + "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul3.isBeforeFirst(), false);
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul4 = conexion.createStatement();

			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void updateAnimalAdoptadosTest() {
		
		String fechaDate = "2023-02-13";

		int codAdopcionNuevo = 651651;
		String[] precioTotalUpdate = { "416" };
		float precioTotalNuevo = 416;
		float precioTotalUpdatear = 416;
		Date fechaNuevo = Date.valueOf(fechaDate);
		;
		int codMascotaNuevo = 4;
		String dniNuevo = "X7650931G";

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";
		
		String column[] = { "PrecioTotal" };
		
		Adopcion adopcion = new Adopcion();
		
		adopcion.setCodAdopcion(codAdopcionNuevo);
		adopcion.setFecha(fechaNuevo);
		adopcion.setPrecioTotal(513);
		
		Connection conexion;

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ADOPCION + "`"
					+ "(`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) " + "VALUES( '" + codAdopcionNuevo
					+ "' , '" + precioTotalUpdatear + "' , '" + fechaNuevo + "' , '" + codMascotaNuevo + "' , '" + dniNuevo
					+ "');");
			
			metodosAdopcion.updateAnimalADoptado(column, precioTotalUpdate, adopcion);
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " where CodAdopcion = '" + codAdopcionNuevo + " ';";
			ResultSet resul3 = sacaCliente.executeQuery(sql);
			while (resul3.next()) {
				assertEquals(precioTotalNuevo, resul3.getFloat(cPrecioTotal));
			}
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul4 = conexion.createStatement();

			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
