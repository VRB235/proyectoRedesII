/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            
            
            
                ServerSocket _serverSocket = new ServerSocket(1234);
                while(true){
            System.out.println("Esperando conexion...");
            _socket = _serverSocket.accept();
            System.out.println("Cliente aceptado: "+_socket.getInetAddress());
            
            DataOutputStream _dataOutputStream;
                try (DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream())) {
                    _dataOutputStream = new DataOutputStream(_socket.getOutputStream());
                    String _peticion = _dataInputStream.readUTF();
                    String [] _peticionSplit = _peticion.split(":");
                    if(_peticion.equals("1:dir")){
                        System.out.println(_peticion);
                        ManejadorOrden _mo = new ManejadorOrden(_peticion);
                        _dataOutputStream.writeUTF(_mo.accion());
                    }
                    else
                    {
                        if(_peticionSplit[0].equals("2")){
                            System.out.println("INSC");
                            ManejadorOrden _mo = new ManejadorOrden(_peticion);
                            _mo.accion();
                            _dataOutputStream.writeUTF("INSERTADO");
                        }
                        else{
                            if(_peticionSplit[0].equals("3")){
                                System.out.println("Video");
                                ManejadorOrden _mo = new ManejadorOrden(_peticion);
                                _mo.accion();
                                _dataOutputStream.writeUTF("INSERTADO");
                            }else{
                                if(_peticionSplit[0].equals("4")){
                                    System.out.println("Descargar");
                                    ManejadorOrden _mo = new ManejadorOrden(_peticion);
                                    String _respuesta = _mo.accion();
                                    System.out.println("Enciado al cliente: "+_respuesta);
                                    _dataOutputStream.writeUTF(_respuesta);
                                }
                                else
                                {
                                    if(_peticionSplit[0].equals("5")){
                                        System.out.println("Videos Mas Descargados");
                                        ManejadorOrden _mo = new ManejadorOrden(_peticion);
                                        String _respuesta = _mo.accion();
                                        System.out.println(_respuesta);
                                        _dataOutputStream.writeUTF(_respuesta);
                                    }
                                    else{
                                        if(_peticionSplit[0].equals("6")){
                                            System.out.println("Clientes Mas Desscargados");
                                            ManejadorOrden _mo = new ManejadorOrden(_peticion);
                                            String _respuesta = _mo.accion();
                                            System.out.println(_respuesta);
                                            _dataOutputStream.writeUTF(_respuesta);
                                        }
                                        else
                                        {
                                            System.out.println("INTENTO FALLIDO SE HACER CAER EL SISTEMA");
                                        }
                                    }
                                }
                            }
                        }
                    }  
                }
            _dataOutputStream.close();
            _socket.close();
            }
            
            
        } catch (IOException e) {
        
            System.out.println("Error "+e.getMessage());
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
