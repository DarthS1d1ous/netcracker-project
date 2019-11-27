package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Set;

public class Role {
    private long id;
    private String title;
    private Set<User> users;

    public Role(String title) {
        this.title = title;
    }

    public Role() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String role) {
        this.title = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(this.title, role.title) &&
                Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, users);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + title + '\'' +
                '}';
    }
}
