package com.lucapiras.snk.post;

import com.lucapiras.snk.utils.viewresolver.IView;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class TimelineView implements IView {

    @Override
    public void show(Model model) {
        
        List<Post> timeline = (List<Post>) model.asMap().get("timeline");
        
        for (Post post : timeline) {
            System.out.println(post.getContent());
        }
    }
    
}