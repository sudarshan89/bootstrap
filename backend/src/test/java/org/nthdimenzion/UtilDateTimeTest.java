package org.nthdimenzion;

import com.google.common.collect.Lists;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.nthdimenzion.object.utils.UtilDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 23/8/13
 * Time: 9:14 PM
 */
public class UtilDateTimeTest {

    @Test
    public void itShouldReturnListOfYearsFromTheRewindedDateToStartingDate(){
        LocalDate startDate=LocalDate.parse("2013-12-02");

        assertThat(Lists.newArrayList("2011","2012","2013"), is(equalTo(UtilDateTime.getYearsUpUntilTheStartingDate(startDate,3))));

        assertThat(Lists.newArrayList("2006","2007","2008","2009","2010"),
                is(equalTo(UtilDateTime.getYearsUpUntilTheStartingDate
                (new LocalDate(2010,3,3),5))));

    }
}
