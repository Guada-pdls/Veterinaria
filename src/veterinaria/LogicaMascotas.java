package veterinaria;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class LogicaMascotas {
    DatosMascotas datosMascotas;  
    int generadorId = 0;

    public LogicaMascotas(boolean estaConectado, Statement sentencia, Connection conexion) {
        datosMascotas = new DatosMascotas(estaConectado, sentencia, conexion);
    }

    public String validarDatosMascota(int edad, String nombre, TipoAnimal tipoAnimal, String raza, String especie, String enfermedad, Socio duenio) {
        if (edad < 0) {
            return "edad";
        }
        if (nombre == null) {
            return "nombre";
        }
        if (tipoAnimal == null) {
            return "tipoAnimal";
        }
        if (especie == null) {
            return "especie";
        }
        if (duenio == null) {
            return "duenio";
        }
        for (Mascota mascota : datosMascotas.obtenerMascotas(duenio)) {
            if (mascota.getNombre().equalsIgnoreCase(nombre)) {
                return "nombre duplicado";
            }
        }
        return ""; 
    }
    
    public Mascota registrarMascota(int edad, String nombre, TipoAnimal tipoAnimal, String raza, String especie, String enfermedad, Socio duenio) {
        generadorId++;
        Mascota mascotaRegistrada = datosMascotas.registrarMascota(generadorId, edad, nombre, tipoAnimal, raza, especie, enfermedad, duenio);
        
        if (mascotaRegistrada != null) {
            duenio.adoptarMascota(mascotaRegistrada);
        }
        
        return mascotaRegistrada;
    }
    
    public boolean modificarMascota(int id, int edad, String nombre, TipoAnimal tipoAnimal, String raza, String especie, String enfermedad, Socio duenio) {
        return datosMascotas.modificarMascota(id, nombre, edad, tipoAnimal, raza, especie, enfermedad, duenio);
    }
    
    public boolean eliminarMascota(int id, Socio duenio) {
        return datosMascotas.eliminarMascota(id, duenio);
    }
    
    public ArrayList<Mascota> obtenerMascotas(Socio socio) {
        return datosMascotas.obtenerMascotas(socio);
    }
    
    public Mascota buscarMascotaPorId(int id) {
        return datosMascotas.buscarMascotaPorId(id);
    }
    
}
