package calculating;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Irina Kazantseva on 06.10.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CalculateTest {

    @Test
    public void testCalculateSumOfAnalyticalRepresentationOfFunctionParallel(){
        double y = 0.3;
        double t = 0.015;
        int k=5;
        double res_1 = Math.exp(-y*t/2)*(120-600*y*t + 600 * y*y * t*t - 200 * Math.pow(y,3)*Math.pow(t,3) +
                25 * Math.pow(y,4) * Math.pow(t, 4) - Math.pow(y,5)*Math.pow(t, 5))/120;

        Calculate calculate = new Calculate();
        double res_2 = calculate.calculateSumOfAnalyticalRepresentationOfFunctionParallel(k, y,t);
        assertTrue("Results are equal", Math.rint(10000.0 * res_1) / 10000.0 == Math.rint(10000.0 * res_2) / 10000.0);
    }

    @Test
    public void testCalculateSumOfAnalyticalRepresentationOfFunction(){
        double y = 0.2;
        double t = 0.915;
        int k=5;
        double res_1 = Math.exp(-y*t/2)*(120-600*y*t + 600 * y*y * t*t - 200 * Math.pow(y,3)*Math.pow(t,3) +
                25 * Math.pow(y,4) * Math.pow(t, 4) - Math.pow(y,5)*Math.pow(t, 5))/120;

        Calculate calculate = new Calculate();
        double res_2 = calculate.calculateSumOfAnalyticalRepresentationOfFunction(k, y,t);
        assertTrue("Results are equal", Math.rint(10000.0 * res_1) / 10000.0 == Math.rint(10000.0 * res_2) / 10000.0);
    }

    @Test
    public void testCalculateSumOfIntegralRepresentationOfFunction(){
        double y = 0.2;
        double t = 0.915;
        int k=5;
        double res_1 = -0.347558*(-2/Math.PI);

        Calculate calculate = new Calculate();
        double res_2 = calculate.calculateSumOfIntegralRepresentationOfFunction(Integer.MAX_VALUE, k, y,t);
        assertTrue("Results are equal", Math.rint(10000.0 * res_1) / 10000.0 == Math.rint(10000.0 * res_2) / 10000.0);
    }




}