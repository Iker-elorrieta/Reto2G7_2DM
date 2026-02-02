package ventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.google.gson.JsonObject;
import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

import javax.swing.JComboBox;

public class PanelCrearReunion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtTema;
	private JTextField txtAula;
	
	//Hemos creado estos mapas para mostrar nombres en los comboBox,
	//recuperar los IDs cuando seleccionas algo,
	//y enviar esos IDs al backend en el JSON
	private Map<String, Integer> mapaAlumnos = new java.util.HashMap<>();
	private Map<String, Integer> mapaCentros = new java.util.HashMap<>();

	public PanelCrearReunion(Map<String, Object> usuarioMap, int profesorId, CardLayout cardLayout, JPanel panelPrincipal, Controlador ctr){
		
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
		
		JComboBox<Object> comboBoxAlumnos = new JComboBox<Object>();
		comboBoxAlumnos.setBounds(260, 96, 300, 28);
		panelCard.add(comboBoxAlumnos);
		ctr.cargarAlumnosEnCombo(profesorId, comboBoxAlumnos, mapaAlumnos);

		JComboBox<Object> comboBoxUbicacion = new JComboBox<Object>();
		comboBoxUbicacion.setBounds(260, 260, 300, 28);
		panelCard.add(comboBoxUbicacion);
		ctr.cargarCentrosEnCombo(comboBoxUbicacion, mapaCentros);
		
		JComboBox<Object> comboBoxEstado = new JComboBox<Object>();
		comboBoxEstado.setBounds(260, 302, 300, 28);
		panelCard.add(comboBoxEstado);
		comboBoxEstado.addItem("Pendiente");
		comboBoxEstado.addItem("Conflicto");
		comboBoxEstado.addItem("Aceptada");
		comboBoxEstado.addItem("Cancelada");
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(260, 340, 150, 28);
		panelCard.add(dateChooser);
		
		String[] horas = new String[24];
		for (int i = 0; i < 24; i++) {
			horas[i] = String.format("%02d:00", i);
		}
		JComboBox<String> comboHora = new JComboBox<>(horas);
		comboHora.setBounds(426, 339, 100, 28);
		panelCard.add(comboHora);
		
		Date fecha = dateChooser.getDate();
		String hora = comboHora.getSelectedItem().toString();
		System.out.println("Fecha: " + fecha);
		System.out.println("Hora: " + hora);
		
		JButton btnCrear = new JButton("Crear reunión");
		btnCrear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // =================Obtener IDs reales================
		            String alumnoNombre = comboBoxAlumnos.getSelectedItem().toString(); //Obtenemos el nombre del alumnos seleccionado
		            int alumnoId = mapaAlumnos.get(alumnoNombre); //Obtenemos el ID del alumno seleccionado

		            String centroNombre = comboBoxUbicacion.getSelectedItem().toString();//Obtenemos el nombre del centro seleccionado
		            int centroId = mapaCentros.get(centroNombre);//Obtenemos el ID del centro seleccionado

		            //===================================================
		            
		            //===================OBTENEMOS EL RESTO DE CAMPOS DEL FORMULARIO=================
		            String estado = comboBoxEstado.getSelectedItem().toString();
		            String titulo = txtTitulo.getText();
		            String tema = txtTema.getText();
		            String aula = txtAula.getText();
		            //===============================================================================
		            
		            
		            //===================PROCESAMOS FECHA Y HORA=====================================
		            Date fecha = dateChooser.getDate();
		            if (fecha == null) {
		                JOptionPane.showMessageDialog(null, "Selecciona una fecha");
		                return;
		            }
		            // Obtener la hora seleccionada (formato HH:mm)
		            String horaStr = comboHora.getSelectedItem().toString();
		            LocalTime horaLocal = LocalTime.parse(horaStr);

		            // Convertir Date -> LocalDate
		            LocalDate fechaLocal = fecha.toInstant()
		                    .atZone(ZoneId.systemDefault())
		                    .toLocalDate();
		            // Combinar fecha + hora en un LocalDateTime
		            LocalDateTime fechaHora = LocalDateTime.of(fechaLocal, horaLocal);
		            //===============================================================================
		            
		            
		            //===================CONSTRUIR EL JSON QUE SE ENVIA AL BACKEND=================
		            JsonObject json = new JsonObject();
		            json.addProperty("profesor_id", profesorId);
		            json.addProperty("alumno_id", alumnoId);
		            json.addProperty("id_centro", centroId);
		            json.addProperty("estado", estado);
		            json.addProperty("titulo", titulo);
		            json.addProperty("tema", tema);
		            json.addProperty("aula", aula);
		            json.addProperty("fecha", fechaHora.toString());

		            // Convertimos el JSON a String para enviarlo
		            String jsonString = json.toString();
		            System.out.println("JSON enviado: " + jsonString);
		            //==============================================================================
		            
		            //=====================ENVIAR LA PETICION HTTP AL BACKEND=======================
		            HttpClient client = HttpClient.newHttpClient();

		            HttpRequest request = HttpRequest.newBuilder()
		                    .uri(URI.create("http://localhost:8080/reuniones"))
		                    .header("Content-Type", "application/json")
		                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
		                    .build();

		            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		            //==============================================================================
		            
		            //======================COMPROBAR RESPUESTA DEL SERVIDOR=======================
		            if (response.statusCode() == 200 || response.statusCode() == 201) {
		                JOptionPane.showMessageDialog(null, "Reunión creada correctamente");
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al crear la reunión: " + response.body());
		            }
		            //============================================================================
		            
		            
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		        }
		    }
		});


		btnCrear.setBounds(326, 400, 200, 35);
		panelCard.add(btnCrear);
		
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
	        
	        txtAula = new JTextField();
	        txtAula.setBounds(260, 219, 300, 28);
	        panelCard.add(txtAula);
	}
}
