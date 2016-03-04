package com.javelin.service.impl;

import com.javelin.model.Comment;
import com.javelin.repository.CommentRepository;
import com.javelin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        if (comment.getCommentDate() == null) {
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            comment.setCommentDate(timestamp);
        }
        return commentRepository.save(comment);
    }
}
