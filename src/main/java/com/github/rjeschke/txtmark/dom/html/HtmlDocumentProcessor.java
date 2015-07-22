package com.github.rjeschke.txtmark.dom.html;

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

import static com.github.rjeschke.txtmark.dom.MarkdownDocumentEmitter.printLines;
import static com.github.rjeschke.txtmark.dom.NodeType.BLOCK_QUOTE;
import static com.github.rjeschke.txtmark.dom.NodeType.CODE;
import static com.github.rjeschke.txtmark.dom.NodeType.DOCUMENT;
import static com.github.rjeschke.txtmark.dom.NodeType.HEADLINE;
import static com.github.rjeschke.txtmark.dom.NodeType.LIST_ITEM;
import static com.github.rjeschke.txtmark.dom.NodeType.PARAGRAPH;
import static com.github.rjeschke.txtmark.dom.NodeType.TEXT;
import static com.github.rjeschke.txtmark.dom.NodeType.UNORDERED_LIST;
import static com.github.rjeschke.txtmark.dom.NodeType.XML;

public class HtmlDocumentProcessor implements DocumentProcessor {
    private final PrintWriter out;

    public HtmlDocumentProcessor(PrintWriter out) {
        this.out = out;
    }

    public static class Buffered implements DocumentProcessor {
        public final CharArrayWriter buffer = new CharArrayWriter();

        private final HtmlDocumentProcessor processor = new HtmlDocumentProcessor(new PrintWriter(buffer));

        @Override
        public void process(MarkdownDocument doc) {
            processor.process(doc);
        }
    }

    @Override
    public void process(MarkdownDocument doc) {
        process((Iterable<MarkdownNode>) doc);
    }

    public void process(MarkdownNode node) {
        process(((MarkdownTreeNode) node).children);
    }

    public void process(Iterable<MarkdownNode> nodes) {
        for (MarkdownNode node : nodes) {
            if (node.getType() == BLOCK_QUOTE) {
                out.println("<blockquote>");
                process(node);
                out.println("</blockquote>");
            } else if (node.getType() == DOCUMENT) {
                out.println("<html>");
                process(node);
                out.println("</html>");
            } else if (node.getType() == CODE) {
                Code code = (Code) node;
                out.println("<pre>");
                printLines(code.getLines(), out);
                out.println("</pre>");
            } else if (node.getType() == HEADLINE) {
                Headline headline = (Headline) node;
                String id = headline.getAttribute("id");

                out.print("<h");
                out.print(headline.level);
                if (id != null) {
                    out.print(" id=\"");
                    out.print(id);
                    out.print("\"");
                }
                out.print(">");
                out.print(headline.text());
                out.print("</h");
                out.print(headline.level);
                out.println(">");
            } else if (node.getType() == PARAGRAPH) {
                Paragraph paragraph = (Paragraph) node;
                out.println("<p>");
                printLines(paragraph.getLines(), out);
                out.println("</p>");
            } else if (node.getType() == LIST_ITEM) {
                out.print("<li>");
                process(node);
                out.println("</li>");
            } else if (node.getType() == TEXT) {
                Text text = (Text) node;
                printLines(text.getLines(), out);
            } else if (node.getType() == XML) {
                Xml xml = (Xml) node;
                printLines(xml.getLines(), out);
            } else if (node.getType() == UNORDERED_LIST) {
                out.print("<ul>");
                process(node);
                out.println("</ul>");
            }
        }
    }
}
