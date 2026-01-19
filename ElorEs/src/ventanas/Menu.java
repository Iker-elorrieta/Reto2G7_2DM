package ventanas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelPrincipal;
    
    //CardLayout para cambiar entre paneles y que no se solape la informacion
    private CardLayout cardLayout;
    private JTextField txtNombre;
    private JTextField txtUserName;
    private JTextField txtDNI;
    private JTextField txtTelefono1;
    private JTextField txtApellidos;
    private JTextField txtEmail;
    private JTextField txtTelefono2;
    private JTextField txtDireccion;

    public Menu(Map<String, Object> usuarioMap, Socket cliente) {

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1160, 710);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        //----------------------------------------------------
        //    CARDLAYOUT Y PANEL PRINCIPAL
        //----------------------------------------------------
        
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout); //Asignamos el layout aqui
        panelPrincipal.setBounds(0, 83, 1144, 588);
        contentPane.add(panelPrincipal);

        
        //-----------------------------------------------------
        //    PANELES QUE SE MOSTRARÁN EN EL CARDLAYOUT
        //------------------------------------------------------

        //Panel Horario
        JPanel panelHorario = new JPanel();
        panelHorario.setLayout(null);
        JLabel lblHorario = new JLabel("Panel Horario ");
        lblHorario.setBounds(374, 0, 265, 30);
        lblHorario.setFont(new Font("Arial Black", Font.BOLD, 16));
        panelHorario.add(lblHorario);

        //Panel Otros Horarios
        JPanel panelOtrosHorarios = new JPanel();
        panelOtrosHorarios.setLayout(null);
        JLabel lblOtros = new JLabel("Panel Otros Horarios ");
        lblOtros.setBounds(356, 0, 315, 30);
        lblOtros.setFont(new Font("Arial Black", Font.BOLD, 16));
        panelOtrosHorarios.add(lblOtros);

        //Panel Reuniones
        JPanel panelReuniones = new JPanel();
        panelReuniones.setLayout(null);
        JLabel lblReuniones = new JLabel("Panel Reuniones");
        lblReuniones.setBounds(360, 0, 276, 30);
        lblReuniones.setFont(new Font("Arial Black", Font.BOLD, 16));
        panelReuniones.add(lblReuniones);
        
        //---------------------------------------------------------
        //    PANEL PERFIL
        //----------------------------------------------------------
        
   
        PanelPerfil panelPerfil = new PanelPerfil(usuarioMap);

        
        
        //---------------------------------------------------------
        //    PANEL CREAR REUNION
        //----------------------------------------------------------
        
        JPanel panelCrearReunion = new JPanel();
        panelCrearReunion.setLayout(null);
        JLabel lblCrearReunion = new JLabel("Panel Crear Reunión ");
        lblCrearReunion.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblCrearReunion.setBounds(321, 0, 330, 30);
        panelCrearReunion.add(lblCrearReunion);
        
        
        //---------------------------------------------------------
        //    PANEL ALUMNOS
        //----------------------------------------------------------
        
        JPanel panelAlumnos = new JPanel();
        panelAlumnos.setLayout(null);
        JLabel lblAlumnos = new JLabel("Panel Alumnos ");
        lblAlumnos.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblAlumnos.setBounds(361, 0, 315, 30);
        panelAlumnos.add(lblAlumnos);
        
       
        //--------------------------------------------------------
        //    AÑADIR LOS PANELES AL CARDLAYOUT CON UN NOMBRE
        //-------------------------------------------------------
        
        panelPrincipal.add(panelHorario, "horario");
        panelPrincipal.add(panelOtrosHorarios, "otrosHorarios");
        panelPrincipal.add(panelReuniones, "reuniones");
        panelPrincipal.add(panelPerfil, "perfil");
        panelPrincipal.add(panelCrearReunion, "crear reunion");
        panelPrincipal.add(panelAlumnos, "alumnos");
        

        //---------------------------------------------------------
        //    BARRA DE MENU
        //----------------------------------------------------------
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 128, 192));
        menuBar.setBounds(0, 0, 1144, 51);
        contentPane.add(menuBar);

        JMenu mnNewMenu = new JMenu("");
        mnNewMenu.setText("Menú     ");
        mnNewMenu.setFont(new Font("Arial", Font.BOLD, 14)); 
        mnNewMenu.setBackground(new Color(0, 128, 192)); 
        mnNewMenu.setForeground(Color.BLACK); 
        menuBar.add(mnNewMenu);

        
        //------------------------------------------------------
        //    OPCIONES DEL MENU Y GESTION DE LA NAVEGACION
        //-----------------------------------------------------

        
        //======================HORARIO====================================
        
        
        JMenuItem menuItemHorario = new JMenuItem("Horario");
        menuItemHorario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Mostrar el panel horario
        		cardLayout.show(panelPrincipal, "horario");
        	}
        });
        mnNewMenu.add(menuItemHorario);
        
        
        //======================PERFIL====================================

        
        JMenuItem menuItemPerfil = new JMenuItem("Perfil");
        menuItemPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(panelPrincipal, "perfil");
        	}
        });
        mnNewMenu.add(menuItemPerfil);
        
        
        //======================ALUMNOS====================================
        
        
        JMenuItem menuItemAlumnos = new JMenuItem("Alumnos");
        menuItemAlumnos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(panelPrincipal, "alumnos");
        	}
        });
        mnNewMenu.add(menuItemAlumnos);
        
        
        //======================OTROS HORARIOS====================================

        
        
        JMenuItem menuItemOtrosHorarios = new JMenuItem("Otros horarios");
        menuItemOtrosHorarios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "otrosHorarios");
        	}
        });
        mnNewMenu.add(menuItemOtrosHorarios);

        
        //======================VER REUNIONES====================================
        
        
        JMenuItem menuItemVerReuniones = new JMenuItem("Ver reuniones");
        menuItemVerReuniones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "reuniones");   
        	}
        });
        mnNewMenu.add(menuItemVerReuniones);
        
        
        //======================CREAR REUNION====================================

        
        JMenuItem menuItemCrearReunion = new JMenuItem("Crear reunión");
        menuItemCrearReunion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(panelPrincipal, "crear reunion");
        	}
        });
        mnNewMenu.add(menuItemCrearReunion);
        
                
                
                
        //---------------------------------------------------------
        //    BOTON DESCONECTAR
        //--------------------------------------------------------
        
        JButton btnDesconectar = new JButton("Desconectar");
        btnDesconectar.setBounds(987, 55, 147, 25);
        contentPane.add(btnDesconectar);
        btnDesconectar.setBackground(new Color(206, 57, 62));
        btnDesconectar.setFont(new Font("Arial", Font.BOLD, 13));

        btnDesconectar.addActionListener((ActionEvent e) -> {
            try {
                cliente.close(); //Cerramos el socket para este cliente
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Login ventanaLogin = new Login();
            ventanaLogin.setVisible(true);
            dispose();
        });
    }
}
