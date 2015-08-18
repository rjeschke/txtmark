package com.github.rjeschke.txtmark.dom.markdown;

import com.github.rjeschke.txtmark.Line;
import com.github.rjeschke.txtmark.dom.Code;
import com.github.rjeschke.txtmark.dom.DocumentProcessor;
import com.github.rjeschke.txtmark.dom.Headline;
import com.github.rjeschke.txtmark.dom.MarkdownDocument;
import com.github.rjeschke.txtmark.dom.MarkdownNode;
import com.github.rjeschke.txtmark.dom.MarkdownTreeNode;
import com.github.rjeschke.txtmark.dom.Paragraph;
import com.github.rjeschke.txtmark.dom.Text;
import com.github.rjeschke.txtmark.dom.Xml;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.List;

import static com.github.rjeschke.txtmark.dom.NodeType.BLOCK_QUOTE;
import static com.github.rjeschke.txtmark.dom.NodeType.CODE;
import static com.github.rjeschke.txtmark.dom.NodeType.DOCUMENT;
import static com.github.rjeschke.txtmark.dom.NodeType.HEADLINE;
import static com.github.rjeschke.txtmark.dom.NodeType.LIST_ITEM;
import static com.github.rjeschke.txtmark.dom.NodeType.PARAGRAPH;
import static com.github.rjeschke.txtmark.dom.NodeType.TEXT;
import static com.github.rjeschke.txtmark.dom.NodeType.UNORDERED_LIST;
import static com.github.rjeschke.txtmark.dom.NodeType.XML;

public class MarkdownDocumentProcessor implements DocumentProcessor {
    private final PrintWriter out;
    private int indent;
    private Character prefix;

    public MarkdownDocumentProcessor(PrintWriter out) {
        this.out = out;
    }

    public static class Buffered implements DocumentProcessor {
        public final CharArrayWriter buffer = new CharArrayWriter();

        private final MarkdownDocumentProcessor processor = new MarkdownDocumentProcessor(new PrintWriter(buffer));

        @Override
        public void process(MarkdownDocument doc) {
            processor.process(doc);
        }
    }

    @Override
    public void process(MarkdownDocument doc) {
        indent = 0;
        process((Iterable<MarkdownNode>) doc);
    }

    public void process(MarkdownNode node) {
        process(((MarkdownTreeNode) node).children);
    }

    public void process(Iterable<MarkdownNode> nodes) {
        for (MarkdownNode node : nodes) {
            if (node.getType() == BLOCK_QUOTE) {
                prefix = '>';
                indent += 4;
                process(node);
                indent -= 4;
                prefix = null;
            } else if (node.getType() == DOCUMENT) {
                process(node);
            } else if (node.getType() == CODE) {
                Code code = (Code) node;
                indent += 4;
                println();
                printLines(code.getLines());
                indent -= 4;
                println();
            } else if (node.getType() == HEADLINE) {
                Headline headline = (Headline) node;

                println();
                for (int i = 0; i < headline.level; i++) {
                    print("#");
                }
                print(" ");
                println(headline.text());
            } else if (node.getType() == PARAGRAPH) {
                Paragraph paragraph = (Paragraph) node;
                println();
                printLines(paragraph.getLines());
            } else if (node.getType() == LIST_ITEM) {
                indent += 4;
                println();
                print("* ");
                process(node);
                indent -= 4;
            } else if (node.getType() == TEXT) {
                Text text = (Text) node;
                printLines(text.getLines());
            } else if (node.getType() == XML) {
                Xml xml = (Xml) node;
                printLines(xml.getLines());
                println();
            } else if (node.getType() == UNORDERED_LIST) {
                process(node);
                println();
            }
        }
    }

    void println(String s) {
        out.print(s);
        println();
    }

    private void println() {
        out.println();
        if (prefix != null) {
            out.print(prefix);
        }
        for (int i = 0; i < indent; i++) {
            out.print(' ');
        }
    }

    void print(String s) {
        out.print(s);
    }

    void print(int i) {
        out.print(i);
    }

    public void printLines(Line line) {
        while (line != null) {
            print(line.value);
            line = line.next;
            if(line != null) {
                println();
            }
        }
    }

    public void printLines(List<String> line) {
        boolean first = true;
        for (String s : line) {
            if (first) {
                first = false;
            } else {
                println();
            }
            print(s);
        }
    }
}
