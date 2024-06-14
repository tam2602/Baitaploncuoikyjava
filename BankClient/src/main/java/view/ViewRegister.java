package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RegisterListenner;
import model.UserAccount;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import java.math.BigDecimal;
import java.net.Socket;

public class ViewRegister extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfAccountNumber;
    private JTextField tfUserName;
    private JTextField tfPassword;
    private JTextField tfRePassword;
    private JTextField tfFullName;
    private JTextField tfRole;
    private JTextField tfBalance;
	private Socket socket;

    public ViewRegister() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 523, 550);
        setTitle("Register");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAccountNumber = new JLabel("Account Number");
        lblAccountNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblAccountNumber.setBounds(57, 20, 150, 43);
        contentPane.add(lblAccountNumber);

        tfAccountNumber = new JTextField();
        tfAccountNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tfAccountNumber.setBounds(246, 20, 232, 43);
        contentPane.add(tfAccountNumber);
        tfAccountNumber.setColumns(10);

        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblUserName.setBounds(57, 80, 97, 43);
        contentPane.add(lblUserName);

        tfUserName = new JTextField();
        tfUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tfUserName.setBounds(246, 80, 232, 43);
        contentPane.add(tfUserName);
        tfUserName.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblPassword.setBounds(57, 140, 97, 43);
        contentPane.add(lblPassword);

        tfPassword = new JTextField();
        tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tfPassword.setBounds(246, 140, 232, 43);
        contentPane.add(tfPassword);
        tfPassword.setColumns(10);

        JLabel lblRePassword = new JLabel("Repeat Password");
        lblRePassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblRePassword.setBounds(57, 200, 150, 43);
        contentPane.add(lblRePassword);

        tfRePassword = new JTextField();
        tfRePassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tfRePassword.setBounds(246, 200, 232, 43);
        contentPane.add(tfRePassword);
        tfRePassword.setColumns(10);

        JLabel lblFullName = new JLabel("Full Name");
        lblFullName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblFullName.setBounds(57, 260, 97, 43);
        contentPane.add(lblFullName);

        tfFullName = new JTextField();
        tfFullName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tfFullName.setBounds(246, 260, 232, 43);
        contentPane.add(tfFullName);
        tfFullName.setColumns(10);

        JLabel lblRole = new JLabel("Role");
        lblRole.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblRole.setBounds(57, 320, 97, 43);
        contentPane.add(lblRole);

        tfRole = new JTextField("user");
        tfRole.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tfRole.setBounds(246, 320, 232, 43);
        contentPane.add(tfRole);
        tfRole.setColumns(10);
        tfRole.setEditable(false);

        JLabel lblBalance = new JLabel("Balance");
        lblBalance.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblBalance.setBounds(57, 380, 97, 43);
        contentPane.add(lblBalance);

        tfBalance = new JTextField("0");
        tfBalance.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tfBalance.setBounds(246, 380, 232, 43);
        contentPane.add(tfBalance);
        tfBalance.setColumns(10);
        tfBalance.setEditable(false);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e->{
        	String accountNumber = tfAccountNumber.getText();
            String userName = tfUserName.getText();
            String password = tfPassword.getText();
            String rePassword = tfRePassword.getText();
            String fullName = tfFullName.getText();
            String role = tfRole.getText();
            System.out.println("b1");
            BigDecimal balance = new BigDecimal(tfBalance.getText());
            boolean check=false;
            if (!password.equals(rePassword)) {
            	 System.out.println("b2");
                JOptionPane.showMessageDialog(null, "Passwords do not match!");
            }else {
            	System.out.println("b3");
            	UserAccount user= new UserAccount(accountNumber, userName, rePassword, fullName, role, balance, null);
            	socket = ViewLogin.getSocket();
            	 System.out.println("b4");
            	RegisterListenner listenner= new RegisterListenner(this, socket);
            	 System.out.println("b5");
            	try {
            		check=listenner.register(user);
            		 System.out.println("b6");
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Registration failed.");
				}
            	if (check) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");

                    setVisible(false);
//					try {
//						loginFrame = new ViewLogin(socket);
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. User may already exist.");
                }
            }

            
        }); 
        btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnRegister.setBounds(207, 435, 105, 55);
        contentPane.add(btnRegister);
        setVisible(true);
    }
}
