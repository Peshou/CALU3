package com.javelin.service.impl;

import com.javelin.domain.BlogPost;
import com.javelin.repository.BlogPostRepository;
import com.javelin.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intel on 01.03.2016.
 */
@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    @Override
    public BlogPost findOneById(Long id) {
        return blogPostRepository.findOne(id);
    }

    @Override
    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }
}
