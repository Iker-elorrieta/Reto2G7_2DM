package ventanas;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

import javax.swing.JScrollPane;

public class PanelAlumnos extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public PanelAlumnos(int profesorId, Controlador ctr) {

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
            new Object[] {"ID", "Nombre", "Apellidos", "Curso", "Ciclo"}, 
            0
        );
        table.setModel(modeloTablaAlumnos);

        ctr.cargarAlumnos(profesorId, modeloTablaAlumnos);
    }
}

