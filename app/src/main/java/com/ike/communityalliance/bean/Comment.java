package com.ike.communityalliance.bean;

import java.util.List;

/**
 * Created by Min on 2017/3/27.
 */

public class Comment {
  private List<CommentBean> comments;

    public Comment(List<CommentBean> comments) {
        this.comments = comments;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }
}
