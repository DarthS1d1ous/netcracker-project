package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "select p.userLikes from Post p where p.id = ?1")
    List<User> findAllPostLikes(long id);

    @Query(value = "select p.userLikes from Post p where p.id = ?1 and p.userLikes = ?2")
    User findPostLikeByUserId(long postId, long userId);

    Optional<User> findById(long id);

}
