package com.netcracker.edu.backend.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts", schema = "backend")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "photo_path")
    private String photoPath;
    private String description;
    @Column(name = "time_creation")
    private Timestamp timeCreation;
    private String title;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "posts_has_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> postTags;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userLikes;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
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

//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }

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
                Objects.equals(user, post.user) &&
                Objects.equals(postTags, post.postTags) &&
                Objects.equals(userLikes, post.userLikes);
//                Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoPath, description, timeCreation, title, user, postTags, userLikes);
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
