package com.lucapiras.snk.post;

import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
public interface IPostController {
    
    public String save(String username, String content, Model model);
    
    public String readTimeline(String username, Model model);
    
    public String readWall(String username, Model model);
    
}