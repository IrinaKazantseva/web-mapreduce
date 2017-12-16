package  web.model;

import org.apache.commons.math3.util.CombinatoricsUtils;

/**
 * Created by Irina Kazantseva on 28.09.2017.
 */
public class ItemOfAnalyticalRepresentationOfFunction {
    private int k;
    private int s;
    private double t;
    private double y;
    private double calc;

    public double getCalc(){
        return calc;
    }
    public ItemOfAnalyticalRepresentationOfFunction(int k, int s, double t, double y) {
        this.k = k;
        this.s = s;
        this.t = t;
        this.y = y;
    }

    public void setCalc(double calc) {
        this.calc = calc;
    }

    public void calculateCurrentItem(){
       calc = CombinatoricsUtils.binomialCoefficientDouble(k, s) * (Math.pow(-y*t, s)/CombinatoricsUtils.factorialDouble(s))*Math.exp(-0.5*y*t);
    }
}