package home.work.parser.model;

import java.util.List;

public interface ParsingStrategy {

    List<Integer> parse(String content, CronExpressionTimeField cronExpressionPart);
    String getIndicator();

}
