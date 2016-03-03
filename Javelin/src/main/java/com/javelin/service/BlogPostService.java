package com.javelin.service;

import com.javelin.domain.BlogPost;

import java.util.List;

/**
 * Created by Intel on 01.03.2016.
 */
public interface BlogPostService {
    BlogPost findOne(Long id);

    List<BlogPost> findAll();

    List<BlogPost> findByBlogId(Long id);

    BlogPost find(Long id, Long postId);

    boolean delete(Long postId);

    void save(BlogPost blogPost);
}
