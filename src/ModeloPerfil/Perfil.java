package ModeloPerfil;

public abstract class Perfil {
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected String direccion;
	protected String contrasenya;

	public Perfil(String nombre, String apellido, String dni, String direccion, String contrasenya) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.direccion = direccion;
		this.contrasenya = contrasenya;
	}
	
	public Perfil() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Override
	public abstract boolean equals(Object obj);

}
