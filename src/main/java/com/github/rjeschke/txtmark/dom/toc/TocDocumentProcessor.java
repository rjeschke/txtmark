package com.github.rjeschke.txtmark.dom.toc;

import com.github.rjeschke.txtmark.Line;
import com.github.rjeschke.txtmark.dom.DocumentProcessor;
import com.github.rjeschke.txtmark.dom.Headline;
import com.github.rjeschke.txtmark.dom.ListItem;
import com.github.rjeschke.txtmark.dom.MarkdownDocument;
import com.github.rjeschke.txtmark.dom.MarkdownNode;
import com.github.rjeschke.txtmark.dom.NodeType;
import com.github.rjeschke.txtmark.dom.Text;
import com.github.rjeschke.txtmark.dom.UnorderedList;

import java.util.ArrayList;
import java.util.List;

public class TocDocumentProcessor implements DocumentProcessor {

    public static class TocEntry {
        public String id;
        public int level;
        public String text;
        public List<TocEntry> children;

        public TocEntry(String id, int level, String text) {
            this.id = id;
            this.level = level;
            this.text = text;
            this.children = new ArrayList<TocEntry>();
        }
    }

    public List<TocEntry> entries;

    private String headerText = "Table of Contents";
    private boolean injectToc = true;

    public TocDocumentProcessor headerText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public TocDocumentProcessor injectToc(boolean injectToc) {
        this.injectToc = injectToc;
        return this;
    }

    @Override
    public void process(MarkdownDocument doc) {
        entries = new ArrayList<TocEntry>();
        List<TocEntry> stack = new ArrayList<TocEntry>();

        for (MarkdownNode node : doc) {
            if (node.getType() == NodeType.HEADLINE) {
                Headline headline = (Headline) node;
                String id = toId(headline.text());
                headline.setAttribute("id", id);
                TocEntry entry = new TocEntry(id, headline.level, headline.text());

                if (entry.level == 1) {
                    entries.add(entry);
                    stack.clear();
                    stack.add(entry);
                } else {
                    TocEntry found = null;
                    for (int i = 0; i < stack.size(); i++) {
                        TocEntry parent = stack.get(i);

                        if (parent.level == entry.level - 1) {
                            found = parent;
                            for (int j = stack.size() - 1; j > i; j--) {
                                stack.remove(j);
                            }
                            stack.add(entry);
                            break;
                        }
                    }

                    // We're missing intermediate levels
                    if (found == null) {
                        found = stack.get(stack.size() - 1);
                        for (int i = stack.size(); i < entry.level; i++) {
                            TocEntry tmp = new TocEntry(null, i, "");
                            found.children.add(tmp);
                            found = tmp;
                            stack.add(found);
                        }
                    }

                    found.children.add(entry);
                }
            }
        }

        if (injectToc) {
            List<MarkdownNode> toc = createToc(entries);

            doc.addChildren(0, toc);
        }
    }

    public List<MarkdownNode> createToc(List<TocEntry> entries) {
        List<MarkdownNode> toc = new ArrayList<MarkdownNode>();

        toc.add(new Headline(1, line(headerText)));
        toc.add(create(entries));
        return toc;
    }

    public UnorderedList create(List<TocEntry> entries) {
        UnorderedList toc = new UnorderedList();

        for (TocEntry entry : entries) {
            ListItem li = toc.addChild(new ListItem());
            String text = "entry: " + entry.text + ", id=" + entry.id;
            li.children.add(new Text(line(text)));

            if (!entry.children.isEmpty()) {
                li.children.add(create(entry.children));
            }
        }

        return toc;
    }

    private Line line(String text) {
        Line line = new Line();
        line.value = text;
        return line;
    }

    public static String toId(String text) {
        return text
                .toLowerCase()
                .replaceAll("  ", " ")
                .replaceAll(" ", "-")
                .replaceAll("'", "");
    }
}
