package org.nthdimenzion.presentation;

import org.nthdimenzion.ddd.infrastructure.LoggedInUserHolder;
import org.nthdimenzion.object.utils.Constants;
import org.nthdimenzion.security.domain.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class LoggedInUserFilter implements Filter {

    private static final String
            SPRING_SECURITY_CHECK_MAPPING = "/login";

    private static final String
            SPRING_SECURITY_LOGOUT_MAPPING = "/j_spring_security_logout";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private FilterConfig filterConfig;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (null == filterConfig) {
            return;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        LoggedInUserHolder.clearLoggedInUser();
        ((HttpServletResponse)response).addHeader("Cache-Control","no-cache");
        ((HttpServletResponse)response).addHeader("Expires","-1");
        if (httpRequest.getRequestURI().endsWith(SPRING_SECURITY_CHECK_MAPPING)) {
            final String username = request.getParameter("j_username");
            LoggedInUserHolder.setUserName(username);
        } else if (httpRequest.getRequestURI().endsWith(SPRING_SECURITY_LOGOUT_MAPPING)){
            LoggedInUserHolder.clearLoggedInUser();
        }
        else {
            final SystemUser systemUser = (SystemUser) httpRequest.getSession().getAttribute(Constants.LOGGED_IN_USER);
            if(systemUser!=null)
                LoggedInUserHolder.setUserName(systemUser.getUsername());
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
        LoggedInUserHolder.clearLoggedInUser();
    }

}


