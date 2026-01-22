package controlador;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import gestores.Gestor;

public class Controlador {

    private Gestor gestor = new Gestor();

    public Map<String, Object> verificarDatosLogIn(String usuario, String hashIntroducido) throws IOException {

        //El gestor devuelve List<Map<String,Object>>
        List<Map<String, Object>> usuarios = gestor.obtenerUsuarios();
        if (usuarios == null) {
        	return null;
        }

        //Buscamos al usuario que coincida con el usuario introducido
        for (Map<String, Object> u : usuarios) {

            String usernameBD = (String) u.get("username");
            String passwordBD = (String) u.get("password");
            String tipo = (String) u.get("tipo");

            //Comprobamos que sea correcto
            if (usernameBD.equals(usuario)) {

                //Solo permite profesores
                if (!tipo.equalsIgnoreCase("profesor")) {
                    return null;
                }

                // Hasheo la contraseña de la BD
                String pwdHashBD = hash(passwordBD);

                // Comparo las contraseñas
                if (pwdHashBD.equals(hashIntroducido)) {
                    return u; //Usuario valido 
                } else {
                    return null;
                }
            }
        }

        return null; //Usuario no encontrado
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
}
