package Dominio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Tabuleiro {
    private String nome_Arquivo;
    private String matriz_Tabeuleiro[][];
    private int tamanho_matriz;


    public Tabuleiro() {
        this.nome_Arquivo = "labirinto1_10T2.txt";
    }

    public String[][] lerArqRetMatriz() throws FileNotFoundException {
        String[][] aux = null;
        int tam = 0;
        Scanner in = new Scanner(new FileReader(nome_Arquivo));
        tam = in.nextInt();
        aux = new String[tam][tam];
        in.close();
        return aux;
    }

    public String[][] geraMatriz() throws IOException {
        String[][] mat = this.lerArqRetMatriz();
        String[] vetorString = new String[mat.length];

        try {
            Scanner in = new Scanner(new FileReader(nome_Arquivo));
            in.nextLine();

            int i;
            while (in.hasNextLine()) {
                for (i = 0; i < vetorString.length; ++i) {
                    String line = in.nextLine().replace(" ", "");
                    vetorString[i] = line;
                }
            }

            for (i = 0; i < mat.length; ++i) {
                for (int j = 0; j < mat.length; ++j) {
                    char ch = vetorString[i].charAt(j);
                    mat[i][j] = String.valueOf(ch);
                }
            }

            in.close();
        } catch (IOException var7) {
            System.out.println(var7.getMessage());
        }
        this.matriz_Tabeuleiro = mat;
        return mat;
    }

    public void printaMatriz() {
        for (int i = 0; i < this.matriz_Tabeuleiro.length; ++i) {
            for (int j = 0; j < this.matriz_Tabeuleiro.length; ++j) {
                System.out.print(this.matriz_Tabeuleiro[i][j] + " ");
            }

            System.out.println();
        }

    }


    public static void main(String[] args) throws IOException {
        System.out.println("Teste ok!!");
        Tabuleiro tab = new Tabuleiro();
        tab.lerArqRetMatriz();
        String[][] matrix = tab.geraMatriz();
        tab.printaMatriz();


    }
}
