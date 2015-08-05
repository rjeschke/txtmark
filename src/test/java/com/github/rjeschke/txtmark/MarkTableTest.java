package com.github.rjeschke.txtmark;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liuzhenchuan@foxmail.com on 8/5/15.
 */
public class MarkTableTest {

    private final String markdownTable =
            "| header1        | header2      |\n" +
            "| -------------  |:------------:|\n" +
            "| col1           | col2         |" ;

    private final String markdownTable2 =
            " header1        | header2      |\n" +
                    " -------------  |:------------:|\n" +
                    " col1           | col2         |" ;

    private final String expectedContent = "<table>\n" +
            "<thead><tr><th>header1</th><th>header2</th></tr></thead>\n" +
            "<tbody>\n" +
            "<tr><td>col1</td><td>col2</td></tr></tbody>\n" +
            "</table>";

    @Test
    public void testConfigure()
    {
        Configuration.Builder builder = Configuration.builder().setSafeMode(true)
                .forceExtentedProfile()
                .setAllowSpacesInFencedCodeBlockDelimiters(true)
                .setEncoding("UTF-8");

        Configuration renderConfig = builder
                .build();
        String parsedContent = Processor.process(markdownTable, renderConfig);
        Assert.assertFalse(parsedContent.contains("<table>"));

        renderConfig = builder.enableParseTable()
                .build();
        parsedContent = Processor.process(markdownTable, renderConfig);
        Assert.assertTrue(parsedContent.contains("<table>"));
    }

    @Test
    public void testOutputTable()
    {
        Configuration renderConfig = Configuration.builder().setSafeMode(true)
                .forceExtentedProfile()
                .enableParseTable()
                .setAllowSpacesInFencedCodeBlockDelimiters(true)
                .setEncoding("UTF-8")
                .build();
        String parsedContent = Processor.process(markdownTable, renderConfig);
        Assert.assertEquals(expectedContent,parsedContent);

        parsedContent = Processor.process(markdownTable2, renderConfig);
        Assert.assertEquals(expectedContent,parsedContent);
    }

}
