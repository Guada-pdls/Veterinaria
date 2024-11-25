package veterinaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionBD {
    private boolean conectado = false;
    private Statement sentencia;
    private Connection conexion;

    public ConexionBD() {
        try {
            // Establecer la conexion con la bdd
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria", "root", "Guada15");

            // Crear una statement para ejecutar las sentencias SQL
            sentencia = conexion.createStatement();

            // Conexion exitosa
            conectado = true;

        }catch (Exception e) {
            // Fallo de conexion
            conectado = false;
            e.printStackTrace();
        }
    }

    public Statement getSentencia() {
        return sentencia;
    }

    public void setSentencia(Statement sentencia) {
        this.sentencia = sentencia;
    }

    public boolean estaConectado() {
        return conectado;
    }

	public Connection getConexion() {
		return conexion;
	}
}