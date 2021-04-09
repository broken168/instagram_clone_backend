package com.brabos.bahia.instagram.test.repositories;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Transactional(readOnly = true)
    UserProfile findByEmail(String email);

    @Transactional(readOnly = true)
    Page<UserProfile> findByUsernameContaining(String username, Pageable pageable);

    @Transactional(readOnly = true)
    Page<UserProfile> findByIdIn(List<Long> ids, Pageable pageable);

}
