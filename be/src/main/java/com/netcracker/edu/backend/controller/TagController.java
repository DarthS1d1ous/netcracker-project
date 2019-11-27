package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Tag;
import com.netcracker.edu.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Tag> getByTitle(@RequestParam(value = "title") String title) {
        Optional<Tag> tag = tagService.findByTitle(title);
        return tag.isPresent() ? ResponseEntity.ok(tag.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Tag> getAllTags() {
        return tagService.findAllTags();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        tagService.deleteTag(id);
    }
}
