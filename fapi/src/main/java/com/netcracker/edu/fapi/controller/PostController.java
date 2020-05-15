package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.restPage.RestPageImpl;
import com.netcracker.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "",
            params = {"page", "size", "direction", "properties"},
            method = RequestMethod.GET)
    public RestPageImpl findAllPosts(@RequestParam("page") int page, @RequestParam("size") int size,
                                     @RequestParam("direction") String direction, @RequestParam("properties") String properties) {
        if (direction.toLowerCase().equals("asc")) {
            return postService.findAllPosts(page, size, Sort.Direction.ASC, properties);
        } else {
            return postService.findAllPosts(page, size, Sort.Direction.DESC, properties);
        }

    }

    @GetMapping("")
    public Post findPostByTitle(@RequestParam(value = "title") String title) {
        return postService.findByTitle(title);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    public List<Post> findPostsByUserId(@PathVariable(name = "userId") long userId) {
        return postService.findPostsByUserId(userId);
    }

    @RequestMapping(value = "/mostByLikes", method = RequestMethod.GET, produces = "application/json")
    public List<Post> find10MostLikedPosts() {
        return postService.findMostLikedPosts();
    }


    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List<Post> findPostsByTags(@RequestParam(name = "title") List<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable(name = "id") long id) {
        return postService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public Post savePost(@Valid @RequestBody Post user) {
        return postService.savePost(user);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long findPostsCount() {
        return postService.findPostsCount();
    }

    @RequestMapping(value = "/{postId}/{userId}/like", method = RequestMethod.POST)
    public void saveLike(@PathVariable(name = "postId") long postId, @PathVariable(name = "userId") long userId) {
        postService.saveLike(postId, userId);
    }

    @RequestMapping(value = "/{postId}/{userId}/like", method = RequestMethod.DELETE)
    public void deleteLike(@PathVariable(name = "postId") long postId, @PathVariable(name = "userId") long userId) {
        postService.deleteLike(postId, userId);
    }

    @RequestMapping(value = "/{postId}/tags/{tagId}", method = RequestMethod.POST)
    public void saveTag(@PathVariable(name = "postId") long postId, @PathVariable(name = "tagId") long tagId) {
        postService.saveTag(postId, tagId);
    }

}
