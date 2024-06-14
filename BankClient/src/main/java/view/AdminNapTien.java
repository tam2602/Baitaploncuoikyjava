package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.UserAccount;

import javax.swing.JLabel;
import java.awt.Font;
import java.math.BigDecimal;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JButton;

public class AdminNapTien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSTK;
	private JTextField textField;
	private JTextField textSoTien;
	private static UserAccount userAccount;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminNapTien frame = new AdminNapTien();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AdminNapTien() {
		userAccount= ViewLogin.getUserview();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSTK = new JLabel("SỐ TÀI KHOẢN");
		lblSTK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSTK.setBounds(23, 40, 157, 37);
		contentPane.add(lblSTK);
		
		tfSTK = new JTextField(userAccount.getAccountNumber());
		tfSTK.setBounds(225, 40, 269, 37);
		contentPane.add(tfSTK); 
		tfSTK.setColumns(10);
		
		JLabel lblTnTenTK = new JLabel("TÊN TÀI KHOẢN");
		lblTnTenTK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTnTenTK.setBounds(23, 108, 183, 37);
		contentPane.add(lblTnTenTK);
		
		textField = new JTextField(userAccount.getFullName());
		textField.setColumns(10);
		textField.setBounds(225, 108, 269, 37);
		contentPane.add(textField);
		
		JLabel lblSoTien = new JLabel("SỐ TIỀN NẠP");
		lblSoTien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSoTien.setBounds(23, 179, 183, 37);
		contentPane.add(lblSoTien);
		
		textSoTien = new JTextField();
		textSoTien.setColumns(10);
		textSoTien.setBounds(225, 181, 269, 37);
		contentPane.add(textSoTien);
		
		JButton btnNewButton = new JButton("NẠP TIỀN");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.addActionListener(e->{
			Socket socket = ViewLogin.getSocket();
		});
		btnNewButton.setBounds(162, 251, 176, 43);
		contentPane.add(btnNewButton);
		setVisible(true);
	}
	public BigDecimal getTienNap() {
		return new BigDecimal(textSoTien.getText());
	}
	public static UserAccount getUserDeposit() {
		return userAccount;
	}
	public String getAccountNumber() {
		return textField.getText().trim();
	}
}
