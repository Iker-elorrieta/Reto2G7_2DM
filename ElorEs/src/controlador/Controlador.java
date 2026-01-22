package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ventanas.Login;


public class Controlador {

	//=============== LOGIN ==============
	public LoginResult botonLogin(JLabel lblAvisoError, JTextField txtUsuario, JPasswordField pwdField) {

	    String usuario = txtUsuario.getText();
	    String pwd = new String(pwdField.getPassword());

	    try {
	        Socket cliente = new Socket("localhost", 4000);
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
	
	public void cargarAlumnos(DefaultTableModel modeloTablaAlumnos, Map<String, Object> usuarioMap) {
		// TODO Auto-generated method stub
		
	}


}
