package com.lucapiras.snk.post;

import com.lucapiras.snk.utils.viewresolver.IView;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class PostingViolationView implements IView {

    @Override
    public void show(Model model) {
        System.out.println("\nThis user cannot post, the user does not exist.\n");
    }
}