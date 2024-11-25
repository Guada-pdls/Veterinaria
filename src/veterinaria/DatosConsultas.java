package veterinaria;

import java.util.ArrayList;

public class DatosConsultas {
	ArrayList<Consulta> consultas = new ArrayList<Consulta>();

    Consulta registrarConsulta(int id, String fecha, String descripcion, FuncionarioMedico medico) {
        Consulta nuevaConsulta = new Consulta(id, fecha, descripcion, medico);
        consultas.add(nuevaConsulta);
        return nuevaConsulta; 
    }
    
    String listarConsultas(Mascota mascota) {
		String listado = "";
		if (mascota.consultas.size() > 0) {
				for (Consulta consulta : mascota.consultas) {
					listado = listado + consulta + "\n";
				}
		} else {
			listado = "No hay consultas registradas para " + mascota.nombre;
		}
		return listado;
	}
}
