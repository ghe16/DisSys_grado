package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class servidotEchoMonotrheadTCPSol {
    
    public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    Socket socket = null;

    try {

        // 1 create a server socket
        serverSocket = new ServerSocket(9999);

        //2 wait for incoming user connection
        socket = serverSocket.accept();

        // 3 reading lines from the socket byte input stram
        BufferedReader userInputLine = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //4 writing lines to thee socket byte output stream
        PrintWriter lineOutputStream = new PrintWriter(socket.getOutputStream(),true);

        //5 read a line from the input stream ssocket and echo it to the output stream socket

        String inputLine;

        while ((inputLine = userInputLine.readLine()) != null) {
            System.out.println("server has recieved " + inputLine);
            // 6 write line to ouutput stream socket when is diffferent from null
            lineOutputStream.println(inputLine);
        }

    } catch (IOException e) {
        System.out.println(e.getMessage());
    } finally {
        // The finally block always execytes whenb the try block exits. 
        // this ensures that the finally block is executed even if an exception is thrown
        if (serverSocket != null) {
            try
            {
                serverSocket.close();
                System.out.println("Shutting down the server");

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    }
}

}