package com.lucapiras.snk.dispatcher;

import com.lucapiras.snk.dispatcher.BasicDispatcher;
import com.lucapiras.snk.dispatcher.IDispatcher;
import com.lucapiras.snk.exception.ExitException;
import com.lucapiras.snk.exception.UnknownRequestException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Luca Piras
 */
public class IDispatcherTest {

    /**
     * Test of dispatch method, of class IDispatcher.
     */
    @Test(expected = ExitException.class)
    public void testDispatchExitException() throws Exception {
        System.out.println("dispatch testDispatchExitException");
        String request = "exit";
        init().dispatch(request);
    }
    
    /**
     * Test of dispatch method, of class IDispatcher.
     */
    @Test
    public void testDispatchUnknownRequestException() throws Exception {
        System.out.println("dispatch testDispatchUnknownRequestException");
                
        List<String> requests = new ArrayList();        
        
        requests.add("");
        
        requests.add("charlie follow");
        requests.add("charlie follow alice");
        requests.add("charlie follows");
        
        requests.add("charlie ->");
        requests.add("charlie -");
        requests.add("charlie >");
        requests.add("charlie *");
        requests.add("charlie - how are you?");
        requests.add("charlie >  how are you?");
        requests.add("charlie * how are you?");
        
        requests.add("charlie write");
        requests.add("charlie open");
        
        requests.add("charlie write ok");
        requests.add("charlie open ok");
        
        int exCount = 0;
        IDispatcher instance = init();
        for (String request : requests) {
            try {
                instance.dispatch(request);
            } catch(UnknownRequestException ex) {
                exCount++;
            }
        }
        
        if (exCount != requests.size()) {
            Assert.fail("Expected " + requests.size() + 
                        " exceptions, while counted " + exCount);
        }
    }
    
    /**
     * Test of dispatch method, of class IDispatcher.
     */
    @Test
    public void testDispatchSuccess() throws Exception {
        System.out.println("dispatch testDispatchSuccess");
        
        IDispatcher instance = init();
        List<String> requests = new ArrayList();        
        requests.add("charlie -> I am fine");
        requests.add("charlie");
        requests.add("charlie follows alice");
        requests.add("wall");
                
        for (String request : requests) {
            instance.dispatch(request);
        }
    }
    
    protected IDispatcher init() {
        return new BasicDispatcher();
    }
}