package Utilitários;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GameState{
    private static GameState instance = null;
    private ArrayList<Personagem> personagensEscolhidos = new ArrayList<>();
    

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
}
