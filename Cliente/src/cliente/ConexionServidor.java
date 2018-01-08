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
public class ConexionServidor {
    Socket _socket;
    String _ip;
    int _puerto;
    String _ipCliente;
    int _puertoCliente;
    DataInputStream _dis;
    DataOutputStream _dos;
    String _comando;
    ManejadorOrden _manejadorOrden;
    String _respuesta = "";
    
    /**
     * Constrcutor de la Conexion al Servidor
     * @param ip
     * @param puerto
     * @param comando 
     */
    public ConexionServidor(String ip, int puerto, String comando) {
        this._ip = ip;
        this._puerto = puerto;
        this._comando = comando;
        this._manejadorOrden = new ManejadorOrden();
    }
    
    public String obtenerRespuesta(){
        return _respuesta;
    }
    
    /**
     * Metodo para conectar y enviar los comandos al servidor
     */
    public void start() {

        try {
            _socket = new Socket(this._ip, this._puerto);
            DataInputStream _dataInputStream = new DataInputStream(_socket.getInputStream());
            DataOutputStream _dataOutputStream = new DataOutputStream(_socket.getOutputStream());

            if(this._comando.equals("dir")){
                
                _dataOutputStream.writeUTF("1:dir");
                this._respuesta = _dataInputStream.readUTF();

                
            }else{
                if(_comando.toLowerCase().equals("videos_mas_descargados")){
                    
                    System.out.println("Videos Mas Descargados");
                    _dataOutputStream.writeUTF("5:videos_mas_descargados");
                    _respuesta = _dataInputStream.readUTF();
                    System.out.println(_respuesta);

                }
                else
                {
                    if(_comando.toLowerCase().equals("clientes_mas_videos")){
                        
                        System.out.println("Clientes Mas Videos");
                        _dataOutputStream.writeUTF("6:clientes_mas_videos");
                        _respuesta = _dataInputStream.readUTF();
                        System.out.println(_respuesta);
                        
                    }
                    else
                    {
                        String [] _comandoSplit = _comando.split(" ");
                        if(_comandoSplit[0].toLowerCase().equals("insc")){
                            
                            _dataOutputStream.writeUTF("2:"+_comandoSplit[1]+":"
                                    +_comandoSplit[2]+":"+_comandoSplit[3]);
                            _respuesta = _dataInputStream.readUTF();
                            
                            System.out.println(_respuesta);

                        }
                        else
                        {
                            if(_comandoSplit[0].toLowerCase().equals("video")){
                                
                                _dataOutputStream.writeUTF("3:"+_comandoSplit[1]
                                        +":"+_comandoSplit[2]+":"+_comandoSplit[3]);
                                _respuesta = _dataInputStream.readUTF();
                                
                                System.out.println(_respuesta);
                                
                            }
                            else{
                                if(_comandoSplit[0].toLowerCase().equals("descargar")){
                                    _dataOutputStream.writeUTF("4:"+_comandoSplit[1]);
                                    _respuesta = _dataInputStream.readUTF();
                                    System.out.println(_respuesta);
                                    

                                }
                            }
                        }
                        
                    }
                }
            }
            
             _dataInputStream.close();
             _dataOutputStream.close();
             _socket.close();
            
  
        } catch (IOException ex) {
            
            System.out.println("Error al crear los stream de entradas y salidad: "
                    + ex.getMessage());
            
        }catch(Exception e){
            
            System.out.println(e.getMessage());
            
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

