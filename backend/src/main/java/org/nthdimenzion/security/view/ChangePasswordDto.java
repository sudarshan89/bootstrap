package org.nthdimenzion.security.view;

import org.nthdimenzion.object.utils.UtilValidator;

/**
 * Created with IntelliJ IDEA.
 * User: Samir
 * Date: 8/26/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChangePasswordDto {

    public String userName;

    public String newPassword;

    public String oldPassword;

    public boolean isOldAndNewPasswordDifferent(){
        return UtilValidator.isNotEmpty(oldPassword) && UtilValidator.isNotEmpty(newPassword) && oldPassword.equals(newPassword);
    }

}
