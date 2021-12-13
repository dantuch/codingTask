package home.work.parser.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CronExpression {

    private Map<CronExpressionTimeField, List<Integer>> timeValues = new LinkedHashMap<>();
    private String command;

    public void addTimeValue(CronExpressionTimeField timeField, List<Integer> parsedValues) {
        timeValues.put(timeField, parsedValues);
    }

    public Map<CronExpressionTimeField, List<Integer>> getTimeValues() {
        return timeValues;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
