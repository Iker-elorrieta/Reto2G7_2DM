package ventanas;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHorarios extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelHorarios(Map<String, Object> usuarioMap) {
		
		setLayout(null);
	    setBounds(0, 0, 1144, 588); 
		
		JLabel lblHorario = new JLabel("Panel Horario ");
		lblHorario.setBounds(374, 0, 265, 30);
		lblHorario.setFont(new Font("Arial Black", Font.BOLD, 16));
		add(lblHorario);
	}

}
