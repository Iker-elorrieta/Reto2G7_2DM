package ventanas;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPerfil extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre, txtUserName, txtDNI, txtTelefono1, txtApellidos, txtEmail, txtTelefono2, txtDireccion;

    public PanelPerfil(Map<String, Object> usuarioMap) {
        setLayout(null);
	    setBounds(0, 0, 1144, 588); 

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
        lblNombre.setBounds(223, 57, 95, 14);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Arial", Font.BOLD, 14));
        txtNombre.setEditable(false);
        txtNombre.setBounds(403, 53, 163, 20);
        add(txtNombre);

        JLabel lblUserName = new JLabel("Username:");
        lblUserName.setFont(new Font("Arial", Font.BOLD, 13));
        lblUserName.setBounds(223, 87, 95, 14);
        add(lblUserName);

        txtUserName = new JTextField();
        txtUserName.setFont(new Font("Arial", Font.BOLD, 14));
        txtUserName.setEditable(false);
        txtUserName.setBounds(403, 84, 163, 20);
        add(txtUserName);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setFont(new Font("Arial", Font.BOLD, 13));
        lblDNI.setBounds(223, 118, 95, 14);
        add(lblDNI);

        txtDNI = new JTextField();
        txtDNI.setFont(new Font("Arial", Font.BOLD, 14));
        txtDNI.setEditable(false);
        txtDNI.setBounds(403, 115, 163, 20);
        add(txtDNI);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
        lblEmail.setBounds(223, 150, 95, 14);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Arial", Font.BOLD, 14));
        txtEmail.setEditable(false);
        txtEmail.setBounds(403, 146, 302, 20);
        add(txtEmail);

        JLabel lblTlf = new JLabel("Teléfono:");
        lblTlf.setFont(new Font("Arial", Font.BOLD, 13));
        lblTlf.setBounds(223, 180, 95, 14);
        add(lblTlf);

        txtTelefono1 = new JTextField();
        txtTelefono1.setFont(new Font("Arial", Font.BOLD, 14));
        txtTelefono1.setEditable(false);
        txtTelefono1.setBounds(403, 177, 146, 20);
        add(txtTelefono1);

        txtTelefono2 = new JTextField();
        txtTelefono2.setFont(new Font("Arial", Font.BOLD, 14));
        txtTelefono2.setEditable(false);
        txtTelefono2.setBounds(559, 177, 146, 20);
        add(txtTelefono2);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(new Font("Arial", Font.BOLD, 13));
        lblDireccion.setBounds(223, 205, 95, 14);
        add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setFont(new Font("Arial", Font.BOLD, 14));
        txtDireccion.setEditable(false);
        txtDireccion.setBounds(403, 208, 302, 20);
        add(txtDireccion);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setFont(new Font("Arial", Font.BOLD, 13));
        lblApellidos.setBounds(223, 235, 95, 14);
        add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setFont(new Font("Arial", Font.BOLD, 14));
        txtApellidos.setEditable(false);
        txtApellidos.setBounds(403, 232, 302, 20);
        add(txtApellidos);

        // Rellenar datos
        txtNombre.setText((String) usuarioMap.get("nombre"));
        txtApellidos.setText((String) usuarioMap.get("apellidos"));
        txtDNI.setText((String) usuarioMap.get("dni"));
        txtTelefono1.setText(String.valueOf(usuarioMap.get("telefono1")));
        txtTelefono2.setText(String.valueOf(usuarioMap.get("telefono2")));
        txtEmail.setText((String) usuarioMap.get("email"));
        txtDireccion.setText((String) usuarioMap.get("direccion"));
        txtUserName.setText((String) usuarioMap.get("username"));
    }
}
