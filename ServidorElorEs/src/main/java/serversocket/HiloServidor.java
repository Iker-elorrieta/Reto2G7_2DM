package serversocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import controlador.Controlador;
import modelo.GestorLogin;
import modelo.Users;

public class HiloServidor extends Thread{
	private Socket cliente;
	private DataInputStream recibe;
	private DataOutputStream envia;
	private GestorLogin gestLogin;
	private Controlador ctr;
	private Users user;
	
	public HiloServidor(Socket cliente) {
		this.cliente = cliente;
	}
	
	public void run() {
		try {
			recibe = new DataInputStream(cliente.getInputStream());
			envia = new DataOutputStream(cliente.getOutputStream());
			gestLogin = new GestorLogin();
			
			//Recibe los datos que introduce el cliente en el login y la contraseña la hashea
			String usuario = recibe.readUTF();
			String pwd = recibe.readUTF();
			String hash = hash(pwd);
			
			user = ctr.verificarDatosLogIn(usuario, pwd);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String hash(String pwd) {
		String resumenString = new String();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte dataBytes[] = pwd.getBytes();
			md.update(dataBytes);
			byte resumen[] = md.digest();
			resumenString = new String(resumen);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resumenString;
	}
	
}
