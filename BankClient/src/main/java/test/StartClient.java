package test;

import java.net.Socket;

import model.UserAccount;
import view.AdminView;
import view.UserWithdrawal;
import view.ViewLogin;

public class StartClient {
public static void main(String[] args) throws Exception {
	Socket socketclient= new Socket("localhost",2000);
	new ViewLogin(socketclient);
	
}
}
