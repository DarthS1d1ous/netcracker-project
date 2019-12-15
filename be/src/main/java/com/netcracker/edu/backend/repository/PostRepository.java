package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);

    @Query(value = "select count(p) from Post p")
    Long findPostsCount();

    List<Post> findPostsByUserIdOrderByTimeCreationDesc(long userId);

    @Query(value = "select p.id, p.photo_path, p.description, p.time_creation, p.title, p.user_id from posts p INNER join likes l on p.id = l.post_id group by p.id order by count(l.post_id) desc limit 10", nativeQuery = true)
    List<Post> findMostLikedPosts();

    Optional<Post> findById(long id);

    @Query(value = "select DISTINCT p.id, p.photo_path, p.description, p.time_creation, p.title, p.user_id from tags t left join posts_has_tags pht on t.id = pht.tag_id left join posts p on pht.post_id = p.id\n" +
            "where t.title in :tags order by p.time_creation desc", nativeQuery = true)
    List<Post> findPostsByTags(List<String> tags);

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
    void deleteLike(@Param("postId") long postId, @Param("userId") long userId);

//    @Query(value = "select * from users where id in \n" +
//            "(select likes.user_id from posts join likes on posts.id = likes.post_id where likes.post_id = ?1) limit 1", nativeQuery = true)
//    User findAllPostLikes(long id);

}


