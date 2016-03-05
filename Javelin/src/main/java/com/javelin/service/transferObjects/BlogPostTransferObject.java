package com.javelin.service.transferObjects;


import javax.validation.constraints.NotNull;

public class BlogPostTransferObject {
    @NotNull
    private String postName;
    @NotNull
    private String postText;

    @NotNull
    private Long blogId;

    public BlogPostTransferObject() {
    }

    public BlogPostTransferObject(String postName, String postText, Long blogId) {
        this.postName = postName;
        this.postText = postText;
        this.blogId = blogId;
    }


    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
