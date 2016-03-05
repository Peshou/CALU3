package com.javelin.service.transferObjects;


import javax.validation.constraints.NotNull;

public class BlogPostTransferObject {
    @NotNull
    private String name;
    @NotNull
    private String text;

    @NotNull
    private Long blogId;
}
