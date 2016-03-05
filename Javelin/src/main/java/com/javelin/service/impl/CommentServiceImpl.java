package com.javelin.service.impl;

import com.javelin.model.BlogPost;
import com.javelin.model.Comment;
import com.javelin.model.User;
import com.javelin.repository.BlogPostRepository;
import com.javelin.repository.BlogRepository;
import com.javelin.repository.CommentRepository;
import com.javelin.repository.UserRepository;
import com.javelin.service.CommentService;
import com.javelin.service.transferObjects.CommentTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
@Autowired
private BlogPostRepository blogPostRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment save(CommentTransferObject comment) {
        Comment com = new Comment();
        BlogPost blogPost = blogPostRepository.findOne(comment.getBlogPostId());
        User user = userRepository.findOneByUsername(comment.getUsername());
        com.setBlogPostId(blogPost);
        com.setUserId(user);
        com.setCommentText(comment.getCommentText());
        if (com.getCommentDate() == null) {
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            com.setCommentDate(timestamp);
        }
        return commentRepository.save(com);
    }
}
