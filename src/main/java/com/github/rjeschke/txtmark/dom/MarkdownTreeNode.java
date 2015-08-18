package com.github.rjeschke.txtmark.dom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MarkdownTreeNode extends MarkdownNode implements Iterable<MarkdownNode> {
    public List<MarkdownNode> children = new ArrayList<MarkdownNode>();

    public MarkdownTreeNode(NodeType type) {
        super(type);
    }

    @Override
    public Iterator<MarkdownNode> iterator() {
        return children.iterator();
    }

    public <T extends MarkdownNode> T addChild(T node) {
        children.add(node);
        return node;
    }

    public void addChild(int index, MarkdownNode node) {
        children.add(index, node);
    }

    public void addChildren(int index, List<MarkdownNode> node) {
        children.addAll(index, node);
    }
}
