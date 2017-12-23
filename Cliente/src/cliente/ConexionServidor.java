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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class ConexionServidor extends Thread {
    Socket _socket;
    String _ip;
    int _puerto;
    String _ipCliente;
    int _puertoCliente;
    DataInputStream _dis;
    DataOutputStream _dos;
    String _comando;
    ManejadorOrden _manejadorOrden;
    public ArrayList<String> _respuesta;
    
    /**
     * Constrcutor de la Conexion al Servidor
     * @param ip
     * @param puerto
     * @param comando 
     */
    public ConexionServidor(String ip, int puerto, String comando, String ipCliente, int puertoCliente) {
        this._ip = ip;
        this._puerto = puerto;
        this._comando = comando;
        this._manejadorOrden = new ManejadorOrden();
        this._ipCliente = ipCliente;
        this._puertoCliente = puertoCliente;
        _respuesta = new ArrayList<>();
    }
    
    public ArrayList<String> obtenerRespuesta (){
        return _respuesta;
    }
    
    /**
     * Metodo para conectar y enviar los comandos al servidor
     */
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
                    String _nroVideosString = _dis.readUTF();
                    int _numVideos = Integer.parseInt(_nroVideosString);
                    System.out.println(_nroVideosString);
                    String _video = "";
                    
                    for (int i = 0; i <= _numVideos; i++) {
                        
                        _respuesta.add(i, _video);
                        
                        //_video = _dis.readUTF();
                        //_respuesta.add(i,_video);
                        
                    }
                    
                    
                }else{
                    
                    desconectar();
                    
                }
                
            }
            
        } catch (IOException ex) {
            
            System.out.println("Error con los Sockets "+ex.getMessage());
            
        }
        
    }
    
    /**
     * Metodo para desconectar el socket
     */
    public void desconectar(){
        
        try {
            
            _dis.close();
            _dos.close();
            _socket.close();
            System.out.println("Conexion cerrada");
            
        } catch (IOException e) {
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
}

