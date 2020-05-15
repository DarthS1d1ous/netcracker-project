package com.netcracker.edu.backend.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "tags", schema = "backend")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
//    @ManyToMany(mappedBy = "postTags")
//    private Set<Post> posts;

}
