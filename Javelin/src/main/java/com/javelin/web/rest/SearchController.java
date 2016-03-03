package com.javelin.web.rest;

import com.javelin.domain.Blog;
import com.javelin.domain.BlogPost;
import com.javelin.search.FTSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private FTSearch ftSearch;

    @RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<?> search(@RequestBody String searchInput) {
        List<Blog> searchResults = new ArrayList<>();
        try {
            searchResults.addAll(ftSearch.search(searchInput));
          //  searchResults.addAll(ftSearch.search(BlogPost.class, searchInput, "name", "text"));
        } catch (Exception ex) {

        }
        return searchResults;
    }
}
