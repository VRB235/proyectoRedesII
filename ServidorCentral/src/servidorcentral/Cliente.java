/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

/**
 *
 * @author Leonardo
 */
public class Cliente {
    
    private int _id;
    private String _nombre;
    private int _numeroVideosDescargados;
    private int _puerto;
    private String _ip;
    
    public Cliente (){
        
    }
    
    public Cliente(int _id, String _nombre, int _numeroVideosDescargados, int _puerto, String _ip) {
        this._id = _id;
        this._nombre = _nombre;
        this._numeroVideosDescargados = _numeroVideosDescargados;
        this._puerto = _puerto;
        this._ip = _ip;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public int getNumeroVideosDescargados() {
        return _numeroVideosDescargados;
    }

    public void setNumeroVideosDescargados(int _numeroVideosDescargados) {
        this._numeroVideosDescargados = _numeroVideosDescargados;
    }

    public int getPuerto() {
        return _puerto;
    }

    public void setPuerto(int _puerto) {
        this._puerto = _puerto;
    }

    public String getIp() {
        return _ip;
    }

    public void setIp(String _ip) {
        this._ip = _ip;
    }

    
    
    
}
