package home.work.parser;

import home.work.parser.model.CronExpressionTimeField;
import home.work.parser.model.CronExpressionTypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CronExpressionParser {

    private CronExpressionTimeField cronExpressionPart;


    public CronExpressionParser(CronExpressionTimeField cronExpressionPart) {
        this.cronExpressionPart = cronExpressionPart;
    }

    public List<Integer> parse(String rawExpression) {
        for (CronExpressionTypes cronExpressionType : CronExpressionTypes.values()) {
            if (rawExpression.contains(cronExpressionType.getIndicator())) {
                return cronExpressionType.parse(rawExpression, cronExpressionPart);
            }
        }

        try {
            // if no special indicator is found, hopefully it's just a number.
            return Collections.singletonList(Integer.valueOf(rawExpression));
        } catch (NumberFormatException e) {
           throw new IllegalStateException(String.format("Unable to parse '%s' there is no matching parser for this format. " +
                   "Current parsers: %s", rawExpression, Arrays.toString(CronExpressionTypes.values())));
        }
    }
}
