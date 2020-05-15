package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "users", schema = "backend")
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String name;
    private Timestamp timeRegistration;
    private String surname;
    private String phone;
    private String username;
    @Length(max = Integer.MAX_VALUE - 1)
    private String mainPhoto;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Post> posts;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "userLikes", cascade = CascadeType.ALL)
    private Set<Post> postLikes;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private Set<User> subscribers = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "subscription_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> subscriptions = new HashSet<>();

    public Set<User> getSubscribers() {
        if (CollectionUtils.isEmpty(subscribers)) {
            return Collections.emptySet();
        }
        return subscribers.stream().map(this::userConverter).collect(Collectors.toSet());
    }

    public Set<User> getSubscriptions() {
        if (CollectionUtils.isEmpty(subscriptions)) {
            return Collections.emptySet();
        }
        return subscriptions.stream().map(this::userConverter).collect(Collectors.toSet());
    }

    private User userConverter(User user) {
        User.UserBuilder userBuilder = user.toBuilder();
        return userBuilder.subscribers(Collections.emptySet())
                .subscriptions(Collections.emptySet())
                .build();
    }
}

