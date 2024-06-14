package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.UserAccount;
import view.AdminView;

public class AdminListener {
    private Socket socket;
    private AdminView adminView;
    private DataInputStream input;
    private DataOutputStream output;

    public AdminListener(Socket socket, AdminView adminView) throws IOException {
        this.socket = socket;
        this.adminView = adminView;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public void addUser() throws IOException {
        UserAccount user = adminView.getSelectedUser();
        if (user != null) {
            output.writeInt(7);
            output.writeUTF(new Gson().toJson(user));
            output.flush();
            boolean success = input.readBoolean();
            if (success) {
                JOptionPane.showMessageDialog(adminView, "Thêm người dùng thành công!");
            } else {
                JOptionPane.showMessageDialog(adminView, "Thêm người dùng thất bại!");
            }
        }
    }

    public void editUser() throws IOException {
        UserAccount user = adminView.getSelectedUser();
        if (user != null) {
            output.writeInt(8);
            output.writeUTF(new Gson().toJson(user));
            output.flush();
            boolean success = input.readBoolean();
            if (success) {
                JOptionPane.showMessageDialog(adminView, "Sửa người dùng thành công!");
            } else {
                JOptionPane.showMessageDialog(adminView, "Sửa người dùng thất bại!");
            }
        }
    }

    public void deleteUser() throws IOException {
        UserAccount user = adminView.getSelectedUser();
        if (user != null) {
            output.writeInt(9);
            output.writeUTF(user.getAccountNumber());
            output.flush();
            boolean success = input.readBoolean();
            if (success) {
                JOptionPane.showMessageDialog(adminView, "Xóa người dùng thành công!");
            } else {
                JOptionPane.showMessageDialog(adminView, "Xóa người dùng thất bại!");
            }
        }
    }

    public void depositMoney() throws IOException {
        UserAccount user = adminView.getSelectedUser();
        if (user != null) {
            output.writeInt(10);
            output.writeUTF(user.getAccountNumber());
            output.writeUTF(adminView.getAmountToDeposit());
            output.flush();
            boolean success = input.readBoolean();
            if (success) {
                JOptionPane.showMessageDialog(adminView, "Nạp tiền thành công!");
            } else {
                JOptionPane.showMessageDialog(adminView, "Nạp tiền thất bại!");
            }
        }
    }

    public void searchUser() throws IOException {
        output.writeInt(6);
        output.writeUTF(adminView.getSearchKeyword());
        output.flush();
        String jsonUsers = input.readUTF();
        List<UserAccount> users = new Gson().fromJson(jsonUsers, new TypeToken<List<UserAccount>>() {}.getType());
        adminView.updateTable(users);
    }

    public void reload() throws IOException {
        output.writeInt(11); // Request code for loading user data
        output.flush();
        String jsonUsers = input.readUTF(); // Receive JSON string from server
        List<UserAccount> users = new Gson().fromJson(jsonUsers, new TypeToken<List<UserAccount>>() {}.getType());
        adminView.updateTable(users);
    }
}
