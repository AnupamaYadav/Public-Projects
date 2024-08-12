package service;

import com.deliveroo.assignment.service.BaseCronParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BaseCronParserTest {

    private BaseCronParser baseCronParser;

    @BeforeEach
    public void setUp() {
        baseCronParser = new BaseCronParser();
    }

    @Test
    public void testWildcardForMinutes() {
        List<String> result = baseCronParser.parseExpression("*", 0, 59);
        assertEquals(60, result.size());
        assertEquals("0", result.get(0));
        assertEquals("59", result.get(59));
    }

    @Test
    public void testWildcardForHours() {
        List<String> result = baseCronParser.parseExpression("*", 0, 23);
        assertEquals(24, result.size());
        assertEquals("0", result.get(0));
        assertEquals("23", result.get(23));
    }

    @Test
    public void testWildcardForDayOfMonth() {
        List<String> result = baseCronParser.parseExpression("*", 1, 31);
        assertEquals(31, result.size());
        assertEquals("1", result.get(0));
        assertEquals("31", result.get(30));
    }

    @Test
    public void testWildcardForMonths() {
        List<String> result = baseCronParser.parseExpression("*", 1, 12);
        assertEquals(12, result.size());
        assertEquals("1", result.get(0));
        assertEquals("12", result.get(11));
    }

    @Test
    public void testWildcardForDayOfWeek() {
        List<String> result = baseCronParser.parseExpression("*", 0, 7);
        assertEquals(8, result.size());
        assertEquals("0", result.get(0));
        assertEquals("7", result.get(7));
    }

    @Test
    public void testCommaSeparatedValuesForMinutes() {
        List<String> result = baseCronParser.parseExpression("1,2,3", 0, 59);
        assertEquals(3, result.size());
        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("3", result.get(2));
    }

    @Test
    public void testRangeForMinutes() {
        List<String> result = baseCronParser.parseExpression("1-5", 0, 59);
        assertEquals(5, result.size());
        assertEquals("1", result.get(0));
        assertEquals("5", result.get(4));
    }

    @Test
    public void testStepValuesForMinutes() {
        List<String> result = baseCronParser.parseExpression("*/15", 0, 59);
        assertEquals(4, result.size());
        assertEquals("0", result.get(0));
        assertEquals("45", result.get(3));
    }

    @Test
    public void testSingleValueForMinutes() {
        List<String> result = baseCronParser.parseExpression("5", 0, 59);
        assertEquals(1, result.size());
        assertEquals("5", result.get(0));
    }

    @Test
    public void testEmptyStringForMinutes() {
        List<String> result = baseCronParser.parseExpression("", 0, 59);
        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }
}
