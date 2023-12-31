/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Páginas;

import Utilitários.ConectaMongo;
import Utilitários.GameState;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class Usuario extends javax.swing.JFrame {

    /**
     * Creates new form Usuário
     */
    public Usuario() {
        initComponents();
        setLocationRelativeTo(null); 
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        user2 = new javax.swing.JTextField();
        user1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        prox = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 788));
        getContentPane().setLayout(null);

        user2.setBackground(new java.awt.Color(0, 51, 255));
        user2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        user2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(user2);
        user2.setBounds(960, 410, 340, 70);

        user1.setBackground(new java.awt.Color(0, 51, 255));
        user1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        user1.setForeground(new java.awt.Color(255, 255, 255));
        user1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user1ActionPerformed(evt);
            }
        });
        getContentPane().add(user1);
        user1.setBounds(70, 410, 340, 70);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\nomeusers.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1400, 788);

        prox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxActionPerformed(evt);
            }
        });
        getContentPane().add(prox);
        prox.setBounds(420, 620, 560, 110);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxActionPerformed
        GameState gameState = GameState.getInstance();
        if(user1.getText().isEmpty() || user2.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Os dois usuários precisam ser preenchidos", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }else{
            ConectaMongo con = new ConectaMongo();
            if(con.userExists(user1.getText()) && con.userExists(user2.getText())){
                new AtleticoMg().setVisible(true);
                gameState.setUser1(user1.getText());
                gameState.setUser2(user2.getText());
                dispose();
            }else{
                con.insertValues(user1.getText(), 0, 0, 0);
                con.insertValues(user2.getText(), 0, 0, 0);
                new AtleticoMg().setVisible(true);
                gameState.setUser1(user1.getText());
                gameState.setUser2(user2.getText());
                dispose();
            }
        }
    }//GEN-LAST:event_proxActionPerformed

    private void user1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user1ActionPerformed

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton prox;
    private javax.swing.JTextField user1;
    private javax.swing.JTextField user2;
    // End of variables declaration//GEN-END:variables
}
