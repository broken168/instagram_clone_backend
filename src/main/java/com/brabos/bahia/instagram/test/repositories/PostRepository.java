package com.brabos.bahia.instagram.test.repositories;

import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.domains.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional(readOnly = true)
    @Query("select u from Post u where u.userProfile.id in :ids")
    Page<Post> getPostsByUsersIds(@Param("ids") List<Long> ids, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("select u from Post u where u.userProfile.id = :id")
    Page<Post> getPostsByUserId(@Param("id") Long id, Pageable pageable);
}
