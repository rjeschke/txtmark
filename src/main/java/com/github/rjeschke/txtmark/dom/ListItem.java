package com.github.rjeschke.txtmark.dom;

import com.github.rjeschke.txtmark.Line;

import static com.github.rjeschke.txtmark.dom.NodeType.LIST_ITEM;

public class ListItem extends MarkdownTreeNode {
//    private Line lines;

    public ListItem(/*Line lines*/) {
        super(LIST_ITEM);
//        this.lines = lines;
    }

//    public Line getLines() {
//        return lines;
//    }
}
