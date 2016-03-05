package com.javelin.service.transferObjects;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class BlogTransferObject {
//    Long id;
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

    public BlogTransferObject(Long id, String blogName, String blogDescription) {
      //  this.id = id;
        this.blogName = blogName;
        this.blogDescription = blogDescription;
    }

    public BlogTransferObject() {
    }

    }

