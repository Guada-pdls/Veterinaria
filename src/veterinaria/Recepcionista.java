package veterinaria;

import java.util.ArrayList;

public class Recepcionista extends Funcionario {
	ArrayList<String> idiomas = new ArrayList<String>();

	public Recepcionista(String nombre, String apellido, String direccion, int ci, String fechaNac, int horasPorSemana, int salario) {
		super(nombre, apellido, direccion, ci, fechaNac, horasPorSemana, salario);
	}

  	String aprenderIdioma(String idioma) {
		idiomas.add(idioma);
		return idiomas.toString();
	} 

}
