package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    Role findByTitle(String title);

    Role saveRole(Role Role);

    void deleteRole(long id);
}
