package controlador;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gestores.Gestor;

public class Controlador {

    private Gestor gestor = new Gestor();

    public Map<String, Object> verificarDatosLogIn(String usuario, String hashIntroducido) throws IOException {

        //Obtenemos la lista de profesores
        List<Map<String, Object>> profesores = gestor.obtenerProfesores();
        if (profesores == null) {
            return null;
        }

        //Buscamos el profesor por username
        for (Map<String, Object> prof : profesores) {

            String usernameBD = (String) prof.get("username");
            String passwordBD = (String) prof.get("password");

            //Coincide el usuario?
            if (usernameBD.equals(usuario)) {

                //Hashear la contraseña almacenada en BD
                String pwdHashBD = hash(passwordBD);

                //Comparar hashes
                if (pwdHashBD.equals(hashIntroducido)) {
                    return prof; //Login correcto
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
		List<Map<String, Object>> alumnos = gestor.obtenerAlumnosProfesor(profeId);
		if(alumnos == null) {
			return null;
		} else {
			
			return Map.of(
					"status", "OK", 
					"alumnos", alumnos);
		}
	}



	public Map<String, Object> obtenerHorarioProfesor(int profesorId) {

	    List<Map<String, Object>> horario = gestor.obtenerHorarioProfesor(profesorId);
	    Map<String, Object> respuesta = new HashMap<>();

	    if (horario == null) {
	        respuesta.put("status", "ERROR");
	        respuesta.put("mensaje", "No se pudo obtener el horario");
	    } else {
	        respuesta.put("status", "OK");
	        respuesta.put("horario", horario);
	    }

	    return respuesta;
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
