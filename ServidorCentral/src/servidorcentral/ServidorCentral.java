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
public class ServidorCentral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        IniciarVentanaConexionSocket ini= new  IniciarVentanaConexionSocket();
        ini.start();
        
 
        
        InicializadorServidor i = new InicializadorServidor("127.0.0.1", 1324);
        i.start();
        
    }
    
}
