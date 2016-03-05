package com.javelin.service.impl;

import com.javelin.model.Blog;
import com.javelin.model.BlogPost;
import com.javelin.model.User;
import com.javelin.repository.BlogPostRepository;
import com.javelin.repository.BlogRepository;
import com.javelin.repository.UserRepository;
import com.javelin.security.SecurityUtils;
import com.javelin.service.BlogPostService;
import com.javelin.service.transferObjects.BlogPostTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
        Collections.sort(allPosts);
        return allPosts;
    }

    @Override
    public List<BlogPost> findByBlogId(Long id) {
        List<BlogPost> posts = blogPostRepository.findByBlogId(id);
        if (posts != null && posts.size() > 0) {
            Collections.sort(posts);
        }
        return posts;
    }

    @Override
    public BlogPost find(Long id, Long postId) {
        BlogPost blogPost = blogPostRepository.findOne(postId);
        blogPost.getComments().size();
        return blogPost;
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

    @Override
    public boolean getAuthor(Long id) {
        if (SecurityUtils.isAuthenticated()) {
            User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
            Blog blog = blogRepository.findOne(id);
            if (user.equals(blog.getUser())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public BlogPost create(BlogPostTransferObject blogPostTransferObject) {
        BlogPost blogPost = new BlogPost();
        blogPost.setName(blogPostTransferObject.getPostName());
        Blog blog = blogRepository.findOne(blogPostTransferObject.getBlogId());
        blogPost.setBlog(blog);
        blogPost.setText(blogPostTransferObject.getPostText());
        if (blogPost.getTimeAdded() == null) {
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            blogPost.setTimeAdded(timestamp);
        }
        return blogPostRepository.save(blogPost);
    }
}
