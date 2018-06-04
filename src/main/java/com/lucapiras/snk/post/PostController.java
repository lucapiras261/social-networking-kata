package com.lucapiras.snk.post;

import com.lucapiras.snk.user.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
@Controller
public class PostController implements IPostController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected IPostService service;
    
    @Override
    public String save(String username, String content, Model model) {
        
        User user = new User(username);
        PostId postId = new PostId(user);
        Post post = new Post(postId, content);
        
        service.save(post);
        
        return "emptyView";
    }

    @Override
    public String readTimeline(String username, Model model) {
        
        User user = new User(username);
        
        List<Post> timeline = service.readTimeline(user);
        
        model.addAttribute("timeline", timeline);
        
        return "timeline";
    }
}