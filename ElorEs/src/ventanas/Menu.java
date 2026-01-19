package ventanas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelPrincipal;
    
    //CardLayout para cambiar entre paneles y que no se solape la informacion
    private CardLayout cardLayout;
   

    public Menu(Map<String, Object> usuarioMap, Socket cliente) {

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1160, 710);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //----------------------------------------------------
        //    CARDLAYOUT Y PANEL PRINCIPAL
        //----------------------------------------------------
        
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBounds(0, 83, 1144, 588);
        contentPane.add(panelPrincipal);

        //Paneles que se muestran en el cardLayout

        //---------------------------------------------------------
        //    PANEL HORARIOS
        //----------------------------------------------------------
      
        PanelHorarios panelHorarios = new PanelHorarios(usuarioMap);

        //---------------------------------------------------------
        //    PANEL OTROS HORARIOS
        //----------------------------------------------------------
        
        PanelOtrosHorarios panelOtrosHorarios = new PanelOtrosHorarios(usuarioMap);

        //---------------------------------------------------------
        //    PANEL REUNIONES
        //----------------------------------------------------------
       
        PanelReuniones panelReuniones = new PanelReuniones(usuarioMap);
        
        //---------------------------------------------------------
        //    PANEL PERFIL
        //----------------------------------------------------------
        
        PanelPerfil panelPerfil = new PanelPerfil(usuarioMap);
        
        //---------------------------------------------------------
        //    PANEL CREAR REUNION
        //----------------------------------------------------------
        
        PanelCrearReunion panelCrearReunion = new PanelCrearReunion(usuarioMap);
        
        //---------------------------------------------------------
        //    PANEL ALUMNOS
        //----------------------------------------------------------
        
        PanelAlumnos panelAlumnos = new PanelAlumnos(usuarioMap);
        
        //--------------------------------------------------------
        //    AÑADIR LOS PANELES AL CARDLAYOUT CON UN NOMBRE
        //-------------------------------------------------------
        
        panelPrincipal.add(panelHorarios, "horarios");
        panelPrincipal.add(panelOtrosHorarios, "otrosHorarios");
        panelPrincipal.add(panelReuniones, "reuniones");
        panelPrincipal.add(panelPerfil, "perfil");
        panelPrincipal.add(panelCrearReunion, "crear reunion");
        panelPrincipal.add(panelAlumnos, "alumnos");
        

        //---------------------------------------------------------
        //    BARRA DE MENU
        //----------------------------------------------------------
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1144, 51);
        menuBar.setBackground(new Color(0, 128, 192));
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
        		cardLayout.show(panelPrincipal, "horarios");
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
        btnDesconectar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    cliente.close(); //Cerramos el socket para este cliente
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Login ventanaLogin = new Login();
                ventanaLogin.setVisible(true);
                dispose();
        	}
        });
        btnDesconectar.setBounds(987, 55, 147, 25);
        contentPane.add(btnDesconectar);
        btnDesconectar.setBackground(new Color(206, 57, 62));
        btnDesconectar.setFont(new Font("Arial", Font.BOLD, 13));

       
    }
}
