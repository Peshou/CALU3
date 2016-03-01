package com.javelin.repository;

import com.javelin.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Intel on 01.03.2016.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
