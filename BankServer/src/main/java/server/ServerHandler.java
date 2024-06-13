package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.List;
import com.google.gson.Gson;
import model.UserAccount;
import util.DatabaseHelper;

public class ServerHandler implements Runnable {
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;

	public ServerHandler(Socket socket) {
		this.socket = socket;
		try {
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			int caseclient = 0;
			while ((caseclient = input.readInt()) != 0) {
				switch (caseclient) {
				case 1:
					checkLogin();
					break;
				case 2:
					addUser();
					break;
				case 3:
					rutTien();
					break;
				case 4:
					edit();
					break;
				case 5:
				transfer();
					break;
				case 6:
					search();
					break;
				case 7:
					addUser();
					break;
				case 8:
					editUser();
					break;
				case 9:
					deleteUser();
					break;
				case 10:
					depositMoney();
					break;
				case 11:
					loadUserData(); // Add this case
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void search() throws IOException {
		String keyword = input.readUTF();
		List<UserAccount> users = DatabaseHelper.searchUsers(keyword);
		Gson gson = new Gson();
		String json = gson.toJson(users);
		output.writeUTF(json);
		output.flush();
	}

	private void addUser() throws IOException {
		String userJson = input.readUTF();
		Gson gson = new Gson();
		UserAccount user = gson.fromJson(userJson, UserAccount.class);
		boolean isSuccess = DatabaseHelper.registerUser(user);
		output.writeBoolean(isSuccess);
//		output.flush();
	}

	private void editUser() throws IOException {
		String userJson = input.readUTF();
		Gson gson = new Gson();
		UserAccount user = gson.fromJson(userJson, UserAccount.class);
		boolean isSuccess = DatabaseHelper.updateUser(user);
		output.writeBoolean(isSuccess);
		output.flush();
	}

	private void deleteUser() throws IOException {
		String accountNumber = input.readUTF();
		boolean isSuccess = DatabaseHelper.deleteUser(accountNumber);
		output.writeBoolean(isSuccess);
		output.flush();
	}

	private void depositMoney() throws IOException {
		String accountNumber = input.readUTF();
		String amountStr = input.readUTF();
		BigDecimal amount = new BigDecimal(amountStr);
		boolean isSuccess = DatabaseHelper.napTien(accountNumber, amount);
		output.writeBoolean(isSuccess);
		output.flush();
	}

	private void transfer() throws IOException {
		String number = input.readUTF();
		UserAccount usertranfer = DatabaseHelper.findUser(number);
		if (usertranfer != null) {
			output.writeUTF(usertranfer.getFullName());
			String numberFrom = input.readUTF();
			String money = input.readUTF();
			boolean check = DatabaseHelper.chuyenTien(numberFrom, number, new BigDecimal(money));
			output.writeBoolean(check);
		} else {
			output.writeUTF(null);
		}
	}

	private void edit() throws IOException {
		String UserEdit = input.readUTF();
		Gson gson = new Gson();
		UserAccount account = gson.fromJson(UserEdit, UserAccount.class);
		boolean check = DatabaseHelper.updateUser(account);
		output.writeBoolean(check);
	}

	private void checkLogin() throws IOException {
		String user = input.readUTF();
		String pass = input.readUTF();
		boolean isAuthenticated = DatabaseHelper.authenticateUser(user, pass);
		output.writeBoolean(isAuthenticated);
		if (isAuthenticated) {
			output.writeUTF(DatabaseHelper.getUserRole(user, pass));
			UserAccount userAccount = DatabaseHelper.getUserAccount(user, pass);
			Gson gson = new Gson();
			String jsonuseraccount = gson.toJson(userAccount);
			output.write(jsonuseraccount.getBytes());
		}
		output.flush();
	}

	private void rutTien() throws IOException {
		String number = input.readUTF();
		String sotien = input.readUTF();
		boolean check = DatabaseHelper.rutTien(number, new BigDecimal(sotien));
		output.writeBoolean(check);
	}

	private void loadUserData() throws IOException {
		List<UserAccount> users = DatabaseHelper.getAllUserAccounts();
		Gson gson = new Gson();
		String jsonUsers = gson.toJson(users);
		output.writeUTF(jsonUsers);
		output.flush();
	}
}
