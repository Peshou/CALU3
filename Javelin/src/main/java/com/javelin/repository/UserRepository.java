package com.javelin.repository;

import com.javelin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);

    User findOneByEmail(String email);


    @Override
    void delete(User t);
}

