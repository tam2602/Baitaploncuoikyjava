package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DrawaListenner;
import model.UserAccount;

import javax.swing.JTextField;

public class UserWithdrawal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRuttien;
	private static UserAccount userAccount;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserWithdrawal frame = new UserWithdrawal();
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
	public UserWithdrawal() {
//		System.out.println("ok");
		userAccount= ViewLogin.getUserview();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 727, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRuttien = new JButton("Rút tiền");
		btnRuttien.addActionListener(e->{
			Socket socket= ViewLogin.getSocket();
			DrawaListenner drawaListenner= new DrawaListenner(this,socket,userAccount.getAccountNumber());
			try {
//				System.out.println("vo duoc day");
				boolean check= drawaListenner.rutTien();
				if(check) {
					JOptionPane.showMessageDialog(null, "Rút thành công!");
					userAccount.setBalance(userAccount.getBalance().subtract(getTienRut()));
					UserView.setUser(userAccount);
					UserView.setBalanceView();
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Rút không thành công!");
					setVisible(false);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		btnRuttien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRuttien.setBounds(232, 493, 187, 57);
		contentPane.add(btnRuttien);
		
		JLabel lblTaikhoan = new JLabel("SỐ TÀI KHOẢN");
		lblTaikhoan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTaikhoan.setBounds(10, 145, 194, 70);
		contentPane.add(lblTaikhoan);
		
		JLabel lblSotaikhoan = new JLabel(userAccount.getAccountNumber());
		lblSotaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSotaikhoan.setBounds(232, 145, 326, 70);
		contentPane.add(lblSotaikhoan);
		
		JLabel lblSD = new JLabel("SỐ DƯ");
		lblSD.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSD.setBounds(10, 260, 141, 70);
		contentPane.add(lblSD);
		
		JLabel lblSD_1 = new JLabel(userAccount.getBalance()+"");
		lblSD_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSD_1.setBounds(232, 260, 326, 70);
		contentPane.add(lblSD_1);
		
		JLabel lblXinChao = new JLabel("XIN CHÀO,"+userAccount.getFullName());
		lblXinChao.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblXinChao.setBounds(10, 33, 657, 70);
		contentPane.add(lblXinChao);
		
		JLabel lblSTinRt = new JLabel("SỐ TIỀN RÚT");
		lblSTinRt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSTinRt.setBounds(10, 356, 187, 70);
		contentPane.add(lblSTinRt);
		
		textFieldRuttien = new JTextField();
		textFieldRuttien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldRuttien.setBounds(231, 360, 338, 66);
		contentPane.add(textFieldRuttien);
		textFieldRuttien.setColumns(10);
		setVisible(true);
	}
	public BigDecimal getTienRut() {
		return new BigDecimal(textFieldRuttien.getText());
	}
	public static UserAccount getUserdraw() {
		return userAccount;
	}
}
