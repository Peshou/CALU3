package com.javelin.repository;

import com.javelin.model.Comment;
import com.javelin.model.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
