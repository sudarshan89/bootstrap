package org.nthdimenzion.security.application.services;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 9/8/13
 * Time: 3:23 PM
 */
public interface IAuthentication {

    void failedLoginAttempt(String username);

    void successFullLogin(String username);

    String encryptPassword(String password);
}
