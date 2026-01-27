package gestores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.ProyectoSpringboot.modelo.Users;


import controlador.HibernateUtil;

public class Gestor {
    

    //============ GESTOR LOGIN ===========//
   
	public List<Map<String, Object>> obtenerProfesores() {
		
		//Gestion de la conexion a la base de datos
	    Session session = session();
	    
	    //Query para obtener solo los profesores
	    String todosProfesores = Querys.PROFESORES;
	    List<Users> listaProfesores = session.createQuery(todosProfesores, Users.class).list();

	    List<Map<String, Object>> listaMap = new ArrayList<>();

	    //Obtenemos todos los datos de los profesores y los guardamos en la lista
	    for (Users u : listaProfesores) {
	        Map<String, Object> map = new HashMap<>();
	        map.put("id", u.getId());
	        map.put("username", u.getUsername());
	        map.put("password", u.getPassword());
	        map.put("tipo", u.getTipos().getName());
	        map.put("email", u.getEmail());
	        map.put("nombre", u.getNombre());
	        map.put("apellidos", u.getApellidos());
	        map.put("telefono1", u.getTelefono1());
	        map.put("telefono2", u.getTelefono2());
	        map.put("direccion", u.getDireccion());
	        map.put("dni", u.getDni());
	        listaMap.add(map);
	    }
	    return listaMap;
	}
	
	//============ OBTENER ALUMNOS POR PROFESOR ===========//

	public List<Map<String, Object>> obtenerAlumnosProfesor(int profeId) {
		// TODO Auto-generated method stub
		Session session = session();
		//Query para obtener los alumnos de un profesor concreto
		String alumnosProfesor = Querys.ALUMNOS_DE_PROFESOR;
		
		Users profesorObj = session.get(Users.class, profeId);

		List<Object[]> listaAlumnos = session.createQuery(alumnosProfesor, Object[].class).setParameter("profesor", profesorObj).getResultList();
		
		List<Map<String, Object>> listaMap = new ArrayList<>();

		//Recorremos la lista de alumnos y los guardamos en un map
		for (Object[] fila: listaAlumnos) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", fila[0]);
			map.put("nombre", fila[1]);
			map.put("apellidos", fila[2]);
			map.put("email", fila[3]);
			map.put("curso", fila[4]);
			map.put("ciclo", fila[5]);
			listaMap.add(map);
		}
		return listaMap;
	}
	
   
	//============ OBTENER HORARIO DEL PROFESOR (ID) ===========//


	public List<Map<String, Object>> obtenerHorarioProfesor(int profesorId) {

	    Session session = session();
	    String horarioProfesor = Querys.HORARIO_PROFESOR;
	    
		Users profesorObj = session.get(Users.class, profesorId);

	    List<Object[]> listaHorario = session.createQuery(horarioProfesor, Object[].class).setParameter("profesor", profesorObj).getResultList();

	    List<Map<String, Object>> listaMap = new ArrayList<>();

	    for (Object[] fila : listaHorario) {
	        Map<String, Object> map = new HashMap<>();
	        map.put("hora", fila[0]);
	        map.put("dia", fila[1]);
	        map.put("asignatura", fila[2]);
	        listaMap.add(map);
	    }
	    return listaMap;
	}

	


	
	
	 private Session session() {
			// TODO Auto-generated method stub
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			return session;
		}


 
}
