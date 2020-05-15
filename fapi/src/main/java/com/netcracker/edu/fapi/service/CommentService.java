package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllComments();

    Comment saveComment(Comment Comment);

    void deleteComment(long id);

    Comment findById(long id);

    List<Comment> findCommentsByPostId(long postId);
}
