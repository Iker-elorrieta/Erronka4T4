package ModeloPerfil;

public interface Especializacion {
	
	public enum Especialidad {
		Perros,Gatos,Loros,Pez,limpieza,ventas
	}
	
	public float calcularSalario(Especialidad especialidad);
	
	public float bonoAntiguedad(int anyos,Especialidad especialidad);

		
}
