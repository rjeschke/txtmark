package com.github.rjeschke.txtmark.dom.misc;

import com.github.rjeschke.txtmark.dom.BlockQuote;
import com.github.rjeschke.txtmark.dom.Code;
import com.github.rjeschke.txtmark.dom.Headline;
import com.github.rjeschke.txtmark.dom.ListItem;
import com.github.rjeschke.txtmark.dom.MarkdownDocument;
import com.github.rjeschke.txtmark.dom.MarkdownEventHandler;
import com.github.rjeschke.txtmark.dom.MarkdownNode;
import com.github.rjeschke.txtmark.dom.Paragraph;
import com.github.rjeschke.txtmark.dom.Text;
import com.github.rjeschke.txtmark.dom.UnorderedList;

import java.util.Arrays;
import java.util.List;

public class DelegatingMarkdownEventHandler extends MarkdownEventHandler {
    private List<MarkdownEventHandler> handlers;

    public DelegatingMarkdownEventHandler(List<MarkdownEventHandler> handlers) {
        this.handlers = handlers;
    }

    public DelegatingMarkdownEventHandler(MarkdownEventHandler... handlers) {
        this(Arrays.asList(handlers));
    }

    @Override
    public void onUnknownType(MarkdownNode node) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onUnknownType(node);
        }
    }

    @Override
    public void onStartDocument(MarkdownDocument doc) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onStartDocument(doc);
        }
    }

    @Override
    public void onEndDocument(MarkdownDocument doc) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onEndDocument(doc);
        }
    }

    @Override
    public void onStartHeadline(Headline headline) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onStartHeadline(headline);
        }
    }

    @Override
    public void onEndHeadline(Headline headline) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onEndHeadline(headline);
        }
    }

    @Override
    public void onStartParagraph(Paragraph paragraph) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onStartParagraph(paragraph);
        }
    }

    @Override
    public void onEndParagraph(Paragraph paragraph) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onEndParagraph(paragraph);
        }
    }

    @Override
    public void onStartBlockQuote(BlockQuote blockQuote) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onStartBlockQuote(blockQuote);
        }
    }

    @Override
    public void onEndBlockQuote(BlockQuote blockQuote) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onEndBlockQuote(blockQuote);
        }
    }

    @Override
    public void onBeginUnorderedList(UnorderedList unorderedList) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onBeginUnorderedList(unorderedList);
        }
    }

    @Override
    public void onEndUnorderedList(UnorderedList unorderedList) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onEndUnorderedList(unorderedList);
        }
    }

    @Override
    public void onStartCode(Code code) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onStartCode(code);
        }
    }

    @Override
    public void onEndCode(Code code) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onEndCode(code);
        }
    }

    @Override
    public void onBeginListItem(ListItem listItem) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onBeginListItem(listItem);
        }
    }

    @Override
    public void onEndListItem(ListItem listItem) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onEndListItem(listItem);
        }
    }

    public void onText(Text text) {
        for (MarkdownEventHandler handler : handlers) {
            handler.onText(text);
        }
    }
}
