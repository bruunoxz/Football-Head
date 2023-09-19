/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Páginas;

import Utilitários.GameState;
import Utilitários.KeyManager;
import Utilitários.Personagem;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author bruno
 */
public class Jogo extends javax.swing.JFrame {
    boolean jogoEmExecucao = true;
    private boolean tabPressed1;
    private boolean tabPressed2;
    int velocidadeX = 10;
    int velocidadeY = 5;
    boolean jogdireita1 = false;
    boolean jogesquerda1 = false;
    boolean jogdireita2 = false;
    boolean jogesquerda2 = false;
    boolean jogchutando1 = false;
    boolean jogchutando2 = false;
    private KeyManager km;
    
    /**
     * Creates new form Jogo
     */
    public Jogo() {
        initComponents();
        GameState gameState = GameState.getInstance();
        if (!gameState.getPersonagensEscolhidos().isEmpty()) {
            Personagem personagem = gameState.getPersonagensEscolhidos().get(0);
            if (personagem != null && personagem.getImagem() != null) {
                //Defina a imagem do JLabel com base no primeiro personagem escolhido
                jogador1.setIcon(personagem.getImagem());
                time1.setIcon(personagem.getPlacar());
            }
        // Verificar se há pelo menos dois personagens escolhidos
        if (gameState.getPersonagensEscolhidos().size() >= 2) {
            // Obter o segundo personagem
            Personagem personagem2 = gameState.getPersonagensEscolhidos().get(1);
            if (personagem2 != null && personagem2.getImagem() != null) {
                    jogador2.setIcon(personagem2.getImagem());
                    time2.setIcon(personagem2.getPlacar());
            }
        }
    }
        km = new KeyManager();
        
            addKeyListener(new KeyAdapter() {
                //Coordenadas do jogador1
                int xInicialp1 = jogador1.getX();
                int yInicialp1 = jogador1.getY();
                int xAtualp1 = xInicialp1;
                int yAtualp1 = yInicialp1;
                boolean pulando1 = false;
                int puloY1 = 0;
                int velocidadeSalto1 = -25; // Velocidade inicial de salto
                int gravidade1 = 2; 
                //Coordenadas do jogador2   
                int xInicialp2 = jogador2.getX();
                int yInicialp2 = jogador2.getY();
                int xAtualp2 = xInicialp2;
                int yAtualp2 = yInicialp2;
                boolean pulando2 = false;
                int puloY2 = 0;
                int velocidadeSalto2 = -25; // Velocidade inicial de salto
                int gravidade2 = 2; 

                @Override
                public void keyPressed(KeyEvent e) {
                 int keyCode = e.getKeyCode();
                switch (keyCode) {
                case KeyEvent.VK_W:
                    if (!pulando1) {
                    pulando1 = true;
                    puloY1 = velocidadeSalto1; // Define a velocidade de salto
                    Timer timer = new Timer(15, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Aplica a velocidade de salto
                            yAtualp1 += puloY1;
                            puloY1 += gravidade1; // Aplica a força de gravidade

                            // Verifica se o jogador atingiu o solo
                            if (yAtualp1 >= yInicialp1) {
                                yAtualp1 = yInicialp1;
                                pulando1 = false; // O jogador parou de pular
                                ((Timer) e.getSource()).stop(); // Para o temporizador
                            }

                            // Atualiza a posição do jogador1
                            jogador1.setLocation(xAtualp1, yAtualp1);
                        }
                    });
                    timer.start();
                }
                    break;
                case KeyEvent.VK_D:
                    xAtualp1 +=10;
                    jogador1.setLocation(xAtualp1, yAtualp1);
                    jogdireita1 = true;
                    break;
                case KeyEvent.VK_A:
                    xAtualp1 -=10;
                    jogador1.setLocation(xAtualp1, yAtualp1);
                    jogesquerda1 = true;
                    break;
                case KeyEvent.VK_Q:
                    tabPressed1 = true;
                    atualizarImagem();
                    jogchutando1 = true;
                    break;
                case KeyEvent.VK_UP:
                    if (!pulando2) {
                    pulando2 = true;
                    puloY2 = velocidadeSalto2; // Define a velocidade de salto
                    Timer timer = new Timer(15, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Aplica a velocidade de salto
                            yAtualp2 += puloY2;
                            puloY2 += gravidade2; // Aplica a força de gravidade

                            // Verifica se o jogador atingiu o solo
                            if (yAtualp2 >= yInicialp2) {
                                yAtualp2 = yInicialp2;
                                pulando2 = false; // O jogador parou de pular
                                ((Timer) e.getSource()).stop(); // Para o temporizador
                            }

                            // Atualiza a posição do jogador1
                            jogador2.setLocation(xAtualp2, yAtualp2);
                        }
                    });
                    timer.start();
                }
                    break;
                case KeyEvent.VK_RIGHT:
                    xAtualp2 +=10;
                    jogador2.setLocation(xAtualp2, yAtualp2);
                    jogdireita2 = true;
                    break;
                case KeyEvent.VK_LEFT:
                    xAtualp2 -=10;
                    jogador2.setLocation(xAtualp2, yAtualp2);
                    jogesquerda2 = true;
                    break;
                case KeyEvent.VK_P:
                    tabPressed2 = true;
                    atualizarImagem();
                    break;
        }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    tabPressed1 = false;
                    atualizarImagem();
                }else if(e.getKeyCode() == KeyEvent.VK_P){
                    tabPressed2 = false;
                    atualizarImagem();
                }else if(e.getKeyCode() == KeyEvent.VK_W){
                if (yAtualp1 >= yInicialp1) {
                yAtualp1 = yInicialp1; // Define a posição inicial somente se estiver no solo
                }
                }else if(e.getKeyCode() == KeyEvent.VK_UP){
                if (yAtualp2 >= yInicialp2) {
                yAtualp2 = yInicialp2;
                }
                }
            }

        });
            
        /*int bolaX = bola.getX();
        int bolaY = bola.getY();
        int larguraDaBola = bola.getWidth();
        int alturaDaBola = bola.getHeight();
        int jogador1X = jogador1.getX();
        int jogador1Y = jogador1.getY();
        int larguraJogador1 = jogador1.getWidth();
        int alturaJogador1 = jogador1.getHeight();
        int jogador2X = jogador2.getX();
        int jogador2Y = jogador2.getY();
        int larguraJogador2 = jogador2.getWidth();
        int alturaJogador2 = jogador2.getHeight();*/
       
        Thread jogoThread = new Thread(this::iniciarLoopDoJogo);
        jogoThread.start();
    }
    
    //MÉTODOS
    
    
        private void verificarColisoes() {
        Rectangle retanguloJogador1 = jogador1.getBounds();
        Rectangle retanguloJogador2 = jogador2.getBounds();
        Rectangle retanguloBola = bola.getBounds();

        if (retanguloJogador1.intersects(retanguloBola) || retanguloJogador2.intersects(retanguloBola)) {
            // Houve uma colisão entre um dos jogadores e a bola
            moverBola(); // Chame o método para mover a bola ou realizar ações relacionadas à colisão
        }
        }
        
            
                
        void moverBola() {
            
        int larguraDaJanela = getWidth();
        int alturaDaJanela = getHeight();

        int bolaX = bola.getX();
        int bolaY = bola.getY();
        int larguraDaBola = bola.getWidth();
        int alturaDaBola = bola.getHeight();

        int velocidadeX = 5;
        int velocidadeY = 0;

        // Atualizar a posição da bola com base na velocidade
        bolaX += velocidadeX;
        bolaY += velocidadeY;
        // Certifique-se de verificar os limites do campo e reverter a direção se a bola atingir um limite
        if (bolaX < 0 || bolaX + larguraDaBola > larguraDaJanela) {
            // A bola atingiu um dos limites laterais, inverta a velocidade horizontal
            velocidadeX = -velocidadeX;
        }
        if (bolaY < 0 || bolaY + alturaDaBola > alturaDaJanela) {
            // A bola atingiu um dos limites superior ou inferior, inverta a velocidade vertical
            velocidadeY = -velocidadeY;
        }
        // Atualize a posição da bola na tela
        bola.setLocation(bolaX, bolaY);
        
        if (colisaoComBola(jogador1, bola)) {
            if(jogdireita1){
                jogesquerda1 = false;
                bolaX += 10;
                bola.setLocation(bolaX, bolaY); 
            }else if(jogesquerda1){
                jogdireita1 = false;
                bolaX -=10;
                bola.setLocation(bolaX, bolaY);
            }else if(jogchutando1){
                bolaX += 30;
                bolaY -= 20;
                bola.setLocation(bolaX, bolaY);
            }
        }else if(colisaoComBola(jogador2, bola)){
            if(jogdireita2){
                jogesquerda2 = false;
                bolaX += 10;
                bola.setLocation(bolaX, bolaY);
            }else if(jogesquerda2){
                jogdireita2 = false;
                bolaX -=10;
                bola.setLocation(bolaX, bolaY);
            }
        }
        
        if(colisaoComJogador(jogador1, jogador2)){
            
        }
    }

    boolean colisaoComBola(JLabel jogador, JLabel bola) {
        return jogador.getBounds().intersects(bola.getBounds());
    }
    boolean colisaoComJogador(JLabel jogador1, JLabel jogador2){
        return jogador1.getBounds().intersects(jogador2.getBounds());
    }
    

    private void atualizarImagem() {
        GameState gameState = GameState.getInstance();
        if (!gameState.getPersonagensEscolhidos().isEmpty()) {
            Personagem personagem = gameState.getPersonagensEscolhidos().get(0);
            Personagem personagem2 = gameState.getPersonagensEscolhidos().get(1);
            if (personagem != null && personagem2 != null) {
                // Obter a imagem2 do personagem
                ImageIcon chutep1 = personagem.getImagem2();
                ImageIcon chutep2 = personagem2.getImagem2();
                if (tabPressed1 && chutep1 != null) {
                    jogador1.setIcon(chutep1);
                } else {
                    jogador1.setIcon(personagem.getImagem());
                }
                if(tabPressed2 && chutep2 !=null){
                    jogador2.setIcon(chutep2);
                }else{
                    jogador2.setIcon(personagem2.getImagem());
                }
            }
        }
    }
    
        private void iniciarLoopDoJogo() {
        while (jogoEmExecucao) {
            // Atualize a lógica do jogo
            verificarColisoes();

            // Renderize a tela
            // ...

            // Adicione uma pequena pausa para controlar a taxa de atualização do loop
            try {
                Thread.sleep(16); // Isso atualiza o jogo aproximadamente a 60 quadros por segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
       private void encerrarJogo() {
        // Execute qualquer lógica de encerramento necessária, como salvar progresso do jogo, etc.
        jogoEmExecucao = false;
        dispose(); // Feche o JFrame
        }
    




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bola = new javax.swing.JLabel();
        jogador1 = new javax.swing.JLabel();
        jogador2 = new javax.swing.JLabel();
        time1 = new javax.swing.JLabel();
        time2 = new javax.swing.JLabel();
        placar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 788));
        getContentPane().setLayout(null);

        bola.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\bola.png")); // NOI18N
        getContentPane().add(bola);
        bola.setBounds(660, 680, 75, 75);
        getContentPane().add(jogador1);
        jogador1.setBounds(120, 620, 134, 134);
        getContentPane().add(jogador2);
        jogador2.setBounds(1060, 620, 134, 134);
        getContentPane().add(time1);
        time1.setBounds(10, 10, 170, 60);
        getContentPane().add(time2);
        time2.setBounds(10, 90, 170, 60);

        placar.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\placar.PNG")); // NOI18N
        getContentPane().add(placar);
        placar.setBounds(0, -10, 276, 250);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\fundojogo.jpg")); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1400, 788);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bola;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jogador1;
    private javax.swing.JLabel jogador2;
    private javax.swing.JLabel placar;
    private javax.swing.JLabel time1;
    private javax.swing.JLabel time2;
    // End of variables declaration//GEN-END:variables
}
