package com.javelin.repository;

import com.javelin.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
    List<BlogPost> findByBlogId(Long id);
}
