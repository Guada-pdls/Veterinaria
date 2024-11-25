package veterinaria;

public class FuncionarioMedico extends Funcionario {
	public FuncionarioMedico(String nombre, String apellido, String direccion, int ci, String fechaNac, int horasPorSemana, int salario) {
		super(nombre, apellido, direccion, ci, fechaNac, horasPorSemana, salario);
	}
	
	public String atenderMascota(String descripcion, Mascota mascota, LogicaConsultas logica) {
		Consulta consultaGenerada = logica.registrarConsulta(descripcion, this, mascota);
		return consultaGenerada.toString();
	}
}