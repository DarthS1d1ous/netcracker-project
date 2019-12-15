package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    private ResponseEntity<Role> getByTitle(@RequestParam(value = "title") String title) {
        Optional<Role> role = roleService.findByTitle(title);
        return role.isPresent() ? ResponseEntity.ok(role.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private List<Role> getAllRoles() {
        return roleService.findAllRoles();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        roleService.deleteRole(id);
    }
}
