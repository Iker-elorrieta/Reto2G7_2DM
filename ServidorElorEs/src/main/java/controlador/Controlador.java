package controlador;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import gestores.GestorLogin;

public class Controlador {

    private GestorLogin gestLogin = new GestorLogin();

    public Map<String, Object> verificarDatosLogIn(String usuario, String hashIntroducido) throws IOException {

        List<Map<String, Object>> usuarios = gestLogin.obtenerUsuarios();
        if (usuarios == null) return null;

        //Buscamos al usuario que coincida con el usuario introducido
        for (Map<String, Object> u : usuarios) {

            String usernameBD = (String) u.get("username");
            String passwordBD = (String) u.get("password");

            if (usernameBD.equals(usuario)) {

                //Compruebo el tipo de usuario
                @SuppressWarnings("unchecked")
                Map<String, Object> tipos = (Map<String, Object>) u.get("tipos");
                String tipo = (String) tipos.get("name");

                //Compruebo que sea profesor
                if (!tipo.equalsIgnoreCase("profesor")) {
                    return null;
                }

                //Hasheo la contraseña de la BD
                String pwdHashBD = hash(passwordBD);

                //Comparo las contraseñas
                if (pwdHashBD.equals(hashIntroducido)) {
                    return u; // Usuario valido
                } else {
                    return null;
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
}
