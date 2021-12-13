package home.work.parser;

import home.work.parser.model.CronExpression;
import home.work.parser.model.CronExpressionTimeField;

import java.util.Arrays;

public class ArgumentsReader {
    public static final String CRON_PARTS_SEPARATOR = " ";
    public static final int EXPECTED_INPUT_LENGTH = 6;
    public static final String EXAMPLE_OF_PROPER_INPUT = "example of proper input: '*/15 0 1,15 * 1-5 /usr/bin/find'";

    public CronExpression read(String[] args) {
        String[] rawExpressionParts = getInputIfValid(args);

        CronExpression cronExpression = new CronExpression();
        cronExpression.addTimeValue(CronExpressionTimeField.MINUTE, new CronExpressionParser(CronExpressionTimeField.MINUTE).parse(rawExpressionParts[0]));
        cronExpression.addTimeValue(CronExpressionTimeField.HOUR, new CronExpressionParser(CronExpressionTimeField.HOUR).parse(rawExpressionParts[1]));
        cronExpression.addTimeValue(CronExpressionTimeField.DAY_OF_MONTH, new CronExpressionParser(CronExpressionTimeField.DAY_OF_MONTH).parse(rawExpressionParts[2]));
        cronExpression.addTimeValue(CronExpressionTimeField.MONTH, new CronExpressionParser(CronExpressionTimeField.MONTH).parse(rawExpressionParts[3]));
        cronExpression.addTimeValue(CronExpressionTimeField.DAY_OF_WEEK, new CronExpressionParser(CronExpressionTimeField.DAY_OF_WEEK).parse(rawExpressionParts[4]));

        cronExpression.setCommand(rawExpressionParts[5]);

        return cronExpression;
    }

    private String[] getInputIfValid(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException(String.format("Input should be formatted as one String (not as %s separate Strings), " +
                    EXAMPLE_OF_PROPER_INPUT + " Current input: %s", args.length, Arrays.toString(args)));
        }

        String rawExpression = args[0];
        String[] rawExpressionParts = rawExpression.split(CRON_PARTS_SEPARATOR);
        if (rawExpressionParts.length != EXPECTED_INPUT_LENGTH) {
            throw new IllegalArgumentException(String.format("Input should be formatted as one String with a separator of '%s'" +
                    " and exactly %s (vs %s currently) values, " + EXAMPLE_OF_PROPER_INPUT + " Current input: %s", CRON_PARTS_SEPARATOR, EXPECTED_INPUT_LENGTH, args.length, Arrays.toString(args)));
        }
        return rawExpressionParts;
    }

}
