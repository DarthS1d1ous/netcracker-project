package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAllRoles();

    Optional<Role> findByTitle(String title);

    Role saveRole(Role role);

    void deleteRole(long id);
}
