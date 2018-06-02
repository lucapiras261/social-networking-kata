package com.lucapiras.dispatcher;

import com.lucapiras.exception.ExitException;
import com.lucapiras.exception.UnknownRequestException;
import com.lucapiras.service.WelcomeService;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Luca Piras
 */
@Component
public class BasicDispatcher implements IDispatcher {

    @Override
    public void dispatch(String request) throws ExitException, UnknownRequestException {
        
        this.checkExtremeCases(request);
                
        if (0 == request.compareToIgnoreCase("wall")) {//MY WALL CASE

            System.out.println("The 'My Wall' function will be implemented soon.");

        } else {
            StringTokenizer st = new StringTokenizer(request, " ");
            String potentialUsername = st.nextToken();

            if (!st.hasMoreElements()) {//SHOW WALL OF A USER CASE

                System.out.println("The 'Show wall of a user' function will be implemented soon.");

            } else {
                
                String nextToken = st.nextToken();
                if (0 == nextToken.compareToIgnoreCase("->") && st.hasMoreElements()) {//POST CASE

                    System.out.println("The post function will be implemented soon.");

                } else if (0 == nextToken.compareToIgnoreCase("follows") && st.hasMoreElements()) {//FOLLOWS CASE

                    System.out.println("The follow function will be implemented soon.");

                } else {

                    throw new UnknownRequestException();
                }
            }
        }
    }

    protected void checkExtremeCases(String request) throws ExitException, UnknownRequestException {
        if (0 == request.compareToIgnoreCase("exit")) {//EXIT CASE
            throw new ExitException();
        }
        
        if (request.isEmpty()) {//EMPTY CASE
            throw new UnknownRequestException();
        }
    }
}