package Dominio;

public class Neuronio1cam {
    //Neuronio para 2 entradas

    private double w0;  // 5 pesos
    private double w1;
    private double w2;
    private double w3;
    private double w4;

    public Neuronio1cam(){

    }

    public double getW0() {
        return w0;
    }

    public void setW0(double w0) {
        this.w0 = w0;
    }

    public double getW1() {
        return w1;
    }

    public void setW1(double w1) {
        this.w1 = w1;
    }

    public double getW2() {
        return w2;
    }

    public void setW2(double w2) {
        this.w2 = w2;
    }

    public double getW3() {
        return w3;
    }

    public void setW3(double w3) {
        this.w3 = w3;
    }

    public double getW4() {
        return w4;
    }

    public void setW4(double w4) {
        this.w4 = w4;
    }

    //    4 valores de entrada em x1,x2,x3,x4
    public double calculaV(int x1, int x2, int x3, int x4) {
        return w0 + (w1 * x1) + (w2 * x2) + (w3 * x3) + (w4 * x4);
    } //calcula o campo local induzido

    // Mudar esta função
    public double calculaY(int x1, int x2, int x3, int x4) { //aplica a função
        double v = calculaV(x1, x2, x3, x4);

//        if (v >= 0) return 1;
//        return 0;
//        return 1 / 1+exp(-v)
        return (1 / (1 + Math.exp(-v)));
    }


    public String toString() {
        return "w0= " + w0 + " w1= " + w1 + " w2= " + w2 + " w3= " + w3 + " w4= " + w4;
    }


}

