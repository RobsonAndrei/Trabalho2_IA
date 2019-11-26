package Dominio;

public class Neuronio2cam {
    private double w0;  // 5 pesos
    private double w1;
    private double w2;
    private double w3;
    private double w4;

    public Neuronio2cam() {

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

    public double calculaV(double y1, double y2, double y3, double y4) {
        return w0 + (w1 * y1) + (w2 * y2) + (w3 * y3) + (w4 * y4);
    }

    public double calculaY(double y1, double y2, double y3, double y4) {
        double v = calculaV(y1, y2, y3, y4);
        return (1 / (1 + Math.exp(-v)));
    }

    public String toString() {
        return "w0= " + w0 + " w1= " + w1 + " w2= " + w2 + " w3= " + w3 + " w4= " + w4;
    }

}
