package com.javelin.service;

import com.javelin.model.Comment;
import com.javelin.service.transferObjects.CommentTransferObject;

public interface CommentService {
    Comment save(CommentTransferObject comment);
}
