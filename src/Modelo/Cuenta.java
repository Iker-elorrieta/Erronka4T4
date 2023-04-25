package Modelo;

import java.util.Objects;

import ModeloPerfil.Cliente;

public class Cuenta {
	private int numeroTarjeta;
	private Cliente cliente;
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(cliente, other.cliente);
	}
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cuenta(int numeroTarjeta, Cliente cliente) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Cuenta [numeroTarjeta=" + numeroTarjeta + ", cliente=" + cliente + "]";
	}
}
