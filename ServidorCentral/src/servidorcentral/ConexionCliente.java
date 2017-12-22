/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Leonardo
 */
public class ConexionCliente extends Thread {
    
    Socket _socket; 
    DataOutputStream _dataOutputStream;
    DataInputStream _dataInputStream;
    String _mensaje;
    ManejadorOrden _manejadorOrden;
    
    public ConexionCliente (Socket socket){
        
        this._socket = socket;
        
    }
    
     public void desconnectar() {
         
        try {
            
            _dataInputStream.close();
            _dataOutputStream.close();
            _socket.close();
            
        } catch (IOException ex) {
            
            System.out.println(ex.getStackTrace());
            
        }
    }
    @Override
    public void run() {
        
        try {
            
            _dataInputStream = new DataInputStream(_socket.getInputStream());
            _dataOutputStream = new DataOutputStream(_socket.getOutputStream());
            
            _mensaje = _dataInputStream.readUTF();
            _manejadorOrden = new ManejadorOrden(_mensaje);
            _dataOutputStream.writeUTF(_manejadorOrden.accion());
   
        } catch (IOException ex) {
            
            System.out.println("Error al crear los stream de entradas y salidad: "
                    + ex.getMessage());
            
        }catch(Exception e){
            
            System.out.println(e.getStackTrace());
            
        }
        
    }
} 