package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.entity.Tag;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.repository.TagRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.CommentService;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

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
    public Post createPost(Post post) {
        saveTags(post.getPostTags());
        post.setPostTags(getPostTagsForSave(post.getPostTags()));
        return postRepository.save(post);
    }

    @Override
    public List<Post> findPostsByUserId(long userId) {
        return postRepository.findPostsByUserIdOrderByTimeCreationDesc(userId);
    }

    @Override
    public List<Post> findMostLikedPosts() {
        return postRepository.findMostLikedPosts();
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
    public List<Post> findPostsByTags(List<String> tagsTitles) {
        tagsTitles = tagsTitles.stream().map(tag -> {
            if (tag.matches("^\\[.+]$")) {
                return tag.substring(1,tag.length()-1);
            }
            if (tag.matches("^\\[.+$")) {
                return tag.substring(1);
            }
            if (tag.matches("^.+]$")) {
                return tag.substring(0, tag.length() - 1);
            }
            return tag;
        }).collect(Collectors.toList());
        return postRepository.findPostsByTags(tagsTitles);
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

    private void saveTags(Set<Tag> tags) {
        List<Tag> allTags = tagRepository.findAll();
        tags.forEach(newTag -> {
            if (allTags.stream().noneMatch(tag -> newTag.getTitle().toLowerCase().equals(tag.getTitle().toLowerCase()))) {
                tagRepository.save(newTag);
            }
        });
    }

    private Set<Tag> getPostTagsForSave(Set<Tag> tags) {
        Set<Tag> newTags = new HashSet<>();
        tags.forEach(tag -> tagRepository.findByTitle(tag.getTitle()).ifPresent(newTags::add));
        return newTags;
    }

}
