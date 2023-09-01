package Utilitários;
public class Personagem {
    private String nome;
    private boolean escolhido;

    public Personagem(String nome) {
        this.nome = nome;
        this.escolhido = false;
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
}
