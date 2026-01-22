package com.example.ProyectoSpringboot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Ciclos;

import controlador.HibernateUtil;

@RestController
@RequestMapping("/ciclos")
public class ControladorCiclos {
	
	
	//Opbtiene todos los ciclos
	@GetMapping
	public List<Ciclos> obtenerCiclos(){
		Session session = session();
		List<Ciclos> listaCiclos = session.createQuery("FROM Ciclos", Ciclos.class).list();
		
		return listaCiclos;
	}
	
	
	
	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
}
