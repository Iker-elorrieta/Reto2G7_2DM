package ventanas;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelAlumnos extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public PanelAlumnos(Map<String, Object> usuarioMap) {

        setLayout(null);
        setBounds(0, 0, 1144, 588); 

        JLabel lblAlumnos = new JLabel("Panel Alumnos");
        lblAlumnos.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblAlumnos.setBounds(361, 0, 315, 30);
        add(lblAlumnos); 
        

        
        table = new JTable();
        table.setBounds(149, 91, 674, 336);
        add(table);
    }
}
