package ModeloPerfil;

import java.util.Arrays;
import java.util.Objects;

import Modelo.Consulta;
import Modelo.Gestion;

public class Empleado extends Perfil implements Especializacion {


	private int antiguedad;
	private float salario;
	private Consulta[] consultas;
	private Gestion[] gestiones; 
	private Especialidad especializacion;



	public Empleado(String nombre, String apellido, String dni, String direccion, String contrasenya, int antiguedad,
			float salario, Consulta[] consultas, Gestion[] gestiones, Especialidad especializacion) {
		super(nombre, apellido, dni, direccion, contrasenya);
		this.antiguedad = antiguedad;
		this.salario = salario;
		this.consultas = consultas;
		this.gestiones = gestiones;
		this.especializacion = especializacion;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public float calcularSalario(Especialidad especialidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float bonoAntiguedad(int anyos, Especialidad especialidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public Especialidad getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(Especialidad especializacion) {
		this.especializacion = especializacion;
	}



	@Override
	public String toString() {
		return "Empleado [antiguedad=" + antiguedad + ", salario=" + salario + ", consultas="
				+ Arrays.toString(consultas) + ", gestiones=" + Arrays.toString(gestiones) + ", especializacion="
				+ especializacion + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", direccion="
				+ direccion + ", contrasenya=" + contrasenya + "]";
	}

	public Consulta[] getConsultas() {
		return consultas;
	}

	public void setConsultas(Consulta[] consultas) {
		this.consultas = consultas;
	}

	public Gestion[] getGestiones() {
		return gestiones;
	}

	public void setGestiones(Gestion[] gestiones) {
		this.gestiones = gestiones;
	}





}
