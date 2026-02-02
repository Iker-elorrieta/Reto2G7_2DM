package ventanas;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelHorarios extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tablaHorario;

    public PanelHorarios(int profesorId, Controlador ctr, CardLayout cardLayout, JPanel panelPrincipal) {

        setLayout(null);
        setBounds(0, 0, 1144, 588);
        setBackground(new Color(50, 50, 50));


        JLabel lblTitulo = new JLabel("Mi horario semanal");
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(450, 20, 300, 30);
        add(lblTitulo);

        //Crear el modelo de la tabla con los días de la semana y las horas
        String[] columnas = {"", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        DefaultTableModel modeloHorario = new DefaultTableModel(columnas, 0);
        
        //6 filas para las horas
        for (int i = 1; i <= 6; i++) {
            Object[] fila = new Object[6];
            fila[0] = "Hora " + i;
            modeloHorario.addRow(fila);
        }
        //Crear la tabla con scroll pane
        tablaHorario = new JTable(modeloHorario);
        tablaHorario.setEnabled(false);
        tablaHorario.setRowHeight(40);
        tablaHorario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaHorario.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tablaHorario);
        scroll.setEnabled(false);
        scroll.setBounds(100, 80, 950, 366);
        add(scroll);
        
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(panelPrincipal, "menu");
        	}
        });
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(0, 128, 192));
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 13));
        btnNewButton.setBounds(100, 482, 89, 30);
        add(btnNewButton);

        //Cargar datos desde el servidor
        //Le pasamos el id del profesor logeado y el modelo de la tabla para rellenarla
        ctr.cargarHorario(profesorId, modeloHorario);
    }
}

