package Utilitários;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class AnimacaoChute {
    private int bolaX;
    private int bolaY;

    public AnimacaoChute(int bolaX, int bolaY) {
        this.bolaX = bolaX;
        this.bolaY = bolaY;
    }

    public ActionListener getActionListener() {
        final int velocidadeSubida = -5;
        final int alturaDesejada = -200;

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualize a posição vertical da bola
                bolaY += velocidadeSubida;
                //bola.setLocation(bolaX, bolaY);

                // Verifique se a bola chegou à altura desejada
                if (bolaY <= alturaDesejada) {
                    // Pare o Timer quando a altura desejada for alcançada
                    ((Timer) e.getSource()).stop();
                }
            }
        };
    }
    
}
