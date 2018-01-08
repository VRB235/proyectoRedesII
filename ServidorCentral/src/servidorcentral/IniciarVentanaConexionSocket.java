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
public class IniciarVentanaConexionSocket extends Thread {
    
    @Override
    public void run(){
        
        ConfiguracionSocket _configuracionSocket = new ConfiguracionSocket();
        _configuracionSocket.setVisible(true);
        
    }
    
}
