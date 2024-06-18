package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TranferListenner;
import model.UserAccount;

import java.awt.Color;
import javax.swing.JTextField;

public class UserTranfer extends JFrame {

	private JPanel contentPane;
	private JTextField textSOTHE;
	private JTextField tfSotien;
	private JButton btnChuynTinNgay;
	private JLabel lblSotaikhoan_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserTranfer frame = new UserTranfer();
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
	public UserTranfer() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 727, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTaikhoan = new JLabel("SỐ THẺ THỤ HƯỞNG");
		lblTaikhoan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTaikhoan.setBounds(10, 109, 260, 70);
		contentPane.add(lblTaikhoan);
		
		JLabel lblSD = new JLabel("SỐ TIỀN");
		lblSD.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSD.setBounds(10, 315, 141, 70);
		contentPane.add(lblSD);
		
		JLabel lblNgiThHng = new JLabel("NGƯỜI THỤ HƯỞNG");
		lblNgiThHng.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNgiThHng.setBounds(10, 211, 260, 70);
		contentPane.add(lblNgiThHng);
		
		lblSotaikhoan_1 = new JLabel("tesst");
		lblSotaikhoan_1.setBackground(new Color(0, 0, 0));
		lblSotaikhoan_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSotaikhoan_1.setBounds(377, 211, 326, 70);
		contentPane.add(lblSotaikhoan_1);
		
		textSOTHE = new JTextField();
		textSOTHE.setBounds(374, 118, 333, 61);
		contentPane.add(textSOTHE);
		textSOTHE.setColumns(10);
		
		tfSotien = new JTextField();
		tfSotien.setColumns(10);
		tfSotien.setBounds(370, 315, 333, 61);
		contentPane.add(tfSotien);
		
		btnChuynTinNgay = new JButton("Chuyển tiền ngay");
		btnChuynTinNgay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnChuynTinNgay.setBounds(402, 500, 181, 57);
		btnChuynTinNgay.setVisible(false);
		btnChuynTinNgay.addActionListener(e->{
			if(getMoney().isBlank()) {
				JOptionPane.showMessageDialog(null, "Trường dữ liệu trống!");
				return;
			}
			try {
				Socket socket = ViewLogin.getSocket();
				TranferListenner listenner= new TranferListenner(socket, this);
				boolean check = listenner.tranFer();
				if(check) {
					JOptionPane.showMessageDialog(null, "chuyen tien thanh cong");
					UserAccount edituser= UserView.getUsermain();
					edituser.setBalance(edituser.getBalance().subtract(new BigDecimal(getMoney())));
					UserView.setBalanceView();
					setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "chuyen tien khong thanh cong");
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "chuyen tien khong thanh cong");
				e1.printStackTrace();
			}
		});
		contentPane.add(btnChuynTinNgay);
		
		JButton btnKimTra = new JButton("Kiểm tra");
		btnKimTra.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnKimTra.setBounds(117, 500, 187, 57);
		btnKimTra.addActionListener(e->{
			if(getAccountNumber().isBlank()) {
				JOptionPane.showMessageDialog(null, "Trường dữ liệu trống!");
				return;
			}
			Socket socket = ViewLogin.getSocket();
			TranferListenner listenner= new TranferListenner(socket, this);
			try {
				String fullname= listenner.checkNumberuser();
				if(!fullname.isBlank()) {
					btnChuynTinNgay.setVisible(true);
					lblSotaikhoan_1.setText(fullname);
		
				}else {
					JOptionPane.showMessageDialog(null, "khong co so tk nay");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		contentPane.add(btnKimTra);
		setVisible(true);
	}
	public String getAccountNumber() {
		return textSOTHE.getText().trim();
	}
	public String getAccountNumberFrom() {
		return UserView.getUsermain().getAccountNumber();
	}
	public String getMoney() {
		try {
			int check = Integer.parseInt(tfSotien.getText());
		} catch (Exception e) {
			return "";
		}
		return tfSotien.getText();
	}
}
