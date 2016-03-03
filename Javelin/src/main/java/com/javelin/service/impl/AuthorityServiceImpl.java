package com.javelin.service.impl;

import com.javelin.domain.Authority;
import com.javelin.repository.AuthorityRepository;
import com.javelin.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findOne(String authority) {
        return authorityRepository.findOne(authority);
    }
}
