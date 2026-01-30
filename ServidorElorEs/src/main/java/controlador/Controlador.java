package controlador;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import gestores.Gestor;

public class Controlador {

    private Gestor gestor = new Gestor();

    public Map<String, Object> verificarDatosLogIn(String usuario, String hashIntroducido) throws IOException {

        //Llamamos al gestor para obtener la lista de profesores y la guardamos en una lista
        List<Map<String, Object>> profesores = gestor.obtenerProfesores();
        //Comprobamos que haya devuelto algo
        if (profesores == null) {
            return null;
        }

        //Buscamos el profesor por username
        for (Map<String, Object> prof : profesores) {

            String usernameBD =  (String) prof.get("username");
            String passwordBD = (String) prof.get("password");

            //Coincide el usuario?
            if (usernameBD.equals(usuario)) {

                //Hashear la contraseña almacenada en BD
                String pwdHashBD = hash(passwordBD);

                //Comparar hashes
                if (pwdHashBD.equals(hashIntroducido)) {
                    return prof; //Login correcto, este "prof" es el profesor correcto y la recogemos en el servidor
                } else {
                    return null; //Contraseña incorrecta
                }
            }
        }
        return null; // Usuario no encontrado
    }


    

    private String hash(String pwd) {
        String pwdHasheada = new String();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte dataBytes[] = pwd.getBytes();
            md.update(dataBytes);
            byte resumen[] = md.digest();
            pwdHasheada = new String(resumen);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pwdHasheada;
    }


    
	public Map<String, Object> obtenerAlumnosProfesor(int profeId) {
		// TODO Auto-generated method stub
		//Llamamos al gestor para obtener la lista de alumnos de ese profesor
		List<Map<String, Object>> alumnos = gestor.obtenerAlumnosProfesor(profeId);
		
		if(alumnos == null) {
			return null;
		} else {
			//Devuelve un map con el estado OK y la lista de alumnos, "un json dentro de otro json"
			return Map.of(
					"status", "OK", 
					"alumnos", alumnos);
		}
	}



	public String obtenerHorarioProfesor(int profesorId) {

	    String horario = gestor.obtenerHorarioProfesor(profesorId);
	    JsonObject respuesta = new JsonObject();

	    if (horario == null || horario.isEmpty()) {
	    	respuesta.addProperty("status", "ERROR");
	    	respuesta.addProperty("mensaje", "No se pudo obtener el horario");
	    	} else { 
	    		respuesta.addProperty("status", "OK");
	    		JsonElement horarioJson = JsonParser.parseString(horario);
	    		respuesta.add("horario", horarioJson); 
	    		}

	    String json = new Gson().toJson(respuesta); 
	    return json;
	    
	}
	
	
	public String obtenerReunionesProfesor(int profesorId) { 
		String reuniones = gestor.obtenerReunionesProfesor(profesorId);
		JsonObject respuesta = new JsonObject();
		
		if (reuniones == null || reuniones.isEmpty()) {
			respuesta.addProperty("status", "ERROR");
			respuesta.addProperty("mensaje", "No se pudieron obtener las reuniones");
		} else {
			respuesta.addProperty("status", "OK");
			JsonElement reunionesJson = JsonParser.parseString(reuniones);
			respuesta.add("reuniones", reunionesJson);
		}
		
		String jsonReuniones = new Gson().toJson(respuesta);
		return jsonReuniones;
	}



	public Map<String, Object> obtenerListaProfesores() {

	    // Pedimos al gestor la lista de profesores
	    List<Map<String, Object>> profesores = gestor.obtenerProfesores();

	    Map<String, Object> respuesta = new HashMap<>();

	    if (profesores == null) {
	        respuesta.put("status", "ERROR");
	        respuesta.put("mensaje", "No se pudo obtener la lista de profesores");
	    } else {
	        respuesta.put("status", "OK");
	        respuesta.put("profesores", profesores);
	    }

	    return respuesta;
	}


}
