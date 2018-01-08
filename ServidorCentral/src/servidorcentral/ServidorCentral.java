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
        /*
        ConfiguracionSocket _configuracionSocket = new ConfiguracionSocket();
        _configuracionSocket.setVisible(true);*/
        /*
        ManejarAccion m = new ManejarAccion();
        ArrayList<Video> lista = m.accionDir();
        
        System.out.println("FOR");
        for (Video video : lista) {
            System.out.println(video.getNombre());
        }
        
        ManejadorOrden ma = new ManejadorOrden("1:dir");
        ma.accion();
        ArrayList<Video> listav = ma.obtenerVideos();
        
        for (Video video : listav) {
            System.out.println(video.getNombre());
        }*/
        
        InicializadorServidor i = new InicializadorServidor("127.0.0.1", 1324);
        i.iniciar();
        
    }
    
}
