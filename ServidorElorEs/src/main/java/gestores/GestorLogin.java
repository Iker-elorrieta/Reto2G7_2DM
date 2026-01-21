package gestores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GestorLogin {
    
    private static final String BASE_URL = "http://localhost:8080/users";

    		//============ CAMBIAR ESTE CODIGO ===========//
    //==== LLAMADA A BASE DE DATOS DIRECTAMENTE NO A LA API ====//
    public List<Map<String, Object>> obtenerUsuarios() throws IOException {
        // Llamamos a la API para traer todos los usuarios
        URL url = new URL(BASE_URL);
        
        // LLamamos a la api y nos devuelve un JSON
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Accept", "application/json");
        
        // Respuesta de la conexion
        int status = conexion.getResponseCode();
        if (status != 200) {
            return null;
        }
        
        // Leemos el JSON
        BufferedReader leer = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        
        // Junta todas las lineas del JSON en un solo string
        StringBuilder response = new StringBuilder(); 
        String line;
        
        while ((line = leer.readLine()) != null) {
            response.append(line);
        }
        
        leer.close();
        conexion.disconnect();
        
        // Convertimos el JSON en una List<Map<String,Object>>
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType(); 
        List<Map<String, Object>> usuarios = gson.fromJson(response.toString(), listType);
        
        return usuarios;
    }
}
