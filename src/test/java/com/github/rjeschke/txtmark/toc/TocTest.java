package com.github.rjeschke.txtmark.toc;

import com.github.rjeschke.txtmark.Configuration;
import com.github.rjeschke.txtmark.Processor;
import com.github.rjeschke.txtmark.dom.MarkdownDocumentEmitter;
import com.github.rjeschke.txtmark.dom.html.HtmlDocumentProcessor;
import com.github.rjeschke.txtmark.dom.markdown.MarkdownDocumentProcessor;
import com.github.rjeschke.txtmark.dom.toc.TocDocumentProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TocTest {

    @Parameterized.Parameter(0)
    public File mdFile;

    @Parameterized.Parameter(1)
    public File htmlFile;

    @Parameterized.Parameter(2)
    public File actualHtmlFile;

    @Parameterized.Parameter(3)
    public File actual2HtmlFile;

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        List<Object[]> cases = new ArrayList<Object[]>();
        cases.add(createCase("toc-1"));
        return cases;
    }

    private static Object[] createCase(String name) {
        return new Object[]{
                new File("src/test/resources/toc/" + name + ".md"),
                new File("src/test/resources/toc/" + name + ".html"),
                new File("src/test/resources/toc/" + name + ".ignore.html"),
                new File("src/test/resources/toc/" + name + "-default.ignore.html"),
        };
    }

    @Test
    public void inputMarkdown() throws IOException {
        for (String line : Files.readAllLines(mdFile.toPath())) {
            System.out.println(line);
        }
    }

    @Test
    public void createToc() throws IOException {
        MarkdownDocumentEmitter emitter = new MarkdownDocumentEmitter();
        Configuration configuration = Configuration.builder().setEmitter(emitter).build();

        Processor.process(mdFile, configuration);

        TocDocumentProcessor toc = new TocDocumentProcessor();
        MarkdownDocumentProcessor.Buffered markdown = new MarkdownDocumentProcessor.Buffered();
        HtmlDocumentProcessor.Buffered html = new HtmlDocumentProcessor.Buffered();
        emitter.process(toc, markdown, html);

        CharArrayWriter buf = new CharArrayWriter();
        mkString(new PrintWriter(buf), toc.entries, 0);
        System.out.println("TOC -------------------------------------------------------------");
        System.out.println(buf);
        System.out.println("MARKDOWN --------------------------------------------------------");
        System.out.println(markdown.buffer.toString());
        System.out.println("HTML ------------------------------------------------------------");
        System.out.println(html.buffer.toString());
    }

    public void mkString(PrintWriter out, Collection<TocDocumentProcessor.TocEntry> entries, int indent) {
        for (TocDocumentProcessor.TocEntry entry : entries) {
            for (int i = 0; i < indent; i++) {
                out.print(' ');
            }

            out.println("level=" + entry.level + ", id=" + entry.id + ", entry.text=" + entry.text);
            mkString(out, entry.children, indent + 1);
        }
    }
}
