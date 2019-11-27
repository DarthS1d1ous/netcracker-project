package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> findAllTags();

    Optional<Tag> findByTitle(String title);

    Tag saveTag(Tag Tag);

    void deleteTag(long id);
}
