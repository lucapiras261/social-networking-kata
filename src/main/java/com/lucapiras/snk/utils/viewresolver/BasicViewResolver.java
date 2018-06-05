package com.lucapiras.snk.utils.viewresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class BasicViewResolver implements IViewResolver {

    @Autowired
    protected IView emptyView;
    
    @Autowired
    protected IView postsView;
    
    @Override
    public void resolve(String view, Model model) {
        
        if (0 == view.compareToIgnoreCase("empty")) {
            emptyView.show(model);
        } else if (0 == view.compareToIgnoreCase("posts")) {
            postsView.show(model);
        }
    }
    
}