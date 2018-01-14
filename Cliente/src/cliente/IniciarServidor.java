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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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
    ServerSocket _ServerSocket;
    
    public IniciarServidor(int puerto){
        this._puerto = puerto;
        try {
            _ServerSocket = new  ServerSocket(_puerto);
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
;
    }
    
    @Override
    public void run(){
        
        
        try {
            while(true){
            System.out.println("Esperando COnexion");
            Socket _socket = _ServerSocket.accept();

            PrintStream _envio = new PrintStream(_socket.getOutputStream());
            DataOutputStream _dos = new DataOutputStream(_socket.getOutputStream());
            DataInputStream _dis = new  DataInputStream(_socket.getInputStream());
            
            String _ruta = _dis.readUTF();
            
            System.out.println("Solicitud de Descarga de: "+_ruta);
            
            FileInputStream _origen = new FileInputStream(_ruta);
            
            byte[] _buffer = new byte[1024];
            int _len;
            while ((_len=_origen.read(_buffer))>0) {                
                _envio.write(_buffer, 0, _len);
            }
            
            System.out.println("ENVIADO");
            
            _dos.writeUTF("ENVIADO");
            
            _dis.close();
            _dos.close();
            _socket.close();
            }
            
            
            
        } catch (IOException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        
        
    }
    
}
