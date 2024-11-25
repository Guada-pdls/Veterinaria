package veterinaria;

public class LogicaConsultas {
	DatosConsultas datosConsultas = new DatosConsultas();
	int generadorId = 0;
	
	public String validarDatosConsulta(String descripcion, FuncionarioMedico medico) {
		if (descripcion == null || descripcion.length() < 4) {
			return "descripcion";		
		} else if (medico == null) {
			return "medico";
		}
		return "";
	}
	
	public Consulta registrarConsulta(String descripcion, FuncionarioMedico medico, Mascota mascota) {
		generadorId++;
		Consulta consultaRegistrada = datosConsultas.registrarConsulta(generadorId, "06/11/2024", descripcion, medico);
		mascota.agregarConsulta(consultaRegistrada);
		return consultaRegistrada;
	}
}
