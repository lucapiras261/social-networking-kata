package com.lucapiras.snk.utils.viewresolver;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class EmptyView implements IView {

    @Override
    public void show(Model model) {
        
    }
    
}