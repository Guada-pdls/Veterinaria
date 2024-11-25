package veterinaria;

public class Funcionario extends Persona {

	private int horasPorSemana, salario;

	
	public Funcionario(String nombre, String apellido, String direccion, int ci, String fechaNac, int horasPorSemana, int salario) {
		super(nombre, apellido, direccion, ci, fechaNac);
		this.horasPorSemana = horasPorSemana;
		this.salario = salario;
	}

	public int getHorasPorSemana() {
		return horasPorSemana;
	}

	public void setHorasPorSemana(int horasPorSemana) {
		this.horasPorSemana = horasPorSemana;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}
}


