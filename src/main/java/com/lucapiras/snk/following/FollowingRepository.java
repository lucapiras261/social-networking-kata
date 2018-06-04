package com.lucapiras.snk.following;


import com.lucapiras.snk.user.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Luca Piras
 */
public interface FollowingRepository extends CrudRepository<Following, FollowingId> {
    
    /**
     * Find all the followers of a user
     * @param followed
     * @return 
     */
    List<Following> findByFollowingIdFollowed(User followed);
    
    /**
     * Find all the users followed by a user
     * @param follower
     * @return 
     */
    List<Following> findByFollowingIdFollower(User follower);
}