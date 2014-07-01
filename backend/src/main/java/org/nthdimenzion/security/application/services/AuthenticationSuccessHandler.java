package org.nthdimenzion.security.application.services;


import com.google.common.base.Function;
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
        Function<UserLogin,String> getHomePageForLoggedInUser = new DefaultHomePageForLoggedInUser();
        String homepageUrl = getHomePageForLoggedInUser.apply(userLogin);
        super.setDefaultTargetUrl(homepageUrl);
    }

    private void setSystemUserInSession(HttpServletRequest request, UserDetails userDetails) {
        SystemUser systemUser = new SystemUser(userDetails);
        request.getSession().setAttribute(Constants.LOGGED_IN_USER, systemUser);
    }


    /***
     * Override this class, in a application specific way
     */
    private class DefaultHomePageForLoggedInUser implements Function<UserLogin,String>{

        @Override
        public String apply(UserLogin userLogin) {
            return userLogin.getHomePageView().homepage;
        }

    }

}
