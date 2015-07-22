package com.github.rjeschke.txtmark.dom;

import com.github.rjeschke.txtmark.Line;

import static com.github.rjeschke.txtmark.dom.NodeType.XML;

public class Xml extends MarkdownTreeNode {
    private Line lines;

    public Xml(Line lines) {
        super(XML);
        this.lines = lines;
    }

    public Line getLines() {
        return lines;
    }
}
