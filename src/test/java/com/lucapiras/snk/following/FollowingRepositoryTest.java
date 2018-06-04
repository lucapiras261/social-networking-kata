package com.lucapiras.snk.following;

import com.lucapiras.snk.SocialNetworkingKataTestApplication;
import com.lucapiras.snk.user.User;
import com.lucapiras.snk.user.UserRepository;
import com.lucapiras.snk.utils.domain.helper.DomainHelperFactory;
import com.lucapiras.snk.utils.domain.helper.IDomainHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Luca Piras
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@SpringBootTest(classes = SocialNetworkingKataTestApplication.class)
public class FollowingRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FollowingRepository followingRepository;
    
    protected IDomainHelper repoHelper;

    public FollowingRepositoryTest() {
        repoHelper = DomainHelperFactory.getRepositoryHelper();
    }
    
    /**
     * This has to fail because the follower and the followed do not exist
     */
    @Test
    public void testSaveFollowerAndFollowedExternalReferenceViolation() {
        
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        
        try {
            followingRepository.save(charlieFollowsBob);

            Iterable<Following> results = followingRepository.findAll();
            int count = 0;
            for (Following result : results) {
                count++;
            }
            
            Assert.assertEquals(0, count);
            
        } catch(Exception ex) {//it is fine also if it throwned an exception;
            //sometimes the exception of the save is throwned with the next read
        }
    }
    
    /**
     * This has to fail because the followed does not exist
     */
    @Test
    public void testSaveFollowedExternalReferenceViolation() {
        
        User charlie = repoHelper.createCharlie();
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        
        userRepository.save(charlie);
        try {
            followingRepository.save(charlieFollowsBob);

            Iterable<Following> results = followingRepository.findAll();
            int count = 0;
            for (Following result : results) {
                count++;
            }
            
            Assert.assertEquals(0, count);
            
        } catch(Exception ex) {//it is fine also if it throwned an exception;
            //sometimes the exception of the save is throwned with the next read
        }
    }
    
    /**
     * This has to fail because the follower does not exist
     */
    @Test
    public void testSaveFollowerExternalReferenceViolation() {
        
        User bob = repoHelper.createBob();
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        
        userRepository.save(bob);
        try {
            followingRepository.save(charlieFollowsBob);

            Iterable<Following> results = followingRepository.findAll();
            int count = 0;
            for (Following result : results) {
                count++;
            }
            
            Assert.assertEquals(0, count);
            
        } catch(Exception ex) {//it is fine also if it throwned an exception;
            //sometimes the exception of the save is throwned with the next read
        }
    }
    
    /**
     * This has to succeed because the follower and the followed exist
     */
    @Test
    public void testSave() {
        
        User charlie = repoHelper.createCharlie();
        User bob = repoHelper.createBob();
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        
        userRepository.save(charlie);
        userRepository.save(bob);
        
        followingRepository.save(charlieFollowsBob);

        Iterable<Following> results = followingRepository.findAll();
        int count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(1, count);
    }
    
    @Test
    public void testSaveFollowingAlreadyExistViolation() {
        
        User charlie = repoHelper.createCharlie();
        User bob = repoHelper.createBob();
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        
        userRepository.save(charlie);
        userRepository.save(bob);
        
        followingRepository.save(charlieFollowsBob);
        
        try {
            followingRepository.save(charlieFollowsBob);

            Iterable<Following> results = followingRepository.findAll();
            int count = 0;
            for (Following result : results) {
                count++;
            }
            
            Assert.assertEquals(1, count);
            
        } catch(Exception ex) {//it is fine also if it throwned an exception;
            //sometimes the exception of the save is throwned with the next read
        }
    }
    
    @Test
    public void testMultipleSaves() {
        
        User charlie = repoHelper.createCharlie();
        User alice = repoHelper.createAlice();
        User bob = repoHelper.createBob();
        Following charlieFollowsAlice = repoHelper.createCharlieFollowsAlice();
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        
        userRepository.save(charlie);
        userRepository.save(alice);
        userRepository.save(bob);
        
        followingRepository.save(charlieFollowsAlice);
        followingRepository.save(charlieFollowsBob);

        Iterable<Following> results = followingRepository.findAll();
        int count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(2, count);
    }
    
    /**
     * Test of findByFollowingIdFollowed method, of class FollowingRepository.
     */
    @Test
    public void testFindByFollowingIdFollowed() {
        
        User charlie = repoHelper.createCharlie();
        User alice = repoHelper.createAlice();
        User bob = repoHelper.createBob();
        Following charlieFollowsAlice = repoHelper.createCharlieFollowsAlice();
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        Following aliceFollowsBob = repoHelper.createAliceFollowsBob();
        
        userRepository.save(charlie);
        userRepository.save(alice);
        userRepository.save(bob);
        
        followingRepository.save(charlieFollowsAlice);
        followingRepository.save(charlieFollowsBob);
        followingRepository.save(aliceFollowsBob);

        Iterable<Following> results = followingRepository.findByFollowingIdFollowed(charlie);
        int count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(0, count);
        
        results = followingRepository.findByFollowingIdFollowed(alice);
        count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(1, count);
        
        results = followingRepository.findByFollowingIdFollowed(bob);
        count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(2, count);
    }

    /**
     * Test of findByFollowingIdFollower method, of class FollowingRepository.
     */
    @Test
    public void testFindByFollowingIdFollower() {
        
        User charlie = repoHelper.createCharlie();
        User alice = repoHelper.createAlice();
        User bob = repoHelper.createBob();
        Following charlieFollowsAlice = repoHelper.createCharlieFollowsAlice();
        Following charlieFollowsBob = repoHelper.createCharlieFollowsBob();
        Following aliceFollowsBob = repoHelper.createAliceFollowsBob();
        
        userRepository.save(charlie);
        userRepository.save(alice);
        userRepository.save(bob);
        
        followingRepository.save(charlieFollowsAlice);
        followingRepository.save(charlieFollowsBob);
        followingRepository.save(aliceFollowsBob);

        Iterable<Following> results = followingRepository.findByFollowingIdFollower(charlie);
        int count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(2, count);
        
        results = followingRepository.findByFollowingIdFollower(alice);
        count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(1, count);
        
        results = followingRepository.findByFollowingIdFollower(bob);
        count = 0;
        for (Following result : results) {
            count++;
        }

        Assert.assertEquals(0, count);
    }
}
