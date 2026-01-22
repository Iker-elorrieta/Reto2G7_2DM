package ventanas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.SystemColor;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

    public Menu(Map<String, Object> usuarioMap, Socket cliente, Controlador ctr) {
    	
    	// Arreglar color azul del menú 
    	UIManager.put("Menu.selectionBackground", new Color(0, 128, 192)); 
    	UIManager.put("MenuItem.selectionBackground", new Color(0, 100, 160)); 
    	UIManager.put("Menu.selectionForeground", Color.WHITE); 
    	UIManager.put("MenuItem.selectionForeground", Color.WHITE);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1160, 710);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Fondo general elegante
        contentPane.setBackground(new Color(40, 40, 40));
        setContentPane(contentPane);

        //----------------------------------------------------
        //    CARDLAYOUT Y PANEL PRINCIPAL
        //----------------------------------------------------
        
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBounds(0, 83, 1144, 588);

        // Borde elegante
        panelPrincipal.setBackground(new Color(50, 50, 50));
        panelPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(80, 80, 80), 2));

        contentPane.add(panelPrincipal);

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
        System.out.println(usuarioMap);
        
        //---------------------------------------------------------
        //    PANEL CREAR REUNION
        //----------------------------------------------------------
        PanelCrearReunion panelCrearReunion = new PanelCrearReunion(usuarioMap);
        
        //---------------------------------------------------------
        //    PANEL ALUMNOS
        //----------------------------------------------------------
        PanelAlumnos panelAlumnos = new PanelAlumnos(usuarioMap, ctr);
        
        //--------------------------------------------------------
        //    AÑADIR LOS PANELES AL CARDLAYOUT
        //-------------------------------------------------------
        
        panelPrincipal.add(panelHorarios, "horarios");
        panelPrincipal.add(panelOtrosHorarios, "otrosHorarios");
        panelPrincipal.add(panelReuniones, "reuniones");
        panelPrincipal.add(panelPerfil, "perfil");
        panelPrincipal.add(panelCrearReunion, "crear reunion");
        panelPrincipal.add(panelAlumnos, "alumnos");
        

        //---------------------------------------------------------
        //    BARRA DE MENU (ESTILO ELEGANTE)
        //----------------------------------------------------------
        
        JMenuBar menuBar = new JMenuBar(); 
        menuBar.setBounds(0, 0, 1144, 51); 
        menuBar.setBackground(SystemColor.scrollbar); 
        menuBar.setBorderPainted(false); 
        menuBar.setLayout(new java.awt.BorderLayout()); 
        contentPane.add(menuBar);

        JMenu mnNewMenu = new JMenu("   Menú   ");
        mnNewMenu.setBackground(new Color(240, 240, 240));
        mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mnNewMenu.setForeground(Color.WHITE);
        mnNewMenu.setOpaque(false);
        menuBar.add(mnNewMenu);
        
     

        //------------------------------------------------------
        //    ESTILO PARA ITEMS DEL MENÚ
        //-----------------------------------------------------

        Font fontItems = new Font("Segoe UI", Font.PLAIN, 14);
        Color hoverColor = new Color(100, 0, 0);
        Color normalColor = new Color(100, 0, 0);

        java.util.function.Consumer<JMenuItem> estilizarItem = item -> {
            item.setFont(fontItems);
            item.setBackground(normalColor);
            item.setForeground(Color.WHITE);
            item.setOpaque(true);

            item.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    item.setBackground(hoverColor);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    item.setBackground(normalColor);
                }
            });
        };

        //------------------------------------------------------
        //    OPCIONES DEL MENU
        //-----------------------------------------------------

        JMenuItem menuItemHorario = new JMenuItem("Horario");
        estilizarItem.accept(menuItemHorario);
        menuItemHorario.addActionListener(e -> cardLayout.show(panelPrincipal, "horarios"));
        mnNewMenu.add(menuItemHorario);

        JMenuItem menuItemPerfil = new JMenuItem("Perfil");
        estilizarItem.accept(menuItemPerfil);
        menuItemPerfil.addActionListener(e -> cardLayout.show(panelPrincipal, "perfil"));
        mnNewMenu.add(menuItemPerfil);

        JMenuItem menuItemAlumnos = new JMenuItem("Alumnos");
        estilizarItem.accept(menuItemAlumnos);
        menuItemAlumnos.addActionListener(e -> cardLayout.show(panelPrincipal, "alumnos"));
        mnNewMenu.add(menuItemAlumnos);

        JMenuItem menuItemOtrosHorarios = new JMenuItem("Otros horarios");
        estilizarItem.accept(menuItemOtrosHorarios);
        menuItemOtrosHorarios.addActionListener(e -> cardLayout.show(panelPrincipal, "otrosHorarios"));
        mnNewMenu.add(menuItemOtrosHorarios);

        JMenuItem menuItemVerReuniones = new JMenuItem("Ver reuniones");
        estilizarItem.accept(menuItemVerReuniones);
        menuItemVerReuniones.addActionListener(e -> cardLayout.show(panelPrincipal, "reuniones"));
        mnNewMenu.add(menuItemVerReuniones);

        JMenuItem menuItemCrearReunion = new JMenuItem("Crear reunión");
        estilizarItem.accept(menuItemCrearReunion);
        menuItemCrearReunion.addActionListener(e -> cardLayout.show(panelPrincipal, "crear reunion"));
        mnNewMenu.add(menuItemCrearReunion);

        //---------------------------------------------------------
        //    BOTON DESCONECTAR (ELEGANTE + HOVER)
        //--------------------------------------------------------
        
        JButton btnDesconectar = new JButton("Desconectar");
        btnDesconectar.setBounds(987, 55, 147, 25);
        btnDesconectar.setBackground(new Color(200, 50, 50));
        btnDesconectar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDesconectar.setForeground(Color.WHITE);
        btnDesconectar.setFocusPainted(false);
        btnDesconectar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnDesconectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Hover elegante
        btnDesconectar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDesconectar.setBackground(new Color(170, 40, 40));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDesconectar.setBackground(new Color(200, 50, 50));
            }
        });

        btnDesconectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Lo enviamos al controlador
            	ctr.cerrarSesion(cliente);
                dispose();
            }
        });

        contentPane.add(btnDesconectar);
    }
}
