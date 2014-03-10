package org.nthdimenzion.ddd.domain.model;

import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 * Author: Nthdimenzion
 */

public final class BillingCycle {

    public static Interval generate(DateTime purchaseDate,DateTime now,Months cycleLength){
        Months gap = Months.monthsBetween(purchaseDate, now);
        DateTime cycleStartDate = purchaseDate.plusMonths(gap.getMonths());
        DateTime cycleEndDate = cycleStartDate.plusMonths(cycleLength.getMonths());
        Interval billingCycle = new Interval(cycleStartDate,cycleEndDate);
        return billingCycle;
    }
}
