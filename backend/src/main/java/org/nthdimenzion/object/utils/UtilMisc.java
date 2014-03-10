/*
 * Filename: UtilMisc
 * Copyright (c) 2012 Onebox, All Rights Reserved
 *
 * This software is the proprietary information of Onebox.
 * Use is subject to license terms.
 *
 * Modified: 7/24/12 6:46 PM
 * Author: Pradyumna
 */

package org.nthdimenzion.object.utils;

import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class UtilMisc {

    private static final Logger logger = LoggerFactory.getLogger(UtilMisc.class);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private UtilMisc() {
    }

    public static String randomAlphaNumericGenerator(int range) {
        String randString = "";
        if (range <= 0) {
            return randString;
        }
        StringBuffer sb = new StringBuffer();
        String block = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFIJKLMNOPQRSTUVWXYZ";
        sb.append(block).append(block.toUpperCase()).append("0123456789");
        block = sb.toString();
        sb = new StringBuffer();
        Random random = new Random();
        try {
            for (int i = 0; i < range; i++) {
                sb.append(Character.toString(block.charAt(random.nextInt(block.length() - 1))));
            }
            randString = sb.toString();
        } catch (ArrayIndexOutOfBoundsException e) {

        } catch (NumberFormatException e) {

        } catch (Exception e) {
        }
        return randString;
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, "UTF-8");
        return writer.toString();
    }

    public static Date convertLocalDateToUtilDate(LocalDate currentDate){
        String result = format.format(currentDate.toDate());
        try {
            Date finalSystemDate = format.parse(result);
            return finalSystemDate;
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
