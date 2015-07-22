package com.github.rjeschke.txtmark.dom.misc;

import com.github.rjeschke.txtmark.Line;
import com.github.rjeschke.txtmark.dom.BlockQuote;
import com.github.rjeschke.txtmark.dom.Code;
import com.github.rjeschke.txtmark.dom.Headline;
import com.github.rjeschke.txtmark.dom.ListItem;
import com.github.rjeschke.txtmark.dom.MarkdownDocument;
import com.github.rjeschke.txtmark.dom.MarkdownEventHandler;
import com.github.rjeschke.txtmark.dom.MarkdownNode;
import com.github.rjeschke.txtmark.dom.Paragraph;
import com.github.rjeschke.txtmark.dom.Text;
import com.github.rjeschke.txtmark.dom.UnorderedList;

import java.io.PrintWriter;

import static com.github.rjeschke.txtmark.dom.MarkdownDocumentEmitter.printLines;

public class HtmlGenerator extends MarkdownEventHandler {
    private PrintWriter out;
    private boolean fullDocument;

    public HtmlGenerator(PrintWriter out, boolean fullDocument) {
        this.out = out;
        this.fullDocument = fullDocument;
    }

    @Override
    public void onUnknownType(MarkdownNode node) {
        throw new RuntimeException("Unknown node type: " + node.getType());
    }

    @Override
    public void onStartDocument(MarkdownDocument doc) {
        if (fullDocument) {
            out.println("<html><body>");
        }
    }

    @Override
    public void onEndDocument(MarkdownDocument doc) {
        if (fullDocument) {
            out.println("</body></html>");
        }
    }

    @Override
    public void onStartHeadline(Headline headline) {
        out.print("<h" + headline.level + ">");
        Line line = headline.lines;
        boolean first = true;
        while (line != null) {
            if (!first) {
                out.println();
            } else {
                first = false;
            }
            out.print(line.value);
            line = line.next;
        }
    }

    @Override
    public void onEndHeadline(Headline headline) {
        out.println("</h" + headline.level + ">");
    }

    public void onStartParagraph(Paragraph paragraph) {
        out.print("<p>");
        printLines(paragraph.getLines(), out);
    }

    @Override
    public void onEndParagraph(Paragraph paragraph) {
        out.println("</p>");
    }

    @Override
    public void onStartBlockQuote(BlockQuote blockQuote) {
        out.println("<blockquote>");
    }

    @Override
    public void onEndBlockQuote(BlockQuote blockQuote) {
        out.println("</blockquote>");
    }

    @Override
    public void onBeginUnorderedList(UnorderedList unorderedList) {
        out.println("<ul>");
    }

    @Override
    public void onEndUnorderedList(UnorderedList unorderedList) {
        out.println("</ul>");
    }

    @Override
    public void onBeginListItem(ListItem listItem) {
        out.print("<li>");
        System.out.println("listItem.children.size() = " + listItem.children.size());
//        printLines("<li>", listItem.getLines());
    }

    @Override
    public void onEndListItem(ListItem listItem) {
        out.println("</li>");
    }

    @Override
    public void onStartCode(Code code) {
        out.print("<code>");
        printLines(code.getLines(), out);
    }

    @Override
    public void onEndCode(Code code) {
        out.println("</pre>");
    }

    public void onText(Text text) {
        printLines(text.getLines(), out);
    }
}
