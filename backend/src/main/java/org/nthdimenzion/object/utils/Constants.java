package org.nthdimenzion.object.utils;

import org.joda.money.CurrencyUnit;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public interface Constants {

    CurrencyUnit INR = CurrencyUnit.of("INR");

    Boolean Success = Boolean.TRUE;
    String  HH_MM_AMPM = "hh:mm a";
    String  DD_MM_YYYY_HH_MM_AMPM =  "dd/MM/yyyy "+HH_MM_AMPM;
    Boolean Failure = Boolean.FALSE;
    String LOGGED_IN_USER = "loggedInUser";
    String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

    String DD_MM_YYYY = "dd-MM-yyyy";

    String YYYY_MM_DD = "yyyy-MM-dd";

    String DD_MM_YYYY_HH_MM_SS_AMPM = "MM/dd/yyyy hh:mm:ss a";

    String DD_MM_YYYY_WITH_SLASH = "dd/MM/yyyy";

    DateTimeFormatter URL_DATE_FORMAT = DateTimeFormat.forPattern(Constants.YYYY_MM_DD);

    DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormat.forPattern(Constants.DD_MM_YYYY_HH_MM_SS);

    DateTimeFormatter DEFAULT_DATE_TIME_FORMAT_AM_PM = DateTimeFormat.forPattern(Constants.DD_MM_YYYY_HH_MM_AMPM);

    DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormat.forPattern(Constants.DD_MM_YYYY);

    DateTimeFormatter AMERICAN_DATE_FORMAT = DateTimeFormat.forPattern(Constants.YYYY_MM_DD);

    DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormat.forPattern(DD_MM_YYYY_WITH_SLASH);
}
