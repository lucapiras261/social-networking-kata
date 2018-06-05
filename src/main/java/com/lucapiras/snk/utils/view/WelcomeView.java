package com.lucapiras.snk.utils.view;

import com.lucapiras.snk.utils.viewresolver.IView;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class WelcomeView implements IView {

    @Override
    public void show(Model model) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nWelcome to Social Networking Kata\n\n");
        sb.append("Commands allowed: \n\n");
        sb.append("- saving (creating a user): <user name> save\n");
        sb.append("- posting: <user name> -> <message>\n");
        sb.append("- reading: <user name>\n");
        sb.append("- following: <user name> follows <another user>\n");
        sb.append("- wall: <user name> wall\n");
        sb.append("- help (to read again these instructions): welcome\n");
        sb.append("- exit: exit\n");
        
        System.out.println(sb);
    }
}