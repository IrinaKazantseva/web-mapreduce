package configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Irina Kazantseva on 11.09.2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class WebMapReduceApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(WebMapReduceApplication.class, args);
    }

}