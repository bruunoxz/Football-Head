/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Páginas;

import Utilitários.ConectaMongo;
import Utilitários.GameState;
import Utilitários.Personagem;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    //Flags
    boolean jogoEmExecucao = true;
    boolean flagJog = true;
    boolean pulando1 = false;
    boolean pulando2 = false;
    boolean colisao1 = false;
    boolean colisao2 = false;
    boolean jogdireita1;
    boolean jogesquerda1;
    boolean jogdireita2;
    boolean jogesquerda2;
    boolean jogchutando1;
    boolean jogchutando2;
    //Array de teclas pressionadas
    private Set<Integer> teclasPressionadasJogador1 = new HashSet<>();
    private Set<Integer> teclasPressionadasJogador2 = new HashSet<>();
    //Valores para animação
    int velocidadeX = 10;
    int velocidadeY = 5;
    int puloY1 = 0;
    int velocidadeSalto1 = -30;
    int gravidade1 = 2; 
    int puloY2 = 0;
    int velocidadeSalto2 = -30;
    int gravidade2 = 2; 
    //Atributos utilizados para armazenar o valor atual de um componente
    int xAtualp1;
    int yAtualp1;
    int xAtualp2;
    int yAtualp2;     
    int bolaYat;
    int bolaXat;
    //Contagem placares
    int count1;
    int count2; 
    GameState gameState = GameState.getInstance();
    Resultado resultado = new Resultado();
    
    /**
     * Creates new form Jogo
     */
    public Jogo() {
        initComponents();
        setLocationRelativeTo(null); 
        setResizable(false);
        count1 = 0;
        count2 = 0;
        placar(); 
        
        Timer timer = new Timer(120000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarJogo();
            }
        });
        timer.start();
        
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
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                // Verifique qual jogador pressionou a tecla
                if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_Q) {
                    // Tecla pressionada pelo jogador 1
                    teclasPressionadasJogador1.add(keyCode);
                } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_P) {
                    // Tecla pressionada pelo jogador 2
                    teclasPressionadasJogador2.add(keyCode);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                // Verifique qual jogador liberou a tecla
                if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_Q) {
                    // Tecla liberada pelo jogador 1
                    teclasPressionadasJogador1.remove(keyCode);
                } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_P) {
                    // Tecla liberada pelo jogador 2
                    teclasPressionadasJogador2.remove(keyCode);
                }
            }
        });
        Thread jogoThread = new Thread(this::iniciarLoopDoJogo);
        jogoThread.start();
        
    }
    
    //MÉTODOS
        
        private void processarTeclasJogador1() {
            int xInicialp1 = jogador1.getX();
            int yInicialp1 = jogador1.getY();
            xAtualp1 = xInicialp1;
            yAtualp1 = yInicialp1;
            
            if(flagJog){
            // Lógica para o jogador 1 com base nas teclas pressionadas
            if (teclasPressionadasJogador1.contains(KeyEvent.VK_D)) {
                xAtualp1 +=7;
                jogador1.setLocation(xAtualp1, yAtualp1);
                jogdireita1 = true;
                if(teclasPressionadasJogador1.contains(KeyEvent.VK_W)){
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
                }else if(teclasPressionadasJogador1.contains(KeyEvent.VK_Q)){
                    atualizarImagem();
                    jogchutando1 = true;
                }
            }else if(teclasPressionadasJogador1.contains(KeyEvent.VK_A)){
                xAtualp1 -=7;
                jogador1.setLocation(xAtualp1, yAtualp1);
                jogesquerda1 = true;
                if(teclasPressionadasJogador1.contains(KeyEvent.VK_W)){
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
                }
            }else if(teclasPressionadasJogador1.contains(KeyEvent.VK_W)){
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
            }else if(teclasPressionadasJogador1.contains(KeyEvent.VK_Q)){
                    jogchutando1 = true;
                    atualizarImagem();
            }else if(!(teclasPressionadasJogador1.contains(KeyEvent.VK_Q))){
                jogchutando1 = false;
                atualizarImagem();
            }
            }
        }

        private void processarTeclasJogador2() {
            // Lógica para o jogador 2 com base nas teclas pressionadas
            int xInicialp2 = jogador2.getX();
            int yInicialp2 = jogador2.getY();
            xAtualp2 = xInicialp2;
            yAtualp2 = yInicialp2;
            
                if(flagJog){
                if (teclasPressionadasJogador2.contains(KeyEvent.VK_RIGHT)) {
                xAtualp2 +=7;
                jogador2.setLocation(xAtualp2, yAtualp2);
                jogdireita2 = true;
                if(teclasPressionadasJogador2.contains(KeyEvent.VK_UP)){
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
                }
            }else if(teclasPressionadasJogador2.contains(KeyEvent.VK_LEFT)){
                xAtualp2 -=7;
                jogador2.setLocation(xAtualp2, yAtualp2);
                jogesquerda2 = true;
                    if(teclasPressionadasJogador2.contains(KeyEvent.VK_UP)){
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
                                   pulando2 = false;
                                   ((Timer) e.getSource()).stop(); 
                               }

                               jogador2.setLocation(xAtualp2, yAtualp2);
                           }
                       });
                       timer.start();
                   }
                }else if(teclasPressionadasJogador2.contains(KeyEvent.VK_P)){
                    jogchutando2 = true;
                    atualizarImagem();
                }
            }else if(teclasPressionadasJogador2.contains(KeyEvent.VK_UP)){
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
            }else if(teclasPressionadasJogador2.contains(KeyEvent.VK_P)){
                    jogchutando2 = true;
                    atualizarImagem();
            }else if(!(teclasPressionadasJogador2.contains(KeyEvent.VK_P))){
                jogchutando2 = false;
                atualizarImagem();
            }
                }
        }
    
    
    
        private void verificarColisoes() {
        Rectangle retanguloJogador1 = jogador1.getBounds();
        Rectangle retanguloJogador2 = jogador2.getBounds();
        Rectangle retanguloBola = bola.getBounds();
        if(colisaoComBola(jogador1, bola) && (colisaoComJogador(jogador1, jogador2))){
                
        }else if(retanguloJogador1.intersects(retanguloBola) || retanguloJogador2.intersects(retanguloBola)) {
            // Houve uma colisão entre um dos jogadores e a bola
            moverBola(); // Chame o método para mover a bola ou realizar ações relacionadas à colisão
        }
        if(colisaoComJogador(jogador1, jogador2)){
                // Verifique a direção do movimento do jogador1 (por exemplo, para a direita)
            if (jogdireita1) {
                // Impedir que jogador1 se mova para a direita (reverta o movimento)
                jogador1.setLocation(jogador1.getX() - 5, jogador1.getY());
            } else if (jogesquerda1) {
                // Impedir que jogador1 se mova para a esquerda (reverta o movimento)
                jogador1.setLocation(jogador1.getX() + 5, jogador1.getY());
            }

            // Verifique a direção do movimento do jogador2 (por exemplo, para a direita)
            if (jogdireita2) {
                // Impedir que jogador2 se mova para a direita (reverta o movimento)
                jogador2.setLocation(jogador2.getX() - 5, jogador2.getY());
            } else if (jogesquerda2) {
                // Impedir que jogador2 se mova para a esquerda (reverta o movimento)
                jogador2.setLocation(jogador2.getX() + 5, jogador2.getY());
            }
        }
        if(colisaoComGol(bola, gol1)){
            gol();
        }else if(colisaoComGol(bola, gol2)){
            gol();
        }
        }
        
        void moverBola() {
            
        int larguraDaJanela = 1350;
        int alturaDaJanela = 788;

        int bolaX = bola.getX();
        int bolaY = bola.getY();
        int larguraDaBola = bola.getWidth();
        int alturaDaBola = bola.getHeight();

        int velocidadeX = 5;
        int velocidadeY = 0;

           // Verifique se a bola atingiu o limite esquerdo ou direito da tela
    if (bolaX < 0 || bolaX + larguraDaBola > larguraDaJanela) {
        velocidadeX = -velocidadeX;

        // Verifique se a bola está no lado esquerdo ou direito da tela e reposicione-a no centro
        if (bolaX < 0) {
            // A bola atingiu o limite esquerdo da tela, mova-a para o centro
            bolaX = 660;
        } else {
            // A bola atingiu o limite direito da tela, mova-a para o centro
            bolaX = 660;
        }
    }

    if (bolaY < 0 || bolaY + alturaDaBola > alturaDaJanela) {
        // A bola atingiu um dos limites superior ou inferior, inverta a velocidade vertical
        velocidadeY = -velocidadeY;
    }

        // Atualize a posição da bola na tela
        bola.setLocation(bolaX, bolaY);
        bolaXat = bolaX;
        bolaYat = bolaY;
        
        if(flagJog){
        if (colisaoComBola(jogador1, bola)) {
            if(jogdireita1){
                jogesquerda1 = false;
                bolaX += 10;
                bola.setLocation(bolaX, bolaY); 
                if(jogchutando1){
                    Timer chuteTimer = new Timer(50, new ActionListener() {
                    double deltaY = -6; // Velocidade vertical negativa para subir
                    double deltaX = 6; // Velocidade horizontal positiva para mover para a direita

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Atualize as posições vertical e horizontal da bola para fazê-la se mover na diagonal
                        bolaYat += deltaY;
                        bolaXat += deltaX;
                        if(bolaXat > 1350){
                            bola.setLocation(660, 680);
                        }else{
                        bola.setLocation((int) bolaXat, (int) bolaYat);
                        }
                        // Verifique se a bola atingiu a altura desejada
                        if (bolaYat <= 550) {
                            deltaY = 4; // Altere a velocidade vertical para fazer a bola cair
                        }

                        // Verifique se a bola atingiu a altura final desejada
                        if (bolaYat >= 680) {
                            bolaYat = 680;
                            // Pare o Timer quando a bola atingir a altura final desejada
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });

                // Inicie o Timer de chute
                chuteTimer.start();
            }
            }else if(jogesquerda1){
                jogdireita1 = false;
                bolaX -=10;
                bola.setLocation(bolaX, bolaY);
            }
        }else if(colisaoComBola(jogador2, bola)){
            if(jogdireita2){
                jogesquerda2 = false;
                bolaX += 10;
                bola.setLocation(bolaX, bolaY);
                if(jogchutando2){
                    bolaX += 30;
                    bolaY -= 20;
                    bola.setLocation(bolaX, bolaY);
                }
            }else if(jogesquerda2){
                jogdireita2 = false;
                bolaX -=10;
                bola.setLocation(bolaX, bolaY);
                if(jogchutando2){
                    Timer chuteTimer = new Timer(50, new ActionListener() {
                    double deltaY = -6; // Velocidade vertical negativa para subir
                    double deltaX = -6; // Velocidade horizontal positiva para mover para a direita

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Atualize as posições vertical e horizontal da bola para fazê-la se mover na diagonal
                        bolaYat += deltaY;
                        bolaXat += deltaX;
                        if(bolaXat < 0){
                            bola.setLocation(660, 680);
                        }else{
                        bola.setLocation((int) bolaXat, (int) bolaYat);
                        }
                        // Verifique se a bola atingiu a altura desejada
                        if (bolaYat <= 550) {
                            deltaY = 4; // Altere a velocidade vertical para fazer a bola cair
                        }

                        // Verifique se a bola atingiu a altura final desejada
                        if (bolaYat >= 680) {
                            bolaYat = 680;
                            // Pare o Timer quando a bola atingir a altura final desejada
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });

                chuteTimer.start();
                }
            }
        }
        }
        
    }
       void gol(){
           try {
            File audioFile = new File("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\GritoGolLuisRoberto.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            if(flagJog){
            if (colisaoComGol(bola, gol1)) {
                clip.start(); // Inicie a reprodução do som
                flagJog = false;
                jogador1.setLocation(120, 620);
                jogador2.setLocation(1060, 620);
                bola.setLocation(660, 680);
                count1++;
            } else if (colisaoComGol(bola, gol2)) {
                clip.start(); // Inicie a reprodução do som
                flagJog = false;
                jogador1.setLocation(120, 620);
                jogador2.setLocation(1060, 620);
                bola.setLocation(660, 680);
                count2++;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            int duracao = 5000; 

        Timer timer = new Timer(duracao, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagJog = true;
                placar();
            }
        });
        timer.setRepeats(false); 
        timer.start();
       }
        
       void placar(){
           ConectaMongo con = new ConectaMongo();
           ImageIcon zero = new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\num0.png");
           ImageIcon um = new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\num1.png");
           ImageIcon dois = new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\num2.png");
           ImageIcon tres = new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\num3.png");
           if(count1 == 0 && count2 == 0){
               num1placar.setIcon(zero);
               num2placar.setIcon(zero);
           }
           if(count1 == 1){
                num2placar.setIcon(um);
           }else if(count1 == 2){
               num2placar.setIcon(dois);
           }else if(count1 == 3){
               num2placar.setIcon(tres);
               resultado.setResultadoImage(new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\Player2wins.png"));
               resultado.setVisible(true);
               //con.updateField(gameState.getUser1(), "vitorias", con.getVitorias(gameState.getUser1()+1));
               con.incrementVitorias(gameState.getUser1());
               con.incrementDerrotas(gameState.getUser2());
               dispose();
           }
           
           if(count2 == 1){
               num1placar.setIcon(um);
           }else if(count2 == 2){
               num1placar.setIcon(dois);
           }else if(count2 == 3){
               num1placar.setIcon(tres);
               resultado.setResultadoImage(new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\Player1wins.png"));
               resultado.setVisible(true);
               con.incrementVitorias(gameState.getUser1());
               con.incrementDerrotas(gameState.getUser2());
               dispose();
           }
       }

    boolean colisaoComBola(JLabel jogador, JLabel bola) {
        return jogador.getBounds().intersects(bola.getBounds());
    }
    boolean colisaoComJogador(JLabel jogador1, JLabel jogador2){
        return jogador1.getBounds().intersects(jogador2.getBounds());
    }
    boolean colisaoComGol(JLabel bola, JLabel gol){
        return bola.getBounds().intersects(gol.getBounds());
    }
    

    private void atualizarImagem() {
        GameState gameState = GameState.getInstance();
        if (!gameState.getPersonagensEscolhidos().isEmpty()) {
            Personagem personagem = gameState.getPersonagensEscolhidos().get(0);
            Personagem personagem2 = gameState.getPersonagensEscolhidos().get(1);
            if (personagem != null && personagem2 != null) {
                ImageIcon chutep1 = personagem.getImagem2();
                ImageIcon chutep2 = personagem2.getImagem2();
                if (jogchutando1 && chutep1 != null) {
                    jogador1.setIcon(chutep1);
                } else {
                    jogador1.setIcon(personagem.getImagem());
                }
                if(jogchutando2 && chutep2 !=null){
                    jogador2.setIcon(chutep2);
                }else{
                    jogador2.setIcon(personagem2.getImagem());
                }
            }
        }
    }
    private void encerrarJogo(){
                 resultado.setResultadoImage(new ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\Empate.png"));
                 resultado.setVisible(true);  
                 dispose();
       }

        private void iniciarLoopDoJogo() {
        while (jogoEmExecucao) {
            jogdireita1 = false;
            jogesquerda1 = false;
            jogdireita2 = false;
            jogesquerda2 = false;
            jogchutando1 = false;
            jogchutando2 = false;
            
             processarTeclasJogador1();
             processarTeclasJogador2();
            
            verificarColisoes();
            

            try {
                Thread.sleep(16); //atualiza o jogo aproximadamente a 60 quadros por segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        num2placar = new javax.swing.JLabel();
        num1placar = new javax.swing.JLabel();
        placar = new javax.swing.JLabel();
        gol2 = new javax.swing.JLabel();
        gol1 = new javax.swing.JLabel();
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
        getContentPane().add(num2placar);
        num2placar.setBounds(190, 90, 80, 70);
        getContentPane().add(num1placar);
        num1placar.setBounds(190, 0, 80, 70);

        placar.setIcon(new javax.swing.ImageIcon("C:\\Users\\bruno\\OneDrive\\Documentos\\NetBeansProjects\\HeadFootball\\src\\main\\java\\res\\placar.PNG")); // NOI18N
        getContentPane().add(placar);
        placar.setBounds(0, -10, 276, 250);
        getContentPane().add(gol2);
        gol2.setBounds(1330, 550, 70, 240);
        getContentPane().add(gol1);
        gol1.setBounds(0, 550, 70, 240);

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
    private javax.swing.JLabel gol1;
    private javax.swing.JLabel gol2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jogador1;
    private javax.swing.JLabel jogador2;
    private javax.swing.JLabel num1placar;
    private javax.swing.JLabel num2placar;
    private javax.swing.JLabel placar;
    private javax.swing.JLabel time1;
    private javax.swing.JLabel time2;
    // End of variables declaration//GEN-END:variables
}
