package org.nthdimenzion.ddd.domain.model;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.junit.Ignore;
import org.junit.Test;
import org.nthdimenzion.object.utils.Constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Author: Nthdimenzion
 */

@Ignore
public class BillingCycleTest {

    @Test
    public void testGenerateBillingCycle() throws Exception {
        DateTime purchaseDate = DateTime.parse("13-10-2010", Constants.DEFAULT_DATE_FORMAT);
        DateTime nowWithDayBeforePurchaseDate = DateTime.parse("03-10-2013", Constants.DEFAULT_DATE_FORMAT);

        Interval billingCycle = BillingCycle.generate(purchaseDate, nowWithDayBeforePurchaseDate, Months.months(3));


        DateTime expectedCycleStart = DateTime.parse("13-09-2013", Constants.DEFAULT_DATE_FORMAT);
        DateTime expectedCycleEnd = DateTime.parse("13-12-2013", Constants.DEFAULT_DATE_FORMAT);
        Interval expectedResult = new Interval(expectedCycleStart,expectedCycleEnd);

        assertThat(billingCycle,is(equalTo(expectedResult)));
    }

    @Test
    public void testGenerateBillingCycle1() throws Exception {
        DateTime purchaseDate = DateTime.parse("13-10-2010", Constants.DEFAULT_DATE_FORMAT);
        DateTime nowWithDayAfterPurchaseDate = DateTime.parse("23-10-2013", Constants.DEFAULT_DATE_FORMAT);

        Interval billingCycle = BillingCycle.generate(purchaseDate, nowWithDayAfterPurchaseDate, Months.months(1));


        DateTime expectedCycleStart = DateTime.parse("13-10-2013", Constants.DEFAULT_DATE_FORMAT);
        DateTime expectedCycleEnd = DateTime.parse("13-11-2013", Constants.DEFAULT_DATE_FORMAT);
        Interval expectedResult = new Interval(expectedCycleStart,expectedCycleEnd);

        assertThat(billingCycle,is(equalTo(expectedResult)));
    }
}
