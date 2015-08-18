package com.github.rjeschke.txtmark.dom;

import com.github.rjeschke.txtmark.Block;
import com.github.rjeschke.txtmark.Emitter;
import com.github.rjeschke.txtmark.Line;
import com.github.rjeschke.txtmark.LinkRef;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MarkdownDocumentEmitter implements Emitter {

    private final MarkdownDocument doc;
    private Deque<MarkdownTreeNode> current;
    private MarkdownTreeNode parent;

    public MarkdownDocumentEmitter() {
        doc = new MarkdownDocument();
        current = new LinkedList<MarkdownTreeNode>();
        current.push(doc);
        parent = doc;
    }

    @Override
    public void addLinkRef(String key, LinkRef linkRef) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void emit(StringBuilder out, Block root) {
        boolean push = false;

        MarkdownNode node;

        // TODO: Only the ones that push should extend MarkdownTreeNode
        switch (root.type) {
            case HEADLINE:
                node = new Headline(root.hlDepth, root.lines);
                break;
            case PARAGRAPH:
                node = new Paragraph(root.lines);
                break;
            case CODE:
                Line line = root.lines;
                List<String> lines = new ArrayList<String>();
                while (line != null) {
                    String s = line.value;

                    int start = s.lastIndexOf(' ') + 1;
                    start = Math.min(start, 4);
                    lines.add(s.substring(start));
                    line = line.next;
                }
                node = new Code(lines);
                break;
            case BLOCKQUOTE:
                node = new BlockQuote();
                push = true;
                break;
            case UNORDERED_LIST:
                node = new UnorderedList();
                push = true;
                break;
            case LIST_ITEM:
                node = new ListItem();
                push = true;
                break;
            case NONE:
                System.out.println("NONE?! hasLines: " + root.hasLines() + ", blocks: " + root.hasBlocks() + ", text:");
                printLines(root.lines, new PrintWriter(System.out));
                node = new Text(root.lines);
                break;
            case XML:
                node = new Xml(root.lines);
                break;
            default:
                throw new RuntimeException("Unknown block type: " + root.type);
        }

        if (node == null) {
            return;
        }

        parent.addChild(node);

        if (push) {
            if (!(node instanceof MarkdownTreeNode)) {
                throw new RuntimeException("Internal error");
            }
            parent = (MarkdownTreeNode) node;

            current.push(parent);
        }

        Block block = root.blocks;
        if (block != null && !push) {
            System.err.println("oops, has children but didn't push.");
        }
        while (block != null) {
            this.emit(out, block);
            block = block.next;
        }

        if (push) {
            current.pop();
            parent = current.getLast();
        }
    }

    @Override
    public void useExtensions(boolean useExtensions) {
        // ignored
    }

    public void process(DocumentProcessor... processors) {
        for (DocumentProcessor processor : processors) {
            processor.process(doc);
        }
    }

    public MarkdownDocument getDocument() {
        return doc;
    }

    public static void printLines(Line line, PrintWriter out) {
        while (line != null) {
            out.print(line.value);
            line = line.next;
            if (line != null) {
                out.println();
            }
        }
    }

    public static void printLines(List<String> lines, PrintWriter out) {
        for (String line : lines) {
            out.println(line);
        }
    }
}
