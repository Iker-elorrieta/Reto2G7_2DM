package ventanas;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCrearReunion extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelCrearReunion(Map<String, Object> usuarioMap){
		
		setLayout(null);
	    setBounds(0, 0, 1144, 588); 
	    
	    JLabel lblCrearReunion = new JLabel("Panel Crear Reunión"); 
		lblCrearReunion.setFont(new Font("Arial Black", Font.BOLD, 16)); 
		lblCrearReunion.setBounds(321, 0, 330, 30); 
		add(lblCrearReunion);
	}

}
