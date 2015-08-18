package com.github.rjeschke.txtmark.dom;

import static com.github.rjeschke.txtmark.dom.NodeType.BLOCK_QUOTE;

public class BlockQuote extends MarkdownTreeNode {
    public BlockQuote() {
        super(BLOCK_QUOTE);
    }
}
