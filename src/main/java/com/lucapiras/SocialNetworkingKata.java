package com.lucapiras;

import com.lucapiras.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;
import java.util.Arrays;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Luca Piras
 */
@SpringBootApplication
public class SocialNetworkingKata implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext ctx;
    
    @Autowired
    private WelcomeService helloService;

    public static void main(String[] args) throws Exception {        
        SpringApplication.run(SocialNetworkingKata.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Spring Boot beans:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                logger.debug(beanName);
            }
        }
        
        if (args.length > 0 ) {
            System.out.println(helloService.getMessage(args[0].toString()));
        }else{
            System.out.println(helloService.getMessage());
        }
        
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String next = scanner.nextLine();
            if (0 != next.compareToIgnoreCase("exit")) {
                System.out.println(helloService.getMessage(next));
            } else {
                exit(0);
            }            
        }
    }
}