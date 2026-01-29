package ventanas;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class PanelReuniones extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable tablaReuniones;
	/**
	 * Create the panel.
	 * @param usuarioMap 
	 */
	public PanelReuniones(Map<String, Object> usuarioMap, int profesorId, Controlador ctr, CardLayout cardLayout) {
		setLayout(null);
	    setBounds(0, 0, 1144, 588); 
	    
	    JLabel lblReuniones = new JLabel("Panel Reuniones");
	    lblReuniones.setBounds(415, 11, 276, 30);
	    lblReuniones.setFont(new Font("Arial Black", Font.BOLD, 16));
	    add(lblReuniones);
	    
	    //Crear el modelo de la tabla con los días de la semana y las horas
        String[] columnas = {"", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        DefaultTableModel modeloReuniones = new DefaultTableModel(columnas, 0);
        
        //6 filas para las horas
        for (int i = 1; i <= 6; i++) {
            Object[] fila = new Object[6];
            fila[0] = "Hora " + i;
            modeloReuniones.addRow(fila);
        }
        //Crear la tabla con scroll pane
        tablaReuniones = new JTable(modeloReuniones);
        tablaReuniones.setEnabled(false);
        tablaReuniones.setRowHeight(40);
        tablaReuniones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaReuniones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tablaReuniones);
        scroll.setEnabled(false);
        scroll.setBounds(100, 80, 950, 366);
        add(scroll);
        
        ctr.cargarHorario(profesorId, modeloReuniones);
        
	}
	
	

}
