/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Leonardo
 */
public class ConexionCliente extends Thread implements Observer{
    
    private Socket _socket; 
    private Packete _packete;
    private DataInputStream _entradaDatos;
    private DataOutputStream _salidaDatos;
    
    public ConexionCliente (Socket socket, Packete packete){
        this._socket = socket;
        this._packete = packete;
        
        try {
            _entradaDatos = new DataInputStream(_socket.getInputStream());
            _salidaDatos = new DataOutputStream(_socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear los stream de entradas y salidad: "+ ex.getMessage());
        }
    }
    
    @Override
    public void run(){
        String mensajeRecibido;
        boolean conectado = true;
        _packete.addObserver(this);
        
        while (conectado) {
            try {
                
                mensajeRecibido = _entradaDatos.readUTF();
                // Pone el mensaje recibido en mensajes para que se notifique 
                // a sus observadores que hay un nuevo mensaje.
                _packete.setMensaje(mensajeRecibido);
                
            } catch (IOException ex) {
                
                System.out.println("Cliente con la IP "+_socket.getInetAddress().getHostName()+" desconectado");
                conectado = false; 
                // Si se ha producido un error al recibir datos del cliente se cierra la conexion con el.
                try {
                    
                    _entradaDatos.close();
                    _salidaDatos.close();
                    
                } catch (IOException ex2) {
                    
                    System.out.println("Error al cerrar los stream de entrada y salida: "+ex2.getMessage());
                    
                }
            }
        }   
    }
    
    @Override
    public void update(Observable o, Object arg) {
        
        try {
            
            // Envia el mensaje al cliente
            _salidaDatos.writeUTF(arg.toString());
            
        } catch (IOException ex) {
            
            System.out.println("Error al enviar mensaje al cliente ("+ex.getMessage()+").");
            
        }
    }

} 