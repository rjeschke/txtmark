package com.github.rjeschke.txtmark.dom;

/**
 * A class instead of an interface to be able to add more methods later.
 * <p/>
 * TODO: Only Tree nodes should have begin/end, the rest should have onTYPE(..)
 */
public abstract class MarkdownEventHandler {
    public void onUnknownType(MarkdownNode node) {
    }

    public void onStartDocument(MarkdownDocument doc) {
    }

    public void onEndDocument(MarkdownDocument doc) {
    }

    public void onStartHeadline(Headline headline) {
    }

    public void onEndHeadline(Headline headline) {
    }

    public void onStartParagraph(Paragraph paragraph) {
    }

    public void onEndParagraph(Paragraph paragraph) {
    }

    public void onStartBlockQuote(BlockQuote blockQuote) {
    }

    public void onEndBlockQuote(BlockQuote blockQuote) {
    }

    public void onBeginUnorderedList(UnorderedList unorderedList) {
    }

    public void onEndUnorderedList(UnorderedList unorderedList) {
    }

    public void onBeginListItem(ListItem listItem) {
    }

    public void onEndListItem(ListItem listItem) {
    }

    public void onStartCode(Code code) {
    }

    public void onEndCode(Code code) {
    }

    public void onText(Text text) {
    }
}
