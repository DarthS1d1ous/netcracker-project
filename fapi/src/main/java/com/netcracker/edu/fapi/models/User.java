package com.netcracker.edu.fapi.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

public class User {
    private long id;
    @NotEmpty
    @Pattern(regexp = "^(?!.*@.*@.*$)(?!.*@.*--.*\\..*$)(?!.*@.*-\\..*$)(?!.*@.*-$)(.*@.+(\\..{1,11})?)$")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$")
    private String password;
    @NotEmpty
    @Pattern(regexp = "(^[A-Z][a-z]{1,20}$)|(^[А-Я][а-я]{1,20}$)")
    private String name;
    @NotNull
    private Timestamp timeRegistration;
    @NotEmpty
    @Pattern(regexp = "(^[A-Z][a-z]{1,20}$)|(^[А-Я][а-я]{1,20}$)")
    private String surname;
    @NotEmpty
    @Pattern(regexp = "(^(?!\\+.*\\(.*\\).*--.*$)(?!\\+.*\\(.*\\).*-$)(\\+[0-9]{1,7}([-0-9]{0,8})?([0-9]?)?)$)|(^[0-9]{1,4}$)")
    private String phone;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z][0-9A-Za-z]{1,15}$")
    private String username;
    private String mainPhoto;

    @NotNull
    private Role role;

//    private Set<Post> posts;
//
//    private Set<Post> postLikes;
//
//    private Set<Comment> comments;

    private Set<User> subscribers;

    private Set<User> subscriptions;

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

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

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

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setUsers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
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
                Objects.equals(mainPhoto, user.mainPhoto) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, timeRegistration, surname, phone, username, mainPhoto, role);
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
}
