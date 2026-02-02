package com.example.ProyectoSpringboot;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Reuniones;

import controlador.HibernateUtil;

@RestController
@RequestMapping("/reuniones")
public class ControladorReuniones {
	
	@GetMapping
	public List<Reuniones> obtenerReuniones() {
	    Session session = session();
	    List<Reuniones> listaReuniones = session.createQuery("FROM Reuniones", Reuniones.class).list();

	    return listaReuniones;
	}
	
	@PostMapping
	public ResponseEntity<?> crearReunion(@RequestBody Map<String, Object> datos) {

	    Session session = session();
	    session.beginTransaction();

	    try {
	        Reuniones r = new Reuniones();
	        // Asignamos el alumno seleccionado (solo el ID)
	        // Este setter crea internamente un Users con ese ID
	        r.setAlumno_id((Integer) datos.get("alumno_id"));
	        r.setProfesor_id((Integer) datos.get("profesor_id")); // Asignamos el id logueado
	        r.setId_centro((Integer) datos.get("id_centro")); //asignamos solo el id del centro seleccionado

	        r.setEstado((String) datos.get("estado"));
	        r.setTitulo((String) datos.get("titulo"));
	        r.setAsunto((String) datos.get("tema"));
	        r.setAula((String) datos.get("aula"));

	        // Convertimos la fecha ISO-8601 (2026-02-02T14:00) 
	        //a Timestamp válido para Hibernate
	        String fechaStr = (String) datos.get("fecha");
	        r.setFecha(Timestamp.valueOf(fechaStr.replace("T", " ") + ":00"));

	        // Guardamos la reunión en la base de datos
	        session.persist(r);
	        session.getTransaction().commit();

	        return ResponseEntity.ok(r);

	    } catch (Exception e) {
	        session.getTransaction().rollback(); //Si algo falla revertimos la transacción
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al crear la reunión");
	    } finally {
	        session.close();
	    }
	}



	
	
	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
}
