package veterinaria;

import java.util.ArrayList;

public class Socio extends Persona {

	Departamento departamento;
	private String fechaInscripcion;
	private int telefono;
	ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
	
	
	public Socio (String nombre, String apellido, String direccion, int ci, String fechaNacimiento , Departamento departamento, int telefono, String fechaInscripcion) {
		super(nombre, apellido, direccion, ci, fechaNacimiento);
		this.departamento = departamento;
		this.telefono = telefono;
		this.fechaInscripcion = fechaInscripcion;
	}
	
	String adoptarMascota(Mascota mascota) {
		mascotas.add(mascota);
		return mascotas.toString();
	}
	
	public void setDepartamento(Departamento departamento) {
	    this.departamento = departamento;
	}
	
    public Departamento getDepartamento() {
        return departamento;
    }

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}

	public ArrayList<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(ArrayList<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

}

