package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Post> getByTitle(@RequestParam(value = "title") String title) {
        Optional<Post> post = postService.findByTitle(title);
        return post.isPresent() ? ResponseEntity.ok(post.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> getById(@PathVariable(name = "id") long id) {
        Optional<Post> post = postService.findById(id);
        return post.isPresent() ? ResponseEntity.ok(post.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "",
            params = {"page", "size", "direction", "properties"},
            method = RequestMethod.GET)
    public Page<Post> getAllPosts(@RequestParam("page") int page, @RequestParam("size") int size,
                                  @RequestParam("direction") Sort.Direction direction, @RequestParam("properties") String properties) {
        return postService.findAllPosts(page, size, direction, properties);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public List<Post> findPostsByUserId(@PathVariable(name = "userId") long userId) {
        return postService.findPostsByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
    }

    @RequestMapping(value = "/{id}/likes", method = RequestMethod.GET)
    public List<User> getAllLikes(@PathVariable(name = "id") long id) {
        return postService.findAllPostLikes(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long getPostsCount() {
        return postService.findPostsCount();
    }

    @RequestMapping(value = "/{postId}/{userId}/like", method = RequestMethod.DELETE)
    public void deleteLike(@PathVariable(name = "postId") long postId, @PathVariable(name = "userId") long userId) {
        postService.deleteLike(postId, userId);
    }

    @RequestMapping(value = "/{postId}/{userId}/like", method = RequestMethod.POST)
    public void saveLike(@PathVariable(name = "postId") long postId, @PathVariable(name = "userId") long userId) {
        postService.saveLike(postId, userId);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List<Post> getPostsByTags(@RequestParam("title") List<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @RequestMapping(value = "/mostByLikes", method = RequestMethod.GET)
    public List<Post> find10MostLikedPosts() {
        return postService.findMostLikedPosts();
    }

    @RequestMapping(value = "/{postId}/tags/{tagId}", method = RequestMethod.POST)
    public void saveTag(@PathVariable(name = "postId") long postId, @PathVariable(name = "tagId") long tagId) {
        postService.saveTag(postId, tagId);
    }

}
