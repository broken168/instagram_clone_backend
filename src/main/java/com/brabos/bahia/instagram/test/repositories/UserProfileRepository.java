package com.brabos.bahia.instagram.test.repositories;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Transactional(readOnly = true)
    UserProfile findByEmail(String email);
}
