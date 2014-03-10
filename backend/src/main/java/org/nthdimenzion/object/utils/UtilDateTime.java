package org.nthdimenzion.object.utils;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: th Dimen
 * Date: 6/28/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public final class UtilDateTime {

    private UtilDateTime() {
    }

    public static  Map<String,Integer> monthMap = new HashMap<>();
    static {

        monthMap.put("Jan",0);
        monthMap.put("Feb",1);
        monthMap.put("Mar",2);
        monthMap.put("Apr",3);
        monthMap.put("May",4);
        monthMap.put("Jun",5);
        monthMap.put("Jul",6);
        monthMap.put("Aug",7);
        monthMap.put("Sep",8);
        monthMap.put("Oct",9);
        monthMap.put("Nov",10);
        monthMap.put("Dec",11);
    }


    public static final String[] months = {// // to be translated over
            // CommonMonthName, see example in
            // accounting
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December" };

    public static final String[] days = {// to be translated over CommonDayName,
            // see example in accounting
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    public static final String[][] timevals = { { "1000", "millisecond" }, { "60", "second" }, { "60", "minute" },
            { "24", "hour" }, { "168", "week" } };


    public static int getIntervalInDays(DateTime startDate,DateTime endDate){
        return Days.daysBetween(startDate,endDate).getDays();
    }

    public   static List<DateTime> getDateTimeWithinRange(DateTime startDate,int interval){
        List<DateTime> dateTimes = new ArrayList<>(interval);
        for(int i = 0; i<interval ; i++){
            DateTime dateTime = startDate.withFieldAdded(DurationFieldType.days(),i);
            dateTimes.add(dateTime);
        }
        return  dateTimes;
    }


    public static DateTime createDateTime(String dateString){
        String[] startDateArray = dateString.split("\\s") ;
        int startDateIntValue = Integer.valueOf(startDateArray[2].replaceAll("\\D","").trim());
        Date date = new Date();
        int year = date.getYear();
        Calendar calendar = new GregorianCalendar();
        calendar.set(year + 1900,monthMap.get(startDateArray[1]),startDateIntValue);
        DateTime dateTime = new DateTime(calendar.getTime());
        return dateTime;
    }

    public static List<String> getYearsUpUntilTheStartingDate(final LocalDate startingDate,int noOfYearsToRewind){
        LocalDate rewindedDate =  startingDate.minusYears(noOfYearsToRewind-1);
        int rewindedYear = rewindedDate.getYear();
        int startingYear  = startingDate.getYear();
        List result =  Lists.newArrayList();
        for(;rewindedYear  <= startingYear ; rewindedYear++){
            result.add(String.valueOf(rewindedYear));
        }
        return result;

    }
}
