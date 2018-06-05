package com.lucapiras.snk.post;

import com.lucapiras.snk.user.User;
import java.util.List;

/**
 *
 * @author Luca Piras
 */
public interface IPostService {
    
    public Post save(Post post);
    
    public List<Post> readTimeline(User user);

    public List<Post> readWall(User user);
     
}