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
import Controlador.MetodosGestionAnimal;
import Modelo.GestionAnimal;
import ModeloAnimal.Mascota;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

class MetodosGestionAnimalTest extends ManagerAbstract {

	final String cCodGestionAnimal = "CodGestionAnimal";
	final String cFecha = "Fecha";
	final String cCantidad = "Cantidad";
	final String cCodMascota = "CodMascota";
	final String cDNI = "DNI";

	MetodosGestionAnimal metodosGestionAnimal = new MetodosGestionAnimal();
	
	// --------------------VariablesNecesariasParaElTest--------------------//
	
	int codGestionAnimalNuevo = 900;
	String fechaDate = "2023-02-13";
	Date fechaNueva = Date.valueOf(fechaDate);
	int cantidadNuevo = 65;
	String[] stockNuevoUpdateado = { "75" };
	int stockNuevo = 75;
	String column[] = { "Cantidad" };
	
	// --------------------------------------------//
	int codMascotaNuevo = 900;
	String especieMascotaNuevo = "Perro";
	int stockMascotaNuevo = 10;
	float precioMascotaNuevo = 50;
	// --------------------------------------------//

	String nombreEmpleado = "Juan";
	String apellidosNuevo = "Calvo";
	String dniEmpleado = "27446177R";
	String direccionNuevo = "AveCesas";
	String contrasenyaNuevo = "2684";
	int codClinica = 2;
	float salarioNuevo = 5615;
	int antiguedadNuevo = 25;
	Especialidad especializacionNuevo = Especialidad.Perros;

	@Test
	void recogerGestionAnimalTest() {




		try {

			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniEmpleado + "' , '" + nombreEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinica + "');");


			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTIONANIMAL + "`"
					+ "(`CodGestionAnimal`, `Fecha`, `Cantidad`, `CodMascota`, `DNI`) " + "VALUES( '"
					+ codGestionAnimalNuevo + "' , '" + fechaNueva + "' , '" + cantidadNuevo + "' , '" + codMascotaNuevo
					+ "' , '" + dniEmpleado + "');");

			ArrayList<GestionAnimal> listaGestionAnimal = metodosGestionAnimal.recogerGestionAnimal();

			String resultado = "";

			for (GestionAnimal gestionAnimal : listaGestionAnimal) {
				if(gestionAnimal.getCodGestionAnimal() == codGestionAnimalNuevo) {
					int pos = listaGestionAnimal.indexOf(gestionAnimal);
					resultado = listaGestionAnimal.get(pos).toString();
				}
			}
			
			assertEquals(resultado, "GestionAnimal [codGestionAnimal=900, fecha=2023-02-13, cantidad=65, listaMAscota=[Mascota [codMascota=1, especie=Perro, stock=15, precio=26.0], Mascota [codMascota=2, especie=Gato, stock=19, precio=15.0], Mascota [codMascota=3, especie=Loro, stock=5, precio=35.0], Mascota [codMascota=4, especie=Pez, stock=50, precio=5.0], Mascota [codMascota=900, especie=Perro, stock=10, precio=50.0]], empleado=Empleado [antiguedad=25, salario=5615.0, especializacion=Perros, nombre=Juan, apellido=Calvo, dni=27446177R, direccion=AveCesas, contrasenya=2684]]");
			
			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE DNI = '" + dniEmpleado + "';");
			
			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	void insertarGestionAnimalTest() {

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		GestionAnimal gestionAnimal = new GestionAnimal();

		gestionAnimal.setCantidad(cantidadNuevo);
		gestionAnimal.setCodGestionAnimal(codGestionAnimalNuevo);
		gestionAnimal.setFecha(fechaNueva);

		Empleado empleadoNuevo = new Empleado();

		empleadoNuevo.setDni(dniEmpleado);
		empleadoNuevo.setAntiguedad(antiguedadNuevo);
		empleadoNuevo.setApellido(apellidosNuevo);
		empleadoNuevo.setContrasenya(contrasenyaNuevo);
		empleadoNuevo.setDireccion(direccionNuevo);
		empleadoNuevo.setEspecializacion(especializacionNuevo);
		empleadoNuevo.setNombre(nombreEmpleado);
		empleadoNuevo.setSalario(salarioNuevo);
		
		
		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniEmpleado + "' , '" + nombreEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinica + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");
			
			metodosGestionAnimal.insertarGestion(gestionAnimal, dniEmpleado, codMascotaNuevo);
			
			Statement sacaGestion = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTIONANIMAL + " order by CodGestionAnimal DESC limit 1";
			
			ResultSet resul3 = sacaGestion.executeQuery(sql);
			while (resul3.next()) {
				
				assertEquals(fechaNueva, resul3.getDate(cFecha));
				assertEquals(cantidadNuevo, resul3.getInt(cCantidad));
				assertEquals(dniEmpleado, resul3.getString(cDNI));
			}
			
			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE DNI = '" + dniEmpleado + "';");
			;
			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void eliminarGestionAnimalTest() {
		
		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniEmpleado + "' , '" + nombreEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinica + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTIONANIMAL + "`"
					+ "(`CodGestionAnimal`, `Fecha`, `Cantidad`, `CodMascota`, `DNI`) " + "VALUES( '"
					+ codGestionAnimalNuevo + "' , '" + fechaNueva + "' , '" + cantidadNuevo + "' , '" + codMascotaNuevo
					+ "' , '" + dniEmpleado + "');");
			
			metodosGestionAnimal.eliminarGestionAnimal(codGestionAnimalNuevo);
			
			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTIONANIMAL + " WHERE CodGestionAnimal = '" + codGestionAnimalNuevo
					+ " ';";
			ResultSet resul4 = sacaAnimal.executeQuery(sql);
			assertEquals(resul4.isBeforeFirst(), false);
			
			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE DNI = '" + dniEmpleado + "';");
			
			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void updateGestionAnimalTest() {
		

		Mascota mascota = new Mascota();

		mascota.setCodMascota(codMascotaNuevo);
		mascota.setEspecie(especieMascotaNuevo);
		mascota.setPrecio(precioMascotaNuevo);
		mascota.setStock(stockMascotaNuevo);

		GestionAnimal gestionAnimal = new GestionAnimal();

		gestionAnimal.setCantidad(65);
		gestionAnimal.setCodGestionAnimal(codGestionAnimalNuevo);
		gestionAnimal.setFecha(fechaNueva);

		Empleado empleadoNuevo = new Empleado();

		empleadoNuevo.setDni(dniEmpleado);
		empleadoNuevo.setAntiguedad(antiguedadNuevo);
		empleadoNuevo.setApellido(apellidosNuevo);
		empleadoNuevo.setContrasenya(contrasenyaNuevo);
		empleadoNuevo.setDireccion(direccionNuevo);
		empleadoNuevo.setEspecializacion(especializacionNuevo);
		empleadoNuevo.setNombre(nombreEmpleado);
		empleadoNuevo.setSalario(salarioNuevo);
		
		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniEmpleado + "' , '" + nombreEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinica + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTIONANIMAL + "`"
					+ "(`CodGestionAnimal`, `Fecha`, `Cantidad`, `CodMascota`, `DNI`) " + "VALUES( '"
					+ codGestionAnimalNuevo + "' , '" + fechaNueva + "' , '" + cantidadNuevo + "' , '" + codMascotaNuevo
					+ "' , '" + dniEmpleado + "');");
			
			metodosGestionAnimal.updateGestion(column, stockNuevoUpdateado, gestionAnimal);
			
			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTIONANIMAL + " WHERE CodGestionAnimal = '" + codGestionAnimalNuevo
					+ " ';";
			ResultSet resul4 = sacaAnimal.executeQuery(sql);
			while (resul4.next()) {
				assertEquals(stockNuevo, resul4.getInt(cCantidad));
			}
			
			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE DNI = '" + dniEmpleado + "';");
			
			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	

	
}
