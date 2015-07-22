package com.github.rjeschke.txtmark;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MarkdownTestSuiteTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        File[] files = new File("markdown-testsuite/tests").listFiles();

        // Just to keep these tests from failing when the markdown test suite is not checked out where expected.
        if (files == null) {
            return Collections.emptyList();
        }

        Arrays.sort(files);

        List<Object[]> cases = new ArrayList<Object[]>();
        for (File mdFile : files) {
            if (mdFile.getName().endsWith(".md")) {
                String name = mdFile.getAbsolutePath().substring(0, mdFile.getAbsolutePath().length() - 3);
                File htmlFile = new File(name + ".out");
                cases.add(new Object[]{name, mdFile, htmlFile});
            }
        }

        return cases;
    }

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public File mdFile;

    @Parameterized.Parameter(2)
    public File htmlFile;

    @Test
    public void testAll() throws IOException {
        String markdown = toString(mdFile);
        String html = Processor.process(markdown).trim();
        String expectedHtml = toString(htmlFile).trim();

        System.out.println("- " + name + ":");
        System.out.println(markdown);
        System.out.println("- EXPECTED -------------------------------------------------------------------------------");
        System.out.println(expectedHtml);
        System.out.println("- ACTUAL ---------------------------------------------------------------------------------");
        System.out.println(html);

        assertEquals(expectedHtml, html);
    }

    private String toString(File file) throws IOException {
        return Files.lines(file.toPath()).collect(Collectors.joining("\n"));
    }
}
