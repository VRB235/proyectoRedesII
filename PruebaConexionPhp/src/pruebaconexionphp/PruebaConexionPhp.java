/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaconexionphp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Leonardo
 */
public class PruebaConexionPhp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            final int portNumber = 6321;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        while (true) {
            Socket socket = serverSocket.accept();
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Client response: " + line);
                pw.println(line);
            }
            
            pw.close();
            br.close();
            os.close();
            socket.close();
        }
        } catch (Exception e) {
        }
        
    }
    
}
