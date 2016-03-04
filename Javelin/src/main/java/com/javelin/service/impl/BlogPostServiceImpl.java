package com.javelin.service.impl;

import com.javelin.model.Blog;
import com.javelin.model.BlogPost;
import com.javelin.model.User;
import com.javelin.repository.BlogPostRepository;
import com.javelin.repository.BlogRepository;
import com.javelin.repository.UserRepository;
import com.javelin.security.SecurityUtils;
import com.javelin.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        return allPosts;
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
        return null;
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
//TODO: SMENI SVE SO BLOGPOSTOVITE BLA BLA
                    }
                }
                else {
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
