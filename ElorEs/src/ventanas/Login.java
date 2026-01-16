package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private static Socket cliente;
    private JPasswordField pwdField;
    private DataOutputStream enviaParametro;
    private DataInputStream recibeParametro;
    private JLabel lblAvisoError;

    public static void main(String[] args) {

        try {
            cliente = new Socket("localhost", 4000);
            System.out.println("Conectado al servidor");

            EventQueue.invokeLater(() -> {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println("No es posible conectar con el servidor");
                }
            });

        } catch (IOException ee) {
            System.out.println("**No es posible conectar con el servidor**");
        }
    }

    public Login() {
        setResizable(false);
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

        JLabel lblNewLabel = new JLabel("Contraseña");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(59, 415, 108, 23);
        panel.add(lblNewLabel);

        pwdField = new JPasswordField();
        pwdField.setFont(new Font("Arial", Font.BOLD, 14));
        pwdField.setBounds(59, 449, 192, 33);
        panel.add(pwdField);

        JLabel lblNewLabel_1 = new JLabel("Usuario");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(59, 314, 108, 23);
        panel.add(lblNewLabel_1);

        JButton btnLogin = new JButton("Iniciar");
        btnLogin.setForeground(new Color(0, 128, 192));
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String usuario = txtUsuario.getText();
                String pwd = new String(pwdField.getPassword());

                try {
                    enviaParametro = new DataOutputStream(cliente.getOutputStream());
                    recibeParametro = new DataInputStream(cliente.getInputStream());

                    //Enviar usuario y contraseña
                    enviaParametro.writeUTF(usuario);
                    enviaParametro.writeUTF(pwd);

                    //Recibir respuesta del servidor
                    String respuestaUsuario = recibeParametro.readUTF();

                    if (!respuestaUsuario.equals("Usuario o contraseña erróneos")) {

                        //Convertir JSON a Map
                        Map<String, Object> usuarioMap = new Gson().fromJson(respuestaUsuario, Map.class);
                        
                       

                        //Abrir menú
                        Menu frame = new Menu();
                        frame.setVisible(true);
                        dispose();

                    } else {
                        lblAvisoError.setText(respuestaUsuario);
                        txtUsuario.setText("");
                        pwdField.setText("");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnLogin.setBounds(107, 546, 98, 47);
        panel.add(btnLogin);

        lblAvisoError = new JLabel("");
        lblAvisoError.setForeground(new Color(179, 0, 4));
        lblAvisoError.setFont(new Font("Arial", Font.BOLD, 14));
        lblAvisoError.setBounds(39, 502, 232, 33);
        panel.add(lblAvisoError);
    }
}
