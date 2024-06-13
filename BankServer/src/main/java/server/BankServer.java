package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {
    private ServerSocket serverSocket;
    private final int port = 2000;

    public BankServer() {
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
	