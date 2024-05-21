package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.persistence.IAuthorityDAO;
import com.mobabuild.api_build.service.IAuthorityService;
import com.mobabuild.api_build.utils.AuthorityName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private IAuthorityDAO authorityDAO;

    @Override
    public Optional<Authority> findByName(AuthorityName name) {
        return authorityDAO.findByName(name);
    }
}
