/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Páginas;

import Utilitários.GameState;
import Utilitários.Personagem;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author bruno
 */
public class Botafogo extends javax.swing.JFrame {

    /**
     * Creates new form TimePlayer1
     */
    public Botafogo() {
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

        botafogop1 = new javax.swing.JLabel();
        voltar = new javax.swing.JButton();
        prox = new javax.swing.JButton();
        direita = new javax.swing.JButton();
        esquerda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 787));
        getContentPane().setLayout(null);

        botafogop1.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\botafogo.jpg")); // NOI18N
        botafogop1.setText("jLabel2");
        getContentPane().add(botafogop1);
        botafogop1.setBounds(0, 0, 1400, 787);

        voltar.setText("jButton1");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
        getContentPane().add(voltar);
        voltar.setBounds(390, 701, 260, 60);

        prox.setText("jButton1");
        prox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxActionPerformed(evt);
            }
        });
        getContentPane().add(prox);
        prox.setBounds(730, 700, 280, 60);

        direita.setText("jButton1");
        direita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direitaActionPerformed(evt);
            }
        });
        getContentPane().add(direita);
        direita.setBounds(820, 160, 71, 100);

        esquerda.setText("jButton2");
        esquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esquerdaActionPerformed(evt);
            }
        });
        getContentPane().add(esquerda);
        esquerda.setBounds(490, 160, 71, 100);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void esquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esquerdaActionPerformed
       new AtleticoMg().setVisible(true);
       dispose();
    }//GEN-LAST:event_esquerdaActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
       new TelaInicial().setVisible(true);
       dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void direitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direitaActionPerformed
        new Corinthians().setVisible(true); 
        dispose();
    }//GEN-LAST:event_direitaActionPerformed

    private void proxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxActionPerformed
    GameState gameState = GameState.getInstance();
    boolean escolhido = false;
    
    for (Personagem personagem : gameState.getPersonagensEscolhidos()) {
        if (personagem.getNome().equals("Tiquinho")) {
            escolhido = true;
            break; 
        }
    }
    
    if (escolhido) {
        JOptionPane.showMessageDialog(this, "Tiquinho já foi escolhido.", "Informação", JOptionPane.INFORMATION_MESSAGE);
    } else if(gameState.getPersonagensEscolhidos().size() >= 1){
        Personagem personagem2 = new Personagem("Tiquinho", new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\tiquinho.png"));
        new Jogo().setVisible(true);
        dispose();
    }else{
        Personagem personagem2 = new Personagem("Tiquinho", new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\tiquinho.png"));
        gameState.adicionarPersonagem(personagem2);
        new Corinthians().setVisible(true);
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
            java.util.logging.Logger.getLogger(Botafogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Botafogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Botafogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Botafogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Botafogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botafogop1;
    private javax.swing.JButton direita;
    private javax.swing.JButton esquerda;
    private javax.swing.JButton prox;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
