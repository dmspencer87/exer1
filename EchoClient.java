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
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());

            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os,true, "UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            Scanner keyboard = new Scanner(System.in);
            while(true) {
                System.out.print("<Client>: ");
                String str = keyboard.nextLine();
                out.println(str);
                if(str.equals("exit")){
                    break;
                }


                System.out.println("<Server>: " + in.readLine());

            }

        }
    }
}