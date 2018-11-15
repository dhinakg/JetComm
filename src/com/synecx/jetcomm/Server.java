package com.synecx.jetcomm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int m_port;

    public Server(int port) {
        m_port = port;
    }

    public void startServer(String data) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(m_port);
            System.out.println("Server started & listening on port " + m_port);

            while (true) {
                Socket socket = serverSocket.accept();
        
                if (!data.isEmpty()) {
                    OutputStream outputStream = socket.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    bufferedWriter.write(data);
                    System.out.println("Data sent to client!");
                    bufferedWriter.close();    
                }
            }

        } catch (IOException e) {
            System.out.println("Error starting server!\n" + e.getMessage());
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing server socket!\n" + e.getMessage());                
                }
            }
        }
    }
}