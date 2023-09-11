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
public class Gremio extends javax.swing.JFrame {

    /**
     * Creates new form Gremio
     */
    public Gremio() {
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
        direita = new javax.swing.JButton();
        esquerda = new javax.swing.JButton();
        voltar = new javax.swing.JButton();
        prox = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 788));
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\gremio.jpg")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1400, 790);

        direita.setText("jButton1");
        direita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direitaActionPerformed(evt);
            }
        });
        getContentPane().add(direita);
        direita.setBounds(830, 140, 71, 120);

        esquerda.setText("jButton1");
        esquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esquerdaActionPerformed(evt);
            }
        });
        getContentPane().add(esquerda);
        esquerda.setBounds(490, 140, 71, 120);

        voltar.setText("jButton1");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
        getContentPane().add(voltar);
        voltar.setBounds(390, 701, 270, 50);

        prox.setText("jButton2");
        prox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxActionPerformed(evt);
            }
        });
        getContentPane().add(prox);
        prox.setBounds(720, 700, 290, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void esquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esquerdaActionPerformed
        new Flamengo().setVisible(true);
        dispose();
    }//GEN-LAST:event_esquerdaActionPerformed

    private void direitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direitaActionPerformed
       new Internacional().setVisible(true);
       dispose();
    }//GEN-LAST:event_direitaActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        new TelaInicial().setVisible(true);
        dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void proxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxActionPerformed
    GameState gameState = GameState.getInstance();
    boolean escolhido = false;
    
    for (Personagem personagem : gameState.getPersonagensEscolhidos()) {
        if (personagem.getNome().equals("Luis Suarez")) {
            escolhido = true;
            break; 
        }
    }
    
    if (escolhido) {
        JOptionPane.showMessageDialog(this, "Luis Suarez já foi escolhido.", "Informação", JOptionPane.INFORMATION_MESSAGE);
    } else if(gameState.getPersonagensEscolhidos().size() >= 1){
        Personagem personagem5 = new Personagem("Luis Suarez", new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\suarez.png"));
        gameState.adicionarPersonagem(personagem5);
        new Jogo().setVisible(true);
        dispose();
    }else{
        Personagem personagem5 = new Personagem("Luis Suarez", new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\suarez.png"));
        gameState.adicionarPersonagem(personagem5);
        new Internacional().setVisible(true);
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
            java.util.logging.Logger.getLogger(Gremio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gremio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gremio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gremio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gremio().setVisible(true);
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
