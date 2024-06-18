package test;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.UserAccount;
import server.BankServer;
import util.DatabaseHelper;

public class StartServer {
    public static void main(String[] args) {
        BankServer server = new BankServer();
        server.startServer();
    	
    }
}
