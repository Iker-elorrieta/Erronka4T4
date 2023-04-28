package Modelo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import ModeloPerfil.Empleado;

public class Gestion {
	private int codGestion;
	private int cantidad;
	private Date fecha;
	private Time hora;
	private Empleado empleado;
	private ArrayList<Producto> productos;

	public Gestion(int idGestion, int cantidad, Date fecha, Time hora, Empleado empleado,
			ArrayList<Producto> productos) {
		super();
		this.codGestion = idGestion;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.hora = hora;
		this.empleado = empleado;
		this.productos = productos;
	}

	public Gestion() {

	}

	public int getCodGestion() {
		return codGestion;
	}

	public void setCodGestion(int codGestion) {
		this.codGestion = codGestion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gestion other = (Gestion) obj;
		return codGestion == other.codGestion;
	}

	@Override
	public String toString() {
		return "Gestion [idGestion=" + codGestion + ", cantidad=" + cantidad + ", fecha=" + fecha + ", hora=" + hora
				+ ", empleado=" + empleado + ", productos=" + productos + "]";
	}

}
