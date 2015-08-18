package com.github.rjeschke.txtmark.dom;

import com.github.rjeschke.txtmark.Line;

import static com.github.rjeschke.txtmark.dom.NodeType.PARAGRAPH;

public class Paragraph extends MarkdownTreeNode {
    private Line lines;

    public Paragraph(Line lines) {
        super(PARAGRAPH);
        this.lines = lines;
    }

    public Line getLines() {
        return lines;
    }
}
