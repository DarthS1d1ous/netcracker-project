package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.restPage.RestPageImpl;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PostService {
    RestPageImpl findAllPosts(int page, int size, Sort.Direction direction, String properties);

    Post findByTitle(String title);

    Post findById(long id);

    Post savePost(Post post);

    void deletePost(long id);

    List<User> findAllPostLikes(long id);

    Long  findPostsCount();

    void saveLike(long postId, long userId);

    void deleteLike(long postId, long userId);

    void saveTag(long postId, long tagId);
}
