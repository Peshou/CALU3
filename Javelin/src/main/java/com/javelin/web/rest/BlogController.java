package com.javelin.web.rest;

import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

import com.javelin.domain.Blog;
import com.javelin.domain.BlogPost;
import com.javelin.service.BlogPostService;
import com.javelin.service.BlogService;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogPostService blogPostService;

    @RequestMapping(value = "/blogs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Blog> getAllBlogPosts() {
        return blogService.findAll();
    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.findOne(id);
    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePostById(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/blogs/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updatePostById(@PathVariable Long id, @Valid Blog blog){
        blogService.save(blog);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value ="/blogs", method = RequestMethod.POST)
    public ResponseEntity<?> saveNewBlog(@RequestParam @Valid Blog blog){
        blog = blogService.save(blog);
     // return ResponseEntity.created()
        return null;
        //TODO: FIX
    }

/*



	@RequestMapping(value = "/blogs/{id}/{post_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BlogPost getBlogPostByUserID(@PathVariable Long id, @PathVariable Long post_id) {
		Blog blog = blogService.findOne(id);
		List<BlogPost> blogPosts = blog.getBlogPosts();
		for (BlogPost bp : blogPosts) {
			if(Long.compare(bp.getId(),post_id)==0)
				return bp;
		}
		return null;
	}
	public BlogPost getBlogByCurrentLoggedInUser(){

	}


	@RequestMapping(value = "/blogs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Blog getBlogByUserID(@PathVariable Long id) {
		Blog blog = blogService.findOne(id);
		blog.getBlogPosts().size();
		return blog;
	}*/

}
