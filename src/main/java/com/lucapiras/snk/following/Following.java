package com.lucapiras.snk.following;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import org.hibernate.annotations.Check;

/**
 *
 * @author Luca Piras
 */
@Entity
@Check(constraints = "follower_username <> followed_username")
public class Following implements Serializable {

    @EmbeddedId
    protected FollowingId followingId;

    protected Following() {}

    public Following(FollowingId followingId) {
        this.followingId = followingId;
    }    

    @Override
    public String toString() {
        return String.format(
                "Following[followingId='%s']", 
                followingId);
    }

    public FollowingId getFollowingId() {
        return followingId;
    }
}