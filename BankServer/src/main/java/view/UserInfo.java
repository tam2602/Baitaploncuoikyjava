package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

public class UserInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
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
	public UserInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRuttien = new JButton("CHỈNH SỬA");
		btnRuttien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRuttien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRuttien.setBounds(338, 628, 187, 57);
		contentPane.add(btnRuttien);
		
		JLabel lblTaikhoan = new JLabel("SỐ TÀI KHOẢN");
		lblTaikhoan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTaikhoan.setBounds(10, 105, 194, 70);
		contentPane.add(lblTaikhoan);
		
		JLabel lblSotaikhoan = new JLabel("tesst");
		lblSotaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSotaikhoan.setBounds(324, 105, 326, 70);
		contentPane.add(lblSotaikhoan);
		
		JLabel lblSD = new JLabel("SỐ DƯ");
		lblSD.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSD.setBounds(10, 203, 141, 70);
		contentPane.add(lblSD);
		
		JLabel lblSD_1 = new JLabel("200000");
		lblSD_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSD_1.setBounds(324, 203, 326, 70);
		contentPane.add(lblSD_1);
		
		JLabel lblXinChao = new JLabel("XIN CHÀO,");
		lblXinChao.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblXinChao.setBounds(10, 33, 657, 70);
		contentPane.add(lblXinChao);
		
		JLabel lblHVTn = new JLabel("HỌ VÀ TÊN");
		lblHVTn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblHVTn.setBounds(10, 296, 194, 70);
		contentPane.add(lblHVTn);
		
		textField = new JTextField();
		textField.setBounds(327, 299, 427, 73);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblHVTn_1 = new JLabel("NHẬP MẬT KHẨU MỚI");
		lblHVTn_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblHVTn_1.setBounds(10, 416, 307, 70);
		contentPane.add(lblHVTn_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(327, 419, 427, 73);
		contentPane.add(textField_1);
		
		JLabel lblHVTn_1_1 = new JLabel("NHẬP LẠI MẬT KHẨU");
		lblHVTn_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblHVTn_1_1.setBounds(10, 517, 271, 70);
		contentPane.add(lblHVTn_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(327, 520, 427, 73);
		contentPane.add(textField_2);
	}
}
