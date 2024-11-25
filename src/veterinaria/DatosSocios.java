	package veterinaria;
	
	import java.sql.Statement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	
	public class DatosSocios {
		ArrayList<Socio> socios = new ArrayList<>();
		boolean estaConectado;
		Statement sentencia;
	
		public DatosSocios(boolean estaConectado, Statement sentencia) {
			this.estaConectado = estaConectado;
			this.sentencia = sentencia;
		}
	
		// Método para registrar un nuevo socio
		public Socio registrarSocio(String nombre, String apellido, String direccion, int ci, String fechaNacimiento, Departamento departamento, int telefono, String fechaInscripcion) {
			boolean agregado = false;
			if (estaConectado) {
				String sentenciaInsertPersona = "INSERT INTO Persona (ci, nombre, apellido, direccion, fecha_nac) VALUES ('"
						+ ci + "','" + nombre + "','" + apellido + "','" + direccion + "','" + fechaNacimiento + "')";
	
				String sentenciaInsertSocio = "INSERT INTO Socio (ci, departamento, fecha_inscripcion) VALUES ('" + ci + "','" + departamento + "', '" + fechaInscripcion + "')";
	
				String sentenciaInsertSocioTel = "INSERT INTO Socio_Tel (ci, telefono) VALUES ('" + ci + "','" + telefono
						+ "')";
	
				try {
					// Ejecutar las sentencias en secuencia
					sentencia.executeUpdate(sentenciaInsertPersona);
					sentencia.executeUpdate(sentenciaInsertSocio);
					sentencia.executeUpdate(sentenciaInsertSocioTel);
	
					agregado = true;
				} catch (Exception e) {
					agregado = false;
					e.printStackTrace();
				}
	
			} 
			Socio nuevoSocio = new Socio(nombre, apellido, direccion, ci, fechaNacimiento, departamento, telefono, fechaInscripcion);
			socios.add(nuevoSocio);
			return nuevoSocio;
		}
	
		// Método para modificar un socio existente
		public boolean modificarSocio(int ci, String nuevoNombre, String nuevoApellido, String nuevaDireccion,
			String nuevaFechaNacimiento, Departamento nuevoDepartamento, int nuevoTelefono, String fechaInscripcion) {
			boolean modificado = false;
	
			if (estaConectado) {
				String sentenciaUpdatePersona = "UPDATE Persona SET nombre = '" + nuevoNombre + "', apellido = '"
						+ nuevoApellido + "', direccion = '" + nuevaDireccion + "', fecha_nac = '" + nuevaFechaNacimiento
						+ "' WHERE ci = '" + ci + "'";
	
				String sentenciaUpdateSocio = "UPDATE Socio SET fecha_inscripcion = '" + fechaInscripcion + "' WHERE ci = '" + ci + "'";
	
				String sentenciaUpdateSocioTel = "UPDATE Socio_Tel SET telefono = '" + nuevoTelefono + "' WHERE ci = '" + ci
						+ "'";
	
				try {
					sentencia.executeUpdate(sentenciaUpdatePersona);
					sentencia.executeUpdate(sentenciaUpdateSocio);
					sentencia.executeUpdate(sentenciaUpdateSocioTel);
	
					modificado = true;
				} catch (Exception e) {
					modificado = false;
					e.printStackTrace();
				}
			} else {
				for (Socio socio : socios) {
					if (socio.getCi() == ci) {
						socio.setNombre(nuevoNombre);
						socio.setApellido(nuevoApellido);
						socio.setDireccion(nuevaDireccion);
						socio.setFechaNacimiento(nuevaFechaNacimiento);
						socio.setDepartamento(nuevoDepartamento);
						socio.setTelefono(nuevoTelefono);
						modificado = true;
						break;
					}
				}
			}
	
			return modificado;
		}
	
		// Método para eliminar un socio existente
		public boolean eliminarSocio(int ci) {
			boolean eliminado = false;
	
			if (estaConectado) {
				String sentenciaDeleteSocioTel = "DELETE FROM Socio_Tel WHERE ci = '" + ci + "'";
				String sentenciaDeleteSocio = "DELETE FROM Socio WHERE ci = '" + ci + "'";
				String sentenciaDeletePersona = "DELETE FROM Persona WHERE ci = '" + ci + "'";
	
				try {
					// Ejecutar las sentencias en orden para eliminar el socio
					sentencia.executeUpdate(sentenciaDeleteSocioTel);
					sentencia.executeUpdate(sentenciaDeleteSocio);
					sentencia.executeUpdate(sentenciaDeletePersona);
	
					eliminado = true;
				} catch (Exception e) {
					eliminado = false;
					e.printStackTrace();
				}
			}
	
			// Si se eliminó de la base de datos o si no hay conexión, quitar de la lista en
			// memoria
			if (eliminado || !estaConectado) {
				socios.removeIf(socio -> socio.getCi() == ci);
				eliminado = true;
			}
	
			return eliminado;
		}
	
		// Método para obtener y actualizar la lista de socios
		public ArrayList<Socio> obtenerSocios() {
			if (estaConectado) {
				socios.clear(); // Limpiar la lista en memoria para evitar duplicados
				String sentenciaSelect = "SELECT * FROM Persona P, Socio S, Socio_Tel ST WHERE S.ci = P.ci and ST.ci = P.ci ORDER BY fecha_inscripcion desc";
	
				  try {
			            ResultSet rs = sentencia.executeQuery(sentenciaSelect);
			            while (rs.next()) {
			                Socio socio = new Socio(
			                    rs.getString("nombre"), 
			                    rs.getString("apellido"), 
			                    rs.getString("direccion"),
			                    rs.getInt("ci"), 
			                    rs.getString("fecha_nac"),
			                    Departamento.valueOf(rs.getString("departamento")),
			                    rs.getInt("telefono"),
			                    rs.getString("fecha_inscripcion") // Mover dentro del constructor
			                );
			                socios.add(socio);
												}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Si no hay conexión, retorna los datos que ya están en memoria
			return socios;
		}
	
		// Método para buscar y devolver un socio por su CI
		public Socio buscarSocioPorCi(int ci) {
			if (estaConectado) {
				String sentenciaSelect = "SELECT * FROM Persona P, Socio S, Socio_Tel ST "
						+ "WHERE P.ci = S.ci AND P.ci = ST.ci AND P.ci = '" + ci + "'";
	
				try {
	
					ResultSet rs = sentencia.executeQuery(sentenciaSelect);
	
					if (rs.next()) {
		                // Crea y devuelve el objeto Socio a partir de los datos obtenidos
		                return new Socio(
		                    rs.getString("nombre"), 
		                    rs.getString("apellido"), 
		                    rs.getString("direccion"),
		                    rs.getInt("ci"), 
		                    rs.getString("fecha_nac"),
		                    Departamento.valueOf(rs.getString("departamento")),
		                    rs.getInt("telefono"),
		                    rs.getString("fecha_inscripcion") // Mover dentro del constructor y sin punto y coma
		                );
		            }
				} catch (Exception e) {
					e.printStackTrace();
					return null; // Si no se encuentra retorna null
				}
	
			} else {
				// Si no hay conexión, buscar en la lista de socios en memoria
				for (Socio socio : socios) {
					if (socio.getCi() == ci) {
						return socio;
					}
				}
			}
	
			// Retorna null si no se encuentra el socio
			return null;
		}
	
	}
