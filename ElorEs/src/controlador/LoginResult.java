package controlador;

import java.net.Socket;
import java.util.Map;

public class LoginResult {

	public Map<String, Object> usuarioMap;
	public Socket cliente;

	public LoginResult(Map<String, Object> usuarioMap, Socket cliente) {
		this.usuarioMap = usuarioMap;
		this.cliente = cliente;
	}
	

}
