package com.javelin.service;

import com.javelin.model.BlogPost;
import com.javelin.service.transferObjects.BlogPostTransferObject;
import com.javelin.service.transferObjects.BlogTransferObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogPostService {
    BlogPost findOne(Long id);

    List<BlogPost> findAll();

    List<BlogPost> findByBlogId(Long id);

    BlogPost find(Long id, Long postId);

    boolean delete(Long postId);

    void save(Long id, Long postId, BlogPostTransferObject blogPost);

    boolean getAuthor(Long id);

    BlogPost create(BlogPostTransferObject blogPostTransferObject);
}
