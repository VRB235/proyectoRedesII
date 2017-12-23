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
    ArrayList<Video> _listaVideos;
    
    /**
     * Constructor del manejador de Ordenes
     * @param mensaje 
     */
    public ManejadorOrden(String mensaje){
        
        this._mensaje = mensaje;
        this._mensajeArreglo = mensaje.split(":");
        
    }
    
    public ArrayList<Video> obtenerVideos(){
        return _listaVideos;
    }
    
    /**
     * Metodo para manejar las acciones realizadas por el servidor
     * @return La respuesta del servidor al cliente
     */
    public void accion(){
        
        if(_mensajeArreglo[0].equals("1")){
            
            _manejarAccion = new ManejarAccion();
            System.out.println("Accion DIR Orden");
             _listaVideos = _manejarAccion.accionDir();
            
        }else{
            
            if(_mensajeArreglo[0].equals("0")){
                
                System.out.println("Accion Cerrar Conexion");
                //return "Accion Cerrar Conexion";
                
            }else{
                
                if(_mensajeArreglo[0].equals("2")){
                    
                    System.out.println("Accion registrar usuario");
                    //return "Accion registrar usuario";
                    
                }else{
                    
                    if(_mensajeArreglo[0].equals("3")){
                        
                        System.out.println("Registrar Video");
                        //return "Registrar Video";
                        
                    }else{
                        
                        if(_mensajeArreglo[0].equals("4")){
                            
                            System.out.println("Descargar Video");
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
        
        //return null;
        
    }
    
    
    
    
}
