package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import ModeloAnimal.Mascota;
import ModeloPerfil.Empleado;

public class GestionAnimal {

	private int codGestionAnimal;
	private Date fecha;
	private int cantidad;
	private ArrayList<Mascota> listaMAscota;
	private Empleado empleado;

	public GestionAnimal(int codGestionAnimal, Date fecha, int cantidad, ArrayList<Mascota> listaMAscota,
			Empleado empleado) {
		this.codGestionAnimal = codGestionAnimal;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.listaMAscota = listaMAscota;
		this.empleado = empleado;
	}

	public GestionAnimal() {

	}

	public int getCodGestionAnimal() {
		return codGestionAnimal;
	}

	public void setCodGestionAnimal(int codGestionAnimal) {
		this.codGestionAnimal = codGestionAnimal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<Mascota> getListaMAscota() {
		return listaMAscota;
	}

	public void setListaMAscota(ArrayList<Mascota> listaMAscota) {
		this.listaMAscota = listaMAscota;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GestionAnimal other = (GestionAnimal) obj;
		return codGestionAnimal == other.codGestionAnimal;
	}

	@Override
	public String toString() {
		return "GestionAnimal [codGestionAnimal=" + codGestionAnimal + ", fecha=" + fecha + ", cantidad=" + cantidad + ", listaMAscota=" + listaMAscota + ", empleado=" + empleado + "]";
	}

}