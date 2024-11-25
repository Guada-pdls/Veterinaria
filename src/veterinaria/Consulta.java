package veterinaria;

public class Consulta {
	int id;
	String fecha, descripcion;
	FuncionarioMedico medico;
	
	public Consulta(int id, String fecha, String descripcion, FuncionarioMedico medico) {
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.medico = medico;
	}
}
