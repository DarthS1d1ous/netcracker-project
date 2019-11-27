package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comment;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAllComments();
    Optional<Comment> findById(long id);
    Comment saveComment(Comment Comment);
    void deleteComment(long id);
}
