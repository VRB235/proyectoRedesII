/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo
 */
public class IniciarDescarga extends Thread {
    
    String _respuesta;
    String _nombre;
    String _ruta;
    String _tamano;
    int _puerto;
    String _ip;
    
    public IniciarDescarga (String _respuesta){
        
        this._respuesta = _respuesta;
        
        String [] _respuestaSplit = _respuesta.split(":");
        
//        _nombre = _respuestaSplit[0];
//        _ruta = _respuestaSplit[1];
//        _tamano = _respuestaSplit[2];
//        _puerto = _respuestaSplit[3];
//        _ip = _respuestaSplit[4];

        _nombre = "1.wmv";
        _ruta = "C:\\Users\\Leonardo\\Videos\\1.wmv";
        _tamano = null;
        _puerto = 9000;
        _ip = "localhost";
        
        System.out.println(_nombre);
        System.out.println(_ruta);
        System.out.println(_tamano);
        System.out.println(_puerto);
        System.out.println(_ip);
        
        
        
    }
    
    @Override
    public void run (){

        try {
            
            Socket socket = new Socket(_ip,_puerto);
            DataInputStream _dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream _dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            _dataOutputStream.writeUTF(_ruta);
            
            InputStream _llegada = socket.getInputStream();
            FileOutputStream _destino = new FileOutputStream("C:\\Users\\Leonardo\\"+_nombre);
            
            byte[] _buffer = new byte[1024];
            int _len;
            while((_len=_llegada.read(_buffer))>0){
                _destino.write(_buffer,0,_len);
            }
            
            
        } catch (IOException ex) {
            
            System.out.println("Error : "+ex.getMessage());
        }
        

    }
    
}
