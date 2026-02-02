package gestores;

public class Querys {
	
	public static final String PROFESORES = "FROM Users u WHERE u.tipos.name = 'profesor'";
	
	/*
	 * Desde la tabla horarios saltamos a modulo, de modulo a ciclo, 
	 * de ciclo a matriculaciones y de matriculacionesa a usuarios
	 * Filtramos por el id del profesor y ordenamos por apellidos y nombre
	 */
	public static final String ALUMNOS_DE_PROFESOR =
		    "SELECT DISTINCT u.id, u.nombre, u.apellidos, u.email, m.curso, c.nombre " +
		    "FROM Horarios h " +
		    "JOIN h.modulos mo " +
		    "JOIN mo.ciclos c " +
		    "JOIN Matriculaciones m ON m.ciclos = c " +
		    "JOIN m.users u " +
		    "WHERE h.users = :profesor " +
		    "ORDER BY u.apellidos, u.nombre";

	
	public static final String HORARIO_PROFESOR =
		    "SELECT h.hora, h.dia, mo.nombre " +
		    "FROM Horarios h " +
		    "JOIN h.modulos mo " +
		    "WHERE h.users = :profesor";
	
	public static final String REUNIONES_PROFESOR =
			"SELECT r.fecha, r.titulo, r.asunto, r.aula "  +
			"FROM Reuniones r " +
			"WHERE r.usersByProfesorId.id = :profesorId " +
			"ORDER BY r.fecha";
}
