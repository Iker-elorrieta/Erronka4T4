package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;

import Modelo.Consulta;
import ModeloAnimal.Animales;
import ModeloPerfil.Empleado;

class ConsultaTest {

	int idConsulta = 464661;
	int idConsulta0 = 0;
	float precio = (float) 19.66;
	Animales animal;
	Empleado empleado;
	Date fecha;
	Time hora;

	Consulta consultaTest = new Consulta(idConsulta, precio, animal, empleado, fecha, hora);
	Consulta consultaTest1 = new Consulta(idConsulta, precio, animal, empleado, fecha, hora);
	Consulta consultaTest2 = null;
	Consulta consultaTest3 = new Consulta(idConsulta0, precio, animal, empleado, fecha, hora);

	@Test
	void test() {

		consultaTest.setIdConsulta(idConsulta);
		consultaTest.setPrecio(precio);
		consultaTest.setAnimal(animal);
		consultaTest.setEmpleado(empleado);
		consultaTest.setFecha(fecha);
		consultaTest.setHora(hora);

		assertEquals(consultaTest.getIdConsulta(), idConsulta);
		assertEquals(consultaTest.getPrecio(), precio);
		assertEquals(consultaTest.getAnimal(), animal);
		assertEquals(consultaTest.getEmpleado(), empleado);
		assertEquals(consultaTest.getFecha(), fecha);
		assertEquals(consultaTest.getHora(), hora);

		assertEquals(consultaTest.toString(),
				"Consulta [idConsulta=464661, precio=19.66, animal=null, empleado=null, fecha=null, hora=null]");
	}

	@Test
	public void testEquals() {
		assertTrue(consultaTest.equals(consultaTest1));
		assertFalse(consultaTest.equals(consultaTest2));
		assertTrue(consultaTest.equals(consultaTest));
		assertFalse(consultaTest.equals(consultaTest3));
		assertFalse(consultaTest.equals(consultaTest.getClass()));
	}
}
