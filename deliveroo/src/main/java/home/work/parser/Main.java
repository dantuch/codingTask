package home.work.parser;

import home.work.parser.model.CronExpression;

public class Main {

    // example input: ~$ your-program ＂*/15 0 1,15 * 1-5 /usr/bin/find＂
    public static void main(String[] args) {
        ArgumentsReader argumentsReader = new ArgumentsReader();
        CronExpression expression = argumentsReader.read(args);
        new CronExpressionPrinter().print(expression);
    }
}
