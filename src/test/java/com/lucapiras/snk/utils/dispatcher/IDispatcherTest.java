package com.lucapiras.snk.utils.dispatcher;

import com.lucapiras.snk.SocialNetworkingKataTestApplication;
import com.lucapiras.snk.exception.ExitException;
import com.lucapiras.snk.exception.UnknownRequestException;
import com.lucapiras.snk.following.Following;
import com.lucapiras.snk.following.FollowingRepository;
import com.lucapiras.snk.post.Post;
import com.lucapiras.snk.post.PostRepository;
import com.lucapiras.snk.utils.domain.helper.DomainHelperFactory;
import com.lucapiras.snk.utils.domain.helper.IDomainHelper;
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
    
    @MockBean
    protected FollowingRepository followingRepository;
    
    @Before
    public void setUp() {
        
        IDomainHelper repoHelper = DomainHelperFactory.getRepositoryHelper();
        
        Post post = repoHelper.createFirstPostCharlie();
        Mockito.when(postRepository.save(post)).thenReturn(post);        
        
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        Mockito.when(followingRepository.save(charlieFollowsBob)).thenReturn(charlieFollowsBob);
    }

    /**
     * Test of dispatch method, of class IDispatcher.
     */
    @Test(expected = ExitException.class)
    public void testDispatchExitException() throws Exception {
        
        String request = "exit";
        dispatcher.dispatch(request);
    }
    
    /**
     * Test of dispatch method, of class IDispatcher.
     */
    @Test
    public void testDispatchUnknownRequestException() throws Exception {
                
        List<String> requests = new ArrayList();        
        
        requests.add("");
        
        requests.add("save charlie");
        requests.add("charlie sav");
        requests.add("charlie saved");
        requests.add("charlie save otherText");
        requests.add("charlie save other text");
        
        requests.add("charlie follow");
        requests.add("charlie follow alice");
        requests.add("charlie follows");
        requests.add("charlie follows alice otherText");
        requests.add("charlie follows alice other text");
        
        requests.add("charlie ->");
        requests.add("charlie -");
        requests.add("charlie >");
        requests.add("charlie *");
        requests.add("charlie - how are you?");
        requests.add("charlie >  how are you?");
        requests.add("charlie * how are you?");
        
        requests.add("charlie wal");
        requests.add("charlie walls");
        requests.add("charlie wall otherText");
        requests.add("charlie wall other text");
        
        requests.add("charlie write");
        requests.add("charlie open");
        
        requests.add("charlie write ok");
        requests.add("charlie open ok");
        
        for (String request : requests) {
            try {
                dispatcher.dispatch(request);
                Assert.fail("Exception not thrown for: "+ request);
            } catch(UnknownRequestException ex) {
            }
        }
    }
    
    /**
     * Test of dispatch method, of class IDispatcher.
     */
    @Test
    public void testDispatchSuccess() throws Exception {
                
        List<String> requests = new ArrayList();
        requests.add("charlie save");
        requests.add("charlie -> I am fine");
        requests.add("charlie");
        requests.add("charlie follows alice");
        requests.add("charlie wall");
                
        for (String request : requests) {
            dispatcher.dispatch(request);
        }
    }
}