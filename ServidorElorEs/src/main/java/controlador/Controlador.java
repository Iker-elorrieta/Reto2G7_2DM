package controlador;

import modelo.GestorLogin;
import modelo.Users;

public class Controlador {
	private GestorLogin gestLogin = new GestorLogin();

 

	public Users verificarDatosLogIn(String usuario, String pwd) {
		// TODO Auto-generated method stub
		Users u = gestLogin.verificarDatosLogIn(usuario, pwd);
		return u;
	}
	

}
