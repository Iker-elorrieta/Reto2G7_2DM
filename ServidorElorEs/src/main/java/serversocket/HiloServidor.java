package serversocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
            
            
            //========= LOGIN =========
            do {
                String usuario = recibeParametro.readUTF();
                String pwd = recibeParametro.readUTF();
                String hash = hash(pwd);

                Map<String, Object> usuarioMap = ctr.verificarDatosLogIn(usuario, hash);

                if (usuarioMap != null) {
                    acceso = true;
                    enviaParametro.writeUTF(new Gson().toJson(usuarioMap));
                } else {
                    enviaParametro.writeUTF("Usuario o contraseña erróneos");
                }

            } while (!acceso);

            while (true) {
                try {
                    String mensaje = recibeParametro.readUTF();

                    Type type = new TypeToken<Map<String, Object>>() {}.getType();
                    Map<String, Object> peticion = new Gson().fromJson(mensaje, type);

                    String accion = (String) peticion.get("accion");

                    switch (accion) {

                        case "ALUMNOS_DE_PROFESOR":
                            int profeId = ((Double) peticion.get("profeId")).intValue();
                            Map<String, Object> respuestaAlumnos = ctr.obtenerAlumnosProfesor(profeId);
                            enviaParametro.writeUTF(new Gson().toJson(respuestaAlumnos));
                            break;

                        case "HORARIO_PROFESOR":
                            int profesorId = ((Double) peticion.get("profeId")).intValue();
                            Map<String, Object> respuestaHorario = ctr.obtenerHorarioProfesor(profesorId);
                            enviaParametro.writeUTF(new Gson().toJson(respuestaHorario));
                            break;
                            
                        case "PROFESORES_LISTA":
                        	Map<String, Object> respuestaProfesores = ctr.obtenerListaProfesores();
							enviaParametro.writeUTF(new Gson().toJson(respuestaProfesores));
							break;

                        default:
                            System.out.println("no reconocida: " + accion);
                    }

                } catch (IOException e) {
                    System.out.println("Cliente desconectado");
                    break;
                }
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
