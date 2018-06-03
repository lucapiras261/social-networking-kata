package com.lucapiras.snk;

import com.lucapiras.snk.dispatcher.IDispatcher;
import com.lucapiras.snk.exception.ExitException;
import com.lucapiras.snk.exception.UnknownRequestException;
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
    private ApplicationContext ctx;
    
    @Autowired
    private IDispatcher dispatcher;

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
        
        this.printWelcome();
        
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                dispatcher.dispatch(scanner.nextLine());
            } catch(UnknownRequestException ex) {
                System.out.println("\nUnknown command, please write again.\n");
            } catch(ExitException ex) {
                exit(0);
            }   
        }
    }

    protected void printWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nWelcome to Social Networking Kata\n\n");
        sb.append("Commands allowed: \n\n");
        sb.append("- posting: <user name> -> <message>\n");
        sb.append("- reading: <user name>\n");
        sb.append("- following: <user name> follows <another user>\n");
        sb.append("- wall: <user name> wall\n");
        sb.append("- exit\n");
        System.out.println(sb);
    }
}