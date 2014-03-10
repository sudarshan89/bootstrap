package org.nthdimenzion.object.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 28/9/13
 * Time: 11:31 PM
 */
public class DateTimeTransformerTest {
    @Test
    public void testTransformToDefaultDateTimeFormat() throws Exception {
        List<Map<String,Object>> rows = Lists.newArrayList();

        Map<String,Object> row1 = Maps.newHashMap();
        row1.put("id","1");
        row1.put("scheduledOn","2013-263T18:00:00.000");

        Map<String,Object> row2 = Maps.newHashMap();
        row2.put("id","2");
        row2.put("scheduledOn","2013-253T18:00:00.000");

        rows.add(row1);
        rows.add(row2);
        DateTimeFormatter dateTimeFormat=Constants.DEFAULT_DATE_TIME_FORMAT;
        List<Map<String,Object>> expectedTransformedRows = DateTimeTransformer.transformToDefaultDateTimeFormat(rows,"scheduledOn",dateTimeFormat);

        Map<String,Object> expectedRow1 = Maps.newHashMap();
        row1.put("id","1");
        row1.put("scheduledOn","20/09/2013 18:00:00");

        Map<String,Object> expectedRow2 = Maps.newHashMap();
        row2.put("id","2");
        row2.put("scheduledOn","10/09/2013 18:00:00");

        expectedTransformedRows.add(expectedRow1);
        expectedTransformedRows.add(expectedRow2);

        assertThat(rows,is(equalTo(expectedTransformedRows)));

    }
}
