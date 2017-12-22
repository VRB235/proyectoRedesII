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
public class Video {
    
    private int _id;
    private String _nombre;
    private String _ruta;
    private String _tamaño;
    private int _numDescargado;
    private int _dueño;

    public Video(){
        
    }
    
    public Video(int _id, String _nombre, String _ruta, String _tamaño, int _numDescargado, int _dueño) {
        this._id = _id;
        this._nombre = _nombre;
        this._ruta = _ruta;
        this._tamaño = _tamaño;
        this._numDescargado = _numDescargado;
        this._dueño = _dueño;
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

    public String getRuta() {
        return _ruta;
    }

    public void setRuta(String _ruta) {
        this._ruta = _ruta;
    }

    public String getTamaño() {
        return _tamaño;
    }

    public void setTamaño(String _tamaño) {
        this._tamaño = _tamaño;
    }

    public int getNumDescargado() {
        return _numDescargado;
    }

    public void setNumDescargado(int _numDescargado) {
        this._numDescargado = _numDescargado;
    }

    public int getDueño() {
        return _dueño;
    }

    public void setDueño(int _dueño) {
        this._dueño = _dueño;
    }
    
    
    
}
