package ventanas; 

import java.awt.CardLayout; 
import java.awt.Font; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel; 
import controlador.Controlador; 
import javax.swing.JButton; 
import java.awt.Color; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
public class PanelOtrosHorarios extends JPanel { 
	private static final long serialVersionUID = 1L; 
	public PanelOtrosHorarios(Controlador ctr, CardLayout cardLayout, JPanel panelPrincipal) { 
		setLayout(null); setBounds(0, 0, 1144, 588); JLabel lblOtros = new JLabel("Horarios de otros profesores"); 
		lblOtros.setBounds(356, 10, 400, 30); lblOtros.setFont(new Font("Arial Black", Font.BOLD, 16)); 
		add(lblOtros); 
		
		// Tabla de profesores 
		String[] columnas = {"ID", "Nombre", "Apellidos"}; 
		DefaultTableModel modeloProfes = new DefaultTableModel(columnas, 0); 
		JTable tablaProfes = new JTable(modeloProfes); 
		JScrollPane scroll = new JScrollPane(tablaProfes); 
		scroll.setBounds(100, 80, 600, 350); add(scroll); 
		
		// Boton Volver 
		JButton btnVolver = new JButton("Volver"); 
		btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "menu")); 
		btnVolver.setForeground(Color.WHITE); btnVolver.setBackground(new Color(0, 128, 192)); 
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13)); 
		btnVolver.setBounds(100, 507, 89, 35); 
		add(btnVolver); 
		
		// Botón Ver horario 
		JButton btnVerHorario = new JButton("Ver horario"); 
		btnVerHorario.setForeground(Color.WHITE); 
		btnVerHorario.setBackground(new Color(0, 150, 100)); 
		btnVerHorario.setFont(new Font("Arial", Font.BOLD, 13)); 
		btnVerHorario.setBounds(220, 507, 120, 35); 
		add(btnVerHorario); 
		
		// Cargar profesores desde el servidor 
		ctr.cargarProfesores(modeloProfes); 
		
		// Acción del botón Ver horario 
		btnVerHorario.addActionListener(new ActionListener() { 
			@Override public void actionPerformed(ActionEvent e) { 
				int fila = tablaProfes.getSelectedRow(); 
				if (fila == -1) { 
					System.out.println("Selecciona un profesor primero"); 
					return; 
				} 
				int profesorId = (int) modeloProfes.getValueAt(fila, 0); 
				PanelHorarios panelHorario = new PanelHorarios(profesorId, ctr, cardLayout, panelPrincipal); 
				panelPrincipal.add(panelHorario, "horarioProfesor"); 
				cardLayout.show(panelPrincipal, "horarioProfesor"); 
				} 
			}); 
		} 

}
