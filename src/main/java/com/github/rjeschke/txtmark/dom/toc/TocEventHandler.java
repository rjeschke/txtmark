package com.github.rjeschke.txtmark.dom.toc;

import com.github.rjeschke.txtmark.dom.BlockQuote;
import com.github.rjeschke.txtmark.dom.Headline;
import com.github.rjeschke.txtmark.dom.MarkdownEventHandler;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A Table of Contents builder from Markdown events.
 *
 * This is probably not the way to do it, as other processors could change the document before one would want the ToC
 * created.
 */
public class TocEventHandler extends MarkdownEventHandler {
    private List<Headline> headlines = new ArrayList<Headline>();

    private int maxLevel;
    private int lastLevel;

    List<Integer> countsByLevel;

    private boolean disabled;

    public TocEventHandler(int maxLevel) {
        this.maxLevel = maxLevel;

        countsByLevel = new ArrayList<Integer>(maxLevel + 1);

        for (int i = 0; i < maxLevel + 1; i++) {
            countsByLevel.add(0);
        }
    }

    @Override
    public void onStartHeadline(Headline headline) {
        if (disabled) {
            return;
        }

        if (headline.level > maxLevel) {
            return;
        }

        Integer count;

        if (headline.level < lastLevel) {
            count = 0;
        } else {
            count = countsByLevel.get(headline.level);
        }

        count = count + 1;
        countsByLevel.set(headline.level, count);

//        System.out.print("Counts: ");
//        for (int i = 1; i < countsByLevel.size(); i++) {
//            Integer integer = countsByLevel.get(i);
//            System.out.print(integer + " ");
//        }
//        System.out.println();

        String id = "";
        for (int i = 1; i <= headline.level; i++) {
            Integer c = countsByLevel.get(i);
            if (id.isEmpty()) {
                id = c.toString();
            } else {
                id += "-" + c;
            }
        }

        headline.setAttribute("headline-level-id", id);
        headline.setAttribute("headline-level-count", count.toString());
        headlines.add(headline);
    }

    @Override
    public void onStartBlockQuote(BlockQuote blockQuote) {
        disabled = true;
    }

    @Override
    public void onEndBlockQuote(BlockQuote blockQuote) {
        disabled = false;
    }

    public List<Headline> getHeadlines() {
        return headlines;
    }

    public String asMarkdown() {
        CharArrayWriter buf = new CharArrayWriter();
        PrintWriter out = new PrintWriter(buf);

        for (Headline headline : headlines) {
            for (int i = 1; i < headline.level; i++) {
                out.print("    ");
            }
            out.print("* [" + headline.text() + "](#headline-" + headline.getAttribute("headline-level-id") + ")");
            out.println();

            lastLevel = headline.level;
        }

        return buf.toString();
    }
}
