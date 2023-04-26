package Modelo;

import java.sql.Time;
import java.util.Date;
import ModeloAnimal.Animales;
import ModeloPerfil.Empleado;

public class Consulta {
	private int idConsulta;
	private float precio;
	private Animales animal;
	private Empleado empleado;
	private Date fecha;
	private Time hora;

	public Consulta(int idConsulta, float precio, Animales animal, Empleado empleado, Date fecha, Time hora) {
		super();
		this.idConsulta = idConsulta;
		this.precio = precio;
		this.animal = animal;
		this.empleado = empleado;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Animales getAnimal() {
		return animal;
	}

	public void setAnimal(Animales animal) {
		this.animal = animal;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
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
	public String toString() {
		return "Consulta [idConsulta=" + idConsulta + ", precio=" + precio + ", animal=" + animal + ", empleado="
				+ empleado + ", fecha=" + fecha + ", hora=" + hora + "]";
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return idConsulta == other.idConsulta;
	}
	
}
