package com.javelin.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@IdClass(CommentId.class)
public class Comment implements Serializable, Comparable<Comment> {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;


    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "blogPostId", referencedColumnName = "id")
    private BlogPost blogPostId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "commentText", nullable = false, length = 4000)
    private String commentText;

    @Column(name = "commentDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public BlogPost getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(BlogPost blogPostId) {
        this.blogPostId = blogPostId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (!getUserId().equals(comment.getUserId())) return false;
        if (!getBlogPostId().equals(comment.getBlogPostId())) return false;
        if (!getCommentId().equals(comment.getCommentId())) return false;
        if (getCommentText() != null ? !getCommentText().equals(comment.getCommentText()) : comment.getCommentText() != null)
            return false;
        return getCommentDate() != null ? getCommentDate().equals(comment.getCommentDate()) : comment.getCommentDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getBlogPostId().hashCode();
        result = 31 * result + getCommentId().hashCode();
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Comment comment) {
        return this.commentDate.compareTo(comment.commentDate);
    }
}
