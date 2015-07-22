package com.github.rjeschke.txtmark;

import com.github.rjeschke.txtmark.dom.MarkdownDocumentEmitter;
import com.github.rjeschke.txtmark.dom.html.HtmlDocumentProcessor;
import com.github.rjeschke.txtmark.dom.markdown.MarkdownDocumentProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@RunWith(Parameterized.class)
public class DocumentTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        List<Object[]> cases = new ArrayList<Object[]>();
        cases.add(makeCase("markdown-1"));
        cases.add(makeCase("markdown-2"));
        cases.add(makeCase("markdown-3"));
        cases.add(makeCase("mega"));
        cases.add(makeCase("mega-2"));
        cases.add(makeCase("api"));
        return cases;
    }

    private static Object[] makeCase(String name) {
        return new Object[]{
                new File("src/test/resources/" + name + ".md"),
                new File("src/test/resources/" + name + ".ignore.regenerated.md"),
                new File("src/test/resources/" + name + ".expected.html"),
                new File("src/test/resources/" + name + ".ignore.html"),
                new File("src/test/resources/" + name + "-default.ignore.html"),
        };
    }

    @Parameterized.Parameter(0)
    public File mdFile;

    @Parameterized.Parameter(1)
    public File regeneratedMdFile;

    @Parameterized.Parameter(2)
    public File htmlFile;

    @Parameterized.Parameter(3)
    public File actualHtmlFile;

    @Parameterized.Parameter(4)
    public File actual2HtmlFile;

    @Test
    public void inputMarkdown() throws IOException {
        for (String line : Files.readAllLines(mdFile.toPath())) {
            System.out.println(line);
        }
    }

    @Test
    public void loggingEmitter() throws IOException {
        Emitter emitter = new MyEmitter();

        Processor.process(mdFile, Configuration.builder()
                .setEmitter(emitter)
                .build());
    }

    @Test
    public void defaultEmitter() throws IOException {
        String html = Processor.process(mdFile);
        Files.write(actual2HtmlFile.toPath(), html.getBytes(UTF_8));
    }

    @Test
    public void markdownDocumentEmitter() throws IOException {
        MarkdownDocumentEmitter emitter = new MarkdownDocumentEmitter();
        Configuration configuration = Configuration.builder().setEmitter(emitter).build();

        Processor.process(mdFile, configuration);

        HtmlDocumentProcessor.Buffered html = new HtmlDocumentProcessor.Buffered();
        MarkdownDocumentProcessor.Buffered markdown = new MarkdownDocumentProcessor.Buffered();

        emitter.process(html, markdown);

        System.out.println("HTML ----------------------------------------------");
        System.out.println(html.buffer.toString());
        Files.write(actualHtmlFile.toPath(), html.buffer.toString().getBytes(UTF_8));

        System.out.println("MARKDOWN ------------------------------------------");
        System.out.println(markdown.buffer.toString());
        Files.write(regeneratedMdFile.toPath(), markdown.buffer.toString().getBytes(UTF_8));
    }

    private static class MyEmitter implements Emitter {
        boolean extensions = false;

        @Override
        public void addLinkRef(String key, LinkRef linkRef) {
            System.out.println("ExtensionsTest.addLinkRef");
            System.out.println("key = " + key);
            System.out.println("linkRef = " + linkRef);
        }

        @Override
        public void emit(StringBuilder out, Block root) {
            emit(root, 0);
        }

        private void emit(Block root, int indent) {
            for (int i = 0; i < indent; i++) {
                System.out.print(' ');
            }
            System.out.println("emit: " + root.type + (root.id != null ? ", id=" + root.id : "") + ", lines=" + root.hasLines() + ", blocks=" + root.hasBlocks());

            root = root.blocks;
            while (root != null) {
                emit(root, indent + 1);
                root = root.blocks;
            }
        }

        @Override
        public void useExtensions(boolean useExtensions) {
            this.extensions = useExtensions;
        }
    }
}
