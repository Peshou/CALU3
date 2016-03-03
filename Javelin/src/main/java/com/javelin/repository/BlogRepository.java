package com.javelin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javelin.domain.Blog;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByUserUsername(String username);
}
