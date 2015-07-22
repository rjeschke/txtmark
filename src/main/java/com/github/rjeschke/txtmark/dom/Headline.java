package com.github.rjeschke.txtmark.dom;

import com.github.rjeschke.txtmark.Line;

import static com.github.rjeschke.txtmark.dom.NodeType.HEADLINE;

/**
 * Should probably be a leaf node. It would be very useful if this could contain only a "text" field instead of a list
 * of lines.
 */
public class Headline extends MarkdownTreeNode {
    public int level;
    public Line lines;

    private static final String EOL = System.getProperty("line.separator");

    public Headline(int level, Line lines) {
        super(HEADLINE);
        this.level = level;
        this.lines = lines;
    }

    public String text() {
        StringBuilder buf = new StringBuilder();

        Line line = this.lines;
        while (line != null) {
            if (buf.length() != 0) {
                buf.append(EOL);
            }
            buf.append(line.value);
            line = line.next;
        }

        return buf.toString();
    }
}
