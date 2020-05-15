package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Tag;
import com.netcracker.edu.fapi.service.TagService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("customTagDetailsService")
public class TagServiceImpl implements TagService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Tag> findAllTags() {
        RestTemplate restTemplate = new RestTemplate();
        Tag[] tag = restTemplate.getForObject(backendServerUrl + "/api/tags", Tag[].class);
        return tag == null ? Collections.emptyList() : Arrays.asList(tag);
    }

    @Override
    public Tag findByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/tags/tag?title=" + title, Tag.class);
    }

    @Override
    public Tag findById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/tags/" + id, Tag.class);
    }

    @Override
    public Tag saveTag(Tag tag) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/tags", tag, Tag.class).getBody();
    }

    @Override
    public void deleteTag(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/tags/" + id);
    }
}
