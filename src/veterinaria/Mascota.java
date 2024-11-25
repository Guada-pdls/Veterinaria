package veterinaria;

import java.util.ArrayList;

public class Mascota {
	int id, edad;
	String nombre, raza, especie, enfermedad;
	TipoAnimal tipoAnimal;
	Socio duenio;
	ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	ArrayList<String> vacunasRecibidas = new ArrayList<String>(); 
	
	public Mascota(int id, int edad, String nombre, TipoAnimal tipoAnimal, String raza, String especie, String enfermedad,
			Socio duenio) {
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
		this.tipoAnimal = tipoAnimal;
		this.raza = raza;
		this.especie = especie;
		this.enfermedad = enfermedad;
		this.duenio = duenio;
	}
	
	String recibirVacuna(String vacuna) {
		vacunasRecibidas.add(vacuna);
		return String.join(", ", vacunasRecibidas);
	}
	
	void agregarConsulta(Consulta consulta) {
		consultas.add(consulta);
	}

	public Socio getDuenio() {
		return duenio;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public int getEdad() {
		return edad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(ArrayList<Consulta> consultas) {
		this.consultas = consultas;
	}

	public String getVacunasRecibidas() {
		return String.join(", ", vacunasRecibidas);
	}

	public void setVacunasRecibidas(ArrayList<String> vacunasRecibidas) {
		this.vacunasRecibidas = vacunasRecibidas;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public void setDuenio(Socio duenio) {
		this.duenio = duenio;
	}
	
	
}
