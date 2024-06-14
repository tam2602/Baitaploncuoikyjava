package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.AdminListener;
import model.UserAccount;

public class AdminView extends JFrame implements MouseListener {
	Vector<Vector<Object>> vData = new Vector<>();
	Vector<String> vTitle = new Vector<>();
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb = new JTable();
	JButton insert, edit, delete;
	JTextField timKiem = new JTextField();
	int selectedRow = -1;
	private JTextField textFieldSTK;
	private JTextField textFieldName;
	private JTextField textFieldUserName;
	private JTextField textPass;
	private JTextField textFieldRole;
	private JTextField textFieldBalance;
	private AdminListener adminListener;

	public AdminView(Socket socket) throws IOException {
		this.adminListener = new AdminListener(socket, this);
		init();
		loadUserData();
	}

	private void init() {
		this.setTitle("Admin");
		this.setSize(1090, 469);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton insert = new JButton("Thêm");
		insert.addActionListener(e -> {
			try {
				addUser();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		JButton edit = new JButton("Sửa");
		edit.addActionListener(e -> {
			try {
				editUser();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		JButton delete = new JButton("Xóa");
		delete.addActionListener(e -> {
			try {
				deleteUser();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		JButton deposit = new JButton("Nạp tiền");
		deposit.addActionListener(e -> {
			try {
				depositMoney();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		JButton search = new JButton("Tìm kiếm");
		search.addActionListener(e -> {
			try {
				searchUser();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		JPanel chucNang = new JPanel(new FlowLayout(FlowLayout.CENTER));
		chucNang.setBounds(0, 402, 1086, 33);
		chucNang.add(deposit);
		chucNang.add(insert);
		chucNang.add(edit);
		chucNang.add(delete);
		chucNang.add(search);
		timKiem.setPreferredSize(new Dimension(200, timKiem.getPreferredSize().height));
		chucNang.add(timKiem);
		getContentPane().add(chucNang);

		// Table titles
		vTitle.add("Số tài khoản");
		vTitle.add("Tên người dùng");
		vTitle.add("Mật khẩu");
		vTitle.add("Họ và tên");
		vTitle.add("Loại người dùng");
		vTitle.add("Số dư");

		model = new DefaultTableModel(vData, vTitle);
		tb = new JTable(model);
		tb.addMouseListener(this);
		tableResult = new JScrollPane(tb);
		tableResult.setBounds(0, 151, 1066, 251);
		getContentPane().add(tableResult);

		JLabel lblSTK = new JLabel("Số tài khoản");
		lblSTK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSTK.setBounds(10, 11, 120, 41);
		getContentPane().add(lblSTK);

		JLabel lblName = new JLabel("Họ và tên");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblName.setBounds(10, 77, 135, 33);
		getContentPane().add(lblName);

		textFieldSTK = new JTextField();
		textFieldSTK.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textFieldSTK.setBounds(147, 16, 153, 33);
		getContentPane().add(textFieldSTK);
		textFieldSTK.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textFieldName.setColumns(10);
		textFieldName.setBounds(147, 77, 153, 33);
		getContentPane().add(textFieldName);

		JLabel lblUserName = new JLabel("Tên người dùng");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUserName.setBounds(333, 11, 146, 41);
		getContentPane().add(lblUserName);

		JLabel lblPass = new JLabel("Mật khẩu");
		lblPass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPass.setBounds(333, 69, 120, 41);
		getContentPane().add(lblPass);

		textFieldUserName = new JTextField();
		textFieldUserName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(489, 16, 153, 33);
		getContentPane().add(textFieldUserName);

		textPass = new JTextField();
		textPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textPass.setColumns(10);
		textPass.setBounds(489, 77, 153, 33);
		getContentPane().add(textPass);

		JLabel lblRole = new JLabel("Loại người dùng");
		lblRole.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRole.setBounds(710, 11, 146, 41);
		getContentPane().add(lblRole);

		JLabel lblBalance = new JLabel("Số dư");
		lblBalance.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBalance.setBounds(710, 69, 146, 41);
		getContentPane().add(lblBalance);

		textFieldRole = new JTextField();
		textFieldRole.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textFieldRole.setColumns(10);
		textFieldRole.setBounds(866, 19, 153, 33);
		getContentPane().add(textFieldRole);

		textFieldBalance = new JTextField();
		textFieldBalance.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(866, 77, 153, 33);
		getContentPane().add(textFieldBalance);
		this.setVisible(true);
	}

	public void loadUserData() {
		try {
			adminListener.reload();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không thể tải dữ liệu người dùng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		selectedRow = tb.getSelectedRow();
		if (selectedRow != -1) {
			textFieldSTK.setText(tb.getValueAt(selectedRow, 0).toString());
			textFieldUserName.setText(tb.getValueAt(selectedRow, 1).toString());
			textPass.setText(tb.getValueAt(selectedRow, 2).toString());
			textFieldName.setText(tb.getValueAt(selectedRow, 3).toString());
			textFieldRole.setText(tb.getValueAt(selectedRow, 4).toString());
			textFieldBalance.setText(tb.getValueAt(selectedRow, 5).toString());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public String getSearchKeyword() {
		return timKiem.getText().trim();
	}

	public void updateTable(List<UserAccount> users) {
		vData.clear();
		if (users != null) {
			for (UserAccount user : users) {
				Vector<Object> row = new Vector<>();
				row.add(user.getAccountNumber());
				row.add(user.getUsername());
				row.add(user.getPassword());
				row.add(user.getFullName());
				row.add(user.getRole());
				row.add(user.getBalance());
				vData.add(row);
			}
		}
		model.setDataVector(vData, vTitle);
		model.fireTableDataChanged();
	}

	public UserAccount getSelectedUser() {
		try {
			String accountNumber = textFieldSTK.getText().trim();
			String username = textFieldUserName.getText().trim();
			String password = textPass.getText().trim();
			String fullName = textFieldName.getText().trim();
			String role = textFieldRole.getText().trim();
			BigDecimal balance = new BigDecimal(textFieldBalance.getText().trim());
			return new UserAccount(accountNumber, username, password, fullName, role, balance, null);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Số dư phải là một số hợp lệ.", "Lỗi định dạng",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	public String getAmountToDeposit() {
		return JOptionPane.showInputDialog(this, "Nhập số tiền muốn nạp:");
	}

	public void addUser() throws IOException {
//		clearFields();
		adminListener.addUser();
		adminListener.reload();
	}

	public void editUser() throws IOException {
		adminListener.editUser();
		adminListener.reload();
	}

	public void deleteUser() throws IOException {
		adminListener.deleteUser();
		adminListener.reload();
	}

	public void depositMoney() throws IOException {
		adminListener.depositMoney();
		adminListener.reload();
	}

	public void clearFields() {
		textFieldSTK.setText("");
		textFieldUserName.setText("");
		textPass.setText("");
		textFieldName.setText("");
		textFieldRole.setText("");
		textFieldBalance.setText("");
	}

	public void searchUser() throws IOException {
		adminListener.searchUser();
	}

}
