package org.nthdimenzion.security.view;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.domain.model.PersonalDetails;
import org.nthdimenzion.object.utils.Constants;
import org.nthdimenzion.presentation.infrastructure.Result;
import org.nthdimenzion.presentation.infrastructure.SecurityService;
import org.nthdimenzion.presentation.infrastructure.ViewSystemMessages;
import org.nthdimenzion.security.application.services.EncryptionUtil;
import org.nthdimenzion.security.application.services.IAuthentication;
import org.nthdimenzion.security.application.services.IEncryportDecryptor;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @TODO Refactor this, Ravi Kumar
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 6/8/13
 * Time: 1:26 PM
 */
@RestController
@RequestMapping(value = "/security")
public class SecurityController {

    private static final String USERINFO = "USERINFO";

    @Autowired
    private IUserLoginRepository iUserLoginRepository;

    private final Gson gson = new Gson();

    @Autowired
    private ICrud crudDao;

    /**
     * Plugin your own implementation here to send out required JSON
     */
    private Function<UserLogin, JsonObject> createJsonResponseFromCurrentUser = (UserLogin userLogin)->{
        PersonalDetails personalDetails = userLogin.getUserInfo();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", personalDetails.id);
        jsonObject.addProperty("firstName", personalDetails.firstName);
        jsonObject.addProperty("middleName", personalDetails.middleName);
        jsonObject.addProperty("lastName", personalDetails.lastName);
        jsonObject.addProperty("permissions", gson.toJson(userLogin.getAllPermissions()));
        return jsonObject;
    };

    @Autowired
    private IAuthentication authentication;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private IEncryportDecryptor iEncryportDecryptor;

    public SecurityController(Function createJsonResponseFromCurrentUser) {
        this.createJsonResponseFromCurrentUser = createJsonResponseFromCurrentUser;
    }

    SecurityController() {
    }

    @RequestMapping(method = GET, value = "/current-user", consumes = ALL_VALUE)
    public ResponseEntity<String> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SystemUser systemUser = (SystemUser) session.getAttribute(Constants.LOGGED_IN_USER);
        if (systemUser == null) {
            return new ResponseEntity(" {\"user\":null}",OK);
        } else if (session.getAttribute(USERINFO) != null) {
            final JsonObject userDetailsJson = (JsonObject) session.getAttribute(USERINFO);
            return new ResponseEntity(gson.toJson(userDetailsJson),OK);
        }
        UserLogin userLogin = iUserLoginRepository.findUserLoginWithUserName(systemUser.getUsername());
        final JsonObject userDetailsJson = createJsonResponseFromCurrentUser.apply(userLogin);
        session.setAttribute(USERINFO, userDetailsJson);
        return new ResponseEntity(gson.toJson(userDetailsJson),OK);
    }

    @RequestMapping(value = "/verifyuserid/{verificationCode}", method = GET, consumes = ALL_VALUE)
    public Boolean verifyUserId(@PathVariable("verificationCode") String verificationCode) {
        String decodedUserCode = EncryptionUtil.decrypt(iEncryportDecryptor, verificationCode);
        UserLogin userLogin = iUserLoginRepository.findUserLoginWithUserName(decodedUserCode);
        return userLogin != null;
    }

    @RequestMapping(method = POST, value = "/forgotPassword/{emailId:.+}", consumes = ALL_VALUE)
    public ResponseEntity<Result> forgotPassword(@PathVariable("emailId") String emailId) {
        ResponseEntity responseEntity = new ResponseEntity(Result.Success(), OK);
        try {
            securityService.forgotPassword(emailId);
        } catch (RuntimeException e) {
            responseEntity = new ResponseEntity(BAD_REQUEST);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/changePassword", method = POST)
    public ResponseEntity<Result> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        if (changePasswordDto.isOldAndNewPasswordDifferent()) {
            return new ResponseEntity(Result.Failure(ViewSystemMessages.OLD_AND_NEW_PASSWORD_SHOULD_NOT_BE_SAME.name()), PRECONDITION_FAILED);
        }
        UserLogin userLogin = iUserLoginRepository.findUserLoginWithUserName(changePasswordDto.userName);
        if (userLogin == null) {
            return new ResponseEntity(Result.Failure(ViewSystemMessages.USER_NOT_FOUND.name()),PRECONDITION_FAILED);
        }
        String encryptedOldPassword = authentication.encryptPassword(changePasswordDto.oldPassword);
        if (!userLogin.isPasswordTheSame(encryptedOldPassword)) {
            return new ResponseEntity(Result.Failure(ViewSystemMessages.OLD_PASSWORD_INCORRECT.name()), EXPECTATION_FAILED);
        }
        userLogin.changePassword(authentication.encryptPassword(changePasswordDto.newPassword));

        crudDao.save(userLogin);

        return new ResponseEntity(Result.Success(ViewSystemMessages.PASSWORD_CHANGED_SUCCESSFULLY.name()), OK);
    }


}
