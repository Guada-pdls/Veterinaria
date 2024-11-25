package veterinaria;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LogicaSocios {
    DatosSocios datosSocios;

    public LogicaSocios(boolean estaConectado, Statement sentencia) {
        datosSocios = new DatosSocios(estaConectado, sentencia);
    }

    public String verificarDatosSocio(String nombre, String apellido, String direccion, int ci, String fechaNacimiento, Departamento departamento, String telefono, String fechaInscripcion) {	
        if ("".equals(nombre)) {
            return "nombre";
        }
        if ("".equals(apellido)) {
            return "apellido";
        }
        if ("".equals(direccion)) {
            return "direccion";
        }
        if ("".equals(departamento)) {
            return "departamento";
        }
        if ("".equals(fechaNacimiento)) {
        	return "fechaNac";
        }
        if ("".equals(telefono)) {
        	return "telefono";
        }
        if ("".equals(fechaInscripcion)) {
        	return "fechaInscripcion";
        }
        if (ci < 0) {
            return "ci";
        }
        
        if (datosSocios.buscarSocioPorCi(ci) != null) { // Si da null significa que no existe, por lo tanto es como decir "si encuentra un socio con esta ci"
        	return "ci duplicada"; // Devuelve error
        }
        
        return ""; // Si no hubo return, no hay error, devuelvo un String vacio
    }
    

    public Socio registrarSocio(String nombre, String apellido, String direccion, int ci, String fechaNacimiento, Departamento departamento, int telefono, String fechaInscripcion) {
        return datosSocios.registrarSocio(nombre, apellido, direccion, ci, fechaNacimiento, departamento, telefono, fechaInscripcion);
    }
    
   public boolean modificarSocio(int ci, String nuevoNombre, String nuevoApellido, String nuevaDireccion, String nuevaFechaNacimiento, Departamento nuevoDepartamento, int nuevoTelefono, String nuevaFechaInscripcion) {
    	return datosSocios.modificarSocio(ci, nuevoNombre, nuevoApellido, nuevaDireccion, nuevaFechaNacimiento, nuevoDepartamento, nuevoTelefono, nuevaFechaInscripcion);
    }
    
    public boolean eliminarSocio(int ci) {
    	return datosSocios.eliminarSocio(ci);
    }
    
    public ArrayList<Socio> obtenerSocios() {
        ArrayList<Socio> socios = datosSocios.obtenerSocios();
        socios.sort(Comparator.comparing(Socio::getFechaInscripcion, Comparator.nullsLast(String::compareTo)).reversed());
        return socios;
    }
    
    public Socio buscarSocioPorCi(int ci) {
    	return datosSocios.buscarSocioPorCi(ci);
    }
}
