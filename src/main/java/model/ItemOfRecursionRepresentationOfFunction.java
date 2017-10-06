package model;

import configuration.WebMapReduceApplication;
import org.apache.log4j.Logger;

/**
 * Created by Irina Kazantseva on 04.10.2017.
 */
public class ItemOfRecursionRepresentationOfFunction {

    private static final Logger log = org.apache.log4j.Logger.getLogger(ItemOfRecursionRepresentationOfFunction.class);

    private ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_1;
    private ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_2;

    private ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_1;
    private ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_2;

    private int number;

    private double t;
    private double y;
    private int k;

    private double calc;

    public ItemOfRecursionRepresentationOfFunction(ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_1, ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_2, ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_1, ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_2, double t, double y, int k) {
        this.itemOfAnalyticalRepresentationOfFunction_k_1 = itemOfAnalyticalRepresentationOfFunction_k_1;
        this.itemOfAnalyticalRepresentationOfFunction_k_2 = itemOfAnalyticalRepresentationOfFunction_k_2;
        this.itemOfRecursionRepresentationOfFunction_k_1 = itemOfRecursionRepresentationOfFunction_k_1;
        this.itemOfRecursionRepresentationOfFunction_k_2 = itemOfRecursionRepresentationOfFunction_k_2;
        this.t = t;
        this.y = y;
        this.k = k;
    }

    public ItemOfRecursionRepresentationOfFunction(ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_1, ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_2, ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_1, ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_2,  double t, double y, int k,int number) {
        this.itemOfAnalyticalRepresentationOfFunction_k_1 = itemOfAnalyticalRepresentationOfFunction_k_1;
        this.itemOfAnalyticalRepresentationOfFunction_k_2 = itemOfAnalyticalRepresentationOfFunction_k_2;
        this.itemOfRecursionRepresentationOfFunction_k_1 = itemOfRecursionRepresentationOfFunction_k_1;
        this.itemOfRecursionRepresentationOfFunction_k_2 = itemOfRecursionRepresentationOfFunction_k_2;
        this.number = number;
        this.t = t;
        this.y = y;
        this.k = k;
    }

    public int getNumber() {
        return number;
    }

    public ItemOfRecursionRepresentationOfFunction getItemOfRecursionRepresentationOfFunction_k_1() {
        return itemOfRecursionRepresentationOfFunction_k_1;
    }

    public void setItemOfRecursionRepresentationFunction_k_1(ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_1) {
        this.itemOfRecursionRepresentationOfFunction_k_1 = itemOfRecursionRepresentationOfFunction_k_1;
    }

    public ItemOfRecursionRepresentationOfFunction getItemOfRecursionRepresentationOfFunction_k_2() {
        return itemOfRecursionRepresentationOfFunction_k_2;
    }

    public void setItemOfRecursionRepresentationOfFunction_k_2(ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationOfFunction_k_2) {
        this.itemOfRecursionRepresentationOfFunction_k_2 = itemOfRecursionRepresentationOfFunction_k_2;
    }

    public ItemOfAnalyticalRepresentationOfFunction getItemOfAnalyticalRepresentationOfFunction_k_1() {
        return itemOfAnalyticalRepresentationOfFunction_k_1;
    }

    public void setItemOfAnalyticalRepresentationOfFunction_k_1(ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_1) {
        this.itemOfAnalyticalRepresentationOfFunction_k_1 = itemOfAnalyticalRepresentationOfFunction_k_1;
    }

    public ItemOfAnalyticalRepresentationOfFunction getItemOfAnalyticalRepresentationOfFunction_k_2() {
        return itemOfAnalyticalRepresentationOfFunction_k_2;
    }

    public void setItemOfAnalyticalRepresentationOfFunction_k_2(ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_k_2) {
        this.itemOfAnalyticalRepresentationOfFunction_k_2 = itemOfAnalyticalRepresentationOfFunction_k_2;
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

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public double getCalc() {
        return calc;
    }

    public void setCalc(double calc) {
        this.calc = calc;
    }

    public void calc(){
        log.debug("Calculating item with number - "+number);
        if(itemOfRecursionRepresentationOfFunction_k_1== null && itemOfRecursionRepresentationOfFunction_k_2== null &&
                itemOfAnalyticalRepresentationOfFunction_k_1 != null && itemOfAnalyticalRepresentationOfFunction_k_2 !=null){
            calc = ((2*k-1-y*t)/k)* itemOfAnalyticalRepresentationOfFunction_k_1.getCalc() - ((k-1)/k)* itemOfAnalyticalRepresentationOfFunction_k_2.getCalc();
        }else{
            if(itemOfRecursionRepresentationOfFunction_k_2== null &&
                    itemOfRecursionRepresentationOfFunction_k_1!= null && itemOfAnalyticalRepresentationOfFunction_k_2 != null &&
                    itemOfAnalyticalRepresentationOfFunction_k_1 == null){
                calc = ((2*k-1-y*t)/k)* itemOfRecursionRepresentationOfFunction_k_1.getCalc()  - ((k-1)/k)* itemOfAnalyticalRepresentationOfFunction_k_2.getCalc();
            }else{
                if(itemOfRecursionRepresentationOfFunction_k_1!= null && itemOfRecursionRepresentationOfFunction_k_2!= null &&
                        itemOfAnalyticalRepresentationOfFunction_k_1 == null && itemOfAnalyticalRepresentationOfFunction_k_2 ==null){
                    calc = ((2*k-1-y*t)/k)* itemOfRecursionRepresentationOfFunction_k_1.getCalc() - ((k-1)/k)* itemOfRecursionRepresentationOfFunction_k_2.getCalc();
                }
            }
        }
    }
}