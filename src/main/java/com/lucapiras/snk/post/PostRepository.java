package com.lucapiras.snk.post;


import com.lucapiras.snk.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Luca Piras
 */
public interface PostRepository extends CrudRepository<Post, PostId> {
    
    List<Post> findByPostIdPostOwnerOrderByPostIdPostTimestampDesc(User postOwner);
    
    @Query("SELECT p "
            + "FROM Post p "
            + "WHERE (p) IN "
            + "( "
            + "SELECT p1 "
            + "FROM Following f, Post p1 "
            + "WHERE f.followingId.follower.username = ?1 AND "
            + "p1.postId.postOwner.username = f.followingId.followed.username "
            + ") "
            + "OR (p) IN "
            + "("
            + "SELECT p2 "
            + "FROM Post p2 "
            + "WHERE p2.postId.postOwner.username = ?1"
            + ") "
            + "ORDER BY p.postId.postTimestamp DESC"
            )
    List<Post> findWallPostsByUsername(String username);
}