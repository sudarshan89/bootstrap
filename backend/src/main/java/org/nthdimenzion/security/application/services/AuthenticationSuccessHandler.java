package org.nthdimenzion.security.application.services;


import org.nthdimenzion.ddd.infrastructure.LoggedInUserHolder;
import org.nthdimenzion.object.utils.Constants;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private IUserLoginRepository userLoginRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoggedInUserHolder.clearLoggedInUser();
        request.getSession().invalidate();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        LoggedInUserHolder.clearLoggedInUser();
        final String username = request.getParameter("j_username");
        LoggedInUserHolder.setUserName(username);
        final UserLogin userLogin = userLoginRepository.findUserLoginWithUserName(username);
        UserDetails userDetails = userService.loadUserByUsername(username);
        setSystemUserInSession(request, userDetails);
        String homepageUrl = homePageLocatorForLoggedInUser.apply(userLogin);
        setDefaultTargetUrl(homepageUrl);
        super.onAuthenticationSuccess(request, response, auth);
    }

    private void setSystemUserInSession(HttpServletRequest request, UserDetails userDetails) {
        SystemUser systemUser = new SystemUser(userDetails);
        request.getSession().setAttribute(Constants.LOGGED_IN_USER, systemUser);
    }

    @Autowired(required = false)
    public void setHomePageLocatorForLoggedInUser(Function<UserLogin, String> homePageLocatorForLoggedInUser) {
        this.homePageLocatorForLoggedInUser = homePageLocatorForLoggedInUser;
    }

    private Function<UserLogin,String> homePageLocatorForLoggedInUser = (userLogin)-> userLogin.getHomePageView().homepage;



}
