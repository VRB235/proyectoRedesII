/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Leonardo
 */
public class InicializadorServidor {

    int _puerto;
    String _ip;
    int _maximoConexiones = 10;
    ServerSocket _serverSocket;
    Socket _socket;
    ConexionCliente _conexionCliente;
    
    /**
     * Constructor vacio del Inicializados de Servidor
     */
    public InicializadorServidor (){
        
    }
    
    /**
     * Cosntrcutor del Inicializador de Servidor
     * @param ip Ip de odnde se conecta
     * @param puerto Puerto por el que escucha
     */
    public InicializadorServidor(String ip, int puerto){
        
        this._puerto = puerto;
        this._ip = ip;
        this._serverSocket = null; 
        this._socket = null;
        
    }

    /**
     * Metodo para iniciar conecion por socket
     */
    public void iniciar(){
        try {
                
        _serverSocket = new ServerSocket(_puerto, _maximoConexiones);
            
        while (true) {
                
            System.out.println("Servidor a la espera de conexiones.");
            _socket = _serverSocket.accept();
            System.out.println("Cliente con la IP: "+_socket.getInetAddress().getHostName()+" conectado");
                
            _conexionCliente = new ConexionCliente(_socket);
            _conexionCliente.start();
                
        }
        } catch (IOException ex) {
                
            System.out.println("Error: "+ex.getMessage());
                
        } finally{
                
            try {
                    
                _socket.close();
                _serverSocket.close();
                    
                } catch (IOException ex) {
                    
                    System.out.println("Error al cerrar el servidor: "+ex.getMessage());
                    
                }
                
            }   
            
        }
        
    /**
     * Metodo apra cerrar conecxion por socket
     */
        public void cerrar (){
            
            try {
                
                    _socket.close();
                    _serverSocket.close();
                    
                } catch (IOException ex) {
                    
                    System.out.println("Error al cerrar el servidor: "+ex.getMessage());
                    
                }
            
        }
}
