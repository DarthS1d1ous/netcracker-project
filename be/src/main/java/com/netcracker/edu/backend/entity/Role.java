package com.netcracker.edu.backend.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "roles", schema = "backend")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
//    @JsonIgnore
//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private Set<User> users;
}
