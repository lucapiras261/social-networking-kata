package com.lucapiras.snk.following;

import com.lucapiras.snk.user.User;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author Luca Piras
 */
@Embeddable
public class FollowingId implements Serializable {
    
    @ManyToOne
    protected User follower;
    
    @ManyToOne
    protected User followed;
    
    public FollowingId() {
    }

    public FollowingId(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
    }
    
    @Override
    public String toString() {
        return String.format(
                "FollowingId[follower='%s', followed='%s']", 
                follower, followed);
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowed() {
        return followed;
    }
}