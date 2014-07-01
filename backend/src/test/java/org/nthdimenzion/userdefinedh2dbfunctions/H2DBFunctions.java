package org.nthdimenzion.userdefinedh2dbfunctions;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 7/1/2014.
 */
public class H2DBFunctions {
    public static String currentDate(Date date)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df.format(date);
        return date1;
    }
}
