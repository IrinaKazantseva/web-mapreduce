package rest;

import calculating.Calculate;
import model.StressRequestDto;
import model.StressResponseDto;
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
    public List<StressResponseDto> stressCalculateAnalyticalFunction(@RequestBody StressRequestDto stressRequestDto){
        List<StressResponseDto> responseDtos = new ArrayList<>();
        for(double y = stressRequestDto.getyMin(); y< stressRequestDto.getyMax(); y+= stressRequestDto.getyStep()){
            for(double t = stressRequestDto.gettMin(); t<stressRequestDto.gettMax(); t+=stressRequestDto.gettStep()){
                for (int n = stressRequestDto.getnMin(); n<stressRequestDto.getnMax(); n+= stressRequestDto.getnStep()){
                    long before = System.currentTimeMillis();
                    if(stressRequestDto.isParallel()){
                        calculate.calculateSumOfAnalyticalRepresentationOfFunctionParallel(n, y, t);
                    }else{
                        calculate.calculateSumOfAnalyticalRepresentationOfFunction(n, y, t);
                    }
                    long after = System.currentTimeMillis();
                    responseDtos.add(new StressResponseDto(t,y,n,0,after-before));
                }
            }
        }
        return responseDtos;
    }

    @RequestMapping(value = "/stress/integral", method = RequestMethod.POST)
    public  List<StressResponseDto> stressCalculateIntegralFunction(@RequestBody StressRequestDto stressRequestDto){
        List<StressResponseDto> responseDtos = new ArrayList<>();
        for(double y = stressRequestDto.getyMin(); y< stressRequestDto.getyMax(); y+= stressRequestDto.getyStep()){
            for(double t = stressRequestDto.gettMin(); t<stressRequestDto.gettMax(); t+=stressRequestDto.gettStep()){
                for(int k=stressRequestDto.getkMin(); k<stressRequestDto.getkMax(); k+=stressRequestDto.getkStep()) {
                    for (int n = stressRequestDto.getnMin(); n < stressRequestDto.getnMax(); n += stressRequestDto.getnStep()) {
                        long before = System.currentTimeMillis();
                        if(stressRequestDto.isParallel()){
                            calculate.calculateSumOfIntegralRepresentationOfFunctionParallel(n, k, y, t);
                        }else {
                            calculate.calculateSumOfIntegralRepresentationOfFunction(n, k, y, t);
                        }
                        long after = System.currentTimeMillis();
                        responseDtos.add(new StressResponseDto(t, y, n, k, after - before));
                    }
                }
            }
        }
        return responseDtos;
    }

    @RequestMapping(value = "/stress/recursion", method = RequestMethod.POST)
    public  List<StressResponseDto> stressCalculateRecursionFunction(@RequestBody StressRequestDto stressRequestDto){
        List<StressResponseDto> responseDtos = new ArrayList<>();
        for(double y = stressRequestDto.getyMin(); y< stressRequestDto.getyMax(); y+= stressRequestDto.getyStep()){
            for(double t = stressRequestDto.gettMin(); t<stressRequestDto.gettMax(); t+=stressRequestDto.gettStep()){
                for (int k = stressRequestDto.getkMin(); k<stressRequestDto.getkMax(); k+= stressRequestDto.getkStep()){
                    long before = System.currentTimeMillis();
                    calculate.calculateSumOfRecursionRepresentationOfFunction(k,y,t);
                    long after = System.currentTimeMillis();
                    responseDtos.add(new StressResponseDto(t,y,0,k,after-before));
                }
            }
        }
        return responseDtos;
    }


}