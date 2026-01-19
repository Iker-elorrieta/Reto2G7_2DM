package ventanas;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelReuniones extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * @param usuarioMap 
	 */
	public PanelReuniones(Map<String, Object> usuarioMap) {
		setLayout(null);
	    setBounds(0, 0, 1144, 588); 
	    
	    JLabel lblReuniones = new JLabel("Panel Reuniones");
	    lblReuniones.setBounds(360, 0, 276, 30);
	    lblReuniones.setFont(new Font("Arial Black", Font.BOLD, 16));
	    add(lblReuniones);
	}

}
