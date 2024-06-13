package server;

import java.math.BigDecimal;

import model.UserAccount;
import util.DatabaseHelper;

public class StartServer {
    public static void main(String[] args) {
        BankServer server = new BankServer();
        server.startServer();
    	
    	
    	System.out.println(DatabaseHelper.getUserRole("e", "123"));
    	UserAccount userAccount= DatabaseHelper.getUserAccount("e", "123");
    	System.out.println(userAccount.getFullName());
    	
//    	UserAccount moi= new UserAccount("039863215","minh123","12345678", "Tran Van Minh", "user", new BigDecimal("3000000000"), null);
//    	DatabaseHelper.updateUser(moi);
    }
}
