package com.javelin.repository;

import com.javelin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);

    User findOneByEmail(String email);

}

