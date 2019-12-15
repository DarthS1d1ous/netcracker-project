package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Tag;
import com.netcracker.edu.fapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.findAllTags();
    }

    @GetMapping("/tag")
    public Tag getTagByTitle(@RequestParam(value = "title") String title) {
        return tagService.findByTitle(title);
    }

    @GetMapping(value = "/{id}")
    public Tag getTagById(@PathVariable(name = "id") long id) {
        return tagService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable(name = "id") long id) {
        tagService.deleteTag(id);
    }

}
