package com.example.ProyectoSpringboot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
}
