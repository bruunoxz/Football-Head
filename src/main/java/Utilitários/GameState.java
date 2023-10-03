package Utilitários;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GameState{
    private static GameState instance = null;
    private ArrayList<Personagem> personagensEscolhidos = new ArrayList<>();
    private String user1;
    private String user2;

    GameState() {
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void adicionarPersonagem(Personagem personagem) {
        personagensEscolhidos.add(personagem);
    }
    public void removerPersonagemEscolhido(Personagem personagem) {
        personagensEscolhidos.remove(personagem);
    }
    public boolean personagemFoiEscolhido(Personagem personagem) {
        return personagensEscolhidos.contains(personagem);
    }
    
    public ArrayList<Personagem> getPersonagensEscolhidos() {
        return personagensEscolhidos;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }
    
}
