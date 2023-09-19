package Utilitários;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
    
    private boolean[] keys = new boolean[256]; //Teclas do teclado
    public static boolean w, a, d, q, left, right, up, p;
    
    public void update(){
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        d = keys[KeyEvent.VK_D];
        q = keys[KeyEvent.VK_Q];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        up = keys[KeyEvent.VK_UP];
        p = keys[KeyEvent.VK_P];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyCode() < 0 || k.getKeyCode() > 255)return;
        keys[k.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent k) {
        if(k.getKeyCode() < 0 || k.getKeyCode() > 255)return;
        keys[k.getKeyCode()] = false;
    }
    
}
