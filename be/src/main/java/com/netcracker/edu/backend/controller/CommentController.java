package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getById(@PathVariable(name = "id") long id) {
        Optional<Comment> comment = commentService.findById(id);
        return comment.isPresent() ? ResponseEntity.ok(comment.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Comment> getAllComments() {
        return commentService.findAllComments();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Comment saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        commentService.deleteComment(id);
    }
}
