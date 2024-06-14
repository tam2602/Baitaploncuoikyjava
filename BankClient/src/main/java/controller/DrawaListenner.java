package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import view.UserView;
import view.UserWithdrawal;

public class DrawaListenner {
	private UserWithdrawal userWithdrawal;
	private Socket socketdraw;
	private String number;
	public DrawaListenner(UserWithdrawal userWithdrawal,Socket socketdraw,String number) {
		this.userWithdrawal=  userWithdrawal;
		this.socketdraw=socketdraw;
		this.number= number;
	}
	
	public boolean rutTien() throws Exception{

		DataInputStream input= new DataInputStream(socketdraw.getInputStream());
		DataOutputStream output= new DataOutputStream(socketdraw.getOutputStream());
		output.writeInt(3);
		output.writeUTF(number);
		output.writeUTF(this.userWithdrawal.getTienRut()+"");
		
		boolean check= input.readBoolean();
		return check;
	}
}
