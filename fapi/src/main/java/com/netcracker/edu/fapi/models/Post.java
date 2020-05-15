package com.netcracker.edu.fapi.models;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

public class Post {
    private long id;
    @NotEmpty(message = "Photo is mandatory")
    private String photoPath;
    @NotNull
    private String description;
    @NotNull(message = "Time creation is mandatory")
    private Timestamp timeCreation;
    @NotNull
    private String title;


    @NotNull(message = "User is mandatory")
    private User user;
    private Set<Tag> postTags;
    private Set<User> userLikes;
    private Set<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(Timestamp timeCreation) {
        this.timeCreation = timeCreation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getPostTags() {
        return postTags;
    }

    public void setPostTags(Set<Tag> postTags) {
        this.postTags = postTags;
    }

    public Set<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Set<User> userLikes) {
        this.userLikes = userLikes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Post(String photoPath, String description, Timestamp timeCreation, String title, User user) {
        this.photoPath = photoPath;
        this.description = description;
        this.timeCreation = timeCreation;
        this.title = title;
        this.user = user;
    }

    public Post() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(photoPath, post.photoPath) &&
                Objects.equals(description, post.description) &&
                Objects.equals(timeCreation, post.timeCreation) &&
                Objects.equals(title, post.title) &&
                Objects.equals(postTags, post.postTags) &&
                Objects.equals(userLikes, post.userLikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoPath, description, timeCreation, title, postTags, userLikes);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", photoPath='" + photoPath + '\'' +
                ", description='" + description + '\'' +
                ", timeCreation=" + timeCreation +
                ", title='" + title + '\'' +
                '}';
    }
}
