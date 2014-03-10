package org.nthdimenzion.security.infrastructure.repositories;

import org.nthdimenzion.ddd.infrastructure.IDaoOperations;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.SecurityGroup;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class UserLoginRepository implements IUserLoginRepository {

    @Autowired
    @Qualifier("daoOperations")
    private IDaoOperations hibernateDaoOperations;

    protected UserLoginRepository(){

    }

    UserLoginRepository(IDaoOperations hibernateDaoOperations) {
       this.hibernateDaoOperations = hibernateDaoOperations;
    }

    @Override
    public UserLogin findUserLoginWithUserName(String username) {
        UserLogin userLogin = hibernateDaoOperations.getEntityManager().createNamedQuery("findUserLoginByUserName",UserLogin.class).setParameter("username",username).getSingleResult();
        return userLogin;
    }

    @Override
    public SecurityGroup findSecurityGroup(String name){
        SecurityGroup securityGroup = hibernateDaoOperations.getEntityManager().createNamedQuery("findSecurityGroupByName",SecurityGroup.class).setParameter("name",name).getSingleResult();
        return securityGroup;
    }
}
