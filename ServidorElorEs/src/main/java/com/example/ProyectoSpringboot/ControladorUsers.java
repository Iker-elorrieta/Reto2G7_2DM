package com.example.ProyectoSpringboot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Users;

import controlador.HibernateUtil;

@RestController
@RequestMapping("/users")

public class ControladorUsers {

	//Obtiene todos los Usuarios
	@GetMapping
	public List<Users> obtenerUsuarios() {
	    Session session = session();
	    List<Users> listaUsuarios = session.createQuery("FROM Users", Users.class).list();
	    //session.close();

	    return listaUsuarios;
	}


	//Crea Usuario
	@PostMapping
	public Users nuevoUsuario(@RequestBody Users nuevo) {
		Session session = session();
		Transaction tx = session.beginTransaction();
		
		session.persist(nuevo); //Guarda el nuevo (usuario) en base de datos
		tx.commit();
		session.close();
		
		return nuevo;
	}
	
	//Eliminar Usuario
	@DeleteMapping("/{id}")
	public String eliminarUsuario(@PathVariable int id) {
		Session session = session();
		Transaction tx = session.beginTransaction();
		
		String mensaje = "";
		Users u = session.get(Users.class, id);
		
		if(u == null) {
			session.close(); //No se ha encontrado el usuario. En la parte visual se enviaria mensaje si Usuario == null
			mensaje = "Usuario no encontrado";
		} else {
			session.remove(u);
			tx.commit();
			session.close();
			mensaje = "Usuario eliminado";
		}
		
		return mensaje;
	}
	
	//Modificar Usuario
	@PutMapping("/{id}")
	public String modificarUsuario(@PathVariable int id, @RequestBody Users actualizado) {
		Session session = session();
		Transaction tx = session.beginTransaction();
		
		String mensaje = "";
		Users u = session.get(Users.class, id);
		if(u == null) {
			session.close(); //No se ha encontrado el usuario. En la parte visual se enviaria mensaje si Usuario == null
			mensaje = "Usuario no encontrado";
		} else {
			/* Aqui se modifican los campos pero no estoy seguro de si 
			 * hay que modificar campos y en ese caso cuales serian
			 * 
			 * u.setUsername(datos.getUsername())*/
			
			//session.merge(); //cambiar parametro 
			tx.commit();
			session.close();
			
			mensaje = "Revisar ControladorUsers";
		}
		
		return mensaje;
	}
	
	
	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
	
	
}
