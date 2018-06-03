package com.lucapiras.snk.utils.viewresolver;

import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
public interface IViewResolver {
    
    public void resolve(String view, Model model);
}