package com.javelin.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.javelin.model.User;
import com.javelin.repository.UserRepository;
import com.javelin.security.SecurityUtils;
import com.javelin.service.transferObjects.BlogTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javelin.model.Blog;
import com.javelin.repository.BlogRepository;
import com.javelin.service.BlogService;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Blog findOne(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public Blog save(BlogTransferObject blogTransferObject) {
        Blog blog = new Blog();
        blog.setName(blogTransferObject.getBlogName());
        blog.setDescription(blogTransferObject.getBlogDescription());
            User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUser().getUsername());
            blog.setUser(user);
            return blogRepository.save(blog);
    }

    @Override
    public List<Blog> findByUserUsername() {
        return blogRepository.findByUserUsername(SecurityUtils.getCurrentUser().getUsername());
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public void update(Blog blog) {
        blogRepository.save(blog);
    }


}
