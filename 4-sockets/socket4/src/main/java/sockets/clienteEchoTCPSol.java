package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class clienteEchoTCPSol {

     public static void main(String[] args) throws IOException {
        // reading the kkeyboard.

        BufferedReader userInputStream = null;
        String userInputLine = null;

        //Socket for connecting to the server 
        Socket socket = null;
        
        // For writing char lines inmediately to the socket byte input stream 
        PrintWriter serverOutputStream = null;

        // For reading lines from the socket byte input stream
        BufferedReader serverInputStream = null;

        try{
            //1 create a socket to conenct

            socket = new Socket("localhost",9999);

            //2 init serverInputStream  for flushing immediately chars to os 
            serverOutputStream = new PrintWriter(socket.getOutputStream(),true);

            //3 init serverinputsteram for read liens from server
            serverInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //4  init user input stream for reading lines from the keyboard.
            userInputStream = new BufferedReader(new InputStreamReader(System.in));

            //5 read  lines till ctrl-z

            do{
                //6 write the line to the serveroutputstram
                userInputLine = userInputStream.readLine();
                serverOutputStream.println(userInputLine);
                System.out.println("echo " + userInputStream.readLine());
            } while (userInputLine != null);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
     } finally {
        if (socket != null) {
            try {
                socket.close();
                System.out.println("clossing connection");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        }
    }
}