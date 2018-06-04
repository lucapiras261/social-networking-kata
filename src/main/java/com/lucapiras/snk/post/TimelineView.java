package com.lucapiras.snk.post;

import com.lucapiras.snk.utils.time.ITimeAgoFormatter;
import com.lucapiras.snk.utils.viewresolver.IView;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class TimelineView implements IView {
    
    @Autowired
    ITimeAgoFormatter timeAgoFormatter;
    
    @Override
    public void show(Model model) {
        
        List<Post> timeline = (List<Post>) model.asMap().get("timeline");
        
        Date now = new Date();
        for (Post post : timeline) {
            Date timestamp = post.getPostId().getPostTimestamp();
            
            StringBuilder sb = new StringBuilder(post.getContent());
            sb.append(" ");
            sb.append(timeAgoFormatter.formatTimeAgo(now, timestamp));
            
            System.out.println(sb.toString());
        }
    }
    
}