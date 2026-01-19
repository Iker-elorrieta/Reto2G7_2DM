package ventanas;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAlumnos extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelAlumnos(Map<String, Object> usuarioMap) {

        setLayout(null);
        setBounds(0, 0, 1144, 588); 

        JLabel lblAlumnos = new JLabel("Panel Alumnos");
        lblAlumnos.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblAlumnos.setBounds(361, 0, 315, 30);
        add(lblAlumnos); 
    }
}
