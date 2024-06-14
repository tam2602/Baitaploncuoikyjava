package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.google.gson.Gson;

import model.UserAccount;
import view.UserInfo;
import view.ViewLogin;

public class InforListener {
	private Socket socketdraw;
	private UserInfo userInfo;
	public InforListener(Socket socketdraw,  UserInfo userInfo) {
		this.socketdraw= socketdraw;
		this.userInfo= userInfo;
		
	}
	public boolean EditInfo() throws Exception{

		DataInputStream input= new DataInputStream(socketdraw.getInputStream());
		DataOutputStream output= new DataOutputStream(socketdraw.getOutputStream());
		output.writeInt(4);
		
		UserAccount useredit= ViewLogin.getUserview();
		useredit.setFullName(this.userInfo.getUserName());
		useredit.setPassword(this.userInfo.getUserPass());
		
		Gson json= new Gson();
		String userjson= json.toJson(useredit);
		output.writeUTF(userjson);
		boolean check= input.readBoolean();
		return check;
	}

}
