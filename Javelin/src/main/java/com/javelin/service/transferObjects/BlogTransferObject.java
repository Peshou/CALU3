package com.javelin.service.transferObjects;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class BlogTransferObject {
    @NotNull
    private String blogName;
    @NotNull
    private String blogDescription;

    public String getBlogName() {
        return blogName;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public BlogTransferObject(String blogName, String blogDescription) {
        this.blogName = blogName;
        this.blogDescription = blogDescription;
    }

    public BlogTransferObject() {
    }

    }

