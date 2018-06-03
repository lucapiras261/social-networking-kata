package com.lucapiras.snk.dispatcher;

import com.lucapiras.snk.SocialNetworkingKataTestApplication;
import com.lucapiras.snk.utils.dispatcher.IDispatcher;
import com.lucapiras.snk.exception.ExitException;
import com.lucapiras.snk.exception.UnknownRequestException;
import com.lucapiras.snk.post.Post;
import com.lucapiras.snk.post.PostId;
import com.lucapiras.snk.post.PostRepository;
import com.lucapiras.snk.user.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Luca Piras
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SocialNetworkingKataTestApplication.class)
public class IDispatcherTest {

    @Autowired
    private IDispatcher dispatcher;
    
    @MockBean
    protected PostRepository postRepository;
    
    @Before
    public void setUp() {
        
        User charlie = new User("charlie");
        
        Post post = new Post(new PostId(charlie), "I am fine");
        
        Mockito.when(postRepository.save(post)).thenReturn(post);
    }

    /**
     * Test of dispatch method, of class IDispatcher.
     */
    @Test(expected = ExitException.class)
    public void testDispatchExitException() throws Exception {
        System.out.println("dispatch testDispatchExitException");
        String request = "exit";
        dispatcher.dispatch(request);
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
        for (String request : requests) {
            try {
                dispatcher.dispatch(request);
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
                
        List<String> requests = new ArrayList();        
        requests.add("charlie -> I am fine");
        requests.add("charlie");
        requests.add("charlie follows alice");
        requests.add("wall");
                
        for (String request : requests) {
            dispatcher.dispatch(request);
        }
    }
}