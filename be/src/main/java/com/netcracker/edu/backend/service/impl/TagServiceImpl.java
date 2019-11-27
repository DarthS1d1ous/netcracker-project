package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Tag;
import com.netcracker.edu.backend.repository.TagRepository;
import com.netcracker.edu.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAllTags() {
        return (List<Tag>) tagRepository.findAll();
    }

    @Override
    public Optional<Tag> findByTitle(String title) {
        return tagRepository.findByTitle(title);
    }

    @Override
    public Tag saveTag(Tag Tag) {
        return tagRepository.save(Tag);
    }

    @Override
    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }
}
