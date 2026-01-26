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
            
            //Bucle hasta que acceso == true
            do {
            	//Parametros recibidos: usuario, pwd y haseahmos la contraseña (lineas 37 y 38 en ElosEs Controlador.java)
                String usuario = recibeParametro.readUTF();
                String pwd = recibeParametro.readUTF();
                String hash = hash(pwd);

                //Enviamos a controlador para verificar datos y lo guardamos en un map
                Map<String, Object> usuarioMap = ctr.verificarDatosLogIn(usuario, hash);

                //Comprobamos que el map no sea nulo y damos acceso 
                if (usuarioMap != null) {
                    acceso = true;
                    /*
                     * Enviamos al cliente el map del usuario convertido a JSON, 
                     * es decir le enviamos el json con los datos del usuario
                     * Linea 41 en ElorEs Controlador.java  
                    */
                    enviaParametro.writeUTF(new Gson().toJson(usuarioMap));
                } else {
                	//Si no le enviamos un mensaje de error que se mostrara en el lblAvisoError en Login.java
                    enviaParametro.writeUTF("Usuario o contraseña erróneos");
                }

            } while (!acceso);

            
            //========= PETICIONES DE MENU =========
            
            //Bucle infinito para atender peticiones del cliente
            while (true) {
                try {
                	//Recibe el json de la peticion (Linea 112, 178, 264 en ElorEs Controlador.java)
                    String mensaje = recibeParametro.readUTF();

                    //Convertimos el JSON recibido a un map
                    Type type = new TypeToken<Map<String, Object>>() {}.getType();
                    Map<String, Object> peticion = new Gson().fromJson(mensaje, type);
                    
                    //Recoge el valor del atributo "accion" del map peticion
                    String accion = (String) peticion.get("accion");
                    
                    //Dependiendo de la accion, realizamos una cosa u otra
                    switch (accion) {

                    	//============ OBTENER ALUMNOS DE PROFESOR ===========//
                    
                        case "ALUMNOS_DE_PROFESOR":
                        	//Recoge el valor del otro atributo, "profeId"
                            int profeId = ((Double) peticion.get("profeId")).intValue();
                            //Llamamos al controlador para obtener los alumnos de ese profesor y lo guardamos en un map
                            Map<String, Object> respuestaAlumnos = ctr.obtenerAlumnosProfesor(profeId);
                            //Enviamos al cliente el map convertido a JSON (linea 117 en ElorEs Controlador.java)
                            enviaParametro.writeUTF(new Gson().toJson(respuestaAlumnos));
                            break;

                        //============ OBTENER HORARIO DE PROFESOR ===========//
                            
                        case "HORARIO_PROFESOR":
                            int profesorId = ((Double) peticion.get("profeId")).intValue();
                            Map<String, Object> respuestaHorario = ctr.obtenerHorarioProfesor(profesorId);
                            //(Linea 185 en ElorEs Controlador.java)
                            enviaParametro.writeUTF(new Gson().toJson(respuestaHorario));
                            break;
                            
						//============ OBTENER LISTA DE PROFESORES ===========//
                            
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
