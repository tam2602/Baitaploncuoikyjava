package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.google.gson.Gson;

import model.UserAccount;
import view.ViewLogin;

public class LoginListenner {
    private ViewLogin viewLogin;
    private Socket socketClient;

    public LoginListenner(ViewLogin viewLogin, Socket socketClient) {
        this.viewLogin = viewLogin;
        this.socketClient = socketClient;
    }

    public String sendUserPass() throws IOException {
        DataOutputStream output = new DataOutputStream(socketClient.getOutputStream());
        DataInputStream input = new DataInputStream(socketClient.getInputStream());

        output.writeInt(1); // gửi case 1
        output.writeUTF(viewLogin.getTextUserName());
        output.writeUTF(viewLogin.getTextPasword());
        output.flush();
        boolean isAuthenticated = input.readBoolean();
        if(!isAuthenticated) {
        	return null;
        }else {
            String role=input.readUTF();
            return role;
        }
        
    }
    
    public UserAccount getUser() throws Exception{
    	 InputStream input= socketClient.getInputStream();
    	 byte[] buffer= new byte[1024];
    	 int reads= input.read(buffer);
    	 String jsonuser= new String(buffer, 0, reads);
    	 
    	 //String jsonuser= input.readUTF();
    	 System.out.println(jsonuser);
    	 Gson gson= new Gson();
    	 UserAccount userAccount= gson.fromJson(jsonuser, UserAccount.class);
    	 return userAccount;
    }
}
