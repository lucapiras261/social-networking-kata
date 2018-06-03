package com.lucapiras.snk.user;

import com.lucapiras.snk.SocialNetworkingKataTestApplication;
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

    @Test
    public void testSave() {
        System.out.println("UserRepositoryTest testSave");
        
        List<User> users = new ArrayList();        
        
        users.add(new User("Alice"));
        users.add(new User("Bob"));
        users.add(new User("Charlie"));
        
        for (User user : users) {
            repository.save(user);
        }
        
        Iterable<User> results = repository.findAll();
        int count = 0;
        for (User result : results) {
            count++;
        }
        
        if (users.size() != count) {
            Assert.fail("Expected " + users.size() + 
                        ", while counted " + count);
        }
    }
    
    @Test
    public void testPrimaryKey() {
        System.out.println("UserRepositoryTest testPrimaryKey");
        
        List<User> users = new ArrayList();        
        
        User duplicatedUser = new User("Alice");
                
        users.add(duplicatedUser);
        users.add(new User("Bob"));
        users.add(new User("Charlie"));
        users.add(duplicatedUser);
        
        for (User user : users) {
            repository.save(user);
        }
        
        Iterable<User> results = repository.findAll();
        int count = 0;
        for (User result : results) {
            count++;
        }
        
        if ((users.size() - 1) != count) {
            Assert.fail("Primary key constraint not working: " +
                        "Expected " + users.size() + 
                        ", while counted " + count);
        }
    }
}