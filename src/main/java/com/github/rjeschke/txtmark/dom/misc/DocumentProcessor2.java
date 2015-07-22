package com.github.rjeschke.txtmark.dom.misc;

import com.github.rjeschke.txtmark.dom.BlockQuote;
import com.github.rjeschke.txtmark.dom.Code;
import com.github.rjeschke.txtmark.dom.Headline;
import com.github.rjeschke.txtmark.dom.ListItem;
import com.github.rjeschke.txtmark.dom.MarkdownDocument;
import com.github.rjeschke.txtmark.dom.MarkdownEventHandler;
import com.github.rjeschke.txtmark.dom.MarkdownNode;
import com.github.rjeschke.txtmark.dom.MarkdownTreeNode;
import com.github.rjeschke.txtmark.dom.NodeType;
import com.github.rjeschke.txtmark.dom.Paragraph;
import com.github.rjeschke.txtmark.dom.Text;
import com.github.rjeschke.txtmark.dom.UnorderedList;

public class DocumentProcessor2 {
    private final MarkdownDocument doc;
    private final MarkdownEventHandler handler;

    public DocumentProcessor2(MarkdownDocument doc, MarkdownEventHandler handler) {
        this.doc = doc;
        this.handler = handler;
    }

    public void process() {
        handler.onStartDocument(doc);
        processChildren(doc);
        handler.onEndDocument(doc);
    }

    protected void processChildren(MarkdownTreeNode nodes) {
        for (MarkdownNode node : nodes) {
            NodeType type = node.getType();
            if (type == NodeType.BLOCK_QUOTE) {
                BlockQuote blockQuote = (BlockQuote) node;
                handler.onStartBlockQuote(blockQuote);
                processChildren(blockQuote);
                handler.onEndBlockQuote(blockQuote);
            } else if (type == NodeType.CODE) {
                Code code = (Code) node;
                handler.onStartCode(code);
                processChildren(code);
                handler.onEndCode(code);
            } else if (type == NodeType.HEADLINE) {
                Headline headline = (Headline) node;
                handler.onStartHeadline(headline);
                processChildren(headline);
                handler.onEndHeadline(headline);
            } else if (type == NodeType.LIST_ITEM) {
                ListItem listItem = (ListItem) node;
                handler.onBeginListItem(listItem);
                processChildren(listItem);
                handler.onEndListItem(listItem);
            } else if (type == NodeType.PARAGRAPH) {
                Paragraph paragraph = (Paragraph) node;
                handler.onStartParagraph(paragraph);
                processChildren(paragraph);
                handler.onEndParagraph(paragraph);
            } else if (type == NodeType.TEXT) {
                Text text = (Text) node;
                handler.onText(text);
            } else if (type == NodeType.UNORDERED_LIST) {
                UnorderedList unorderedList = (UnorderedList) node;
                handler.onBeginUnorderedList(unorderedList);
                processChildren(unorderedList);
                handler.onEndUnorderedList(unorderedList);
            } else {
                handler.onUnknownType(node);
            }
        }
    }
}
