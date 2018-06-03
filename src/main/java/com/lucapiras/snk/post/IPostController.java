package com.lucapiras.snk.post;

import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
public interface IPostController {
    
    public String save(String username, String content, Model model);
}