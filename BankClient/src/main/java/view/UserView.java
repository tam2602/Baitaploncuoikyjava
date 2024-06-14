package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginListenner;
import model.UserAccount;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class UserView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ViewLogin vlogin;
	private static JLabel lblXinChao;
	private static UserAccount userAccount;
	private static JLabel lblSD_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserView frame = new UserView();
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
	public UserView(ViewLogin vlogin) {
		this.vlogin= vlogin;
		userAccount=ViewLogin.getUserview();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 702);
		setTitle("tu tu");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChuyenkhoan = new JButton("Chuyển khoản");
		btnChuyenkhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserTranfer();
			}
		});
		btnChuyenkhoan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnChuyenkhoan.setBounds(0, 608, 181, 57);
		contentPane.add(btnChuyenkhoan);
		
		JButton btnRuttien = new JButton("Rút tiền");
		btnRuttien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserWithdrawal();
			}
		});
		btnRuttien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRuttien.setBounds(254, 608, 187, 57);
		contentPane.add(btnRuttien);
		
		JButton btnThongtincanhan = new JButton("Chỉnh sửa hồ sơ");
		btnThongtincanhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserInfo();
			}
		});
		btnThongtincanhan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThongtincanhan.setBounds(513, 608, 194, 57);
		contentPane.add(btnThongtincanhan);
		
		JLabel lblTaikhoan = new JLabel("SỐ TÀI KHOẢN");
		lblTaikhoan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTaikhoan.setBounds(10, 216, 194, 70);
		contentPane.add(lblTaikhoan);
		
		JLabel lblSotaikhoan = new JLabel(userAccount.getAccountNumber());
		lblSotaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSotaikhoan.setBounds(254, 216, 326, 70);
		contentPane.add(lblSotaikhoan);
		
		JLabel lblSD = new JLabel("SỐ DƯ");
		lblSD.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSD.setBounds(10, 344, 141, 70);
		contentPane.add(lblSD);
		
		lblSD_1 = new JLabel(userAccount.getBalance()+"");
		lblSD_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSD_1.setBounds(254, 344, 326, 70);
		contentPane.add(lblSD_1);
		
		lblXinChao = new JLabel("XIN CHÀO,"+userAccount.getFullName());
		lblXinChao.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblXinChao.setBounds(10, 33, 657, 70);
		contentPane.add(lblXinChao);
		setVisible(true);
	}
	public static void setUser(UserAccount newuser) {
		userAccount= newuser;
	}
	public static UserAccount getUsermain() {
		return userAccount;
	}
	public static void setBalanceView() {
		lblSD_1.setText(userAccount.getBalance()+"");
	}
	public static void setFullname() {
		lblXinChao.setText("XIN CHÀO,"+userAccount.getFullName());;
	}
}
