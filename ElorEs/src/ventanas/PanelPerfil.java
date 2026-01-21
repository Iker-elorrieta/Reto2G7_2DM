package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador;


public class PanelPerfil extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtNombre, txtUserName, txtDNI, txtTelefono1, txtApellidos, txtEmail, txtTelefono2, txtDireccion;
    private Controlador ctr = new Controlador();
    
    public PanelPerfil(Map<String, Object> usuarioMap) {

        setLayout(null);
        setBounds(0, 0, 1144, 588);
        setBackground(new Color(45, 45, 45)); // fondo elegante oscuro

        // ============================
        // PANEL CONTENEDOR ELEGANTE
        // ============================
        JPanel panelCard = new JPanel();
        panelCard.setLayout(null);
        panelCard.setBounds(200, 40, 750, 500);
        panelCard.setBackground(new Color(60, 60, 60));
        panelCard.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2, true));
        add(panelCard);

        // Título elegante
        JLabel lblTitulo = new JLabel("Perfil del Usuario");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(260, 20, 300, 40);
        panelCard.add(lblTitulo);

        // ============================
        // ESTILO GENERAL PARA LABELS
        // ============================
        Font labelFont = new Font("Segoe UI", Font.BOLD, 15);
        Color labelColor = new Color(220, 220, 220);

        // ============================
        // ESTILO GENERAL PARA TEXTFIELDS
        // ============================
        Font textFont = new Font("Segoe UI", Font.PLAIN, 15);
        Color textBg = new Color(80, 80, 80);
        Color textFg = Color.WHITE;
        Color textBorder = new Color(140, 140, 140);

        // MÉTODO PARA APLICAR ESTILO A TEXTFIELDS
        java.util.function.Consumer<JTextField> estilizarCampo = campo -> {
            campo.setFont(textFont);
            campo.setEditable(false);
            campo.setBackground(textBg);
            campo.setForeground(textFg);
            campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(textBorder, 1),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)
            ));
        };

        // ============================
        // CAMPOS Y LABELS
        // ============================

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(labelFont);
        lblNombre.setForeground(labelColor);
        lblNombre.setBounds(120, 100, 120, 20);
        panelCard.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(260, 100, 300, 28);
        estilizarCampo.accept(txtNombre);
        panelCard.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setFont(labelFont);
        lblApellidos.setForeground(labelColor);
        lblApellidos.setBounds(120, 140, 120, 20);
        panelCard.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(260, 140, 300, 28);
        estilizarCampo.accept(txtApellidos);
        panelCard.add(txtApellidos);

        JLabel lblUserName = new JLabel("Username:");
        lblUserName.setFont(labelFont);
        lblUserName.setForeground(labelColor);
        lblUserName.setBounds(120, 180, 120, 20);
        panelCard.add(lblUserName);

        txtUserName = new JTextField();
        txtUserName.setBounds(260, 180, 300, 28);
        estilizarCampo.accept(txtUserName);
        panelCard.add(txtUserName);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setFont(labelFont);
        lblDNI.setForeground(labelColor);
        lblDNI.setBounds(120, 220, 120, 20);
        panelCard.add(lblDNI);

        txtDNI = new JTextField();
        txtDNI.setBounds(260, 220, 300, 28);
        estilizarCampo.accept(txtDNI);
        panelCard.add(txtDNI);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        lblEmail.setForeground(labelColor);
        lblEmail.setBounds(120, 260, 120, 20);
        panelCard.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(260, 260, 300, 28);
        estilizarCampo.accept(txtEmail);
        panelCard.add(txtEmail);

        JLabel lblTlf = new JLabel("Teléfonos:");
        lblTlf.setFont(labelFont);
        lblTlf.setForeground(labelColor);
        lblTlf.setBounds(120, 300, 120, 20);
        panelCard.add(lblTlf);

        txtTelefono1 = new JTextField();
        txtTelefono1.setBounds(260, 300, 140, 28);
        estilizarCampo.accept(txtTelefono1);
        panelCard.add(txtTelefono1);

        txtTelefono2 = new JTextField();
        txtTelefono2.setBounds(420, 300, 140, 28);
        estilizarCampo.accept(txtTelefono2);
        panelCard.add(txtTelefono2);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(labelFont);
        lblDireccion.setForeground(labelColor);
        lblDireccion.setBounds(120, 340, 120, 20);
        panelCard.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(260, 340, 300, 28);
        estilizarCampo.accept(txtDireccion);
        panelCard.add(txtDireccion);

        // ============================
        // RELLENAR DATOS
        // ============================
        ctr.rellenarDatosPerfil(txtNombre, 
        		txtApellidos, 
        		txtDNI, 
        		txtTelefono1, 
        		txtTelefono2, 
        		txtEmail, 
        		txtDireccion, 
        		txtUserName, 
        		usuarioMap 
        );
       
    }
}
