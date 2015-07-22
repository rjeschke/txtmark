package com.github.rjeschke.txtmark.dom;

import java.util.HashMap;
import java.util.Map;

/**
 * A reference to the parent might be useful.
 */
public class MarkdownNode {
    private final NodeType type;
    private final Map<String, String> attributes = new HashMap<String, String>();

    public MarkdownNode(NodeType type) {
        this.type = type;
    }

    public final NodeType getType() {
        return type;
    }

    public void setAttribute(String name, String value) {
        attributes.put(name, value);
    }

    public String getAttribute(String name) {
        return attributes.get(name);
    }
}
