package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ModeloPerfil.Cliente;
import ModeloPerfil.Cuenta;

class CuentaTest {

	int numeroCuenta = 1662;
	int numeroCuenta0 = 0;
	Cliente cliente;

	Cuenta cuentaTest = new Cuenta(numeroCuenta, cliente);
	Cuenta cuentaTest1 = new Cuenta(numeroCuenta, cliente);
	Cuenta cuentaTest2 = null;
	Cuenta cuentaTest3 = new Cuenta(numeroCuenta0, cliente);

	@Test
	void test() {
		Cuenta cuentaTestVacio = new Cuenta();
		cuentaTestVacio.setCliente(cliente);
		cuentaTestVacio.setNumeroCuenta(numeroCuenta);

		cuentaTest.setCliente(cliente);
		cuentaTest.setNumeroCuenta(numeroCuenta);
		
		assertEquals(cuentaTest.getCliente(), cliente);
		assertEquals(cuentaTest.getNumeroCuenta(), numeroCuenta);
		
		assertEquals(cuentaTest.toString(), "Cuenta [numeroCuenta=1662, cliente=null]");
	}

	@Test
	public void testEquals() {
		assertTrue(cuentaTest.equals(cuentaTest1));
		assertFalse(cuentaTest.equals(cuentaTest2));
		assertTrue(cuentaTest.equals(cuentaTest));
		assertFalse(cuentaTest.equals(cuentaTest3));
		assertFalse(cuentaTest.equals(cuentaTest.getClass()));
	}
	
}
