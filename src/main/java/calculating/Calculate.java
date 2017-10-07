package calculating;

import model.ItemOfAnalyticalRepresentationOfFunction;
import model.ItemOfIntegralRepresentationOfFunction;
import model.ItemOfRecursionRepresentationOfFunction;
import model.Type;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.*;
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Irina Kazantseva on 28.09.2017.
 */
@Component
public class Calculate {

    public Calculate(){}

    public double calculateSumOfAnalyticalRepresentationOfFunctionParallel(int n, double y, double t){
        List<ItemOfAnalyticalRepresentationOfFunction> items = new ArrayList<ItemOfAnalyticalRepresentationOfFunction>();
        for(int i=0; i<=n; i++){
            items.add(new ItemOfAnalyticalRepresentationOfFunction(n, i, t, y));
        }
        DoubleSummaryStatistics c =items.parallelStream().peek((i)-> i.calculateCurrentItem()).collect(Collectors.summarizingDouble((i)-> ((ItemOfAnalyticalRepresentationOfFunction)i).getCalc()));
        return c.getSum();
    }

    public double calculateSumOfAnalyticalRepresentationOfFunction(int n, double y, double t){
        List<ItemOfAnalyticalRepresentationOfFunction> items = new ArrayList<ItemOfAnalyticalRepresentationOfFunction>();
        for(int i=0; i<=n; i++){
            items.add(new ItemOfAnalyticalRepresentationOfFunction(n, i, t, y));
        }
        DoubleSummaryStatistics c =items.stream().peek((i)-> i.calculateCurrentItem()).collect(Collectors.summarizingDouble((i)-> ((ItemOfAnalyticalRepresentationOfFunction)i).getCalc()));
        return c.getSum();
    }

    public double calculateSumOfIntegralRepresentationOfFunction(int n, int k, double y, double t){
        List<ItemOfIntegralRepresentationOfFunction> items = new ArrayList<ItemOfIntegralRepresentationOfFunction>();
        items.add(new ItemOfIntegralRepresentationOfFunction(k, 0, t, y, Type.FIRST));
        items.add(new ItemOfIntegralRepresentationOfFunction(k, Math.PI/2, t, y,Type.LAST));
        for(int i=1; i<n; i++){
            items.add(new ItemOfIntegralRepresentationOfFunction(k, i*(Math.PI/(2*n)), t, y,Type.MIDDLE ));
        }
        DoubleSummaryStatistics c = items.stream().peek(i-> i.calculateCurrentItem()).collect(Collectors.summarizingDouble((i)-> ((ItemOfIntegralRepresentationOfFunction)i).getCalc()));
        return c.getSum() * (Math.PI/(2*n)) *2* (Math.pow(-1, k)/Math.PI) ;

    }

    public double calculateSumOfIntegralRepresentationOfFunctionParallel(int n, int k, double y, double t){
        List<ItemOfIntegralRepresentationOfFunction> items = new ArrayList<ItemOfIntegralRepresentationOfFunction>();
        items.add(new ItemOfIntegralRepresentationOfFunction(k, 0, t, y, Type.FIRST));
        items.add(new ItemOfIntegralRepresentationOfFunction(k, Math.PI/2, t, y,Type.LAST));
        for(int i=1; i<n; i++){
            items.add(new ItemOfIntegralRepresentationOfFunction(k, i*(Math.PI/(2*n)), t, y,Type.MIDDLE ));
        }
        DoubleSummaryStatistics c = items.parallelStream().peek(i-> i.calculateCurrentItem()).collect(Collectors.summarizingDouble((i)-> ((ItemOfIntegralRepresentationOfFunction)i).getCalc()));
        return c.getSum() * (Math.PI/(2*n)) *2* (Math.pow(-1, k)/Math.PI) ;
    }


    public double calculateSumOfRecursionRepresentationOfFunction(int k, double y, double t){

        if(k>1) {
            ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_0 = new ItemOfAnalyticalRepresentationOfFunction(0, 0, t, y);
            itemOfAnalyticalRepresentationOfFunction_0.calculateCurrentItem();
            ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_1_0 = new ItemOfAnalyticalRepresentationOfFunction(1, 0, t, y);
            itemOfAnalyticalRepresentationOfFunction_1_0.calculateCurrentItem();
            ItemOfAnalyticalRepresentationOfFunction itemOfAnalyticalRepresentationOfFunction_1_1 = new ItemOfAnalyticalRepresentationOfFunction(1, 1, t, y);
            itemOfAnalyticalRepresentationOfFunction_1_1.calculateCurrentItem();
            Double item_1 = itemOfAnalyticalRepresentationOfFunction_1_0.getCalc() + itemOfAnalyticalRepresentationOfFunction_1_1.getCalc();
            itemOfAnalyticalRepresentationOfFunction_1_1.setCalc(item_1);
            Double item_0 = itemOfAnalyticalRepresentationOfFunction_0.getCalc();

            List<ItemOfRecursionRepresentationOfFunction> list = new ArrayList<>();
            list.add(new ItemOfRecursionRepresentationOfFunction(itemOfAnalyticalRepresentationOfFunction_1_1, itemOfAnalyticalRepresentationOfFunction_0,
                    null, null, t, y, k, 0));
            list.add(new ItemOfRecursionRepresentationOfFunction(null, itemOfAnalyticalRepresentationOfFunction_1_1,
                    list.get(0), null, t, y, k, 1));

            for (int i = 2; i < k; i++) {
                ItemOfRecursionRepresentationOfFunction itemOfRecursionRepresentationFunction = new ItemOfRecursionRepresentationOfFunction(null, null,
                         list.get(i - 1),list.get(i - 2), t, y, k, i);
                list.add(itemOfRecursionRepresentationFunction);
            }
            list = list.stream().peek((i) -> i.calc()).collect(Collectors.toList());
            return list.get(list.size()-1).getCalc();
        }else{
            return 0.0;
        }
    }
}