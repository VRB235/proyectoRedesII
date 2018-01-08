/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo
 */
public class IniciarServidor extends Thread {
    
    int _puerto;
    
    public IniciarServidor(int puerto){
        this._puerto = puerto;
    }
    
    @Override
    public void run(){
        
        ServerSocket _ServerSocket;
        try {
            _ServerSocket = new  ServerSocket(_puerto);
            System.out.println("Esperando COnexion");;
            Socket _socket = _ServerSocket.accept();
            
            DataOutputStream _dos = new DataOutputStream(_socket.getOutputStream());
            DataInputStream _dis = new  DataInputStream(_socket.getInputStream());
            
            System.out.println(_dis.readUTF());
            
            _dos.writeUTF("CHAO");
            
            _dis.close();
            _dos.close();
            _socket.close();
            
        } catch (IOException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        
        
    }
    
}
