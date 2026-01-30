package ventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PanelCrearReunion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtTema;

	public PanelCrearReunion(Map<String, Object> usuarioMap, CardLayout cardLayout, JPanel panelPrincipal){
		
		setLayout(null);
	    setBounds(0, 0, 1144, 588);
		
		JPanel panelCard = new JPanel();
		panelCard.setLayout(null);
		panelCard.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2, true));
		panelCard.setBackground(new Color(60, 60, 60));
		panelCard.setBounds(217, 47, 750, 500);
		add(panelCard);
		
		JLabel lblCrearReunin = new JLabel("Crear Reunión");
		lblCrearReunin.setForeground(Color.WHITE);
		lblCrearReunin.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblCrearReunin.setBounds(298, 24, 300, 40);
		panelCard.add(lblCrearReunin);
		
		JLabel lblAlumno = new JLabel("Alumno");
		lblAlumno.setForeground(new Color(220, 220, 220));
		lblAlumno.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAlumno.setBounds(120, 100, 120, 20);
		panelCard.add(lblAlumno);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setForeground(new Color(220, 220, 220));
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTitulo.setBounds(120, 140, 120, 20);
		panelCard.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(260, 140, 300, 28);
		panelCard.add(txtTitulo);
		
		JLabel lblTema = new JLabel("Tema:");
		lblTema.setForeground(new Color(220, 220, 220));
		lblTema.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTema.setBounds(120, 180, 120, 20);
		panelCard.add(lblTema);
		
		txtTema = new JTextField();
		txtTema.setBounds(260, 180, 300, 28);
		panelCard.add(txtTema);
		
		JLabel lblAula = new JLabel("Aula:");
		lblAula.setForeground(new Color(220, 220, 220));
		lblAula.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAula.setBounds(120, 220, 120, 20);
		panelCard.add(lblAula);
		
		JLabel lblUbicacion = new JLabel("Ubicacion:");
		lblUbicacion.setForeground(new Color(220, 220, 220));
		lblUbicacion.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblUbicacion.setBounds(120, 260, 120, 20);
		panelCard.add(lblUbicacion);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(new Color(220, 220, 220));
		lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblEstado.setBounds(120, 300, 120, 20);
		panelCard.add(lblEstado);
		
		JLabel lblDaYHora = new JLabel("Día y Hora:");
		lblDaYHora.setForeground(new Color(220, 220, 220));
		lblDaYHora.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDaYHora.setBounds(120, 340, 120, 20);
		panelCard.add(lblDaYHora);
		
		JComboBox comboBoxAlumnos = new JComboBox();
		comboBoxAlumnos.setBounds(260, 96, 300, 28);
		panelCard.add(comboBoxAlumnos);
		
		JComboBox comboBoxAula = new JComboBox();
		comboBoxAula.setBounds(260, 219, 300, 28);
		panelCard.add(comboBoxAula);
		
		JComboBox comboBoxAlumnos_1 = new JComboBox();
		comboBoxAlumnos_1.setBounds(260, 260, 300, 28);
		panelCard.add(comboBoxAlumnos_1);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(260, 302, 300, 28);
		panelCard.add(comboBoxEstado);
		
		 JButton btnNewButton = new JButton("Volver");
		 btnNewButton.setBounds(67, 419, 89, 30);
		 panelCard.add(btnNewButton);
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		cardLayout.show(panelPrincipal, "menu");
	        	}
	        });
	        btnNewButton.setForeground(new Color(255, 255, 255));
	        btnNewButton.setBackground(new Color(0, 128, 192));
	        btnNewButton.setFont(new Font("Arial", Font.BOLD, 13));
	}
}
