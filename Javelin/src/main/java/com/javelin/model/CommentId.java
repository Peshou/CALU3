package com.javelin.model;


import java.io.Serializable;

public class CommentId implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long blogPostId;
    private Long commentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentId)) return false;

        CommentId commentId1 = (CommentId) o;

        if (!getUserId().equals(commentId1.getUserId())) return false;
        if (!getBlogPostId().equals(commentId1.getBlogPostId())) return false;
        return getCommentId().equals(commentId1.getCommentId());

    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getBlogPostId().hashCode();
        result = 31 * result + getCommentId().hashCode();
        return result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(Long blogPostId) {
        this.blogPostId = blogPostId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
