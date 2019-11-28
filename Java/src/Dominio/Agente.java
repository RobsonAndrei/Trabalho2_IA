package Dominio;

import sun.tools.jconsole.Tab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Agente {
    private int coord_X;
    private int coord_Y;
    private int totalMoedas;
    private int pontos;
//=> Importante o agente ter mapeado os seus movimentos no tabuleiro
//=> para cima, baixo, esquerda e direita.

    public Agente() {
        this.coord_X = 0;
        this.coord_Y = 0;
        this.totalMoedas = 0;
        this.pontos = 0;
    }

    public String[] percepcao() throws IOException {
        /***
         *  vet[0] = cima
         *  vet[1] = direita
         *  vet[2] = esquerda
         *  vet[3] = baixo
         */

        Tabuleiro tab = new Tabuleiro();
        String[][] matriz = tab.geraMatriz();
        String[] vet = new String[4];
        int ultimaPosicao = matriz.length - 1;

        coord_X = tab.getAgent_X();
        coord_Y = tab.getAgent_Y();

        if (coord_X == 0 && coord_Y == 0) {
            vet[0] = null;
            vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
            vet[2] = null;
            vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
            return vet;
        }
        if (coord_X == ultimaPosicao && coord_Y == ultimaPosicao) {
            vet[0] = tab.percebCoord(coord_X - 1, coord_Y);
            vet[1] = null;
            vet[2] = tab.percebCoord(coord_X, coord_Y - 1);
            vet[3] = null;
            return vet;
        }
        if (coord_X == ultimaPosicao && coord_Y == 0) {
            vet[0] = tab.percebCoord(coord_X - 1, coord_Y);
            vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
            ;
            vet[2] = null;
            vet[3] = null;
            return vet;
        }
        if (coord_X == 0 && coord_Y == ultimaPosicao) {
            vet[0] = null;
            vet[1] = null;
            vet[2] = tab.percebCoord(coord_X, coord_Y - 1);
            vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
            return vet;
        }
        if (coord_X >= 1 && coord_X <= ultimaPosicao - 1 && coord_Y == 0) {
            vet[0] = tab.percebCoord(coord_X - 1, coord_Y);
            vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
            vet[2] = null;
            vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
            return vet;
        }
        if (coord_X == 0 && coord_Y >= 1 && coord_Y <= ultimaPosicao - 1) {
            vet[0] = null;
            vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
            vet[2] = tab.percebCoord(coord_X, coord_Y - 1);
            vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
            return vet;
        }
        if (coord_X == ultimaPosicao && coord_Y >= 1 && coord_Y <= ultimaPosicao - 1) {
            vet[0] = tab.percebCoord(coord_X - 1, coord_Y);
            vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
            vet[2] = tab.percebCoord(coord_X, coord_Y - 1);
            vet[3] = null;
            return vet;
        }
        if (coord_X >= 1 && coord_X <= ultimaPosicao - 1 && coord_Y == ultimaPosicao) {
            vet[0] = tab.percebCoord(coord_X - 1, coord_Y);
            vet[1] = null;
            vet[2] = tab.percebCoord(coord_X, coord_Y - 1);
            vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
            return vet;
        }


        vet[0] = tab.percebCoord(coord_X - 1, coord_Y);
        vet[1] = tab.percebCoord(coord_X, coord_Y + 1);
        vet[2] = tab.percebCoord(coord_X, coord_Y - 1);
        vet[3] = tab.percebCoord(coord_X + 1, coord_Y);
        return vet;
    }

    //      === Método conversor de vetor de String gerado pela matriz      ===
    //      === para um vetor de interios que ira ser usado na rede neural  ===

    /***
     *
     * @param vet
     * @return
     *      === Codificação para a redeNural
     *      *** 0 => 0      ==> posição vazia
     *      *** 1 => 1      ==> parede
     *      *** M => 2      ==> moeda
     *      *** S => 3      ==> saída
     *      *** null => 4   ==> posição nula
     */
    public int[] convertVetor(String[] vet) {
        int[] vetorInt = new int[vet.length];

        for (int i = 0; i < 4; i++) {
            if ("0".equals(vet[i])) vetorInt[i] = 0;
            else {
                if ("1".equals(vet[i])) {
                    vetorInt[i] = 1;

                } else {
                    if ("M".equals(vet[i])) {
                        vetorInt[i] = 2;

                    } else {
                        if ("S".equals(vet[i])) {
                            vetorInt[i] = 3;

                        } else {
                            if (vet[i] == null) {
                                vetorInt[i] = 4;
                            }
                        }
                    }
                }
            }
        }


        return vetorInt;
    }

    public void AlgorGenetico() throws IOException {

        /***
         *      ======================
         *      Pseudocódigo:
         *      ======================
         *      Seja S(t) população de cromossomos na geração (t)
         *      t <== 0
         *      Inicializar S(t)
         *      Avaliar S(t)
         *          Enquanto o critério de parada não for satisfeito FAÇA
         *          t<== t+1
         *          Selecionar S(t) apartir de S(t-1)
         *          Aplicar Crossover sobre S(t)
         *          Aplicar Motação sobre S(t)
         *          Avaliar S(t)
         *          FIM Enquanto
         */
        RedeNeural net = new RedeNeural();
        double[] vetPopulacaoInicial = new double[40];
        List<double[]> vetPop = new ArrayList<>();
        int pontos = 0;
        int moedas = 0;
        int geracao = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < vetPopulacaoInicial.length; j++) {
                vetPopulacaoInicial[j] = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            }
            vetPop.add(vetPopulacaoInicial);
        }


//        for (int i = 0; i < vetPopulacaoInicial.length; i++) {
//            System.out.printf("%.02f", vetPopulacaoInicial[i]);
//            System.out.println();
//        }

        net.ajustePesos(vetPopulacaoInicial);
        String[] vetPerc = percepcao();
        int[] strToint = convertVetor(vetPerc);
        double[] vetSaida = net.getSaida(strToint[0], strToint[1], strToint[2], strToint[3]);
        System.out.println("========= Vetor de Saida da rede =========");
        for (int i = 0; i < vetSaida.length; i++) {
            System.out.println(vetSaida[i]);
        }

        int maioPosiVetorSaida = getMaioPosiVetorSaida(vetSaida);


        System.out.println("============= Indicação da rede ==========");
        if (maioPosiVetorSaida == 0) {
            System.out.println("Indicação da rede: CIMA");
        }
        if (maioPosiVetorSaida == 1) {
            System.out.println("Indicação da rede: DIREITA");
        }
        if (maioPosiVetorSaida == 2) {
            System.out.println("Indicação da rede: ESQUERDA");
        }
        if (maioPosiVetorSaida == 3) {
            System.out.println("Indicação da rede: BAIXO");
        }
        System.out.println("==========================================");


    }

    public int getMaioPosiVetorSaida(double[] vetSaida) {
        double maior = 0.0;
        int maiorint = -1;
        for (int i = 0; i < vetSaida.length; i++) {
            if (vetSaida[i] > maior) {
                maior = vetSaida[i];
                maiorint = i--;
            }
        }
        return maiorint;
    }


    public static void main(String[] args) throws IOException {
        Agente ag = new Agente();
        String[] vetor = ag.percepcao();
        System.out.println("========= Percepção do tabuleiro =========");
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i]);
        }

        int[] vetint = ag.convertVetor(vetor);
        System.out.println("===== Codificação entrada da rede: X =====");
        for (int i = 0; i < vetint.length; i++) {
            System.out.println(vetint[i]);
        }

        ag.AlgorGenetico();

    }


}
