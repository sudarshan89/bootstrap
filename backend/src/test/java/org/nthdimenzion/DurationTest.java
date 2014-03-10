package org.nthdimenzion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nthdimenzion.object.utils.DurationTransformer;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Samir
 * Date: 8/27/13
 * Time: 8:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class DurationTest {

    private List<Map<String, Object>> resultList;

    @Before
    public void setUp() {
        resultList = Lists.newArrayList();
        Map resultMap = Maps.newHashMap();
        resultMap.put("duration", "PT7200S");
        resultMap.put("examCode", "AIIMS");
        resultMap.put("examName", "All India Institute of Medical Science");
        resultList.add(resultMap);
    }

    @Test
    public void itShouldTransformToMinuteAndReturnSameMap() {
        List<Map<String, Object>> resultList = DurationTransformer.transformToMinute(this.resultList, "duration");
        Map<String, Object> resultMap = resultList.get(0);
        Assert.assertEquals(120L, resultMap.get("duration"));
        Assert.assertEquals("AIIMS", resultMap.get("examCode"));
        Assert.assertEquals("All India Institute of Medical Science", resultMap.get("examName"));
    }

    @Test
    public void itShouldTransformToHourAndReturnSameMap() {
        List<Map<String, Object>> resultList = DurationTransformer.transformToHour(this.resultList, "duration");
        Map<String, Object> resultMap = resultList.get(0);
        Assert.assertEquals(2L, resultMap.get("duration"));
        Assert.assertEquals("AIIMS", resultMap.get("examCode"));
        Assert.assertEquals("All India Institute of Medical Science", resultMap.get("examName"));
    }

}
