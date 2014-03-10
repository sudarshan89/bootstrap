package org.nthdimenzion.security.application.services;

import org.nthdimenzion.ddd.domain.annotations.DomainService;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@DomainService
class UserService implements UserDetailsService,IAuthentication {

    private UserDetailsService userDetailsService;

    private IUserLoginRepository userLoginRepository;

    private UserLoginFinder userLoginFinder;

    private PasswordEncoder passwordEncoder;

    private SystemWideSaltSource saltSource;

    UserService() {
    }

    @Autowired
    public UserService(UserDetailsService userValidationService, IUserLoginRepository userLoginRepository, UserLoginFinder userLoginFinder, PasswordEncoder passwordEncoder, SystemWideSaltSource saltSource) {
        this.userDetailsService = userValidationService;
        this.userLoginRepository = userLoginRepository;
        this.userLoginFinder = userLoginFinder;
        this.passwordEncoder = passwordEncoder;
        this.saltSource = saltSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        SystemUser systemUser = new SystemUser(userDetails);
        Map<String,Object> advancedUserAttributes = userLoginFinder.getAdvancedUserAttributes(username);
        systemUser.setIsAccountNonLocked((Boolean) advancedUserAttributes.get("isAccountNonLocked"));
        return systemUser;
    }

    @Transactional
    public void successFullLogin(String username){
        UserLogin userLogin = userLoginRepository.findUserLoginWithUserName(username);
        userLogin.successfullLogin();
    }

    @Override
    @Transactional
    public void failedLoginAttempt(String username) {
        UserLogin userLogin = userLoginRepository.findUserLoginWithUserName(username);
        userLogin.failedLoginAttempt();
    }

    @Override
    public String encryptPassword(String password){
        return passwordEncoder.encodePassword(password, saltSource.getSystemWideSalt());
    }
}