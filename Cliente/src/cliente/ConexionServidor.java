/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo
 */
public class ConexionServidor extends Thread {
    Socket _socket;
    ObjectOutputStream _ObjetoSaliente;
    ObjectInputStream _ObjetoEntrante;
    String _ip;
    int _puerto;
    Packete _packete;
    DataInputStream _dis;
    DataOutputStream _dos;
    
    public ConexionServidor(String ip, int puerto) {
        this._ip = ip;
        this._puerto = puerto;
        _packete = new Packete(1, "HOLA", null);
    }
    @Override
    public void run() {
        try {/*
            _socket = new Socket(_ip, _puerto);
            _dos = new DataOutputStream(_socket.getOutputStream());
            _dis = new DataInputStream(_socket.getInputStream());
            _dos.writeUTF("hola");
            String respuesta="";
            respuesta = _dis.readUTF();
            System.out.println(respuesta);
            _dis.close();
            _dos.close();
            _socket.close();*/
            _socket = new Socket(_ip, _puerto);
            _ObjetoSaliente = new ObjectOutputStream(_socket.getOutputStream());
            _ObjetoEntrante = new ObjectInputStream(_socket.getInputStream());
            Object _obj = null;
            Object _objS = null;
            if(_packete instanceof Object)
            {
                _obj = (Object)_packete;
                _ObjetoSaliente.flush();
                _ObjetoSaliente.writeObject(_obj);
                
                _objS = _ObjetoEntrante.readObject();
                if (_objS instanceof Packete){
                    _packete = (Packete) _objS;
                    System.out.println(_packete.getMensaje());
                }
            }
            _ObjetoEntrante.close();
            _ObjetoSaliente.close();
            _socket.close();
        } catch (IOException ex) {
            System.out.println("Error con los Sockets "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error con las clases "+ex.getMessage());
        }
    }
}

