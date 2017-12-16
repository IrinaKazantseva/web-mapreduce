package web.model;

/**
 * Created by Irina Kazantseva on 07.10.2017.
 */
public class RequestDto {
    private int n;
    private int k;
    private double y;
    private double t;
    private boolean parallel;
    public RequestDto(){}
    public RequestDto(int n, int k, double y, double t, boolean isParallel) {
        this.n = n;
        this.k = k;
        this.y = y;
        this.t = t;
        this.parallel = isParallel;
    }

    public boolean isParallel() {
        return parallel;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }
}
