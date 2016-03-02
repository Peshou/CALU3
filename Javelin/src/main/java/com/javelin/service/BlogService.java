package com.javelin.service;

import java.util.List;

import com.javelin.domain.Blog;

public interface BlogService {
	Blog findOne(Long id);

	List<Blog> findAll();


}
