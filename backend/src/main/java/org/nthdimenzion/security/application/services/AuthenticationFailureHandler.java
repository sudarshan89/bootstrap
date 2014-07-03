package org.nthdimenzion.security.application.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 3/6/13
 * Time: 10:28 PM
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private enum AuthenticationFailureErrorCodes {
        BAD_CREDENTIALS {
            @Override
            String getDescription() {
               return "Invalid username or password.";
            }
        },
        ACCOUNT_LOCKED{
            @Override
            String getDescription() {
                return "Account is locked.Contact Administrator.";
            }
        },
        ACCOUNT_DISABLED {
            @Override
            String getDescription() {
                return "Account is disabled.Contact Administrator.";
            }
        }, UNKNOWN_AUTHENTICATION_EXCEPTION{
            @Override
            String getDescription() {
                return "Reason Unknown,try after some time.";
            }
        },MAX_SESSION_REACHED {
            @Override
            String getDescription() {
                return "You already have an active session.End the session to login again.";
            }
        };

        abstract String getDescription();
    }

    @Autowired
    private IAuthentication authenticationService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException authenticationException)
            throws IOException, ServletException {

        if (authenticationException instanceof BadCredentialsException) {
            if (isValidUserName(authenticationException)) {
                authenticationService.failedLoginAttempt(getUserName(authenticationException));
            }
        }
        AuthenticationFailureErrorCodes authenticationFailureErrorCode = transformExceptionIntoErrorCode(authenticationException);
        JsonObject jsonReply =  new JsonObject();
        jsonReply.addProperty("authError",authenticationFailureErrorCode.getDescription());
        final String reply =new Gson().toJson(jsonReply);
        response.setStatus(401);
        response.getWriter().print(reply);
        super.onAuthenticationFailure(request,response,authenticationException);
    }

    private String getUserName(AuthenticationException authenticationException) {
        return authenticationException.getAuthentication().getName();
    }

    private boolean isValidUserName(AuthenticationException authenticationException) {
        return authenticationException.getExtraInformation() != null;

    }

    private AuthenticationFailureErrorCodes transformExceptionIntoErrorCode(AuthenticationException
                                                                                    authenticationException) {
        if (authenticationException instanceof BadCredentialsException || authenticationException instanceof
                UsernameNotFoundException) {
            return AuthenticationFailureErrorCodes.BAD_CREDENTIALS;
        } else if (authenticationException instanceof DisabledException) {
            return AuthenticationFailureErrorCodes.ACCOUNT_DISABLED;
        } else if (authenticationException instanceof LockedException) {
            return AuthenticationFailureErrorCodes.ACCOUNT_LOCKED;
        } else if (authenticationException instanceof SessionAuthenticationException) {
        return AuthenticationFailureErrorCodes.MAX_SESSION_REACHED;
        }
        return AuthenticationFailureErrorCodes.UNKNOWN_AUTHENTICATION_EXCEPTION;
    }
}
