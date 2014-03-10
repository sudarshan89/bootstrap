package org.nthdimenzion.object.utils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Samir
 * Date: 8/27/13
 * Time: 8:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeTransformer {

    public static List<Map<String, Object>> transformToDefaultDateTimeFormat(List<Map<String, Object>> resultList, String datetimeKey,DateTimeFormatter dateTimeFormat) {
        for (Map<String, Object> resultMap : resultList) {
            if (resultMap.get(datetimeKey) != null) {
                LocalDateTime localDateTime = LocalDateTime.parse(resultMap.get(datetimeKey).toString());
                resultMap.put(datetimeKey, localDateTime.toString(dateTimeFormat));
            }
        }
        return resultList;
    }

}
