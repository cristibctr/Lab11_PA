/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class Connection {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                System.out.println(in.readLine());
                String response, request;
                do{
                    Scanner keyboard = new Scanner(System.in);
                    request = keyboard.next();
                    out.println(request);
                    response = in.readLine();
                    System.out.println(response);
                } while (response.contains("Fail"));
                
                do{
                    Scanner keyboard = new Scanner(System.in);
                    request = keyboard.next();
                    out.println(request);
                    if(!request.contains("exit")){
                        response = in.readLine();
                        System.out.println(response);
                    }
                } while(!(request.contains("stop") || request.contains("exit")));
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
