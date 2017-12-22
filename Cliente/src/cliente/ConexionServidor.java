/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class ConexionServidor extends Thread {
    Socket _socket;
    String _ip;
    int _puerto;
    DataInputStream _dis;
    DataOutputStream _dos;
    String _comando;
    ManejadorOrden _manejadorOrden;
    
    public ConexionServidor(String ip, int puerto, String comando) {
        this._ip = ip;
        this._puerto = puerto;
        this._comando = comando;
        this._manejadorOrden = new ManejadorOrden();
    }
    @Override
    public void run() {
        
        try {
            
            if(_manejadorOrden.accion(_comando)!=null){
                
                _socket = new Socket(_ip, _puerto);
                _dos = new DataOutputStream(_socket.getOutputStream());
                _dis = new DataInputStream(_socket.getInputStream());
            
                if(!_manejadorOrden.accion(_comando).equals("0:exit"))
                {
                    
                    _dos.writeUTF(_manejadorOrden.accion(_comando));
                    System.out.println(_dis.readUTF());
                    
                }else{
                    
                    desconectar();
                    
                }
                
            }
            
        } catch (IOException ex) {
            
            System.out.println("Error con los Sockets "+ex.getMessage());
            
        }
        
    }
    
    public void desconectar(){
        
        try {
            
            _dis.close();
            _dos.close();
            _socket.close();
            System.out.println("Conexion cerrada");
            
        } catch (IOException e) {
            
            System.out.println("Error cerrando conexion: "+e.getStackTrace());
            
        }
        
    }
    
}

