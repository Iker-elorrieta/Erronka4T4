package Modelo;

import java.sql.Date;
import java.util.ArrayList;

import ModeloAnimal.Mascota;
import ModeloPerfil.Cliente;

public class Adopcion {

	private int codAdopcion;
	private float precioTotal;
	private Date fecha;
	private ArrayList<Mascota> listaMascota;
	private Cliente cliente;

	public Adopcion(int codAdopcion, float precioTotal, Date fecha, int cantidadAdopcion,
			ArrayList<Mascota> listaMascota, Cliente cliente) {
		this.codAdopcion = codAdopcion;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
		this.listaMascota = listaMascota;
		this.cliente = cliente;
	}

	public Adopcion() {

	}

	public int getCodAdopcion() {
		return codAdopcion;
	}

	public void setCodAdopcion(int codAdopcion) {
		this.codAdopcion = codAdopcion;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Mascota> getListaMascota() {
		return listaMascota;
	}

	public void setListaMascota(ArrayList<Mascota> listaMascota) {
		this.listaMascota = listaMascota;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
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
		Adopcion other = (Adopcion) obj;
		return codAdopcion == other.codAdopcion;
	}

	@Override
	public String toString() {
		return "Adopcion [codAdopcion=" + codAdopcion + ", precioTotal=" + precioTotal + ", fecha=" + fecha
				+ ", listaMascota=" + listaMascota + ", cliente=" + cliente + "]";
	}



}
