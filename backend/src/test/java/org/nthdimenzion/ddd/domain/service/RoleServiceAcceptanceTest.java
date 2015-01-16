package org.nthdimenzion.ddd.domain.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.ddd.domain.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Author: Nthdimenzion
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:integrationTestContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class RoleServiceAcceptanceTest {

    @Autowired
    private RoleService roleService;

    @Test
    @DatabaseSetup("classpath:testdata/acceptance/bootstrap/setupDataForDomainRole.xml")
    @Ignore
    public void testRolePlayedByUser() throws Exception {
        final Admin admin = roleService.getRolePlayedByUser("admin@gmail.com");
        assertThat(admin,notNullValue());
        assertThat(admin.getPersonalDetails().firstName,is("FIRST"));
        assertThat(admin.getPersonalDetails().lastName,is("LAST"));
    }
}
