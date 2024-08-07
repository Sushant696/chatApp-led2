package sockets;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 5000;
    private static Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("Chat Server is running on port " + PORT);
        try {
            while (true) {
                new ClientHandler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class ClientHandler extends Thread {
        private String username;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                username = in.readLine();
                System.out.println(username + " connected");

                synchronized (clients) {
                    clients.add(this);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(username + ": " + message);
                    for (ClientHandler client : clients) {
                        client.out.println(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    synchronized (clients) {
                        clients.remove(this);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println(username + " disconnected");
            }
        }
    }
}