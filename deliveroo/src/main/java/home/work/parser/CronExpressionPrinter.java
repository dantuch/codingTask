package home.work.parser;

import home.work.parser.model.CronExpression;
import home.work.parser.model.CronExpressionTimeField;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CronExpressionPrinter {

    public static final String COMMAND = "command";
    public static final int EXPECTED_NAME_LENGTH = 14;

    public void print(CronExpression expression) {
        printTimeFields(expression);
        printCommandField(expression);
    }

    private void printTimeFields(CronExpression expression) {
        for (Map.Entry<CronExpressionTimeField, List<Integer>> timeFieldAndValues : expression.getTimeValues().entrySet()) {
            String nameToPrint = getName14CharsLong(timeFieldAndValues.getKey().getName());
            String values = timeFieldAndValues.getValue().stream().map(String::valueOf).collect(Collectors.joining(" "));
            System.out.println(nameToPrint + values);
        }
    }

    private void printCommandField(CronExpression expression) {
        System.out.println(getName14CharsLong(COMMAND) + expression.getCommand());
    }

    private String getName14CharsLong(String name) {
        StringBuilder sb = new StringBuilder(name);
        while (sb.length() < EXPECTED_NAME_LENGTH) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
