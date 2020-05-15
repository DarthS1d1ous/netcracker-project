package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Role;
import com.netcracker.edu.fapi.service.RoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("customRoleDetailsService")
public class RoleServiceImpl implements RoleService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Role> findAllRoles() {
        RestTemplate restTemplate = new RestTemplate();
        Role[] role = restTemplate.getForObject(backendServerUrl + "/api/roles", Role[].class);
        return role == null ? Collections.emptyList() : Arrays.asList(role);
    }

    @Override
    public Role findByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/roles/role?title=" + title, Role.class);
    }

    @Override
    public Role saveRole(Role role) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/roles", role, Role.class).getBody();
    }

    @Override
    public void deleteRole(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/roles/" + id);
    }
}
