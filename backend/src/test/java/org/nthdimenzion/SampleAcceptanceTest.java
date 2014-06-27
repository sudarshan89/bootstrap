package org.nthdimenzion;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.security.domain.SecurityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Author: Nthdimenzion
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:integrationTestContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class SampleAcceptanceTest {

    @Autowired
    private ICrud crudDao;

    /**
     * This sample test should validate the test harness
     * @throws Exception
     */
    @Test
    public void sampleTestShouldValidateBasicTestHarness() throws Exception {
        assertThat(true,is(true));
        SecurityGroup sg = new SecurityGroup(20l,"TestGroup");
        crudDao.save(sg);
    }
}
