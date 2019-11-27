package com.netcracker.edu.fapi.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Comment {
    private long id;
    private Timestamp timeCreation;
    private String message;
    private User user;
    private Post post;

    public Comment(Timestamp timeCreation, String message, User user, Post post) {
        this.timeCreation = timeCreation;
        this.message = message;
        this.user = user;
        this.post = post;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(Timestamp timeCreation) {
        this.timeCreation = timeCreation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Objects.equals(timeCreation, comment.timeCreation) &&
                Objects.equals(message, comment.message) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeCreation, message, user, post);
    }
}
