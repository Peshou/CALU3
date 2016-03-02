package com.javelin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javelin.domain.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
