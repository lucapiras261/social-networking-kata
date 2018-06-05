package com.lucapiras.snk.following;

import com.lucapiras.snk.utils.viewresolver.IView;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FollowingViolationView implements IView {

    @Override
    public void show(Model model) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nThis following cannot be saved, possible reasons:\n");
        sb.append("- one of the user does not exist;\n");
        sb.append("- both of the users do not exist;\n");
        sb.append("- a user cannot follow herself.\n");
        
        System.out.println(sb.toString());
    }
}