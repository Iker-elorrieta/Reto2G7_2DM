package controlador;

import java.io.IOException;

import com.example.ProyectoSpringboot.modelo.GestorLogin;
import com.example.ProyectoSpringboot.modelo.Users;

public class Controlador {
	private GestorLogin gestLogin = new GestorLogin();

 

	public Users verificarDatosLogIn(String usuario, String hash) throws IOException {
		// TODO Auto-generated method stub
		Users u = null;
		u = gestLogin.verificarDatosLogIn(usuario, hash);
		return u;
	}
	

}
