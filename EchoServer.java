/************************************************************************************
 *	file: EchoClient.java
 *	author: Daniel Spencer
 *	class: CS 380 - computer networks
 *
 *	assignment: exercise 1
 *	date last modified: 9/282017
 *
 *	purpose:   simple server and cliant programs
 *
 *
 ************************************************************************************/
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Runnable echo = () -> {
                        try {
                            String address = socket.getInetAddress().getHostAddress();
                            System.out.printf("Client connected: %s%n", address);
                            OutputStream os = socket.getOutputStream();
                            PrintStream out = new PrintStream(os, true, "UTF-8");
                            out.printf("Hi %s, thanks for connecting!%n", address);

                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            while (true) {
                                String str = in.readLine();
                                if (str.equals("exit")) {
                                    System.out.printf("Client disconnected: %n", address);
                                    socket.close();
                                    break;
                                }
                                out.println(str);
                            }
                        }
                        catch(InterruptedIOException e){
                            return;
                        }
                        catch(IOException e){
                            return;
                        }
                    };

                    Thread echoThread = new Thread(echo);
                    echoThread.start();

                }
                catch(Exception e){
                    return;
                }

            }
        }
    }
}