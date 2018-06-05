package com.lucapiras.snk;

import com.lucapiras.snk.utils.dispatcher.IDispatcher;
import com.lucapiras.snk.exception.ExitException;
import com.lucapiras.snk.utils.exceptionhandler.IExceptionHandler;
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
public class SocialNetworkingKataApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ApplicationContext ctx;
    
    @Autowired
    protected IDispatcher dispatcher;
    
    @Autowired
    protected IExceptionHandler exceptionHandler;

    public static void main(String[] args) throws Exception {        
        SpringApplication.run(SocialNetworkingKataApplication.class, args);
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
        
        /*if (args.length > 0 ) {
            dispatcher.dispatch(args[0].toString());
        }*/
        
        this.startListening();
    }

    protected void startListening() throws Exception {
        
        //welcome and instructions
        dispatcher.dispatch("welcome");
            
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                dispatcher.dispatch(scanner.nextLine());
            } catch(ExitException ex) {
                exit(0);
            } catch(Exception ex) {
                exceptionHandler.handle(ex);
            }
        }
    }
}