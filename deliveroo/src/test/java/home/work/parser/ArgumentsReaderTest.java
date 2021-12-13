package home.work.parser;

import home.work.parser.model.CronExpression;
import org.junit.Assert;
import org.junit.Test;

public class ArgumentsReaderTest {


    @Test
    public void shouldParseValidInput() {
        //given
        String [] input = new String[] {"/15 0 1,15 * 1-5 /usr/bin/find"};
        ArgumentsReader argumentsReader = new ArgumentsReader();
        //when
        CronExpression read = argumentsReader.read(input);

        //then
        Assert.assertEquals("/usr/bin/find", read.getCommand());
    }
}