package ModeloPerfil;

import java.util.Objects;

public class Cuenta {
	private int numeroCuenta;
	Cliente cliente;

	public Cuenta(int numeroCuenta, Cliente cliente) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.cliente = cliente;
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
		return numeroCuenta == other.numeroCuenta;
	}
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", cliente=" + cliente + "]";
	}

}
