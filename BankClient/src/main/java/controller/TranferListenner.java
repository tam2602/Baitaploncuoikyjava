package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import view.UserTranfer;

public class TranferListenner {
	private Socket socket;
	private UserTranfer userTranfer;
	private DataOutputStream output;
	private DataInputStream input;
	public TranferListenner(Socket socket, UserTranfer userTranfer) {
		this.socket = socket;
		this.userTranfer = userTranfer;
		try {
			this.output=new DataOutputStream(socket.getOutputStream());
			this.input= new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String checkNumberuser() throws IOException {
		output.writeInt(5);
		System.out.println("aa1");
		output.writeUTF(this.userTranfer.getAccountNumber());
		System.out.println("aa2");
		String fullname=input.readUTF();
		System.out.println("aa3");
		return fullname;
	}
	public boolean tranFer() throws IOException {
		output.writeUTF(this.userTranfer.getAccountNumberFrom());
		output.writeUTF(this.userTranfer.getMoney());
		return input.readBoolean();
	}
}
