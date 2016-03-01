package com.javelin.web.rest;

import com.javelin.domain.BlogPost;
import com.javelin.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Intel on 01.03.2016.
 */
@RestController
@RequestMapping("/api")
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;

    @RequestMapping(value = "/blogposts",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogPost> getAllBlogPosts(){
        return blogPostService.findAll();
    }
}
