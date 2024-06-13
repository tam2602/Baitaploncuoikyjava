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
import java.awt.Color;
import javax.swing.JTextField;

public class UserTranfer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textSOTHE;
	private JTextField tfSotien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserTranfer frame = new UserTranfer();
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
	public UserTranfer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblSotaikhoan_1 = new JLabel("tesst");
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
		
		JButton btnChuynTinNgay = new JButton("Chuyển tiền ngay");
		btnChuynTinNgay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnChuynTinNgay.setBounds(402, 500, 181, 57);
		contentPane.add(btnChuynTinNgay);
		
		JButton btnKimTra = new JButton("Kiểm tra");
		btnKimTra.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnKimTra.setBounds(117, 500, 187, 57);
		contentPane.add(btnKimTra);
	}

}
