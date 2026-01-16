package controlador;

import java.io.IOException;
import java.util.Map;

import com.example.ProyectoSpringboot.modelo.GestorLogin;

public class Controlador {
    private GestorLogin gestLogin = new GestorLogin();

    public Map<String, Object> verificarDatosLogIn(String usuario, String hash) throws IOException {
        // TODO Auto-generated method stub
        Map<String, Object> u = null;
        u = gestLogin.verificarDatosLogIn(usuario, hash);
        return u;
    }
}
