package com.lucapiras.snk.post;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Luca Piras
 */
@Entity
public class Post implements Serializable {

    @EmbeddedId
    protected PostId postId;
    
    @Column(nullable = false)
    protected String content;

    protected Post() {}

    public Post(PostId postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format(
                "Post[postId='%s', content='%s']", 
                postId, content);
    }

    public PostId getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }
}