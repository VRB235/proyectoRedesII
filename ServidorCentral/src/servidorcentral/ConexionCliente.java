/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Leonardo
 */
public class ConexionCliente extends Thread {
    
    private Socket _socket; 
    private Packete _packete;
    private ObjectOutputStream _ObjetoSaliente;
    private ObjectInputStream _ObjetoEntrante;
    DataOutputStream _dos;
    DataInputStream _dis;
    
    public ConexionCliente (Socket socket, Packete packete){
        this._socket = socket;
        this._packete = packete;
        try {/*
            _dos  = new DataOutputStream(_socket.getOutputStream());
            _dis = new DataInputStream(_socket.getInputStream());
            System.out.println(_dis.readUTF());
            _dos.writeUTF("COmo estas?");
            _dis.close();
            _dos.close();
            _socket.close();*/
            
            _ObjetoSaliente = new ObjectOutputStream(_socket.getOutputStream());
            _ObjetoEntrante = new ObjectInputStream(_socket.getInputStream());
            
            
            /*_ObjetoEntrante = new ObjectInputStream(_socket.getInputStream());
            System.out.println(_ObjetoEntrante.readObject());
            _packete = (Packete) _ObjetoEntrante.readObject();
            
            System.out.println("Accion: "+_packete.getAccion());
            System.out.println("Mensaje: "+_packete.getMensaje());
            _packete.setMensaje("Como estas?");
            _ObjetoSaliente.writeObject(this._packete);
            _ObjetoEntrante.close();
            _ObjetoSaliente.close();
            _socket.close();*/
            
        } catch (IOException ex) {
            System.out.println("Error al crear los stream de entradas y salidad: "+ ex.getMessage());
        }/*
        catch(ClassNotFoundException ex){
            System.out.println("Error con al clase"+ex.getMessage());
        }*/
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
     public void desconnectar() {
        try {
            _ObjetoEntrante.close();
            _ObjetoSaliente.close();
            _socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
            //Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {/*
        String accion = "";
        try {
            accion = _dis.readUTF();
            if(accion.equals("hola")){
                _dos.writeUTF("adios");
            }
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        desconnectar();*/
        
        try {
            Object _obj = _ObjetoEntrante.readObject();
            if(_obj instanceof Packete)
            {
                _packete.setMensaje("Como estas?");
                Object _paquete = (Packete)_packete;
                _ObjetoSaliente.flush();
                _ObjetoSaliente.writeObject(_paquete);
                
            }
            desconnectar();
        } catch (Exception e) {
        }
        
    }
} 