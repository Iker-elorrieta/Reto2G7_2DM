package gestores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.ProyectoSpringboot.modelo.Users;


import controlador.HibernateUtil;

public class GestorLogin {
    

    		//============ CAMBIAR ESTE CODIGO ===========//
    //==== LLAMADA A BASE DE DATOS DIRECTAMENTE NO A LA API ====//
	public List<Map<String, Object>> obtenerUsuarios() {
	    Session session = session();
	    List<Users> listaUsuarios = session.createQuery("FROM Users", Users.class).list();

	    List<Map<String, Object>> listaMap = new ArrayList<>();

	    for (Users u : listaUsuarios) {
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

    
    private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
 
}
