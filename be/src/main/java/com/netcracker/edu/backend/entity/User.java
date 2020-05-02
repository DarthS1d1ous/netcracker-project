package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", schema = "backend")
public class User implements Serializable {

    private long id;
    private String email;
    private String password;
    private String name;
    private Timestamp timeRegistration;
    private String surname;
    private String phone;
    private String username;
    private String mainPhoto;


    private Role role;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Post> posts;
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "userLikes")
//    private Set<Post> postLikes;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private Set<Comment> comments;


    private Set<User> subscribers = new HashSet<>();

    private Set<User> subscriptions = new HashSet<>();

    public User(String email, String password, String name, Timestamp timeRegistration, String surname, String phone, String username, String mainPhoto) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.timeRegistration = timeRegistration;
        this.surname = surname;
        this.phone = phone;
        this.username = username;
        this.mainPhoto = mainPhoto;
    }

    public User() {
    }

    @Column(name = "main_photo")
    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "time_registration")
    public Timestamp getTimeRegistration() {
        return timeRegistration;
    }

    public void setTimeRegistration(Timestamp timeRegistration) {
        this.timeRegistration = timeRegistration;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Set<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(Set<Post> posts) {
//        this.posts = posts;
//    }
//
//    public Set<Post> getPostLikes() {
//        return postLikes;
//    }
//
//    public void setPostLikes(Set<Post> postLikes) {
//        this.postLikes = postLikes;
//    }
//
//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    public Set<User> getSubscribers() {
        if (subscribers != null) {
            return subscribers.stream().map(this::userConverter).collect(Collectors.toSet());
        }
        return null;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "subscription_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getSubscriptions() {
        if (subscriptions != null) {
            return subscriptions.stream().map(this::userConverter).collect(Collectors.toSet());
        }
        return null;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(timeRegistration, user.timeRegistration) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(username, user.username) &&
                Objects.equals(role, user.role) &&
//                Objects.equals(posts, user.posts) &&
//                Objects.equals(postLikes, user.postLikes) &&
//                Objects.equals(comments, user.comments) &&
                Objects.equals(subscribers, user.subscribers) &&
                Objects.equals(subscriptions, user.subscriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, timeRegistration, surname, phone, username, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", timeRegistration=" + timeRegistration +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }

    public User userConverter(User user) {
//        user.setSubscribers(user.getSubscribers().stream().peek(user1 -> {
//            user1.setSubscribers(new HashSet<>());
//            user1.setSubscriptions(new HashSet<>());
//        }).collect(Collectors.toSet()));
//        user.setSubscriptions(user.getSubscriptions().stream().peek(user1 -> {
//            user1.setSubscribers(new HashSet<>());
//            user1.setSubscriptions(new HashSet<>());
//        }).collect(Collectors.toSet()));
//        return user;
        User userDTO = new User();
        userDTO.setId(user.getId());
        return userDTO;
    }
}

