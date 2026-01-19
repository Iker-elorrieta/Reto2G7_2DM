package com.example.ProyectoSpringboot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Matriculaciones;

import controlador.HibernateUtil;

@RestController
@RequestMapping("/matriculaciones")
public class ControladorMatriculaciones {
	
	@GetMapping
	public List<Matriculaciones> obtenerMatriculaciones(){
		Session session = session();
		List<Matriculaciones> listaMatriculaciones = session.createQuery("From Matriculaciones", Matriculaciones.class).list();
		
		return listaMatriculaciones;
	}

	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
	
}
