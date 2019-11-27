package Dominio;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RedeNeural {
    private Neuronio1cam n1;
    private Neuronio1cam n2;
    private Neuronio1cam n3;
    private Neuronio1cam n4;

    private Neuronio2cam n5;
    private Neuronio2cam n6;
    private Neuronio2cam n7;
    private Neuronio2cam n8;

    public RedeNeural() {
        this.n1 = new Neuronio1cam();
        this.n2 = new Neuronio1cam();
        this.n3 = new Neuronio1cam();
        this.n4 = new Neuronio1cam();

        this.n5 = new Neuronio2cam();
        this.n6 = new Neuronio2cam();
        this.n7 = new Neuronio2cam();
        this.n8 = new Neuronio2cam();
    }

    public double[] getSaida(int x1, int x2, int x3, int x4) {
        double[] vetorSaida = new double[4];
        vetorSaida[0] = n5.calculaY(n1.calculaY(x1, x2, x3, x4),
                n2.calculaY(x1, x2, x3, x4),
                n3.calculaY(x1, x2, x3, x4),
                n4.calculaY(x1, x2, x3, x4));
        vetorSaida[1] = n6.calculaY(n1.calculaY(x1, x2, x3, x4),
                n2.calculaY(x1, x2, x3, x4),
                n3.calculaY(x1, x2, x3, x4),
                n4.calculaY(x1, x2, x3, x4));
        vetorSaida[2] = n7.calculaY(n1.calculaY(x1, x2, x3, x4),
                n2.calculaY(x1, x2, x3, x4),
                n3.calculaY(x1, x2, x3, x4),
                n4.calculaY(x1, x2, x3, x4));
        vetorSaida[3] = n8.calculaY(n1.calculaY(x1, x2, x3, x4),
                n2.calculaY(x1, x2, x3, x4),
                n3.calculaY(x1, x2, x3, x4),
                n4.calculaY(x1, x2, x3, x4));

        return vetorSaida;
    }

    public void ajustePesos(double[] vetorPesos) {

        for (int i = 0; i < vetorPesos.length; i++) {
            switch (i) {
                case 0://===================
                    n1.setW0(vetorPesos[i]);
                case 1:
                    n1.setW1(vetorPesos[i]);
                case 2:
                    n1.setW2(vetorPesos[i]);
                case 3:
                    n1.setW3(vetorPesos[i]);
                case 4:
                    n1.setW4(vetorPesos[i]);
                case 5://===================
                    n2.setW0(vetorPesos[i]);
                case 6:
                    n2.setW1(vetorPesos[i]);
                case 7:
                    n2.setW2(vetorPesos[i]);
                case 8:
                    n2.setW3(vetorPesos[i]);
                case 9:
                    n2.setW4(vetorPesos[i]);
                case 10://===================
                    n3.setW0(vetorPesos[i]);
                case 11:
                    n3.setW1(vetorPesos[i]);
                case 12:
                    n3.setW2(vetorPesos[i]);
                case 13:
                    n3.setW3(vetorPesos[i]);
                case 14:
                    n3.setW4(vetorPesos[i]);
                case 15://===================
                    n4.setW0(vetorPesos[i]);
                case 16:
                    n4.setW1(vetorPesos[i]);
                case 17:
                    n4.setW2(vetorPesos[i]);
                case 18:
                    n4.setW3(vetorPesos[i]);
                case 19:
                    n4.setW4(vetorPesos[i]);
                case 20://===================
                    n5.setW0(vetorPesos[i]);
                case 21:
                    n5.setW1(vetorPesos[i]);
                case 22:
                    n5.setW2(vetorPesos[i]);
                case 23:
                    n5.setW3(vetorPesos[i]);
                case 24:
                    n5.setW4(vetorPesos[i]);
                case 25://===================
                    n6.setW0(vetorPesos[i]);
                case 26:
                    n6.setW1(vetorPesos[i]);
                case 27:
                    n6.setW2(vetorPesos[i]);
                case 28:
                    n6.setW3(vetorPesos[i]);
                case 29:
                    n6.setW4(vetorPesos[i]);
                case 30://===================
                    n7.setW0(vetorPesos[i]);
                case 31:
                    n7.setW1(vetorPesos[i]);
                case 32:
                    n7.setW2(vetorPesos[i]);
                case 33:
                    n7.setW3(vetorPesos[i]);
                case 34:
                    n7.setW4(vetorPesos[i]);
                case 35://===================
                    n8.setW0(vetorPesos[i]);
                case 36:
                    n8.setW1(vetorPesos[i]);
                case 37:
                    n8.setW2(vetorPesos[i]);
                case 38:
                    n8.setW3(vetorPesos[i]);
                case 39:
                    n8.setW4(vetorPesos[i]);
            }
        }
    }

    public static void main(String[] args) {
        RedeNeural net = new RedeNeural();
        double[] vetorPesos = new double[40];
//        Vetor que gera números aleatórios de -1.0 à 1.0 que servirão para o Algoritimo Genêtico

        for (int i = 0; i < vetorPesos.length; i++) {
            vetorPesos[i] = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        }

        net.ajustePesos(vetorPesos);
        double[] vetExit = net.getSaida(0, 1, 1, 3);
        for (int i = 0; i < vetExit.length; i++) {
            System.out.printf("%.03f", vetExit[i]);
            System.out.println();
        }

    }

}
