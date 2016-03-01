package com.javelin.service;

import com.javelin.domain.BlogPost;

import java.util.List;

/**
 * Created by Intel on 01.03.2016.
 */
public interface BlogPostService {
    BlogPost findOneById(Long id);

    List<BlogPost> findAll();
}
