/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author Leonardo
 */
public class ManejadorOrden {
    
    /**
     * Metodo para transformar las ordenes del cliente en ordenes entendibles 
     * por el servidor
     * @param comando Comando que se desea ejecutar por el servidor
     * @return Paquete a ser enviado al servidor
     */
    public String accion(String comando){
        
        try {
            
            String _comando = comando.toLowerCase();
            String [] _comandoArreglo = _comando.split(" ");
            if(_comandoArreglo[0].equals("dir")){
                
                return "1:"+_comandoArreglo[0];
                
            }else{
                
                if(_comandoArreglo[0].equals("exit")){
                    
                    return "0:"+_comandoArreglo[0];
                    
                }else{
                    
                    if(_comandoArreglo[0].contains("insc")){
                        
                        return "2:"+_comandoArreglo[0]+":"+_comandoArreglo[1];
                        
                    }else{
                        
                        if(_comandoArreglo[0].equals("videos")){
                            
                            return "3:"+_comandoArreglo[0]+":"+_comandoArreglo[1]
                                    +":"+_comandoArreglo[2];
                            
                        }else{
                            
                            if(_comandoArreglo[0].equals("descargar")){
                                
                                return "4:"+_comandoArreglo[0];
                                
                            }else{
                                
                                if(_comandoArreglo[0].equals("videos_mas_descargados")){
                                    
                                    return "5:"+_comandoArreglo[0];
                                    
                                }else{
                                    
                                    if(_comandoArreglo[0].equals("clientes_mas_videos")){
                                        
                                        return "6:"+_comandoArreglo[0];
                                        
                                    }
                                    
                                }
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
            }
            
        } catch (Exception e) {
            
            System.out.println("Error en Comando: "+e.getStackTrace());
            
        }
        
        return null;
        
    }
    
}
