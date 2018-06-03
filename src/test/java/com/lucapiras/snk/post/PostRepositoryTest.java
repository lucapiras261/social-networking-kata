package com.lucapiras.snk.post;

import com.lucapiras.snk.SocialNetworkingKataTestApplication;
import com.lucapiras.snk.user.User;
import com.lucapiras.snk.user.UserRepository;
import com.lucapiras.snk.utils.domain.helper.DomainHelperFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lucapiras.snk.utils.domain.helper.IDomainHelper;
import org.junit.Assert;

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
public class PostRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;

    protected IDomainHelper repoHelper;

    public PostRepositoryTest() {
        repoHelper = DomainHelperFactory.getRepositoryHelper();
    }
    
    /**
     * This has to fail because the user that posts does not exist
     */
    @Test
    public void testSaveExternalReferenceViolation() {
        System.out.println("PostRepositoryTest testSaveExternalReferenceViolation");
        
        Post firstPostBob = repoHelper.createFirstPostBob();
        
        try {
            postRepository.save(firstPostBob);

            Iterable<Post> results = postRepository.findAll();
            int count = 0;
            for (Post result : results) {
                count++;
            }
            
            if (count > 0) {
                Assert.fail("It saved a post for a user that does not exist!");
            }
        } catch(Exception ex) {//it is fine also if it throwned an exception;
            //this is because sometimes it doesn't flush the save soon and do not
            //throw the expected exception and, therefore, if it thrown it with
            // the findAll, such exception is related to the external reference
            //violation of the previous save, therefore it is fine
        }
    }
    
    /**
     * This has to succeed because, not like testSaveExternalReferenceViolation,
     * here we have save the user of the post we want to save
     */
    @Test
    public void testSave() {
        System.out.println("PostRepositoryTest testSave");
        
        User bob = repoHelper.createBob();
        Post firstPostBob = repoHelper.createFirstPostBob();
                
        userRepository.save(bob);
        postRepository.save(firstPostBob);

        Iterable<Post> results = postRepository.findAll();
        int count = 0;
        for (Post result : results) {
            count++;
        }

        if (count != 1) {
            Assert.fail("It did not save a post for a user that exist!");
        }
    }
    
    /**
     * Two consecutive saves update the same item (this is due because, without
     * a delay the timestamp is the same, and the item is the same because of
     * the key, same user and same timestamp)
     */
    @Test
    public void testUpdate() {
        System.out.println("PostRepositoryTest testUpdate");
        
        User bob = repoHelper.createBob();
        
        Post firstPostBob = repoHelper.createFirstPostBob();
        Post secondPostBob = repoHelper.createSecondPostBob();
                
        userRepository.save(bob);
        postRepository.save(firstPostBob);
        postRepository.save(secondPostBob);

        Iterable<Post> results = postRepository.findAll();
        int count = 0;
        for (Post result : results) {
            count++;
        }

        if (count == 2) {
            Assert.fail("It did not update the post with the second content!");
        }
    }
    
    /**
     * Two consecutive saves for the same user with a different timestamp have
     * to create 2 entries (here we can similate it thanks to the delay, so
     * we have for the 2 different posts the same user and different timestamp)
     */
    @Test
    public void testMultipleSaves() throws InterruptedException {
        System.out.println("PostRepositoryTest testMultipleSaves");
        
        User bob = repoHelper.createBob();
        
        Post firstPostBob = repoHelper.createFirstPostBob();
        Thread.sleep(10);
        Post secondPostBob = repoHelper.createSecondPostBob();
                
        userRepository.save(bob);
        postRepository.save(firstPostBob);
        postRepository.save(secondPostBob);

        Iterable<Post> results = postRepository.findAll();
        int count = 0;
        for (Post result : results) {
            count++;
        }
        
        if (count < 2) {
            Assert.fail("It did not save 2 posts that are different! "
                    + "Expected 2, while them were: " + count);
        }
    }
    
    /**
     * Test of findByPostIdPostOwnerOrderByPostIdPostTimestampAsc method, of class PostRepository.
     */
    @Test
    public void testFindByPostIdPostOwnerOrderByPostIdPostTimestampAsc() throws InterruptedException {
        System.out.println("findByPostIdPostOwnerOrderByPostIdPostTimestampAsc");
        
        User bob = repoHelper.createBob();
        
        userRepository.save(repoHelper.createAlice());
        userRepository.save(bob);
        userRepository.save(repoHelper.createCharlie());
        
        postRepository.save(repoHelper.createFirstPostCharlie());
        
        postRepository.save(repoHelper.createFirstPostBob());
        Thread.sleep(10);
        postRepository.save(repoHelper.createSecondPostBob());
        
        postRepository.save(repoHelper.createFirstPostAlice());
        
        Iterable<Post> results = postRepository.findByPostIdPostOwnerOrderByPostIdPostTimestampAsc(bob);
        int count = 0;
        for (Post result : results) {
            count++;
        }
        
        Assert.assertEquals(2, count);
    }
}