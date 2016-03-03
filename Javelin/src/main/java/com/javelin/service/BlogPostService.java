package com.javelin.service;

import com.javelin.domain.BlogPost;

import java.util.List;

public interface BlogPostService {
    BlogPost findOne(Long id);

    List<BlogPost> findAll();

    List<BlogPost> findByBlogId(Long id);

    BlogPost find(Long id, Long postId);

    boolean delete(Long postId);

    void save(BlogPost blogPost);
}
