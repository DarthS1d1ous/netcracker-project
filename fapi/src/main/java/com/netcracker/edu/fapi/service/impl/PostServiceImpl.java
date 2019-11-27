package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.restPage.RestPageImpl;
import com.netcracker.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("customPostDetailsService")
public class PostServiceImpl implements PostService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public RestPageImpl findAllPosts(int page, int size, Sort.Direction direction, String properties) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/posts?page=" + page + "&size=" + size + "&direction=" + direction + "&properties=" + properties, RestPageImpl.class);
    }

    @Override
    public Post findByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/posts?title=" + title, Post.class);
    }

    @Override
    public Post findById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/posts/" + id, Post.class);
    }

    @Override
    public Post savePost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/posts", post, Post.class).getBody();
    }

    @Override
    public void deletePost(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/posts/" + id);
    }

    @Override
    public List<User> findAllPostLikes(long id) {
        return null;
    }

    @Override
    public Long findPostsCount() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/posts/count", Long.class);
    }

    @Override
    public void saveLike(long postId, long userId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/posts/" + postId + "/" + userId + "/like", true, boolean.class).getBody();
    }

    @Override
    public void deleteLike(long postId, long userId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/posts/" + postId + "/" + userId + "/like");
    }

    @Override
    public void saveTag(long postId, long tagId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/posts/" + postId + "/tags/" + tagId, true, boolean.class).getBody();
    }
}
