package com.github.rjeschke.txtmark.dom;

import com.github.rjeschke.txtmark.Line;

import static com.github.rjeschke.txtmark.dom.NodeType.PARAGRAPH;
import static com.github.rjeschke.txtmark.dom.NodeType.TEXT;

public class Text extends MarkdownTreeNode {
    private Line lines;

    public Text(Line lines) {
        super(TEXT);
        this.lines = lines;
    }

    public Line getLines() {
        return lines;
    }
}
