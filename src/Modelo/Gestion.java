package Modelo;

import java.sql.Time;
import java.util.Date;
import ModeloPerfil.Empleado;

public class Gestion {
	private int idGestion;
	private int cantidad;
	private Date fecha;
	private Time hora;
	private Empleado empleado;
	private Producto[] productos;
	
	public Gestion(int idGestion, int cantidad, Date fecha, Time hora, Empleado empleado, Producto[] productos) {
		this.idGestion = idGestion;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.hora = hora;
		this.empleado = empleado;
		this.productos = productos;
	}

	public int getIdGestion() {
		return idGestion;
	}

	public void setIdGestion(int idGestion) {
		this.idGestion = idGestion;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gestion other = (Gestion) obj;
		return idGestion == other.idGestion;
	}

	@Override
	public String toString() {
		return "Gestion [idGestion=" + idGestion + ", cantidad=" + cantidad + ", fecha=" + fecha + ", hora=" + hora
				+ ", empleado=" + empleado + ", productos=" + productos + "]";
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}

}
