package org.nthdimenzion.ddd.domain.service;

import com.google.common.base.Preconditions;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.domain.model.PersonRole;
import org.nthdimenzion.ddd.domain.sharedkernel.DomainRole;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 10/4/13
 * Time: 11:19 PM
 */
@Service
public class RoleService {

    @Autowired
    private ICrud crudDao;

    @Autowired
    private IUserLoginRepository userLoginRepository;

    private Function<String,Class> findDomainRole = (String role) -> {
        DomainRole domainRole = DomainRole.valueOf(role);
        String className = domainRole.getDomainClass();
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Class Not found " + className);
        }
        return clazz;
    };

    public <T> T getRolePlayedByUser(String username)  {
        UserLogin userLogin = userLoginRepository.findUserLoginWithUserName(username);
        PersonRole personRole = userLogin.getPersonRole();
        Preconditions.checkNotNull(personRole);
        Class clazz = findDomainRole.apply(personRole.getDomainRole());
        return (T) crudDao.find(clazz, personRole.getId());
    }

    @Autowired(required = false)
    public void setFindDomainRole(Function<String, Class> findDomainRole) {
        this.findDomainRole = findDomainRole;
    }


}
