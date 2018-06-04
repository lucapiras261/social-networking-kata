package com.lucapiras.snk.post;

import com.lucapiras.snk.user.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luca Piras
 */
@Service
public class PostService implements IPostService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected PostRepository repository;
    
    @Override
    public Post save(Post post) {
        Post saved = repository.save(post);
        
        if (logger.isDebugEnabled()) {
            logger.debug("All the post of this user in cronological order:");
            logger.debug("-------------------------------");
            for (Post current : repository.findByPostIdPostOwnerOrderByPostIdPostTimestampAsc(post.getPostId().getPostOwner())) {
                logger.debug(current.toString());
            }
            logger.debug("");
        }
        
        return saved;
    }

    @Override
    public List<Post> readTimeline(User user) {
        
        return repository.findByPostIdPostOwnerOrderByPostIdPostTimestampAsc(user);
        
    }
}