package com.lucapiras.snk.post;


import com.lucapiras.snk.user.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Luca Piras
 */
public interface PostRepository extends CrudRepository<Post, PostId> {
    
    List<Post> findByPostIdPostOwnerOrderByPostIdPostTimestampAsc(User postOwner);
    
}