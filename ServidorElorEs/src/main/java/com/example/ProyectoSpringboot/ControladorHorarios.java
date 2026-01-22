package com.example.ProyectoSpringboot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSpringboot.modelo.Horarios;

import controlador.HibernateUtil;


@RestController
@RequestMapping("/horarios")
public class ControladorHorarios {
	
	//Obtiene Horarios
	@GetMapping
	public List<Horarios> obtenerHorarios(){
		Session session = session();
		List<Horarios> listaHorarios = session.createQuery("From Horarios", Horarios.class).list();
		
		return listaHorarios;
	}
	
	
	
	

	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
}
