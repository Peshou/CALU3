package com.javelin.service;

import java.util.List;

import com.javelin.model.Blog;
import com.javelin.service.transferObjects.BlogTransferObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
	Blog findOne(Long id);

	List<Blog> findAll();

	void delete(Long id);

	Blog save(BlogTransferObject blogTransferObject);

	List<Blog> findByUserUsername();

	Page<Blog> findAll(Pageable pageable);

	void update(Blog blog);
}
