package com.example.ProyectoSpringboot.modelo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class GestorLogin {
    
    private static final String BASE_URL = "http://localhost:8080/users";

    
    public Map<String, Object> verificarDatosLogIn(String usuario, String hashIntroducido) throws IOException{
        //Llamamos a la API para traer todos los usuarios
        URL url = new URL(BASE_URL);
        Map<String, Object> usuarioEncontrado = null; //Usuario que vamos a devolver si encuentra y coincide
        
        //LLamamos a la api y nos devuelve un JSON
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Accept", "application/json");
        
        int status = conexion.getResponseCode();
        if(status != 200) {
            usuarioEncontrado = null;
        }
        
        //Leemos el JSON
        BufferedReader leer = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        //Junta todas las lineas del JSON en un solo string
        StringBuilder response = new StringBuilder(); 
        String line;
        
        while((line = leer.readLine()) != null) {
            response.append(line);
        }
        
        leer.close();
        conexion.disconnect();
        
        //Convertimos el JSON en una List<Map<String,Object>>
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType(); 
        List<Map<String, Object>> usuarios = gson.fromJson(response.toString(), listType);
        
        
        
        //Buscamos al usuario que coincida con el usuario introducido
        //Cuando lo encuentra recoge la contraseña y la hashea
        //Compara los 2 hashes, el introducido(ya hasheado) y el encontrado (hasheado ahora)
        
        for(int i = 0; i < usuarios.size(); i ++) {
            
            Map<String, Object> u = usuarios.get(i);
            
            String usernameBD = (String) u.get("username");
            String passwordBD = (String) u.get("password");
            
            if(usernameBD.equals(usuario)) {//Busqueda de usuario y mando la contraseña a hashear
                
                String pwdHashBD = hash(passwordBD);
                
                if(pwdHashBD.equals(hashIntroducido)) { //Comparo las contraseñas
                    usuarioEncontrado = u; //Asociamos el usuario encontrado a la variable declarada arriba
                } else {
                    usuarioEncontrado = null;
                }
            }
        }
        
        return usuarioEncontrado;
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
