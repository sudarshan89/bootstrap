package org.nthdimenzion.security.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IUserLoginRepository {

    UserLogin findUserLoginWithUserName(String username);

    public SecurityGroup findSecurityGroup(String name);

    }
