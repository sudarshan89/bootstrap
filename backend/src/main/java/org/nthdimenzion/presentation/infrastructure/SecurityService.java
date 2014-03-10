package org.nthdimenzion.presentation.infrastructure;

import org.apache.commons.lang.RandomStringUtils;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.domain.model.PersonalDetails;
import org.nthdimenzion.security.application.services.IAuthentication;
import org.nthdimenzion.security.application.services.MailService;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nthdimenzion
 */

@Service
public class SecurityService {

    @Autowired
    private IUserLoginRepository iUserLoginRepository;
    @Autowired
    private ICrud crudDao;
    @Autowired
    private MailService mailService;

    @Autowired
    private IAuthentication authentication;


    @Transactional
    public void forgotPassword(String emailId) {
        UserLogin userLogin = iUserLoginRepository.findUserLoginWithUserName(emailId);
        String newPassword = RandomStringUtils.randomAlphabetic(6);
        if (userLogin != null) {
            PersonalDetails personalDetails = userLogin.getPersonRole().getPersonalDetails();
            String firstName = personalDetails.firstName;
            String lastName = personalDetails.lastName;
            userLogin.changePassword(authentication.encryptPassword(newPassword));
            crudDao.save(userLogin);
            Map userMailDetailMap = populateForgotPasswordMailDetailMap(firstName, lastName, newPassword);
            userMailDetailMap.put("recipientMailAddress", personalDetails.email);
            mailService.prepareNewPasswordContentAndSendMail(userMailDetailMap);

        }

    }

    private Map populateForgotPasswordMailDetailMap(String firstName, String lastName, String newPassword) {
        Map userMailDetailMap = new HashMap();
        userMailDetailMap.put("password", newPassword);
        userMailDetailMap.put("firstName", firstName);
        userMailDetailMap.put("lastName", lastName);
        return userMailDetailMap;
    }


}
