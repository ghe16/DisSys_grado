package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class echoServiceSol implements Runnable {
    Socket socket = null;
    int id = 0;

    public echoServiceSol(Socket socket, int id) {
        // 1. Initialise the thread with the socket and the id connections
        this.socket = socket;
        this.id = id;
        System.out.println(" Echo created " + id + " Starting");
    }
    
    public void run() {
        String inputLine = null;
        try {
            // 2. create an input line stream for reading linees from the socket byte input stream
            BufferedReader userInputLine = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3 create an output line stream for writing lines to the socket byte output stream immediately
            PrintWriter lineOutputStream = new PrintWriter(socket.getOutputStream(),true);

            // 4 while not eof
            while((inputLine = userInputLine.readLine()) != null) {
                // 5. read a line for the input line stream
                System.out.println(" echo thread"+ id + " " + inputLine);
                //6. write the line in the lineOutputStream

                lineOutputStream.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (socket != null)
            try{
                socket.close();
            } catch (IOException e) {
                System.out.println(" " + e.getMessage());
            }
        }
    }
}

