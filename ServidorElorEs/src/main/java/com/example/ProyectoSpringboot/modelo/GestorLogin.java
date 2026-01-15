package com.example.ProyectoSpringboot.modelo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import controlador.HibernateUtil;

public class GestorLogin {

	public Users verificarDatosLogIn(String usuario, String pwd) {
		// TODO Auto-generated method stub
		Session session = session();
		Users user = null;
		String query = "from Users where username = :usuario AND password = :pwd";
		Query<Users> q = session.createQuery(query, Users.class);
		q.setParameter("usuario", usuario); 
		q.setParameter("pwd", pwd);
		user = q.uniqueResult();
		
		session.close();
		
		return user;
	}
	
	private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}

}
