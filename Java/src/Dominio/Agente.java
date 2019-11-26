package Dominio;

import sun.tools.jconsole.Tab;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Agente {
    private int coord_X;
    private int coord_Y;
//=> Importante o agente ter mapeado os seus movimentos no tabuleiro
//=> para cima, baixo, esquerda e direita.

    public Agente() {
        this.coord_X = 0;
        this.coord_Y = 0;
    }

    public String[] percepcao() throws IOException {
        /***
         *  vet[0] = cima
         *  vet[1] = direita
         *  vet[2] = esquerda
         *  vet[3] = baixo
         */
        Tabuleiro tab = new Tabuleiro();
        tab.geraMatriz();


        String[] vet = new String[4];

        tab.moverAgente(tab.getTamanho_matriz() - 1, tab.getTamanho_matriz() - 1);
        coord_X = tab.getAgent_X();
        coord_Y = tab.getAgent_Y();

        if (coord_X == 0 && coord_Y == 0) {
            vet[0] = null;
            vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
            vet[2] = null;
            vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
            return vet;
        }
        if (coord_X == tab.getTamanho_matriz() - 1 && coord_Y == tab.getTamanho_matriz() - 1) {
            vet[0] = null;
            vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
            vet[2] = null;
            vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
            return vet;
        }
        return vet;
    }

    public static void main(String[] args) throws IOException {
        Agente ag = new Agente();
        String[] vetor = ag.percepcao();
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i]);
        }

    }


}
