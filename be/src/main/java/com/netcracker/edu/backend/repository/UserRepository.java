package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Transactional
    @Query(value = "delete from subscriptions where subscription_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteSubscription(long subscriptionId, long userId);

    @Modifying
    @Transactional
    @Query(value = "insert into subscriptions values (?1,?2);", nativeQuery = true)
    void insertSubscription(long subscriptionId, long userId);

    @Query(value = "select u.username from User u where u.username = ?1")
    String findUsernameIfExists(String username);

    @Query(value = "select count(post_id) from likes where user_id = ?1", nativeQuery = true)
    Integer findUserLikesCount(long id);
}
