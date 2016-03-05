package com.javelin.web;

import java.util.List;

import com.javelin.service.UserService;
import com.javelin.service.transferObjects.BlogTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javelin.model.Blog;
import com.javelin.service.BlogPostService;
import com.javelin.service.BlogService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogPostService blogPostService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/blogs/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Blog> getAllBlogs(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size", required = false) Integer size) {
        if (page != null && size != null) {
            Pageable pageable = new PageRequest(page, size);
            return blogService.findAll(pageable).getContent();
        }
        return blogService.findAll();
    }


    @RequestMapping(value = "/blogs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Blog> getAllBlogsFromUserLoggedIn() {
        return blogService.findByUserUsername();
    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.findOne(id);
    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBlogById(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBlogById(@PathVariable Long id, @Valid Blog blog) {
        blogService.update(blog);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/blogs", method = RequestMethod.POST)
    public ResponseEntity<?> saveNewBlog(@RequestBody BlogTransferObject blogTransferObject) {

       Blog blogz = blogService.save(blogTransferObject);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
