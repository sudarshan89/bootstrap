package org.nthdimenzion.security.application.services;

/**
 * Author: Nthdimenzion
 */


public class AuthenticationStub implements IAuthentication {
    @Override
    public void failedLoginAttempt(String username) {

    }

    @Override
    public void successFullLogin(String username) {

    }

    @Override
    public String encryptPassword(String password) {
        return null;
    }

}
