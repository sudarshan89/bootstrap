package org.nthdimenzion.ddd.domain.service;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 11/9/13
 * Time: 12:37 AM
 */
public class RoleServiceUnitTest {

    @Test
    public void testGetRoleClass(){
        RoleService roleService = new RoleService();
        Class clazz = roleService.findDomainRole.apply("ADMIN");
        assertThat(clazz,is(notNullValue()));
        assertThat(clazz.getSimpleName(),is("Admin"));

    }
}
