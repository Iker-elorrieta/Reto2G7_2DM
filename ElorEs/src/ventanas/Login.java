package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Controlador;
import controlador.LoginResult;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Cursor;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField pwdField;
    private JLabel lblAvisoError;
    private Controlador ctr = new Controlador();

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                System.out.println("No es posible conectar con el servidor");
            }
        });
    }

    public Login() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1160, 710);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ================= FONDO =================
        ImageIcon fondoLogo = new ImageIcon("img/fondoLogin.jpg");
        JLabel lblFondo = new JLabel(fondoLogo);
        lblFondo.setBounds(0, 0, 1160, 710);
        contentPane.add(lblFondo);

        // ================= PANEL CENTRAL =================
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(380, 80, 400, 520);
        panel.setBackground(new Color(60, 60, 60, 160)); // negro semitransparente
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        lblFondo.add(panel);

        // ================= LOGO =================
        ImageIcon iconoLogin = new ImageIcon("img/LOGO.png");
        Image imgEscalada = iconoLogin.getImage().getScaledInstance(285, 95, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imgEscalada));
        lblLogo.setBounds(10, 30, 380, 140);
        panel.add(lblLogo);

        // ================= CAMPOS =================
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Reem Kufi", Font.BOLD, 17));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(60, 200, 200, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtUsuario.setBounds(60, 230, 280, 35);
        txtUsuario.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(txtUsuario);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setFont(new Font("Reem Kufi", Font.BOLD, 17));
        lblContraseña.setForeground(Color.WHITE);
        lblContraseña.setBounds(60, 280, 200, 25);
        panel.add(lblContraseña);

        pwdField = new JPasswordField();
        pwdField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        pwdField.setBounds(60, 310, 280, 35);
        pwdField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(pwdField);

        // ================= BOTÓN LOGIN =================
        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        btnLogin.setForeground(Color.BLACK);
        Color grisNormal = Color.LIGHT_GRAY; 
        Color grisHover = new Color(180, 180, 180); // gris más oscuro
        btnLogin.setBackground(Color.LIGHT_GRAY);
        btnLogin.setBounds(110, 380, 180, 45);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createEmptyBorder());
        
     // ===== EFECTO HOVER (color + agrandar) ===== 
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() { 
        	@Override 
        	public void mouseEntered(java.awt.event.MouseEvent evt) 
        	{ 
        		btnLogin.setBackground(grisHover); // Agrandar suavemente 
        		btnLogin.setBounds( btnLogin.getX() - 5, // mover un poco a la izquierda 
        		btnLogin.getY() - 3, // mover un poco arriba 
        		btnLogin.getWidth() + 10, // aumentar ancho 
        		btnLogin.getHeight() + 6 // aumentar alto 
        		); 
        		} 
        	@Override 
        	public void mouseExited(java.awt.event.MouseEvent evt) { 
        		btnLogin.setBackground(grisNormal); // Volver al tamaño original 
        		btnLogin.setBounds(110, 380, 180, 45);
        	}
        });
        panel.add(btnLogin);

        // ================= MENSAJE DE ERROR =================
        lblAvisoError = new JLabel("");
        lblAvisoError.setForeground(new Color(255, 80, 80));
        lblAvisoError.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAvisoError.setBounds(60, 350, 280, 25);
        panel.add(lblAvisoError);

        // ================= LÓGICA DEL BOTÓN =================
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                //Mandamos todo al controlador
            	LoginResult result = ctr.botonLogin(lblAvisoError, txtUsuario, pwdField);
            	
                if (result != null) {
                    Menu frame = new Menu(result.usuarioMap, result.cliente);
                    frame.setVisible(true);
                    dispose();
                }
            }
        });

    }
}
