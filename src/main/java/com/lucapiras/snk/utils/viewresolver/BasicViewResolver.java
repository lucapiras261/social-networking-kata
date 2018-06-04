package com.lucapiras.snk.utils.viewresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class BasicViewResolver implements IViewResolver {

    @Autowired
    protected IView emptyView;
    
    @Autowired
    protected IView timelineView;
    
    @Override
    public void resolve(String view, Model model) {
        
        if (0 == view.compareToIgnoreCase("emptyView")) {
            emptyView.show(model);
        } else if (0 == view.compareToIgnoreCase("timeline")) {
            timelineView.show(model);
        }
    }
    
}