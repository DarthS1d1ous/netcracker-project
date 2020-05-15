package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Role;
import com.netcracker.edu.fapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping("/role")
    public Role getRoleByTitle(@RequestParam(value = "title") String title) {
        return roleService.findByTitle(title);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable(name = "id") long id) {
        roleService.deleteRole(id);
    }
}
