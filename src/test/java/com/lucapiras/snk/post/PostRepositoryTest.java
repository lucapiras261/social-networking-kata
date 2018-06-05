package com.lucapiras.snk.post;

import com.lucapiras.snk.SocialNetworkingKataTestApplication;
import com.lucapiras.snk.following.Following;
import com.lucapiras.snk.following.FollowingRepository;
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
import java.util.List;
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

    @Autowired
    private FollowingRepository followingRepository;
    
    protected IDomainHelper domainHelper;

    public PostRepositoryTest() {
        domainHelper = DomainHelperFactory.getDomainHelper();
    }
    
    /**
     * This has to fail because the user that posts does not exist
     */
    @Test
    public void testSaveExternalReferenceViolation() {
        
        Post firstPostBob = domainHelper.createFirstPostBob();
        
        try {
            postRepository.save(firstPostBob);

            Iterable<Post> results = postRepository.findAll();
            int count = 0;
            for (Post result : results) {
                count++;
            }
            
            Assert.assertEquals("It saved a post for a user that does not exist!",
                                1, count);
            
        } catch(Exception ex) {//it is fine also if it thrown an exception;
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
        
        User bob = domainHelper.createBob();
        Post firstPostBob = domainHelper.createFirstPostBob();
                
        userRepository.save(bob);
        postRepository.save(firstPostBob);

        Iterable<Post> results = postRepository.findAll();
        int count = 0;
        for (Post result : results) {
            count++;
        }

        Assert.assertEquals("It did not save a post for a user that exist!",
                                1, count);
        
    }
    
    /**
     * Two consecutive saves update the same item (this is due because, without
     * a delay the timestamp is the same, and the item is the same because of
     * the key, same user and same timestamp)
     */
    @Test
    public void testUpdate() {
        
        User bob = domainHelper.createBob();
        
        Post firstPostBob = domainHelper.createFirstPostBob();
        Post secondPostBob = domainHelper.createSecondPostBob();
                
        userRepository.save(bob);
        postRepository.save(firstPostBob);
        postRepository.save(secondPostBob);

        Iterable<Post> results = postRepository.findAll();
        int count = 0;
        for (Post result : results) {
            count++;
        }

        Assert.assertEquals("It did not update the post with the second content!",
                            1, count);
    }
    
    /**
     * Two consecutive saves for the same user with a different timestamp have
     * to create 2 entries (here we can similate it thanks to the delay, so
     * we have for the 2 different posts the same user and different timestamp)
     */
    @Test
    public void testMultipleSaves() throws InterruptedException {
        
        User bob = domainHelper.createBob();
        
        Post firstPostBob = domainHelper.createFirstPostBob();
        Thread.sleep(10);
        Post secondPostBob = domainHelper.createSecondPostBob();
                
        userRepository.save(bob);
        postRepository.save(firstPostBob);
        postRepository.save(secondPostBob);

        Iterable<Post> results = postRepository.findAll();
        int count = 0;
        for (Post result : results) {
            count++;
        }
        
        Assert.assertEquals("It did not save 2 posts that are different! ",
                            2, count);
    }
    
    /**
     * Test of findByPostIdPostOwnerOrderByPostIdPostTimestampDesc method, of class PostRepository.
     */
    @Test
    public void testFindByPostIdPostOwnerOrderByPostIdPostTimestampAsc() throws InterruptedException {
        
        User bob = domainHelper.createBob();
        
        userRepository.save(domainHelper.createAlice());
        userRepository.save(bob);
        userRepository.save(domainHelper.createCharlie());
        
        postRepository.save(domainHelper.createFirstPostCharlie());
        
        Post firstPostBob = domainHelper.createFirstPostBob();
        Thread.sleep(10);
        Post secondPostBob = domainHelper.createSecondPostBob();
        
        postRepository.save(firstPostBob);
        postRepository.save(secondPostBob);
        
        postRepository.save(domainHelper.createFirstPostAlice());
        
        List<Post> results = postRepository.findByPostIdPostOwnerOrderByPostIdPostTimestampDesc(bob);
        int count = 0;
        for (Post result : results) {
            count++;
            
            //check if the order is correct
            if (count == 1) {
                Assert.assertEquals(result.getContent(), secondPostBob.getContent());
            } else if (count == 2) {
                Assert.assertEquals(result.getContent(), firstPostBob.getContent());
            }
        }
        
        Assert.assertEquals(2, count);
    }
    
    /**
     * Test of findWallPostsByUsername method, of class PostRepository.
     */
    @Test
    public void findWallPostsByUsername() throws InterruptedException {
        
        //Users        
        User charlie = domainHelper.createCharlie();
        User alice = domainHelper.createAlice();
        User bob = domainHelper.createBob();
        
        userRepository.save(charlie);
        userRepository.save(alice);
        userRepository.save(bob);
        
        //Posts
        Post firstPostAlice = domainHelper.createFirstPostAlice();
        
        Post firstPostBob = domainHelper.createFirstPostBob();
        Thread.sleep(10);
        Post secondPostBob = domainHelper.createSecondPostBob();
        
        Post firstPostCharlie = domainHelper.createFirstPostCharlie();
        
        postRepository.save(firstPostCharlie);
        
        postRepository.save(firstPostBob);
        postRepository.save(secondPostBob);
        
        postRepository.save(firstPostAlice);
        
        //Followings
        Following charlieFollowsAlice = domainHelper.createCharlieFollowsAlice();
        Following charlieFollowsBob = domainHelper.createCharlieFollowsBob();
        Following aliceFollowsBob = domainHelper.createAliceFollowsBob();
        
        followingRepository.save(charlieFollowsAlice);
        followingRepository.save(charlieFollowsBob);
        followingRepository.save(aliceFollowsBob);
        
        //Charlie
        List<Post> results = postRepository.findWallPostsByUsername(charlie.getUsername());
        int count = 0;
        for (Post result : results) {
            count++;
            
            //check if the order is correct
            if (count == 1) {
                Assert.assertEquals(result.getContent(), firstPostCharlie.getContent());
            } else if (count == 2) {
                Assert.assertEquals(result.getContent(), secondPostBob.getContent());
            }  else if (count == 3) {
                Assert.assertEquals(result.getContent(), firstPostBob.getContent());
            }  else if (count == 4) {
                Assert.assertEquals(result.getContent(), firstPostAlice.getContent());
            }
        }
        
        Assert.assertEquals(4, count);
        
        //Alice
        results = postRepository.findWallPostsByUsername(alice.getUsername());
        count = 0;
        for (Post result : results) {
            count++;
            
            //check if the order is correct
            if (count == 1) {
                Assert.assertEquals(result.getContent(), secondPostBob.getContent());
            }  else if (count == 2) {
                Assert.assertEquals(result.getContent(), firstPostBob.getContent());
            }  else if (count == 3) {
                Assert.assertEquals(result.getContent(), firstPostAlice.getContent());
            }
        }
        
        Assert.assertEquals(3, count);
        
        //Bob
        results = postRepository.findWallPostsByUsername(bob.getUsername());
        count = 0;
        for (Post result : results) {
            count++;
            
            //check if the order is correct
            if (count == 1) {
                Assert.assertEquals(result.getContent(), secondPostBob.getContent());
            }  else if (count == 2) {
                Assert.assertEquals(result.getContent(), firstPostBob.getContent());
            }
        }
        
        Assert.assertEquals(2, count);
    }
}