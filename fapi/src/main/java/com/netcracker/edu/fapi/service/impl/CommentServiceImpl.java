package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("customCommentDetailsService")
public class CommentServiceImpl implements CommentService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Comment> findAllComments() {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] comment = restTemplate.getForObject(backendServerUrl+"/api/comments", Comment[].class);
        return comment == null ? Collections.emptyList() : Arrays.asList(comment);
    }
    

    @Override
    public Comment findById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/comments/" + id, Comment.class);
    }

    @Override
    public List<Comment> findCommentsByPostId(long postId){
        RestTemplate restTemplate = new RestTemplate();
        Comment[] comment = restTemplate.getForObject(backendServerUrl+"/api/comments/post/" + postId, Comment[].class);
        return comment == null ? Collections.emptyList() : Arrays.asList(comment);
    }

    @Override
    public Comment saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/comments", comment, Comment.class).getBody();
    }

    @Override
    public void deleteComment(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/comments/" + id);
    }
}
