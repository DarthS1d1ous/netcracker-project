package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.restPage.RestPageImpl;
import javafx.geometry.Pos;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PostService {
    RestPageImpl findAllPosts(int page, int size, Sort.Direction direction, String properties);

    Post findByTitle(String title);

    Post findById(long id);

    Post savePost(Post post);

    Post createPost(Post post);

    void deletePost(long id);

    List<Post> findMostLikedPosts();

    List<User> findAllPostLikes(long id);

    Long  findPostsCount();

    List<Post> findPostsByTags(List<String> tags);

    List<Post> findPostsByUserId(long userId);

    void saveLike(long postId, long userId);

    void deleteLike(long postId, long userId);

    void saveTag(long postId, long tagId);
}
