package com.lucapiras.snk.post;

import com.lucapiras.snk.utils.time.ITimeAgoFormatter;
import com.lucapiras.snk.utils.viewresolver.IView;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class PostsView implements IView {
    
    @Autowired
    ITimeAgoFormatter timeAgoFormatter;
    
    @Override
    public void show(Model model) {
        
        Boolean showPostOwner = (Boolean) model.asMap().get("showPostOwner");
        List<Post> posts = (List<Post>) model.asMap().get("posts");
        
        Date now = new Date();
        for (Post post : posts) {
            Date timestamp = post.getPostId().getPostTimestamp();
            
            StringBuilder sb = new StringBuilder();
            sb = this.appendPostOwner(showPostOwner, sb, post);
            sb.append(post.getContent());
            sb.append(" ");
            sb.append(timeAgoFormatter.formatTimeAgo(now, timestamp));
            
            System.out.println(sb.toString());
        }
    }

    protected StringBuilder appendPostOwner(Boolean showPostOwner, 
                                            StringBuilder sb, Post post) {
        
        if (showPostOwner) {
            sb.append(post.getPostId().getPostOwner().getUsername());
            sb.append(" - ");
        }
        
        return sb;
    }
    
}