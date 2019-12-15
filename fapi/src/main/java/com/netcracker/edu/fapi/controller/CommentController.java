package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.service.CommentService;
import com.netcracker.edu.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.findAllComments();
    }

    @GetMapping(value = "/{id}")
    public Comment getCommentById(@PathVariable(name = "id") long id) {
        return commentService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public Comment saveComment(@Valid @RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable(name = "id") long id) {
        commentService.deleteComment(id);
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public List<Comment> findCommentsByPostId(@PathVariable(name = "postId") long postId){
        return commentService.findCommentsByPostId(postId);
    }
}
