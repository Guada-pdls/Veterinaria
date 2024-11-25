package veterinaria;

public class Veterinario extends FuncionarioMedico {

	private String cursosEspecializacion;
	
	public Veterinario(String nombre, String apellido, String direccion, int ci, String fechaNac, int horasPorSemana, int salario, String cursosEspecializacion) {
		super(nombre, apellido, direccion, ci, fechaNac, horasPorSemana, salario);
		this.cursosEspecializacion = cursosEspecializacion;
	}

	public String getCursosEspecializacion() {
		return cursosEspecializacion;
	}

	public void setCursosEspecializacion(String cursosEspecializacion) {
		this.cursosEspecializacion = cursosEspecializacion;
	}
		
}

