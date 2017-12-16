package web.model;

/**
 * Created by Irina Kazantseva on 07.10.2017.
 */
public class StressRequestDto {
    private int nMin;
    private int nMax;
    private int nStep;
    private double yMin;
    private double yMax;
    private double yStep;
    private double tMin;
    private double tMax;
    private double tStep;
    private int kMin;
    private int kMax;
    private int kStep;
    private boolean isParallel;
    public StressRequestDto(){}
    public StressRequestDto(int nMin, int nMax, int nStep, double yMin, double yMax, double yStep, double tMin, double tMax, double tStep, int kMin, int kMax, int kStep, boolean isParallel) {
        this.nMin = nMin;
        this.nMax = nMax;
        this.nStep = nStep;
        this.yMin = yMin;
        this.yMax = yMax;
        this.yStep = yStep;
        this.tMin = tMin;
        this.tMax = tMax;
        this.tStep = tStep;
        this.kMin = kMin;
        this.kMax = kMax;
        this.kStep = kStep;
        this.isParallel = isParallel;
    }

    public int getnMin() {
        return nMin;
    }

    public void setnMin(int nMin) {
        this.nMin = nMin;
    }

    public int getnMax() {
        return nMax;
    }

    public void setnMax(int nMax) {
        this.nMax = nMax;
    }

    public int getnStep() {
        return nStep;
    }

    public void setnStep(int nStep) {
        this.nStep = nStep;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    public double getyStep() {
        return yStep;
    }

    public void setyStep(double yStep) {
        this.yStep = yStep;
    }

    public double gettMin() {
        return tMin;
    }

    public void settMin(double tMin) {
        this.tMin = tMin;
    }

    public double gettMax() {
        return tMax;
    }

    public void settMax(double tMax) {
        this.tMax = tMax;
    }

    public double gettStep() {
        return tStep;
    }

    public void settStep(double tStep) {
        this.tStep = tStep;
    }

    public int getkMin() {
        return kMin;
    }

    public void setkMin(int kMin) {
        this.kMin = kMin;
    }

    public int getkMax() {
        return kMax;
    }

    public void setkMax(int kMax) {
        this.kMax = kMax;
    }

    public int getkStep() {
        return kStep;
    }

    public void setkStep(int kStep) {
        this.kStep = kStep;
    }

    public boolean isParallel() {
        return isParallel;
    }

    public void setParallel(boolean parallel) {
        isParallel = parallel;
    }
}