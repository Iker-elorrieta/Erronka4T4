package Modelo;

import java.util.ArrayList;

import ModeloPerfil.Empleado;

public class ClinicaVeterinaria {
	private String ubicacion;
	private int codVeterinaria;
	private ArrayList<Empleado> empleados;

	public ClinicaVeterinaria(String ubicacion, int codVeterinaria, ArrayList<Empleado> empleados) {
		super();
		this.ubicacion = ubicacion;
		this.codVeterinaria = codVeterinaria;
		this.empleados = empleados;
	}

	public ClinicaVeterinaria() {

	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCodVeterinaria() {
		return codVeterinaria;
	}

	public void setCodVeterinaria(int codVeterinaria) {
		this.codVeterinaria = codVeterinaria;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicaVeterinaria other = (ClinicaVeterinaria) obj;
		return codVeterinaria == other.codVeterinaria;
	}

	@Override
	public String toString() {
		return "ClinicaVeterinaria [ubicacion=" + ubicacion + ", codVeterinaria=" + codVeterinaria + ", empleados="
				+ empleados + "]";
	}



}
