package com.javelin.web.rest;

import com.javelin.domain.Blog;
import com.javelin.domain.BlogPost;
import com.javelin.search.FTSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 3/2/2016.
 */
@RestController
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private FTSearch ftSearch;

    @RequestMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<?> search(String query) {
        List searchResults = new ArrayList<>();
        try {
            searchResults.addAll(ftSearch.search(Blog.class, query, "name", "description"));
            searchResults.addAll(ftSearch.search(BlogPost.class, query, "name", "text"));
        }
        catch(Exception ex){

        }
        return searchResults;
    }


}
