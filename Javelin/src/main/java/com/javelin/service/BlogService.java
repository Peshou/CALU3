package com.javelin.service;

import java.util.List;

import com.javelin.model.Blog;

public interface BlogService {
	Blog findOne(Long id);

	List<Blog> findAll();

	void delete(Long id);

	Blog save(Blog blog);

	List<Blog> findByUserUsername();
}
