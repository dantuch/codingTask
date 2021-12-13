# codingTask

This is a java maven project

Main class: home.work.parser.Main

exapmle of proper input: "*/15 0 1,15 * 1-5 /usr/bin/find"

code structure:

Main - has a main method
ArgumentsReader - read agruments provided to the app, does some simple validation
CronExpressionParser - parses the data into model (CronExpression) that can later on be printed by: CronExpressionPrinter

Parsing is being done using ParsingStrategy - an interface that is being implemented by set of CronExpressionTypes Enums, each having their own 'parse' method and has it's own indicator - a character/String that has to be present in the input value to be interpretable by this parser.

There is also CronExpressionTimeField - Enum that specifies time fields usable by the tool: minute, hour, days, months, etc - with their max and min values. E.g. day of week has min val of 1, and max val of 7.


