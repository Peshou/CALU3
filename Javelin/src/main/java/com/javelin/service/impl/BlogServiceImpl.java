package com.javelin.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javelin.domain.Blog;
import com.javelin.repository.BlogRepository;
import com.javelin.service.BlogService;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogRepository blogRepository;

	@Override
	public Blog findOne(Long id) {
		return blogRepository.findOne(id);
	}

	@Override
	public List<Blog> findAll() {
		return blogRepository.findAll();
	}

}
