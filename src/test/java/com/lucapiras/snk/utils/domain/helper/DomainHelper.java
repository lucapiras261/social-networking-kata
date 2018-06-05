package com.lucapiras.snk.utils.domain.helper;

import com.lucapiras.snk.following.Following;
import com.lucapiras.snk.following.FollowingId;
import com.lucapiras.snk.post.Post;
import com.lucapiras.snk.post.PostId;
import com.lucapiras.snk.user.User;

/**
 *
 * @author Luca Piras
 */
public class DomainHelper implements IDomainHelper {

    @Override
    public User createCharlie() {
        return new User("Charlie");
    }
    
    @Override
    public User createBob() {
        return new User("Bob");
    }
    
    @Override
    public User createAlice() {
        return new User("Alice");
    }
    
    @Override
    public Post createFirstPostCharlie() {
        return new Post(new PostId(this.createCharlie()), "I'm in New York today! Anyone wants to have a coffee?");
    }
    
    
    @Override
    public Post createFirstPostBob() {
        return new Post(new PostId(this.createBob()), "Damn! We lost!");
    }
    
    @Override
    public Post createSecondPostBob() {
        return new Post(new PostId(this.createBob()), "Good game though.");
    }


    @Override
    public Post createFirstPostAlice() {
        return new Post(new PostId(this.createAlice()), "I love the weather today");
    }

    
    @Override
    public Following createCharlieFollowsAlice() {
        
        FollowingId followingId = new FollowingId(this.createCharlie(), this.createAlice());
        
        return new Following(followingId);
    }
    
    @Override
    public Following createCharlieFollowsBob() {
        
        FollowingId followingId = new FollowingId(this.createCharlie(), this.createBob());
        
        return new Following(followingId);
    }

    @Override
    public Following createAliceFollowsBob() {
        FollowingId followingId = new FollowingId(this.createAlice(), this.createBob());
        
        return new Following(followingId);
    }
    
}