package model;

/**
 * Created by Irina Kazantseva on 07.10.2017.
 */
public class StressResponseDto {
    private double t;
    private double y;
    private int n;
    private int k;
    private long time;

    public StressResponseDto(double t, double y, int n, int k, long time) {
        this.t = t;
        this.y = y;
        this.n = n;
        this.k = k;
        this.time = time;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}