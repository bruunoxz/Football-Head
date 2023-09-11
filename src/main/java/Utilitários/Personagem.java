package Utilitários;

import javax.swing.ImageIcon;


public class Personagem {
    private String nome;
    private boolean escolhido;
    private ImageIcon imagem;

    public Personagem(String nome, ImageIcon imagem) {
        this.nome = nome;
        this.escolhido = false;
        GameState gameState = GameState.getInstance();
        if(gameState.getPersonagensEscolhidos().size() >= 1){
            this.imagem = imagem;
        }else{
            this.imagem = espelharHorizontalmente(imagem);
    }
    }
    public String getNome() {
        return nome;
    }

    public boolean isEscolhido() {
        return escolhido;
    }

    public void setEscolhido(boolean escolhido) {
        this.escolhido = escolhido;
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }
    
    
    public ImageIcon espelharHorizontalmente(ImageIcon original) {
        int largura = original.getIconWidth();
        int altura = original.getIconHeight();
        java.awt.Image imagemOriginal = original.getImage();
        java.awt.Image imagemEspelhada = new java.awt.image.BufferedImage(largura, altura, java.awt.image.BufferedImage.TYPE_INT_ARGB);

        java.awt.Graphics2D g2d = (java.awt.Graphics2D) imagemEspelhada.getGraphics();
        g2d.drawImage(imagemOriginal, largura, 0, -largura, altura, null);
        g2d.dispose();

        return new ImageIcon(imagemEspelhada);
    }
}
