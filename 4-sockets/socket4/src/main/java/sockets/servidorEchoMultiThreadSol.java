package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorEchoMultiThreadSol {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int numConnections = 0;
        int max = 3;

        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("Echo server is listening for connections");

            while (numConnections < max) {
                socket = serverSocket.accept();
                numConnections++;
                Thread thread = new Thread(new echoServiceSol(socket,numConnections));
                thread.start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if ( serverSocket != null)
            try { 
                serverSocket.close(); 
            } catch (IOException e) { 
                System.out.println(e.getMessage()); 
            }
        }

    }
    
}
