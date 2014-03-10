package org.nthdimenzion.security.servlets;

import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.security.application.services.EncryptionUtil;
import org.nthdimenzion.security.application.services.IEncryportDecryptor;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Samir
 * Date: 8/13/13
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserVerificationServlet implements HttpRequestHandler {

    @Autowired
    private ICrud crudDao;

    @Autowired
    private IEncryportDecryptor iEncryportDecryptor;

    @Autowired
    private IUserLoginRepository iuserLoginRepository;

    private final Logger logger = LoggerFactory.getLogger(UserVerificationServlet.class);

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String encodedVerificationCode = request.getParameter("verificationCode");
        String userName = EncryptionUtil.decrypt(iEncryportDecryptor, encodedVerificationCode);
        logger.debug("User Verification Servlet Decrypted UserName********************");
        UserLogin userLogin = iuserLoginRepository.findUserLoginWithUserName(userName);
        String landingPage = "";
        if (userLogin != null && userLogin.isEmailNotVerified()) {
            userLogin.enableUserLogin();
            userLogin.setIsEmailVerified(Boolean.TRUE);
            crudDao.save(userLogin);
            landingPage = "/#/verifyregistration/" + encodedVerificationCode;
            request.setAttribute("status", "authorized");
        } else {
            landingPage = "";
            request.setAttribute("status", "unauthorized");
        }
        logger.debug("Landing Page********************", landingPage);
        request.setAttribute("userName", userName);
        response.sendRedirect(landingPage);
    }
}
