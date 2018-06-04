package com.lucapiras.snk.utils.dispatcher;

import com.lucapiras.snk.exception.ExitException;
import com.lucapiras.snk.exception.UnknownRequestException;
import com.lucapiras.snk.following.IFollowingController;
import com.lucapiras.snk.model.BasicModel;
import com.lucapiras.snk.post.IPostController;
import com.lucapiras.snk.user.IUserController;
import com.lucapiras.snk.utils.string.AdvancedStringTokenizer;
import com.lucapiras.snk.utils.viewresolver.IViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
@Component
public class BasicDispatcher implements IDispatcher {

    @Autowired
    protected IUserController userController;
    
    @Autowired
    protected IPostController postController;
    
    @Autowired
    protected IFollowingController followingController;
    
    @Autowired
    protected IViewResolver viewResolver;
    
    @Override
    public void dispatch(String request) throws ExitException, UnknownRequestException {
        String returnView = "";
        Model model = new BasicModel();
                
        this.checkExtremeCases(request);
        
        if (0 == request.compareToIgnoreCase("wall")) {//MY WALL CASE

            System.out.println("The 'My Wall' function will be implemented soon.");

        } else {
            String delim = " ";
            AdvancedStringTokenizer st = new AdvancedStringTokenizer(request, delim);
            String firstToken = st.nextToken();

            if (!st.hasMoreElements()) {//SHOW TIMELINE OF A USER CASE

                returnView = postController.readTimeline(firstToken, model);

            } else {
                
                String secondToken = st.nextToken();
                
                if (0 == firstToken.compareToIgnoreCase("save")) {
                    
                    returnView = userController.save(secondToken, model);
                    
                } else if (0 == secondToken.compareToIgnoreCase("->") && 
                           st.hasMoreElements()) {//POST CASE

                    returnView = postController.save(firstToken, 
                                                     st.extractRemainingText(delim),
                                                     model);
                    
                } else if (0 == secondToken.compareToIgnoreCase("follows") && 
                           st.hasMoreElements()) {//FOLLOWS CASE

                    returnView = followingController.save(firstToken, 
                                                          st.nextToken(),
                                                          model);

                } else {

                    throw new UnknownRequestException();
                }
            }
        }
        
        viewResolver.resolve(returnView, model);
    }

    protected void checkExtremeCases(String request) throws ExitException, UnknownRequestException {
        if (0 == request.compareToIgnoreCase("exit")) {//EXIT CASE
            throw new ExitException();
        }
        
        if (request.isEmpty()) {//EMPTY CASE
            throw new UnknownRequestException();
        }
    }
}