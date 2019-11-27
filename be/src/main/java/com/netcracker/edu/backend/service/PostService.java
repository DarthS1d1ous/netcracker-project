package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Page<Post> findAllPosts(int page, int size, Sort.Direction direction, String properties);

    Optional<Post> findByTitle(String title);

    Optional<Post> findById(long id);

    Post savePost(Post post);

    void deletePost(long id);

    List<User> findAllPostLikes(long id);

    Long  findPostsCount();

    void saveLike(long postId, long userId);
    void deleteLike(long postId, long userId);

    void saveTag(long postId, long tagId);
}
