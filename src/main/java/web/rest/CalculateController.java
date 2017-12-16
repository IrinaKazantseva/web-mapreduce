package web.rest;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.calculating.Calculate;
import web.model.RequestDto;
import web.model.StressRequestDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irina Kazantseva on 07.10.2017.
 */
@RestController
@RequestMapping("/calculate")
public class CalculateController {

    @Autowired
    private Calculate calculate;

    @GetMapping("/ping")
    public Long stressCalculateAnalyticalFunction() {
        return 1l;
    }

    @RequestMapping(value = "/stress/analytical", method = RequestMethod.POST)
    public Long stressCalculateAnalyticalFunction(@RequestBody StressRequestDto stressRequestDto) {
        List<Long> responseDtos = new ArrayList<>();

        for (double y = stressRequestDto.getyMin(); y < stressRequestDto.getyMax(); y += stressRequestDto.getyStep()) {
            List<Double[]> matrix = new ArrayList<>();
            for (double t = stressRequestDto.gettMin(); t < stressRequestDto.gettMax(); t += stressRequestDto.gettStep()) {
                Double[] vector = new Double[(int) ((stressRequestDto.getnMax() - stressRequestDto.getnMin()) / stressRequestDto.getnStep())];
                int i=0;
                for (int n = stressRequestDto.getnMin(); n < stressRequestDto.getnMax(); n += stressRequestDto.getnStep()) {
                    if (stressRequestDto.isParallel()) {
                        vector[i] = calculate.calculateSumOfAnalyticalRepresentationOfFunctionParallel(n, y, t);
                    } else {
                        vector[i] = calculate.calculateSumOfAnalyticalRepresentationOfFunction(n, y, t);
                    }
                    i++;


                }
                matrix.add(vector);
            }
            double[][] matr = new double[matrix.size()][matrix.get(0).length];
            for (int i = 0; i < matrix.size(); i++) {
                for (int j = 0; j < matrix.get(0).length; j++) {
                    matr[i][j] = matrix.get(i)[j];
                }
            }
            RealMatrix coefficients = new Array2DRowRealMatrix(matr, false);
            long before = System.currentTimeMillis();
            try {
                coefficients.transpose();
                MatrixUtils.inverse(coefficients);
                MatrixUtils.inverse(coefficients.transpose().multiply(coefficients));
            }catch (Exception e){
                e.printStackTrace();
            }
            long after = System.currentTimeMillis();
            responseDtos.add(after - before);
        }
        Long time = responseDtos.stream().mapToLong(Long::longValue).sum();
        return time;
    }

    @RequestMapping(value = "/stress/integral", method = RequestMethod.POST)
    public Long stressCalculateIntegralFunction(@RequestBody StressRequestDto stressRequestDto) {
        List<Long> responseDtos = new ArrayList<>();
        for (double y = stressRequestDto.getyMin(); y < stressRequestDto.getyMax(); y += stressRequestDto.getyStep()) {
            for (double t = stressRequestDto.gettMin(); t < stressRequestDto.gettMax(); t += stressRequestDto.gettStep()) {
                List<Double[]> matrix = new ArrayList<>();
                for (int k = stressRequestDto.getkMin(); k < stressRequestDto.getkMax(); k += stressRequestDto.getkStep()) {
                    Double[] vector = new Double[(int) ((stressRequestDto.getnMax() - stressRequestDto.getnMin()) / stressRequestDto.getnStep())];
                    int i=0;
                    for (int n = stressRequestDto.getnMin(); n < stressRequestDto.getnMax(); n += stressRequestDto.getnStep()) {
                        if (stressRequestDto.isParallel()) {
                            vector[i] = calculate.calculateSumOfIntegralRepresentationOfFunctionParallel(n, k, y, t);
                        } else {
                            vector[i] = calculate.calculateSumOfIntegralRepresentationOfFunction(n, k, y, t);
                        }
                        i++;
                    }
                    matrix.add(vector);
                }

                double[][] matr = new double[matrix.size()][matrix.get(0).length];
                for (int i = 0; i < matrix.size(); i++) {
                    for (int j = 0; j < matrix.get(0).length; j++) {
                        matr[i][j] = matrix.get(i)[j];
                    }
                }
                RealMatrix coefficients = new Array2DRowRealMatrix(matr, false);
                long before = System.currentTimeMillis();
                try {
                    coefficients.transpose();
                    MatrixUtils.inverse(coefficients);
                    MatrixUtils.inverse(coefficients.transpose().multiply(coefficients));
                }catch (Exception e){
                    e.printStackTrace();
                }  long after = System.currentTimeMillis();
                responseDtos.add(after - before);
            }
        }
        Long time = responseDtos.stream().mapToLong(Long::longValue).sum();
        return time;
    }

    @RequestMapping(value = "/stress/recursion", method = RequestMethod.POST)
    public Long stressCalculateRecursionFunction(@RequestBody StressRequestDto stressRequestDto) {
        List<Long> responseDtos = new ArrayList<>();
        for (double y = stressRequestDto.getyMin(); y < stressRequestDto.getyMax(); y += stressRequestDto.getyStep()) {
            List<Double[]> matrix = new ArrayList<>();

            for (double t = stressRequestDto.gettMin(); t < stressRequestDto.gettMax(); t += stressRequestDto.gettStep()) {
                Double[] vector = new Double[(int) ((stressRequestDto.getkMax() - stressRequestDto.getkMin()) / stressRequestDto.getkStep())];
                int i=0;
                for (int k = stressRequestDto.getkMin(); k < stressRequestDto.getkMax(); k += stressRequestDto.getkStep()) {
                    vector[i] = calculate.calculateSumOfRecursionRepresentationOfFunction(k, y, t);
                    i++;
                }
                matrix.add(vector);
            }


            double[][] matr = new double[matrix.size()][matrix.get(0).length];
            for (int i = 0; i < matrix.size(); i++) {
                for (int j = 0; j < matrix.get(0).length; j++) {
                    matr[i][j] = matrix.get(i)[j];
                }
            }
            RealMatrix coefficients = new Array2DRowRealMatrix(matr, false);
            long before = System.currentTimeMillis();
            try {
                coefficients.transpose();
                MatrixUtils.inverse(coefficients);
                MatrixUtils.inverse(coefficients.transpose().multiply(coefficients));
            }catch (Exception e){
                e.printStackTrace();
            }
            long after = System.currentTimeMillis();
            responseDtos.add(after - before);
        }
        Long time = responseDtos.stream().mapToLong(Long::longValue).sum();
        return time;
    }

    @RequestMapping(value = "/analytical", method = RequestMethod.POST)
    public Long calculateAnalyticalFunction(@RequestBody RequestDto requestDto) {
        long before = System.currentTimeMillis();
        double result = 0.0;
        if (requestDto.isParallel()) {
            result = calculate.calculateSumOfAnalyticalRepresentationOfFunctionParallel(requestDto.getN(), requestDto.getY(), requestDto.getT());
        } else {
            result = calculate.calculateSumOfAnalyticalRepresentationOfFunction(requestDto.getN(), requestDto.getY(), requestDto.getT());
        }
        long after = System.currentTimeMillis();
        return after - before;
    }

    @RequestMapping(value = "/integral", method = RequestMethod.POST)
    public Long stressCalculateIntegralFunction(@RequestBody RequestDto requestDto) {
        long before = System.currentTimeMillis();
        double result = 0.0;
        if (requestDto.isParallel()) {
            result = calculate.calculateSumOfIntegralRepresentationOfFunctionParallel(requestDto.getN(), requestDto.getK(), requestDto.getY(), requestDto.getT());
        } else {
            result = calculate.calculateSumOfIntegralRepresentationOfFunction(requestDto.getN(), requestDto.getK(), requestDto.getY(), requestDto.getT());
        }
        long after = System.currentTimeMillis();
        return after - before;

    }

    @RequestMapping(value = "/recursion", method = RequestMethod.POST)
    public Long calculateRecursionFunction(@RequestBody RequestDto requestDto) {
        long before = System.currentTimeMillis();
        double result = 0.0;
        result = calculate.calculateSumOfRecursionRepresentationOfFunction(requestDto.getK(), requestDto.getY(), requestDto.getT());
        long after = System.currentTimeMillis();
        return after - before;

    }


}