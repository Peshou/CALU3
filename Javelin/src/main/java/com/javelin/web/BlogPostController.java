package com.javelin.web;

import com.javelin.model.BlogPost;
import com.javelin.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/blogs",produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;


    @RequestMapping(value = "/posts/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostService.findAll();
        if (blogPosts != null && blogPosts.size() > 0) {
            return ResponseEntity.ok(blogPosts);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPostsForBlog(@PathVariable Long id) {
        List<BlogPost> blogPosts = blogPostService.findByBlogId(id);
        if (blogPosts != null && blogPosts.size() > 0) {
            return ResponseEntity.ok(blogPosts);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBlogPostFromBlog(@PathVariable Long id, @PathVariable Long postId) {
        BlogPost blogPost = blogPostService.find(id, postId);
        return ResponseEntity.ok(blogPost);
    }

    @RequestMapping(value = "/{id}/posts/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBlogPostFromBlog(@PathVariable Long id, @PathVariable Long postId) {
        BlogPost blogPost = blogPostService.find(id, postId);
        boolean success = blogPostService.delete(postId);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}/posts/{postId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBlogPostFromBlog(@PathVariable Long id, @PathVariable Long postId,@Valid  BlogPost blogPost) {
        blogPostService.save(blogPost);
        return ResponseEntity.ok().build();
    }
}
