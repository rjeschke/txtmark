package com.github.rjeschke.txtmark.dom;

import java.util.List;

import static com.github.rjeschke.txtmark.dom.NodeType.CODE;

public class Code extends MarkdownTreeNode {
    private List<String> lines;

    public Code(List<String> lines) {
        super(CODE);
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }
}
