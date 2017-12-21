/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.File;
import java.util.Observable;

/**
 *
 * @author Leonardo
 */
public class Packete extends Observable {
    
    private int _accion;
    private String _mensaje;
    private File _file;

    public Packete (){
        
    }
    
    public Packete(int _accion, String _mensaje, File _file) {
        this._accion = _accion;
        this._mensaje = _mensaje;
        this._file = _file;
    }

    public int getAccion() {
        return _accion;
    }

    public void setAccion(int _accion) {
        this._accion = _accion;
    }

    public String getMensaje() {
        return _mensaje;
    }

    public void setMensaje(String _mensaje) {
        this._mensaje = _mensaje;
    }

    public File getFile() {
        return _file;
    }

    public void setFile(File _file) {
        this._file = _file;
    }
    
    
    
    
    
    
}
