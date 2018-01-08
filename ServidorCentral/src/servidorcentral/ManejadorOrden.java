/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.util.ArrayList;

/**
 *
 * @author Leonardo
 */
public class ManejadorOrden {
    
    String _mensaje;
    String[] _mensajeArreglo;
    ManejarAccion _manejarAccion;
    String _listaVideos;
    
    /**
     * Constructor del manejador de Ordenes
     * @param mensaje 
     */
    public ManejadorOrden(String mensaje){
        
        this._mensaje = mensaje;
        this._mensajeArreglo = mensaje.split(":");
        
    }
    
    public String obtenerVideos(){
        return _listaVideos;
    }
    
    /**
     * Metodo para manejar las acciones realizadas por el servidor
     */
    public String accion(){
        
        if(_mensajeArreglo[0].equals("1")){
            
            _manejarAccion = new ManejarAccion();
            System.out.println("Accion DIR Orden");
             return _manejarAccion.accionDir();
            
        }else{
            
            if(_mensajeArreglo[0].equals("0")){
                
                System.out.println("Accion Cerrar Conexion");
                //return "Accion Cerrar Conexion";
                
            }else{
                
                if(_mensajeArreglo[0].equals("2")){
                    
                    System.out.println("Accion registrar usuario");
                    _manejarAccion = new ManejarAccion();
                    return _manejarAccion.accionInsn(_mensajeArreglo[1], 
                            _mensajeArreglo[2], Integer.parseInt(_mensajeArreglo[3]));
                    
                }else{
                    
                    if(_mensajeArreglo[0].equals("3")){
                        
                        System.out.println("Accion Registrar Video");
                        _manejarAccion = new ManejarAccion();
                        return _manejarAccion.accionVideo(_mensajeArreglo[1],
                                null, _mensajeArreglo[2], _mensajeArreglo[3]);
                        //return "Registrar Video";
                        
                    }else{
                        
                        if(_mensajeArreglo[0].equals("4")){
                            
                            System.out.println("Descargar Video");
                            
                            _manejarAccion = new ManejarAccion();
                            return _manejarAccion.accionDescargar(_mensajeArreglo[1]);
                            
                            //return "Descargar Video";
                            
                        }else{
                            
                            if(_mensajeArreglo[0].equals("5")){
                                
                                System.out.println("Videos mas Descargados");
                                //return "Videos mas Descargados";
                                
                            }else{
                                
                                if(_mensajeArreglo[0].equals("6")){
                                    
                                    System.out.println("Clientes con mas descargas");
                                    //return "Clientes con mas descargas";
                                    
                                }
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
            }
            
        }
        
        return null;
        
    }
    
    
    
    
}
