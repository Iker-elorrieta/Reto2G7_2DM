package serversocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.ProyectoSpringboot.modelo.Users;

import controlador.Controlador;

public class HiloServidor extends Thread{
	private Socket cliente;
	private DataInputStream recibeParametro;
	private DataOutputStream enviaParametro;
	private ObjectInputStream recibeObjeto;
	private ObjectOutputStream enviaObjeto;
	private Controlador ctr = new Controlador();
	private Users user;
	
	public HiloServidor(Socket cliente) {
		this.cliente = cliente;
	}
	
	public void run() {
		try {
			recibeParametro = new DataInputStream(cliente.getInputStream());
			enviaParametro = new DataOutputStream(cliente.getOutputStream());
			
			//Recibe los datos que introduce el cliente en el login y la contraseña la hashea
			String usuario = recibeParametro.readUTF();
			String pwd = recibeParametro.readUTF();
			String hash = hash(pwd);
			
			user = ctr.verificarDatosLogIn(usuario, hash);
			if(user != null) {
				String usuarioRegistrado = user.getUsername();
				String claveRegistrada = user.getPassword();
				String claveRegistradaHash = hash(claveRegistrada);
				
				if(usuario.equals(usuarioRegistrado) && hash.equals(claveRegistradaHash)) {
					enviaParametro.writeBoolean(true);
					enviaParametro.writeUTF("Inicio de sesión correcto");
				} else {
					enviaParametro.writeUTF("Usuario o contraseña erróneos");
				}
			} else {
				enviaParametro.writeUTF("El usuario no existe");
			}
			
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
