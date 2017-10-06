package configuration;

import calculating.Calculate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

/**
 * Created by Irina Kazantseva on 11.09.2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class WebMapReduceApplication {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WebMapReduceApplication.class);

    public static void main(String[] args) throws Exception {
        //log.debug("Application start successfully");
      /*  Calculate calculate = new Calculate();
        calculate.calculateSumOfRecursionRepresentationOfFunction(20, 0.5, 0.4);
*/
       SpringApplication.run(WebMapReduceApplication.class, args);
    }

}