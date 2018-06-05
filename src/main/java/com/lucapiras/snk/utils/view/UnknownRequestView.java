package com.lucapiras.snk.utils.view;

import com.lucapiras.snk.utils.viewresolver.IView;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class UnknownRequestView implements IView {

    @Override
    public void show(Model model) {
        System.out.println("\nUnknown command, please write again.\n");
    }
}