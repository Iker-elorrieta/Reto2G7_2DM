package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ventanas.Login;


public class Controlador {
	private Socket cliente;

	//=============== LOGIN ==============
	
	public LoginResult botonLogin(JLabel lblAvisoError, JTextField txtUsuario, JPasswordField pwdField) {

	    String usuario = txtUsuario.getText();
	    String pwd = new String(pwdField.getPassword());

	    try {
	        cliente = new Socket("localhost", 4000);
	        DataOutputStream enviaParametro = new DataOutputStream(cliente.getOutputStream());
	        DataInputStream recibeParametro = new DataInputStream(cliente.getInputStream());

	        //Enviamos al servidor los parametros
	        enviaParametro.writeUTF(usuario);
	        enviaParametro.writeUTF(pwd);
	        
	        //Recibimos el json que se envia en HiloServidor si coinciden las credenciales
	        String respuestaUsuario = recibeParametro.readUTF();

	        if (!respuestaUsuario.equals("Usuario o contraseña erróneos")) {
	        	
	        	Type type = new TypeToken<Map<String, Object>>() {}.getType();
	        	Map<String, Object> usuarioMap = new Gson().fromJson(respuestaUsuario, type);
	            return new LoginResult(usuarioMap, cliente);
                
	        } else {
	            lblAvisoError.setText(respuestaUsuario);
	            txtUsuario.setText("");
	            pwdField.setText("");
	           
	        }

	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return null;
	}

	
	//=============== CERAR SESION ==============
	
	public void cerrarSesion(Socket cliente) {
		// TODO Auto-generated method stub
		 try {
             cliente.close();
             Login ventanaLogin = new Login();
             ventanaLogin.setVisible(true);
         } catch (IOException ex) {
             ex.printStackTrace();
         }
	}

	//=============== PERFIL ==============
	
	public void rellenarDatosPerfil(JTextField txtNombre, JTextField txtApellidos, JTextField txtDNI, JTextField txtTelefono1, JTextField txtTelefono2, JTextField txtEmail, JTextField txtDireccion, JTextField txtUserName, Map<String, Object> usuarioMap) {
		// TODO Auto-generated method stub
		txtNombre.setText((String) usuarioMap.get("nombre"));
        txtApellidos.setText((String) usuarioMap.get("apellidos"));
        txtDNI.setText((String) usuarioMap.get("dni"));
        txtTelefono1.setText(String.valueOf(usuarioMap.get("telefono1")));
        txtTelefono2.setText(String.valueOf(usuarioMap.get("telefono2")));
        txtEmail.setText((String) usuarioMap.get("email"));
        txtDireccion.setText((String) usuarioMap.get("direccion"));
        txtUserName.setText((String) usuarioMap.get("username"));
	}


	//=============== CARGAR ALUMNOS ==============
	
	public void cargarAlumnos(int profesorId, DefaultTableModel modeloTablaAlumnos) {
	    try {
	        //Creamos un json de una peticion
	        Map<String, Object> peticion = Map.of(
	            "accion", "ALUMNOS_DE_PROFESOR",
	            "profeId", profesorId
	        );

	        String jsonPeticion = new Gson().toJson(peticion);

	        //Enviamos al servidor la peticion
	        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
	        dos.writeUTF(jsonPeticion);
	        

	        //recibimos la respuesta
	        DataInputStream dis = new DataInputStream(cliente.getInputStream());
	        String jsonRespuesta = dis.readUTF();

	        //Parseamos la respuesta
	        Type type = new TypeToken<Map<String, Object>>() {}.getType();
	        Map<String, Object> respuesta = new Gson().fromJson(jsonRespuesta, type);

	        if (!"OK".equals(respuesta.get("status"))) {
	            System.out.println("Error: " + respuesta.get("mensaje"));
	            return;
	        }

	        //Obtenemos la lista de Alumnos
	        Type type2 = new TypeToken<Map<String, Object>>() {}.getType();
	        Map<String, Object> respuesta2 = new Gson().fromJson(jsonRespuesta, type2);

	        Type listaType = new TypeToken<List<Map<String, Object>>>() {}.getType();
	        List<Map<String, Object>> alumnos = new Gson().fromJson(
	                new Gson().toJson(respuesta2.get("alumnos")),
	                listaType
	        );


	        // 6. Rellenar tabla
	        modeloTablaAlumnos.setRowCount(0);

	        for (Map<String, Object> alum : alumnos) {
	            modeloTablaAlumnos.addRow(new Object[]{
	                alum.get("id"),
	                alum.get("nombre"),
	                alum.get("apellidos"),
	                alum.get("curso"),
	                alum.get("ciclo")
	            });
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public void cargarHorario(int profesorId, DefaultTableModel modeloHorario) {
		// TODO Auto-generated method stub
		Map<String, Object> peticionHorario = Map.of(
	            "accion", "HORARIO_PROFESOR",
	            "profeId", profesorId
	        );

	        String jsonPeticion = new Gson().toJson(peticionHorario);

	        //Enviamos al servidor la peticion
	        try {
				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
				dos.writeUTF(jsonPeticion);
				
				//recibimos la respuesta
		        DataInputStream dis = new DataInputStream(cliente.getInputStream());
		        String jsonRespuesta = dis.readUTF();
		        
		        //Parseamos la respuesta
		        Type type = new TypeToken<Map<String, Object>>() {}.getType();
		        Map<String, Object> respuesta = new Gson().fromJson(jsonRespuesta, type);
		        
		        if (!"OK".equals(respuesta.get("status"))) {
		            System.out.println("Error: " + respuesta.get("mensaje"));
		            return;
		        }
		        
		        //Obtenemos la lista de Horario
		        Type type2 = new TypeToken<Map<String, Object>>() {}.getType();
		        Map<String, Object> respuesta2 = new Gson().fromJson(jsonRespuesta, type2);
		        
		        Type listaType = new TypeToken<List<Map<String, Object>>>() {}.getType();
		        List<Map<String, Object>> horario = new Gson().fromJson(
		                new Gson().toJson(respuesta2.get("horario")),
		                listaType
		        );
		        
		        // Rellenar tabla
		        // Mapa para convertir el día en columna
		        Map<String, Integer> dias = Map.of(
		            "LUNES", 1,
		            "MARTES", 2,
		            "MIERCOLES", 3,
		            "JUEVES", 4,
		            "VIERNES", 5
		        );

		        for (Map<String, Object> clase : horario) {

		            // DIA siempre es String
		            String diaTexto = ((String) clase.get("dia")).toUpperCase();
		            int dia = dias.get(diaTexto);

		            // HORA puede ser Number o String → convertir de forma segura
		            Object horaObj = clase.get("hora");
		            int hora;

		            if (horaObj instanceof Number) {
		                hora = ((Number) horaObj).intValue();
		            } else {
		                hora = Integer.parseInt((String) horaObj);
		            }

		            // Asignatura
		            String asignatura = (String) clase.get("asignatura");

		            // Rellenar celda
		            modeloHorario.setValueAt(asignatura, hora - 1, dia);
		        }

		        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	public void cargarProfesores(DefaultTableModel modeloProfes) {

	    //Peticion que le voy a enviar al servidor
	    Map<String, Object> peticion = Map.of(
	        "accion", "PROFESORES_LISTA"
	    );

	    String jsonPeticion = new Gson().toJson(peticion);

	    try {
	        //Enviar peticion
	        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
	        dos.writeUTF(jsonPeticion);

	        //Recibir respuesta
	        DataInputStream dis = new DataInputStream(cliente.getInputStream());
	        String jsonRespuesta = dis.readUTF();

	        //Parsear respuesta
	        Type type = new TypeToken<Map<String, Object>>() {}.getType();
	        Map<String, Object> respuesta = new Gson().fromJson(jsonRespuesta, type);

	        //Comprobar estado
	        if (!"OK".equals(respuesta.get("status"))) {
	            System.out.println("Error: " + respuesta.get("mensaje"));
	            return;
	        }

	        //Obtener lista de profesores
	        Type listaType = new TypeToken<List<Map<String, Object>>>() {}.getType();
	        List<Map<String, Object>> profesores = new Gson().fromJson(
	            new Gson().toJson(respuesta.get("profesores")),
	            listaType
	        );

	        // Limpiar tabla
	        modeloProfes.setRowCount(0);

	        // Rellenar tabla
	        for (Map<String, Object> prof : profesores) {

	            int id = ((Number) prof.get("id")).intValue();
	            String nombre = (String) prof.get("nombre");
	            String apellidos = (String) prof.get("apellidos");

	            modeloProfes.addRow(new Object[]{id, nombre, apellidos});
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}




}
