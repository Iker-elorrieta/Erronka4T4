package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controlador.MetodosClinicaVeterinaria;
import Modelo.ClinicaVeterinaria;

class MetodosClinicaVeterinariaTest {

	MetodosClinicaVeterinaria metodosClinicaVeterinaria = new MetodosClinicaVeterinaria();
	
	@Test
	void test() throws SQLException {
		ArrayList<ClinicaVeterinaria> listaClinicaVeterinaria = metodosClinicaVeterinaria.recogerClinicaVeterinaria();
		String resultado = listaClinicaVeterinaria.get(0).toString();
		assertEquals(resultado, "ClinicaVeterinaria [ubicacion=Madrid, codVeterinaria=1, empleados=[Empleado [antiguedad=4, salario=1300.0, especializacion=limpieza, nombre=Hilario, apellido=Quintana, dni=05491864Q, direccion=Avenida Cotto, 747, 2º E, contrasenya=1], Empleado [antiguedad=7, salario=1500.0, especializacion=ventas, nombre=Erica , apellido=Antunez, dni=25881680X, direccion=Travesía Gamboa, 618, 4º C, contrasenya=5], Empleado [antiguedad=10, salario=2000.0, especializacion=ventas, nombre=Eduardo, apellido=Manos Tijeras, dni=34321343F, direccion=Avinguda Toro, 0, Bajo 1º, contrasenya=7], Empleado [antiguedad=1, salario=1700.0, especializacion=Gatos, nombre=Itziar , apellido=Valverde, dni=41896850G, direccion=Praza Rafael, 05, 9º B, contrasenya=8], Empleado [antiguedad=2, salario=1700.0, especializacion=Pez, nombre=Amaya , apellido=Marin, dni=64585000B, direccion=Travessera Ponce, 523, Bajo 6º, contrasenya=14], Empleado [antiguedad=11, salario=1700.0, especializacion=Loros, nombre=Consuelo , apellido=Rio, dni=72904384L, direccion=Travessera Vega, 37, 60º 9º, contrasenya=17], Empleado [antiguedad=8, salario=1700.0, especializacion=Perros, nombre=Helena , apellido=Freire, dni=94565675W, direccion=Passeig Arnau, 75, 57º 4º, contrasenya=19]]]");
	}

}
