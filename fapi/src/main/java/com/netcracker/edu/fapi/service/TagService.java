package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();

    Tag findByTitle(String title);

    Tag saveTag(Tag Tag);

    void deleteTag(long id);

    Tag findById(long id);
}
