package Dominio;

import sun.tools.jconsole.Tab;

public class Agente {
    private int coord_X;
    private int coord_Y;
//=> Importante o agente ter mapeado os seus movimentos no tabuleiro
//=> para cima, baixo, esquerda e direita.

    public Agente() {

    }

    public String[] percepcao(Tabuleiro tab) {
        /***
         *  vet[0] = cima
         *  vet[1] = direita
         *  vet[2] = esquerda
         *  vet[3] = baixo
         */

        int coordx = 0;
        int coordy = 0;
        String[] vet = new String[4];
        coordy = tab.getAgent_Y();
        coordx = tab.getAgent_X();

        if (coordx == 0 && coordy == 0) {
            vet[0] = null;
            vet[1] = tab.percebCoord(coordx, coordy + 1);
            vet[2] = null;
            vet[3] = tab.percebCoord(coordx + 1, coordy);
            return vet;
        }
        if (coordx >= 1 && coordx <= tab.getTamanho_matriz() - 2) {

        }


        return vet;
    }


}
