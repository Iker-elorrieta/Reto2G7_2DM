package gestores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.ProyectoSpringboot.modelo.Users;


import controlador.HibernateUtil;

public class GestorLogin {
    

    		//============ CAMBIAR ESTE CODIGO ===========//
    //==== LLAMADA A BASE DE DATOS DIRECTAMENTE NO A LA API ====//
    public List<Users> obtenerUsuarios() {
	    Session session = session();
	    List<Users> listaUsuarios = session.createQuery("FROM Users", Users.class).list();
	    //session.close();

	    return listaUsuarios;
	}
    
    private Session session() {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		return session;
	}
 
}
