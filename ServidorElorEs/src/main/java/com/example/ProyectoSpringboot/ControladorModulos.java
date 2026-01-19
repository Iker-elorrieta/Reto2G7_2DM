package com.example.ProyectoSpringboot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Modulos;

import controlador.HibernateUtil;

@RestController
@RequestMapping("/modulos")

public class ControladorModulos {
	
	@GetMapping
	public List<Modulos> obtenerModulos(){
		Session session = session();
		List<Modulos> listaModulos = session.createQuery("From Modulos", Modulos.class).list();
		return listaModulos;
	}
	
	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
	
}
