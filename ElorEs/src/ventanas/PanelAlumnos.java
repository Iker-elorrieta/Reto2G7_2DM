package ventanas;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAlumnos extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public PanelAlumnos(int profesorId, Controlador ctr, CardLayout cardLayout, JPanel panelPrincipal) {

        setLayout(null);
        setBounds(0, 0, 1144, 588); 

        JLabel lblAlumnos = new JLabel("Panel Alumnos");
        lblAlumnos.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblAlumnos.setBounds(361, 0, 315, 30);
        add(lblAlumnos);
        
        JScrollPane scrollPaneAlumnos = new JScrollPane();
        scrollPaneAlumnos.setBounds(117, 129, 846, 298);
        add(scrollPaneAlumnos);
        
        table = new JTable();
        scrollPaneAlumnos.setViewportView(table);
        
        DefaultTableModel modeloTablaAlumnos = new DefaultTableModel(
            new Object[] {"Nombre", "Apellidos", "Curso", "Ciclo"}, 
            0
        );
        table.setModel(modeloTablaAlumnos);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "menu");
            }
        });
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setBackground(new Color(0, 128, 192));
        btnVolver.setBounds(117, 506, 89, 30);
        add(btnVolver);

        ctr.cargarAlumnos(profesorId, modeloTablaAlumnos);
    }
}
