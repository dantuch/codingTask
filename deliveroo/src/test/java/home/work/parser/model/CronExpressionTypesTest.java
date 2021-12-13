package home.work.parser.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.List;

public class CronExpressionTypesTest {
    @Test
    public void shouldParseStringThatContainsRunInAllMonths() {
        //given
        String input = "*";
        ParsingStrategy strategy = CronExpressionTypes.ALL_OF;
        //when
        List<Integer> parsed = strategy.parse(input, CronExpressionTimeField.MONTH);
        //then
        MatcherAssert.assertThat(parsed, IsIterableContainingInOrder.contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
    }


    @Test
    public void shouldParseStringThatContainsRunEvery15Minute() {
        //given
        String input = "*/15";
        ParsingStrategy strategy = CronExpressionTypes.EVERY_X_TIMES;
        //when
        List<Integer> parsed = strategy.parse(input, CronExpressionTimeField.MINUTE);
        //then
        MatcherAssert.assertThat(parsed, IsIterableContainingInOrder.contains(0, 15, 30, 45));
    }


    @Test
    public void shouldParseStringThatContainsRunFrom1To5DayOfWeek() {
        //given
        String input = "1-5";
        ParsingStrategy strategy = CronExpressionTypes.FROM_TO;
        //when
        List<Integer> parsed = strategy.parse(input, CronExpressionTimeField.DAY_OF_WEEK);
        //then
        MatcherAssert.assertThat(parsed, IsIterableContainingInOrder.contains(1, 2, 3, 4, 5));
    }

    @Test
    public void shouldParseStringThatContainsRun1stAnd15thDayOfMonth() {
        //given
        String input = "1,15";
        ParsingStrategy strategy = CronExpressionTypes.NUMBERS_COMMA_SEPARATED;
        //when
        List<Integer> parsed = strategy.parse(input, CronExpressionTimeField.DAY_OF_MONTH);
        //then
        MatcherAssert.assertThat(parsed, IsIterableContainingInOrder.contains(1, 15));
    }

    @Test
    public void shouldParseStringThatContainsRun1stAnd2ndAnd3rdDayOfMonth() {
        //given
        String input = "1,2,3";
        ParsingStrategy strategy = CronExpressionTypes.NUMBERS_COMMA_SEPARATED;
        //when
        List<Integer> parsed = strategy.parse(input, CronExpressionTimeField.DAY_OF_MONTH);
        //then
        MatcherAssert.assertThat(parsed, IsIterableContainingInOrder.contains(1, 2, 3));
    }

}