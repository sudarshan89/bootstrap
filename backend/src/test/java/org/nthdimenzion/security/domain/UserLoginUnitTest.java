package org.nthdimenzion.security.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 9/8/13
 * Time: 11:50 PM
 */

import com.google.common.base.Optional;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.nthdimenzion.ddd.domain.model.PersonRole;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.nthdimenzion.object.utils.Constants.DEFAULT_DATE_FORMAT;

public class UserLoginUnitTest {

    private UserLogin userLogin = new UserLogin("testUser","testUser","1");


    @Before
    public void setup(){
        userLogin = new UserLogin("testUser","testUser","1");

    }

    @Test
    public void itShouldIncrementFailedLoginCount_WhenLoginAttemptFailed(){
        userLogin.enableUserLogin();

        userLogin.failedLoginAttempt();

        assertThat(userLogin.getNumberOfFailedLoginAttempts(),is(equalTo(1)));
        assertThat(userLogin.getIsAccountNonLocked(),is(equalTo(Boolean.TRUE)));
        assertThat(userLogin.getIsEnabled(),is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void itShouldLockUserLogin_WhenFailedLoginAttemptsExceedMaximumFailedLoginAttemptsAllowed(){
        userLogin.unLockUserAccount();

        userLogin.failedLoginAttempt();
        userLogin.failedLoginAttempt();
        userLogin.failedLoginAttempt();
        userLogin.failedLoginAttempt();
        userLogin.failedLoginAttempt();
        userLogin.failedLoginAttempt();

        assertThat(userLogin.getNumberOfFailedLoginAttempts(),is(equalTo(6)));
        assertThat(userLogin.getIsAccountNonLocked(),is(equalTo(Boolean.FALSE)));
        assertThat(userLogin.getIsEnabled(),is(equalTo(Boolean.TRUE)));

    }

    @Test
    public void givenPreviousFailedLoginAttempts_itShouldResetFailedLoginAttempts_WhenASuccessfullLoginOccurs(){
        userLogin.enableUserLogin();

        userLogin.failedLoginAttempt();
        userLogin.failedLoginAttempt();

        userLogin.successfullLogin();

        assertThat(userLogin.getNumberOfFailedLoginAttempts(),is(equalTo(0)));
        assertThat(userLogin.getIsAccountNonLocked(),is(equalTo(Boolean.TRUE)));
        assertThat(userLogin.getIsEnabled(),is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void whenUserIsEnabled_itShouldResetAccountToBehaveLikeANewlyCreatedUserLogin(){
        assertThat(Boolean.FALSE,is(userLogin.getIsEnabled()));
        userLogin.enableUserLogin();
        assertThat(Boolean.TRUE,is(userLogin.getIsEnabled()));
        assertThat(Boolean.TRUE,is(userLogin.getIsAccountNonLocked()));
        assertThat(0,is(userLogin.getNumberOfFailedLoginAttempts()));

    }

    @Test
    public void whenUserIsDisabled_itShould(){
        userLogin.enableUserLogin();

        userLogin.disableLogin();

        assertThat(Boolean.FALSE,is(userLogin.getIsEnabled()));
        assertThat(Boolean.TRUE,is(userLogin.getIsAccountNonLocked()));
        assertThat(0,is(userLogin.getNumberOfFailedLoginAttempts()));

    }

    @Test
    public void givenUserLoginWithNoDomainRole_whenTheDomainRoleIsQueries_itShouldReturnEmpty(){
        Optional<String> role = userLogin.getRole();
        assertThat(role.isPresent(),is(false));

    }

    @Test
    public void givenUserLoginWithUserDomainRole_whenTheDomainRoleIsQueries_itShouldReturnTheDomainRole(){
        PersonRole personRole = mock(PersonRole.class);
        when(personRole.getDomainRole()).thenReturn("USER_ROLE");
        userLogin.attachRole(personRole);
        Optional<String> role = userLogin.getRole();
        assertThat(role.isPresent(),is(true));
    }

    @Test
    public void givenUserLoginWithUnlimitedValidity_whenValidityIsChecked_itShouldReturnTrue(){
        boolean isValid = userLogin.isValid();
        assertThat(isValid,is(Boolean.TRUE));
    }

    @Test
    public void whenCurrentDateIsBeforeValidityExpiration_itShouldReturnTrue(){
        LocalDate loginValidityUpto = DEFAULT_DATE_FORMAT.parseLocalDate("10-10-2010");
        ReflectionTestUtils.setField(userLogin,"validUptoDate",loginValidityUpto );
        LocalDate now = DEFAULT_DATE_FORMAT.parseLocalDate("10-09-2010");
        boolean isValid = userLogin.isValid(now);
        assertThat(isValid,is(Boolean.TRUE));

    }

    @Test
    public void whenCurrentDateIsAfterValidityExpiration_itShouldReturnFalse(){
        LocalDate loginValidityUpto = DEFAULT_DATE_FORMAT.parseLocalDate("10-10-2010");
        ReflectionTestUtils.setField(userLogin,"validUptoDate",loginValidityUpto );
        LocalDate now = DEFAULT_DATE_FORMAT.parseLocalDate("10-11-2010");
        boolean isNotValid = userLogin.isValid(now);
        assertThat(isNotValid,is(Boolean.FALSE));

    }
}
