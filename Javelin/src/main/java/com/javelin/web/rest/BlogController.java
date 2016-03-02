package com.javelin.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javelin.domain.Blog;
import com.javelin.domain.BlogPost;
import com.javelin.service.BlogPostService;
import com.javelin.service.BlogService;

@RestController
@RequestMapping("/api")
public class BlogController {

	@Autowired
	BlogService blogService;

	@Autowired
	BlogPostService blogPostService;
	
	@RequestMapping(value = "/blog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Blog> getAllBlogPosts() {
		return blogService.findAll();
	}
	@RequestMapping(value = "/blog/{id}/{post_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BlogPost getBlogPostByUserID(@PathVariable Long id, @PathVariable Long post_id) {
		Blog blog = blogService.findOne(id);
		List<BlogPost> blogPosts = blog.getBlogPosts();
		for (BlogPost bp : blogPosts) {
			if(Long.compare(bp.getId(),post_id)==0)
				return bp;
		}
		return null;
	}
	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Blog getBlogPostByUserID(@PathVariable Long id) {
		Blog blog = blogService.findOne(id);
		blog.getBlogPosts().size();
		return blog;
	}
	
}
