/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristi.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

/**
 *
 * @author crist
 */
public class ClientThread extends Thread{
    
    private Socket socket = null;
    
    public ClientThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String raspuns = "Use Register or Login";
            out.println(raspuns);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            while(!(response.contains("Login") || response.contains("Register"))){
                out.println("Fail");
                out.flush();
                response = in.readLine();
            }
            out.println("Success!");
            out.flush();
            do{
                response = in.readLine();
                switch(response){
                    case "friend":
                        out.println("Server received the request ..." + response);
                        out.flush();
                        break;
                    case "send": 
                        out.println("Server received the request ..." + response);
                        out.flush();
                        break;
                    case "read": 
                        out.println("Server received the request ..." + response);
                        out.flush();
                        break;
                    case "stop":
                        out.println("Server stopped");
                        out.flush();
                        System.exit(0);
                        break;
                    case "exit":
                        break;
                    default:
                        out.println("Incorrect request ...");
                        out.flush();
                }
            } while(!response.contains("exit"));
        } catch(IOException e){
            System.err.println("Error: " + e);
        }
        finally {
            try{
                socket.close();
            } catch(IOException e) {
                System.err.println(e);
            }
        }
    }
    
}
