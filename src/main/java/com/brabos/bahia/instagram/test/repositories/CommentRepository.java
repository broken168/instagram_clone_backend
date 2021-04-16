package com.brabos.bahia.instagram.test.repositories;

import com.brabos.bahia.instagram.test.domains.Comment;
import com.brabos.bahia.instagram.test.domains.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Transactional(readOnly = true)
    @Query("select comment from Comment comment where comment.post.id = :id")
    Page<Comment> findCommentsByPost(@Param("id") Long id, Pageable pageable);
}
