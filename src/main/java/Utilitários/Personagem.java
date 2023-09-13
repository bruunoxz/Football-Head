package Utilitários;

import javax.swing.ImageIcon;


public class Personagem {
    private String nome;
    private boolean escolhido;
    private ImageIcon imagem;
    private ImageIcon imagem2;

    public Personagem(String nome, ImageIcon imagem, ImageIcon imagem2) {
        this.nome = nome;
        this.escolhido = false;
        GameState gameState = GameState.getInstance();
        if(gameState.getPersonagensEscolhidos().size() >= 1){
            this.imagem = imagem;
            this.imagem2 = imagem2;
        }else{
            this.imagem = espelharHorizontalmente(imagem);
            this.imagem2 = espelharHorizontalmente(imagem2);
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

    public ImageIcon getImagem2() {
        return imagem2;
    }

    public void setImagem2(ImageIcon imagem2) {
        this.imagem2 = imagem2;
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
