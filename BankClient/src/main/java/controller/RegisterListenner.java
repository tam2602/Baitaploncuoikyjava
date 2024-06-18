package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import model.UserAccount;
import view.ViewRegister;

public class RegisterListenner {
	private ViewRegister vr;
	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	public RegisterListenner(ViewRegister vr, Socket socket) {
		this.vr = vr;
		this.socket = socket;
		try {
			output = new DataOutputStream(socket.getOutputStream());
	        input = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized boolean register(UserAccount user) throws IOException{
		output.writeInt(2);
		Gson gson = new Gson();
		String jsonuser= gson.toJson(user);
		System.out.println("b8");
		output.writeUTF(jsonuser);
		System.out.println("b9");
		boolean check=input.readBoolean();
		System.out.println("b10");
		return check;
	}
	public synchronized boolean checkAccNumber() throws IOException {
		output.writeInt(12);
		output.writeUTF(vr.getAccNumber());
		return input.readBoolean();
	}
}
