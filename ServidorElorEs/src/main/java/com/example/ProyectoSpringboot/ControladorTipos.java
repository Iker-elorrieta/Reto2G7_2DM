package com.example.ProyectoSpringboot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Tipos;

import controlador.HibernateUtil;

@RestController
@RequestMapping("/tipos")
public class ControladorTipos {

	@GetMapping
	public List<Tipos> obtenerTipos() {
	    Session session = session();
	    List<Tipos> listaTipos = session.createQuery("FROM Tipos", Tipos.class).list();

	    return listaTipos;
	}
	
	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
}
