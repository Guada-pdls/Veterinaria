package veterinaria;

public class Persona {
 
	private String nombre, apellido, direccion, fechaNacimiento;
	private int ci;
	
	public Persona(String nombre, String apellido,String direccion, int ci, String fechaNacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.ci = ci;
		this.fechaNacimiento = fechaNacimiento;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNac) {
		this.fechaNacimiento = fechaNac;
	}

} 