package com.lucapiras.snk.user;

import com.lucapiras.snk.SocialNetworkingKataTestApplication;
import com.lucapiras.snk.utils.domain.helper.DomainHelperFactory;
import com.lucapiras.snk.utils.domain.helper.IDomainHelper;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Luca Piras
 */
/*@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.lucapiras.snk"})*/
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@SpringBootTest(classes = SocialNetworkingKataTestApplication.class)
public class UserRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    protected IDomainHelper domainHelper;

    public UserRepositoryTest() {
        domainHelper = DomainHelperFactory.getDomainHelper();
    }
    
    @Test
    public void testSave() {
        
        List<User> users = new ArrayList();        
        
        users.add(domainHelper.createAlice());
        users.add(domainHelper.createBob());
        users.add(domainHelper.createCharlie());
        
        for (User user : users) {
            repository.save(user);
        }
        
        Iterable<User> results = repository.findAll();
        int count = 0;
        for (User result : results) {
            count++;
        }
        
        Assert.assertEquals("It did not update the post with the second content!",
                            users.size(), count);
    }
    
    @Test
    public void testPrimaryKey() {
        
        List<User> users = new ArrayList();        
        
        User duplicatedUser = domainHelper.createAlice();
                
        users.add(duplicatedUser);
        users.add(domainHelper.createBob());
        users.add(domainHelper.createCharlie());
        users.add(duplicatedUser);
        
        for (User user : users) {
            repository.save(user);
        }
        
        Iterable<User> results = repository.findAll();
        int count = 0;
        for (User result : results) {
            count++;
        }
        
        Assert.assertEquals("Primary key constraint not working!",
                            users.size() - 1, count);
    }
}