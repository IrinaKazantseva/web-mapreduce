package rest;

import calculating.Calculate;
import model.RequestDto;
import model.ResponseDto;
import model.StressRequestDto;
import model.StressResponseDto;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/stress/analytical", method = RequestMethod.POST)
    public List<Long> stressCalculateAnalyticalFunction(@RequestBody StressRequestDto stressRequestDto) {
        List<Long> responseDtos = new ArrayList<>();

        for (double y = stressRequestDto.getyMin(); y < stressRequestDto.getyMax(); y += stressRequestDto.getyStep()) {
            List<Double[]> matrix = new ArrayList<>();
            for (double t = stressRequestDto.gettMin(); t < stressRequestDto.gettMax(); t += stressRequestDto.gettStep()) {
                Double[] vector = new Double[(int)((stressRequestDto.getnMax()-stressRequestDto.getnMin())/stressRequestDto.getnStep())];
                for (int n = stressRequestDto.getnMin(); n < stressRequestDto.getnMax(); n += stressRequestDto.getnStep()) {
                    if (stressRequestDto.isParallel()) {
                       vector[n] = calculate.calculateSumOfAnalyticalRepresentationOfFunctionParallel(n, y, t);
                    } else {
                        vector[n] = calculate.calculateSumOfAnalyticalRepresentationOfFunction(n, y, t);
                    }


                }
                matrix.add(vector);
                }
            double[][] matr = new double[matrix.size()][matrix.get(0).length];
            for(int i=0; i<matrix.size(); i++){
                for(int j=0; j<matrix.get(0).length; j++){
                    matr[i][j] = matrix.get(i)[j];
                }
            }
            RealMatrix coefficients = new Array2DRowRealMatrix(matr, false);
            long before = System.currentTimeMillis();
            coefficients.transpose();
            MatrixUtils.inverse(coefficients);
            MatrixUtils.inverse(coefficients.transpose().multiply(coefficients));
            long after = System.currentTimeMillis();
            responseDtos.add(after - before);
        }
        return responseDtos;
    }

    @RequestMapping(value = "/stress/integral", method = RequestMethod.POST)
    public List<Long> stressCalculateIntegralFunction(@RequestBody StressRequestDto stressRequestDto) {
        List<Long> responseDtos = new ArrayList<>();
        for (double y = stressRequestDto.getyMin(); y < stressRequestDto.getyMax(); y += stressRequestDto.getyStep()) {
            for (double t = stressRequestDto.gettMin(); t < stressRequestDto.gettMax(); t += stressRequestDto.gettStep()) {
                List<Double[]> matrix = new ArrayList<>();
                for (int k = stressRequestDto.getkMin(); k < stressRequestDto.getkMax(); k += stressRequestDto.getkStep()) {
                    Double[] vector = new Double[(int)((stressRequestDto.getnMax()-stressRequestDto.getnMin())/stressRequestDto.getnStep())];
                    for (int n = stressRequestDto.getnMin(); n < stressRequestDto.getnMax(); n += stressRequestDto.getnStep()) {
                        if (stressRequestDto.isParallel()) {
                            vector[n] = calculate.calculateSumOfIntegralRepresentationOfFunctionParallel(n, k, y, t);
                        } else {
                            vector[n] = calculate.calculateSumOfIntegralRepresentationOfFunction(n, k, y, t);
                        }
                    }
                }

                double[][] matr = new double[matrix.size()][matrix.get(0).length];
                for(int i=0; i<matrix.size(); i++){
                    for(int j=0; j<matrix.get(0).length; j++){
                        matr[i][j] = matrix.get(i)[j];
                    }
                }
                RealMatrix coefficients = new Array2DRowRealMatrix(matr, false);
                long before = System.currentTimeMillis();
                coefficients.transpose();
                MatrixUtils.inverse(coefficients);
                MatrixUtils.inverse(coefficients.transpose().multiply(coefficients));
                long after = System.currentTimeMillis();
                responseDtos.add(after - before);
            }
        }
        return responseDtos;
    }

    @RequestMapping(value = "/stress/recursion", method = RequestMethod.POST)
    public List<Long> stressCalculateRecursionFunction(@RequestBody StressRequestDto stressRequestDto) {
        List<Long> responseDtos = new ArrayList<>();
        for (double y = stressRequestDto.getyMin(); y < stressRequestDto.getyMax(); y += stressRequestDto.getyStep()) {
            List<Double[]> matrix = new ArrayList<>();

            for (double t = stressRequestDto.gettMin(); t < stressRequestDto.gettMax(); t += stressRequestDto.gettStep()) {
                Double[] vector = new Double[(int)((stressRequestDto.getkMax()-stressRequestDto.getkMin())/stressRequestDto.getkStep())];
                for (int k = stressRequestDto.getkMin(); k < stressRequestDto.getkMax(); k += stressRequestDto.getkStep()) {
                    vector[k] =    calculate.calculateSumOfRecursionRepresentationOfFunction(k, y, t);
                }
            }


            double[][] matr = new double[matrix.size()][matrix.get(0).length];
            for(int i=0; i<matrix.size(); i++){
                for(int j=0; j<matrix.get(0).length; j++){
                    matr[i][j] = matrix.get(i)[j];
                }
            }
            RealMatrix coefficients = new Array2DRowRealMatrix(matr, false);
            long before = System.currentTimeMillis();
            coefficients.transpose();
            MatrixUtils.inverse(coefficients);
            MatrixUtils.inverse(coefficients.transpose().multiply(coefficients));
            long after = System.currentTimeMillis();
            responseDtos.add(after - before);
        }
        return responseDtos;
    }

    @RequestMapping(value = "/analytical", method = RequestMethod.POST)
    public ResponseDto calculateAnalyticalFunction(@RequestBody RequestDto requestDto) {
        long before = System.currentTimeMillis();
        double result = 0.0;
        if (requestDto.isParallel()) {
            result = calculate.calculateSumOfAnalyticalRepresentationOfFunctionParallel(requestDto.getN(), requestDto.getY(), requestDto.getT());
        } else {
            result = calculate.calculateSumOfAnalyticalRepresentationOfFunction(requestDto.getN(), requestDto.getY(), requestDto.getT());
        }
        long after = System.currentTimeMillis();
        return new ResponseDto(result,after-before);
    }

    @RequestMapping(value = "/integral", method = RequestMethod.POST)
    public ResponseDto stressCalculateIntegralFunction(@RequestBody RequestDto requestDto) {
        long before = System.currentTimeMillis();
        double result = 0.0;
        if (requestDto.isParallel()) {
            result = calculate.calculateSumOfIntegralRepresentationOfFunctionParallel(requestDto.getN(), requestDto.getK(), requestDto.getY(), requestDto.getT());
        } else {
            result = calculate.calculateSumOfIntegralRepresentationOfFunction(requestDto.getN(), requestDto.getK(), requestDto.getY(), requestDto.getT());
        }
        long after = System.currentTimeMillis();
        return new ResponseDto(result,after-before);

    }

    @RequestMapping(value = "/recursion", method = RequestMethod.POST)
    public ResponseDto calculateRecursionFunction(@RequestBody RequestDto requestDto) {
        long before = System.currentTimeMillis();
        double result = 0.0;
        result = calculate.calculateSumOfRecursionRepresentationOfFunction(requestDto.getK(), requestDto.getY(), requestDto.getT());
        long after = System.currentTimeMillis();
        return new ResponseDto(result,after-before);

    }


}