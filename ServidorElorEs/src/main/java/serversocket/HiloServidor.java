package serversocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import controlador.Controlador;

public class HiloServidor extends Thread{
    private Socket cliente;
    private DataInputStream recibeParametro;
    private DataOutputStream enviaParametro;
    private Controlador ctr = new Controlador();
    
    public HiloServidor(Socket cliente) {
        this.cliente = cliente;
    }
    
    public void run() {
        try {
            recibeParametro = new DataInputStream(cliente.getInputStream());
            enviaParametro = new DataOutputStream(cliente.getOutputStream());
            boolean acceso = false;
            
            do {
            	
                //Recibe los datos que introduce el cliente en el login y la contraseña la hashea
            	String usuario = recibeParametro.readUTF();
                String pwd = recibeParametro.readUTF();
                String hash = hash(pwd);

                // Llamamos al controlador (que ahora devuelve Map<String,Object>)
                 Map<String, Object> usuarioMap = ctr.verificarDatosLogIn(usuario, hash);
                
                if(usuarioMap != null) {
                	acceso = true;
                    //Convertimos el usuario en un json y enviamos el json
                    String json = new com.google.gson.Gson().toJson(usuarioMap);
                    enviaParametro.writeUTF(json);
                } else {
                    enviaParametro.writeUTF("Usuario o contraseña erróneos");
                }
                
            } while (acceso != true);
            
            
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
