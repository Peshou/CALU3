package com.javelin.web;

import com.javelin.model.Comment;
import com.javelin.service.CommentService;
import com.javelin.service.transferObjects.CommentTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Comment saveComment(@RequestBody @Valid CommentTransferObject comment) {
        return commentService.save(comment);
    }
}
