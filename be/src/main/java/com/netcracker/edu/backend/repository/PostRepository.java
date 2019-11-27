package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);

    @Query(value = "select count(p) from Post p")
    Long findPostsCount();

    Optional<Post> findById(long id);

    @Modifying
    @Transactional
    @Query(value = "insert into likes(user_id,post_id) values (:userId,:postId)", nativeQuery = true)
    void saveLike(@Param("postId") long postId, @Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "insert into posts_has_tags(post_id,tag_id) values (:postId,:tagId)", nativeQuery = true)
    void saveTag(@Param("postId") long postId, @Param("tagId") long tagId);

    @Modifying
    @Transactional
    @Query(value = "delete from likes where post_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteLike(@Param("postId") long postId,@Param("userId") long userId);

//    @Query(value = "select * from users where id in \n" +
//            "(select likes.user_id from posts join likes on posts.id = likes.post_id where likes.post_id = ?1) limit 1", nativeQuery = true)
//    User findAllPostLikes(long id);

}


