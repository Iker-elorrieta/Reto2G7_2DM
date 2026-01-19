package ventanas;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelOtrosHorarios extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * @param usuarioMap 
	 */
	public PanelOtrosHorarios(Map<String, Object> usuarioMap) {
		setLayout(null);
	    setBounds(0, 0, 1144, 588); 
		
	    setLayout(null);
        JLabel lblOtros = new JLabel("Panel Otros Horarios ");
        lblOtros.setBounds(356, 0, 315, 30);
        lblOtros.setFont(new Font("Arial Black", Font.BOLD, 16));
        add(lblOtros);
	}

}
