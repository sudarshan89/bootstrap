package org.nthdimenzion.object.utils;

import org.joda.time.Duration;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Samir
 * Date: 8/27/13
 * Time: 8:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class DurationTransformer {

    public static List<Map<String, Object>> transformToMinute(List<Map<String, Object>> resultList, String durationKey) {
        for (Map<String, Object> resultMap : resultList) {
            if (resultMap.get(durationKey) != null) {
                Duration duration = new Duration(resultMap.get(durationKey));
                long minutes = duration.getStandardMinutes();
                resultMap.put(durationKey, minutes);
            }
        }
        return resultList;
    }

    public static List<Map<String, Object>> transformToHour(List<Map<String, Object>> resultList, String durationKey) {
        for (Map<String, Object> resultMap : resultList) {
            if (resultMap.get(durationKey) != null) {
                Duration duration = new Duration(resultMap.get(durationKey));
                long hours = duration.getStandardHours();
                resultMap.put(durationKey, hours);
            }
        }
        return resultList;
    }

    public static List<Map<String, Object>> transformToMonths(List<Map<String, Object>> resultList, String durationKey) {
        for (Map<String, Object> resultMap : resultList) {
            if (resultMap.get(durationKey) != null) {
                Duration duration = new Duration(resultMap.get(durationKey));
                int months = duration.toStandardDays().toStandardWeeks().getWeeks()/4;
                resultMap.put(durationKey, months);
            }
        }
        return resultList;
    }
}
