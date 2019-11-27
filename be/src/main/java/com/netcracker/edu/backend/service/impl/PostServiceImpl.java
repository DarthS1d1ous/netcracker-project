package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Post> findAllPosts(int page, int size, Sort.Direction direction, String properties) {
        return postRepository.findAll(PageRequest.of(page, size, Sort.by(direction, properties)));
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    @Override
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<User> findAllPostLikes(long id) {
        return userRepository.findAllPostLikes(id);
    }

    @Override
    public Long findPostsCount() {
        return postRepository.findPostsCount();
    }

    @Override
    public void saveLike(long postId, long userId) {
        postRepository.saveLike(postId, userId);
    }

    @Override
    public void deleteLike(long postId, long userId) {
        postRepository.deleteLike(postId, userId);
    }

    @Override
    public void saveTag(long postId, long tagId) {
        postRepository.saveTag(postId, tagId);
    }

}
