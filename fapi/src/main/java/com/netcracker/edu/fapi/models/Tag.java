package com.netcracker.edu.fapi.models;

import java.util.Objects;

public class Tag {
    private long id;
    private String title;

    public Tag(String title) {
        this.title = title;
    }

    public Tag() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id &&
                Objects.equals(title, tag.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
