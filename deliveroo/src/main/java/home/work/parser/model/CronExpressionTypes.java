package home.work.parser.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum CronExpressionTypes implements ParsingStrategy {

    EVERY_X_TIMES("/") {
        @Override
        public List<Integer> parse(String content, CronExpressionTimeField timeField) {
            Integer everyX = Integer.valueOf(content.substring(content.indexOf(getIndicator()) + 1));

            return getValuesFromMinToMax(timeField)
                    .filter(n -> n % everyX == 0)
                    .collect(Collectors.toList());
        }
    },

    FROM_TO("-") {
        @Override
        public List<Integer> parse(String content, CronExpressionTimeField timeField) {
            String[] minMax = content.split(getIndicator());
            int min = Integer.valueOf(minMax[0]);
            int max = Integer.valueOf(minMax[1]);

            return getValuesFromMinToMax(timeField)
                    .filter(n -> min <= n && n <= max)
                    .collect(Collectors.toList());
        }
    },

    NUMBERS_COMMA_SEPARATED(",") {
        @Override
        public List<Integer> parse(String content, CronExpressionTimeField timeField) {
            Set<Integer> searchedValues = Arrays.stream(content.split(getIndicator()))
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());

            return getValuesFromMinToMax(timeField)
                    .filter(searchedValues::contains)
                    .collect(Collectors.toList());
        }
    },

    ALL_OF("*") {
        @Override
        public List<Integer> parse(String content, CronExpressionTimeField timeField) {
            return getValuesFromMinToMax(timeField)
                    .collect(Collectors.toList());
        }
    };

    private String indicator;

    CronExpressionTypes(String indicator) {
        this.indicator = indicator;
    }

    public String getIndicator() {
        return indicator;
    }

    private static Stream<Integer> getValuesFromMinToMax(CronExpressionTimeField timeField) {
        return IntStream.rangeClosed(timeField.getMinValue(), timeField.getMaxValue()).boxed();
    }

}
