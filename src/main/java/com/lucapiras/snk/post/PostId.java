package com.lucapiras.snk.post;

import com.lucapiras.snk.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Luca Piras
 */
@Embeddable
public class PostId implements Serializable {

    @ManyToOne
    protected User postOwner;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date postTimestamp;

    protected PostId() {
    }

    public PostId(User postOwner) {
        this.postOwner = postOwner;
        this.postTimestamp = new Date();
    }

    @Override
    public String toString() {
        return String.format("PostId[postOwner='%s', postTimestamp='%s']",
                postOwner, postTimestamp);
    }

    public User getPostOwner() {
        return postOwner;
    }

    public Date getPostTimestamp() {
        return postTimestamp;
    }
}