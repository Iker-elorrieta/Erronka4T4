package ModeloPerfil;

public interface Especializacion {
	public static final int salarioEstandar = 1050;
	
	public enum Especialidad {
		Perros,Gatos,Loros,Pez,limpieza,ventas,admin
	}
	
	public float calcularSalario(Especialidad especialidad);
	
	public float bonoAntiguedad(int anyos,Especialidad especialidad);

		
}
