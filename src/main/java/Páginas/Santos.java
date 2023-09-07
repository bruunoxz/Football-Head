/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Páginas;

import Utilitários.GameState;
import Utilitários.Personagem;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class Santos extends javax.swing.JFrame {

    /**
     * Creates new form Santos
     */
    public Santos() {
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

        jLabel1 = new javax.swing.JLabel();
        voltar = new javax.swing.JButton();
        prox = new javax.swing.JButton();
        esquerda = new javax.swing.JButton();
        direita = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 788));
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\santos.jpg")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1400, 788);

        voltar.setText("jButton1");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
        getContentPane().add(voltar);
        voltar.setBounds(390, 711, 270, 40);

        prox.setText("jButton2");
        prox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxActionPerformed(evt);
            }
        });
        getContentPane().add(prox);
        prox.setBounds(730, 711, 280, 50);

        esquerda.setText("jButton3");
        esquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esquerdaActionPerformed(evt);
            }
        });
        getContentPane().add(esquerda);
        esquerda.setBounds(500, 160, 71, 100);

        direita.setText("jButton4");
        direita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direitaActionPerformed(evt);
            }
        });
        getContentPane().add(direita);
        direita.setBounds(820, 150, 71, 120);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        new TelaInicial().setVisible(true);
        dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void esquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esquerdaActionPerformed
       new Palmeiras().setVisible(true);
       dispose();
    }//GEN-LAST:event_esquerdaActionPerformed

    private void direitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direitaActionPerformed
       new SaoPaulo().setVisible(true);
    }//GEN-LAST:event_direitaActionPerformed

    private void proxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxActionPerformed
        GameState gameState = GameState.getInstance();
        boolean escolhido = false;

        for (Personagem personagem : gameState.getPersonagensEscolhidos()) {
            if (personagem.getNome().equals("Soteldo")) {
                escolhido = true;
                break; 
            }
        }

        if (escolhido) {
            JOptionPane.showMessageDialog(this, "Soteldo já foi escolhido.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } else if(gameState.getPersonagensEscolhidos().size() >= 1){
            Personagem personagem8 = new Personagem("Soteldo", new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\soteldo.png"));
            new Jogo().setVisible(true);
            dispose();
        }else{
            Personagem personagem8 = new Personagem("Soteldo", new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\soteldo.png"));
            gameState.adicionarPersonagem(personagem8);
            new Flamengo().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_proxActionPerformed

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
            java.util.logging.Logger.getLogger(Santos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Santos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Santos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Santos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Santos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton direita;
    private javax.swing.JButton esquerda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton prox;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
