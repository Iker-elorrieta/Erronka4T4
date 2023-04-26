package Modelo;

import java.util.Objects;

public class Cuenta {
	private String numeroCuenta;
	private String contrasenya;

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", contrasenya=" + contrasenya + "]";
	}

	public Cuenta(String numeroCuenta, String contrasenya) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.contrasenya = contrasenya;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(numeroCuenta, other.numeroCuenta);
	}


	
}
