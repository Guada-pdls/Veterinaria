package veterinaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatosMascotas {
    boolean estaConectado;
    Statement sentencia;
    Connection conexion;

    public DatosMascotas(boolean estaConectado, Statement sentencia, Connection conexion) {
        this.estaConectado = estaConectado;
        this.sentencia = sentencia;
        this.conexion = conexion;
    }

    // Método para registrar una nueva mascota
    public Mascota registrarMascota(int id, int edad, String nombre, TipoAnimal tipoAnimal, String raza, String especie, String enfermedad, Socio duenio) {
        Mascota nuevaMascota = null;
        
        if (estaConectado) {
            String sentenciaInsertMascota = "INSERT INTO Mascota (nombre, tipo_animal, raza, especie, edad, enfermedad) VALUES (?, ?, ?, ?, ?, ?)";
            String sentenciaInsertRelacion = "INSERT INTO tiene (idM, ciS) VALUES (?, ?)";
            
            try (PreparedStatement psMascota = conexion.prepareStatement(sentenciaInsertMascota, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psRelacion = conexion.prepareStatement(sentenciaInsertRelacion)) {
                
                // Insertar en tabla Mascota
                psMascota.setString(1, nombre);
                psMascota.setString(2, tipoAnimal.toString());
                psMascota.setString(3, raza);
                psMascota.setString(4, especie);
                psMascota.setInt(5, edad);
                psMascota.setString(6, enfermedad);
                psMascota.executeUpdate();
                
                // Obtener el ID generado
                ResultSet generatedKeys = psMascota.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);

                    // Insertar en tabla de relación con el dueño
                    psRelacion.setInt(1, idGenerado);
                    psRelacion.setInt(2, duenio.getCi());
                    psRelacion.executeUpdate();

                    // Crear el objeto Mascota
                    nuevaMascota = new Mascota(idGenerado, edad, nombre, tipoAnimal, raza, especie, enfermedad, duenio);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Si no está conectado, crea una mascota localmente
            nuevaMascota = new Mascota(0, edad, nombre, tipoAnimal, raza, especie, enfermedad, duenio);
        }
        return nuevaMascota;
    }


    // Método para obtener las mascotas de un socio específico
    public ArrayList<Mascota> obtenerMascotas(Socio socio) {
        socio.getMascotas().clear(); // Limpiar la lista para evitar duplicados

        if (estaConectado) {
            String sentenciaSelect = "SELECT M.* FROM Mascota M JOIN tiene T ON M.id = T.idM WHERE T.ciS = '" + socio.getCi() + "'";

            try {
                ResultSet rs = sentencia.executeQuery(sentenciaSelect);
                while (rs.next()) {
                    Mascota mascota = new Mascota(
                        rs.getInt("id"),
                        rs.getInt("edad"),
                        rs.getString("nombre"),
                        TipoAnimal.valueOf(rs.getString("tipo_animal")),
                        rs.getString("raza"),
                        rs.getString("especie"),
                        rs.getString("enfermedad"),
                        socio
                    );
                    socio.getMascotas().add(mascota);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return socio.getMascotas();
    }

    // Método para buscar una mascota por su ID
    public Mascota buscarMascotaPorId(int id) {
        if (estaConectado) {
            String sentenciaSelect = "SELECT * FROM Mascota WHERE id = '" + id + "'";
            
            try {
                ResultSet rs = sentencia.executeQuery(sentenciaSelect);
                
                if (rs.next()) {
                    return new Mascota(
                        rs.getInt("id"),
                        rs.getInt("edad"),
                        rs.getString("nombre"),
                        TipoAnimal.valueOf(rs.getString("tipo_animal")),
                        rs.getString("raza"),
                        rs.getString("especie"),
                        rs.getString("enfermedad"),
                        null
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Método para modificar una mascota existente
    public boolean modificarMascota(int id, String nuevoNombre, int nuevaEdad, TipoAnimal nuevoTipo, String nuevaRaza, String nuevaEspecie, String nuevaEnfermedad, Socio duenio) {
        boolean modificado = false;
        
        if (estaConectado) {
            String sentenciaUpdateMascota = "UPDATE Mascota SET nombre = '" + nuevoNombre + "', edad = " + nuevaEdad + 
                                            ", tipo_animal = '" + nuevoTipo + "', raza = '" + nuevaRaza + "', especie = '" + nuevaEspecie +
                                            "', enfermedad = '" + nuevaEnfermedad + "' WHERE id = " + id;
            try {
                int filasAfectadas = sentencia.executeUpdate(sentenciaUpdateMascota);
                modificado = (filasAfectadas > 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Actualizar la lista de mascotas en memoria
        if (modificado || !estaConectado) {
            for (Mascota mascota : duenio.getMascotas()) {
                if (mascota.getId() == id) {
                    mascota.setNombre(nuevoNombre);
                    mascota.setEdad(nuevaEdad);
                    mascota.setTipoAnimal(nuevoTipo);
                    mascota.setRaza(nuevaRaza);
                    mascota.setEspecie(nuevaEspecie);
                    mascota.setEnfermedad(nuevaEnfermedad);
                    modificado = true;
                    break;
                }
            }
        }
        
        return modificado;
    }

    // Método para eliminar una mascota existente
    public boolean eliminarMascota(int id, Socio duenio) {
        boolean eliminado = false;

        if (estaConectado) {
            String sentenciaDeleteRelacion = "DELETE FROM tiene WHERE idM = " + id;
            String sentenciaDeleteMascota = "DELETE FROM Mascota WHERE id = " + id;

            try {
                sentencia.executeUpdate(sentenciaDeleteRelacion); // Eliminar la relación
                int filasAfectadas = sentencia.executeUpdate(sentenciaDeleteMascota); // Eliminar la mascota
                eliminado = (filasAfectadas > 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Eliminar la mascota del ArrayList del socio en memoria
        if (eliminado || !estaConectado) {
            duenio.getMascotas().removeIf(mascota -> mascota.getId() == id);
        }

        return eliminado;
    }

}
