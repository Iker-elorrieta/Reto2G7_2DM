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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ventanas.Login;


public class Controlador {
	private Socket cliente;


	//=============== LOGIN ==============
	
	public LoginResult botonLogin(JLabel lblAvisoError, JTextField txtUsuario, JPasswordField pwdField) {
		
		//recoge los datos de los campos que ha introducido el usuario
	    String usuario = txtUsuario.getText();
	    String pwd = new String(pwdField.getPassword());

	    //Se conecta al servidor
	    try {
	        cliente = new Socket("localhost", 4000);
	        DataOutputStream enviaParametro = new DataOutputStream(cliente.getOutputStream());
	        DataInputStream recibeParametro = new DataInputStream(cliente.getInputStream());

	        //Enviamos al servidor los parametros (lineas 41 y 42 en HiloServidor.java)
	        enviaParametro.writeUTF(usuario);
	        enviaParametro.writeUTF(pwd);
	        
	        /*Recibimos el json que se envia en HiloServidor si coinciden las credenciales 
	         * Linea 56 o 59 en HiloServidor.java
	         */
	        String respuestaUsuario = recibeParametro.readUTF();

	        /*Comprobamos que la respuesta no sea el error
	         * y lo guardamos en LoginResult para no perder la conexion del socket.
	         * (como no se pueden pasar 2 objetos a la vez, creamos esta clase LoginResult 
	         * con 2 atributos: el map del usuario y el socket del cliente y le pasamos el objeto)
	         */
	        if (!respuestaUsuario.equals("Usuario o contraseña erróneos")) {
	        	
	        	Type type = new TypeToken<Map<String, Object>>() {}.getType();
	        	Map<String, Object> usuarioMap = new Gson().fromJson(respuestaUsuario, type);
	            return new LoginResult(usuarioMap, cliente);
                
	        } else { //Si no, mostramos el error en el lblAvisoError
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
	        //Creamos un map de una peticion
	    	Map<String, Object> peticion = Map.of(
	            "accion", "ALUMNOS_DE_PROFESOR",
	            "profeId", profesorId
	        );
	    	//convertimos la peticion a json
	        String jsonPeticion = new Gson().toJson(peticion);

	        //Enviamos al servidor el json de la peticion (linea 70 en HiloServidor.java)
	        DataOutputStream enviaParametro = new DataOutputStream(cliente.getOutputStream());
	        enviaParametro.writeUTF(jsonPeticion);
	        

	        //recibimos la respuesta (linea 90 en HiloServidor.java)
	        DataInputStream recibeParametro = new DataInputStream(cliente.getInputStream());
	        String jsonRespuesta = recibeParametro.readUTF();

	        //Parseamos la respuesta
	        Type type = new TypeToken<Map<String, Object>>() {}.getType();
	        Map<String, Object> respuesta = new Gson().fromJson(jsonRespuesta, type);

	        //Leemos la respuesta
	        if (!"OK".equals(respuesta.get("status"))) {
	            System.out.println("Error: " + respuesta.get("mensaje"));
	            return;
	        }

	        //Obtenemos la lista de Alumnos
			//Se hace 2 veces porque la respuesta json que envia el servidor tiene otro json dentro
	        Type tipoRespuestaCompleta = new TypeToken<Map<String, Object>>() {}.getType();
	        Map<String, Object> respuestaServidor = new Gson().fromJson(jsonRespuesta, tipoRespuestaCompleta);

	        //El json de alumnos que esta dentro de la respuesta del servidor
	        Type tipoListaAlumnos = new TypeToken<List<Map<String, Object>>>() {}.getType();
	        List<Map<String, Object>> alumnos = new Gson().fromJson(
	                new Gson().toJson(respuestaServidor.get("alumnos")),
	                tipoListaAlumnos	
	        );


	        //Rellenar tabla con los datos de los alumnos
	        modeloTablaAlumnos.setRowCount(0);

	        for (Map<String, Object> alum : alumnos) {
	            modeloTablaAlumnos.addRow(new Object[]{
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


	//=============== CARGAR HORARIO ==============
	
	public void cargarHorario(int profesorId, DefaultTableModel modeloHorario) {
		// TODO Auto-generated method stub
		
		//Creamos un json de una peticion, luego el servidor leera el json y
		//dependiendo de lo que pida el json, hara una cosa u otra
		
		//Creamos el map de la peticion
		Map<String, Object> peticionHorario = Map.of(
	            "accion", "HORARIO_PROFESOR",
	            "profeId", profesorId
				);
		
		//Convertimos la peticion a json
		String jsonPeticion = new Gson().toJson(peticionHorario);

		//Enviamos al servidor el json de la peticion
		try {
			DataOutputStream enviaParametro = new DataOutputStream(cliente.getOutputStream());
			enviaParametro.writeUTF(jsonPeticion);
				
			//recibimos la respuesta (linea 99 en HiloServidor.java)
			DataInputStream recibeParametro = new DataInputStream(cliente.getInputStream());
			String jsonRespuestaHorarios = recibeParametro.readUTF();

			//Parseamos la respuesta
			JsonObject respuesta = new Gson().fromJson(jsonRespuestaHorarios, JsonObject.class);

			//Comprobamos el estado de la respuesta del servidor
			String status = respuesta.get("status").getAsString(); 
			if (!status.equals("OK")) { 
				System.out.println("Error: " + respuesta.get("mensaje").getAsString());
				return; 
			}

			//Obtenemos la lista de Horario
			//Se hace 2 veces porque la respuesta json que envia el servidor tiene otro json dentro
			JsonArray horarioArray = respuesta.getAsJsonArray("horario");

			//El json de horario que esta dentro de la respuesta del servidor
			Type tipoLista = new TypeToken<List<List<Object>>>(){}.getType();
			List<List<Object>> listaHorario = new Gson().fromJson(horarioArray, tipoLista);


			//Rellenar tabla
			//Mapa para convertir el dia en columna
			Map<String, Integer> dias = Map.of(
					"LUNES", 1,
		            "MARTES", 2,
		            "MIERCOLES", 3,
		            "JUEVES", 4,
		            "VIERNES", 5
		        );

		        
			for (List<Object> fila : listaHorario) { 
				int hora = ((Number) fila.get(0)).intValue();
				String diaTexto = ((String) fila.get(1)).toUpperCase();
				String asignatura = (String) fila.get(2);
				
				int dia = dias.get(diaTexto);
				
				modeloHorario.setValueAt(asignatura, hora - 1, dia);
			}
		        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	//=============== CARGAR PROFESORES (para el horario) ==============
	
	public void cargarProfesores(DefaultTableModel modeloProfes) {

		//Creamos un json de una peticion, luego el servidor leera el json y
			//dependiendo de lo que pida el json, hara una cosa u otra
		Map<String, Object> peticion = Map.of(
	        "accion", "PROFESORES_LISTA"
	    );

		//Convertimos la peticion a json
	    String jsonPeticion = new Gson().toJson(peticion);

	    try {
	        //Enviar peticion
	        DataOutputStream enviaParametro = new DataOutputStream(cliente.getOutputStream());
	        enviaParametro.writeUTF(jsonPeticion);

	        //Recibir respuesta
	        DataInputStream reciveParametro = new DataInputStream(cliente.getInputStream());
	        String jsonRespuesta = reciveParametro.readUTF();

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

	        //Limpiar tabla
	        modeloProfes.setRowCount(0);

	        //Rellenar tabla
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
