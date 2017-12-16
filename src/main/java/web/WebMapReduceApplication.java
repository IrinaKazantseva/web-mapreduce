package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Irina Kazantseva on 11.09.2017.
 */
@SpringBootApplication
@ComponentScan(basePackages="web")
public class WebMapReduceApplication {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WebMapReduceApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebMapReduceApplication.class, args);
    }

}