/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class ConfiguracionSocket extends javax.swing.JFrame {

    InicializadorServidor _inicializadorServidor;
    
    /**
     * Creates new form ConfiguracionSocket
     */
    public ConfiguracionSocket() {
        
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_ip = new javax.swing.JLabel();
        jLabel_puerto = new javax.swing.JLabel();
        jTextField_ip = new javax.swing.JTextField();
        jTextField_puerto = new javax.swing.JTextField();
        jButton_conectar = new javax.swing.JButton();
        jButton_desconectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_ip.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_ip.setText("IP :");

        jLabel_puerto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_puerto.setText("Puerto :");

        jTextField_ip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_ip.setText("localhost");

        jTextField_puerto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_puerto.setText("6321");

        jButton_conectar.setText("Conectar");
        jButton_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_conectarActionPerformed(evt);
            }
        });

        jButton_desconectar.setText("Desconectar");
        jButton_desconectar.setToolTipText("");
        jButton_desconectar.setEnabled(false);
        jButton_desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_desconectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_puerto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_ip)
                            .addComponent(jTextField_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jButton_conectar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_desconectar)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_ip)
                    .addComponent(jLabel_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_puerto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_conectar)
                    .addComponent(jButton_desconectar))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_conectarActionPerformed
        // TODO add your handling code here:
        
        try {
            
            Consola _consola = new Consola();
        _consola.setVisible(true);
            this.dispose();
//            _inicializadorServidor = new InicializadorServidor
//            (jTextField_ip.getText(), Integer.parseInt(jTextField_puerto.getText()));
//            jButton_conectar.setEnabled(false);
//            jButton_desconectar.setEnabled(true);
//            _inicializadorServidor.start();
            
            
        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(rootPane, "Puerto Invalido");
            
        }
        
        
    }//GEN-LAST:event_jButton_conectarActionPerformed

    private void jButton_desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_desconectarActionPerformed
        // TODO add your handling code here:
        
        _inicializadorServidor.cerrar();
        jButton_conectar.setEnabled(true);
        jButton_desconectar.setEnabled(false);
        
    }//GEN-LAST:event_jButton_desconectarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguracionSocket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_conectar;
    private javax.swing.JButton jButton_desconectar;
    private javax.swing.JLabel jLabel_ip;
    private javax.swing.JLabel jLabel_puerto;
    private javax.swing.JTextField jTextField_ip;
    private javax.swing.JTextField jTextField_puerto;
    // End of variables declaration//GEN-END:variables
}
