package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1160, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(416, 0, 311, 671);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
		txtUsuario.setBounds(59, 348, 192, 33);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JTextField();
		txtClave.setFont(new Font("Arial", Font.PLAIN, 15));
		txtClave.setColumns(10);
		txtClave.setBounds(59, 449, 192, 33);
		panel.add(txtClave);
		
		JLabel lblNewLabel = new JLabel("Contraseña");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(59, 415, 108, 23);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(59, 314, 108, 23);
		panel.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Iniciar");
		btnLogin.setForeground(new Color(0, 128, 192));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(106, 531, 98, 47);
		panel.add(btnLogin);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(59, 65, 192, 187);
		panel.add(panelLogo);

	}
}
