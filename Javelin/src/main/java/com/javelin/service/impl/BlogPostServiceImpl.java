package com.javelin.service.impl;

import com.javelin.domain.Blog;
import com.javelin.domain.BlogPost;
import com.javelin.domain.User;
import com.javelin.repository.BlogPostRepository;
import com.javelin.repository.BlogRepository;
import com.javelin.repository.UserRepository;
import com.javelin.security.SecurityUtils;
import com.javelin.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Intel on 01.03.2016.
 */
@Service
@Transactional
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public BlogPost findOne(Long id) {
        return blogPostRepository.findOne(id);
    }

    @Override
    public List<BlogPost> findAll() {
        List<BlogPost> allPosts = blogPostRepository.findAll();
        List<BlogPost> publicPosts = new ArrayList<>();
        for (BlogPost post : allPosts) {
            if (post.isPublic_post()) {
                publicPosts.add(post);
            }
        }
        return publicPosts;
    }

    @Override
    public List<BlogPost> findByBlogId(Long id) {
        Blog blog = blogRepository.findOne(id);
        List<BlogPost> posts = blogPostRepository.findByBlogId(id);
        List<BlogPost> finalPosts = new ArrayList<>();
        User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
        if (user != null) {
            if (user.equals(blog.getUser())) {
                return posts;
            }
        }
        for (BlogPost post : posts) {
            if (post.isPublic_post()) {
                finalPosts.add(post);
            }
        }
        return finalPosts;
    }

    @Override
    public BlogPost find(Long id, Long postId) {
        Blog blog = blogRepository.findOne(id);
        for (BlogPost blogPost : blog.getBlogPosts()) {
            if (Long.compare(blogPost.getId(), postId) == 0) {
                User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
                if (user != null) {
                    if (user.equals(blog.getUser())) {
                        return blogPost;

                    }
                } else {
                    if (blogPost.isPublic_post()) {
                        return blogPost;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long postId) {
        try {
            blogPostRepository.delete(postId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void save(BlogPost blogPost) {
        if (blogPost.getTimeAdded() == null) {
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            blogPost.setTimeAdded(timestamp);
        }
        blogPostRepository.save(blogPost);
    }
}
