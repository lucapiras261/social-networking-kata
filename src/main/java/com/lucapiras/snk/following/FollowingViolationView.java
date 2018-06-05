package com.lucapiras.snk.following;

import com.lucapiras.snk.utils.viewresolver.IView;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FollowingViolationView implements IView {

    @Override
    public void show(Model model) {
        System.out.println("\nOne of the user (or both) does not exist.\n");
    }
}