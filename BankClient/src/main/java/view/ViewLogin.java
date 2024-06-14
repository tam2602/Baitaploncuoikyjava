package view;

import java.awt.Font;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AdminListener;
import controller.LoginListenner;
import model.UserAccount;

public class ViewLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfUserName;
    private JTextField tfPassword;
	private static UserAccount userAccount;
    private static Socket socketClient;
	private static LoginListenner listenner;

    public ViewLogin(Socket socketClient) throws Exception {
        this.socketClient = socketClient;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Login");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblUserName.setBounds(41, 50, 97, 43);
        contentPane.add(lblUserName);

        tfUserName = new JTextField();
        tfUserName.setBounds(181, 50, 232, 43);
        contentPane.add(tfUserName);
        tfUserName.setColumns(10);

        tfPassword = new JTextField();
        tfPassword.setColumns(10);
        tfPassword.setBounds(181, 104, 232, 43);
        contentPane.add(tfPassword);

        if (this.socketClient == null) {
            System.out.println("Socket client not initialized.");
        }

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            new Thread(() -> {
                try {
                    listenner = new LoginListenner(this, this.socketClient);
                    String checklogin = listenner.sendUserPass();
                    userAccount = listenner.getUser();
                    if (checklogin.equals("user")) {
                        
                    	setVisible(false);
                    	System.out.println("user");
                        new UserView(this);
                    } 
                    else if (checklogin.equals("admin")) {
                    	setVisible(false);
                    	AdminView adminView = new AdminView(socketClient);
                        AdminListener adminListener = new AdminListener(socketClient, adminView);
                        System.out.println("admin");
                    } else {
                    	JOptionPane.showMessageDialog(null, "TÀI KHOẢN VÀ MẬT KHẨU KHÔNG CHÍNH XÁC");
                    	System.out.println("null");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "TÀI KHOẢN VÀ MẬT KHẨU KHÔNG CHÍNH XÁC");
                } catch (Exception e1) {
					// TODO Auto-generated catch block
                	JOptionPane.showMessageDialog(null, "TÀI KHOẢN VÀ MẬT KHẨU KHÔNG CHÍNH XÁC");
					e1.printStackTrace();
				}
            }).start();
        });

        btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnLogin.setBounds(249, 188, 105, 55);
        contentPane.add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> {
            ViewRegister registerFrame = new ViewRegister();
            registerFrame.setVisible(true);
        });
        btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnRegister.setBounds(105, 188, 105, 55);
        contentPane.add(btnRegister);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblPassword.setBounds(41, 104, 97, 43);
        contentPane.add(lblPassword);

        setVisible(true);
    }

    public String getTextUserName() {
        return tfUserName.getText().trim();
    }

    public String getTextPasword() {
        return tfPassword.getText().trim();
    }
    public static Socket getSocket() {
    	return socketClient;
    }
    public static UserAccount getUserview() {
    	return userAccount;
    }
}
