/*
 * Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
 * See LICENSE.txt for licensing information.
 */
package com.github.rjeschke.txtmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * Markdown processor class.
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * <code>String result = Processor.process("This is ***TXTMARK***");
 * </code>
 * </pre>
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
public class Processor
{
    /** The reader. */
    private final Reader reader;
    /** The emitter. */
    private final Emitter emitter;
    /** Extension flag. */
    private boolean useExtensions = false;

    /**
     * Constructor.
     * 
     * @param reader
     *            The input reader.
     */
    private Processor(final Reader reader, final Decorator decorator,
            final boolean safeMode)
    {
        this.reader = reader;
        this.emitter = new Emitter(decorator, safeMode);
    }

    /**
     * Transforms an input String into XHTML using the default Decorator.
     * 
     * @param input
     *            The String to process.
     * @return The processed String.
     */
    public static String process(final String input)
    {
        try
        {
            return process(new StringReader(input));
        }
        catch (IOException e)
        {
            // This _can never_ happen
            return null;
        }
    }

    /**
     * Transforms an input String into XHTML using the default Decorator.
     * 
     * @param input
     *            The String to process.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     */
    public static String process(final String input, final boolean safeMode)
    {
        try
        {
            return process(new StringReader(input), safeMode);
        }
        catch (IOException e)
        {
            // This _can never_ happen
            return null;
        }
    }

    /**
     * Transforms an input String into XHTML.
     * 
     * @param input
     *            The String to process.
     * @param decorator
     *            The decorator to use.
     * @return The processed String.
     */
    public static String process(final String input, final Decorator decorator)
    {
        try
        {
            return process(new StringReader(input), decorator);
        }
        catch (IOException e)
        {
            // This _can never_ happen
            return null;
        }
    }

    /**
     * Transforms an input String into XHTML.
     * 
     * @param input
     *            The String to process.
     * @param decorator
     *            The decorator to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     */
    public static String process(final String input, final Decorator decorator,
            final boolean safeMode)
    {
        try
        {
            return process(new StringReader(input), decorator, safeMode);
        }
        catch (IOException e)
        {
            // This _can never_ happen
            return null;
        }
    }

    /**
     * Transforms an input file into XHTML using UTF-8 encoding and the default
     * Decorator.
     * 
     * @param file
     *            The File to process.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file) throws IOException
    {
        return process(file, "UTF-8");
    }

    /**
     * Transforms an input file into XHTML using UTF-8 encoding and the default
     * Decorator.
     * 
     * @param file
     *            The File to process.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file, final boolean safeMode)
            throws IOException
    {
        return process(file, "UTF-8", safeMode);
    }

    /**
     * Transforms an input file into XHTML using UTF-8 encoding.
     * 
     * @param file
     *            The File to process.
     * @param decorator
     *            The decorator to use.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file, final Decorator decorator)
            throws IOException
    {
        return process(file, "UTF-8", decorator);
    }

    /**
     * Transforms an input file into XHTML using UTF-8 encoding.
     * 
     * @param file
     *            The File to process.
     * @param decorator
     *            The decorator to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file, final Decorator decorator,
            final boolean safeMode) throws IOException
    {
        return process(file, "UTF-8", decorator, safeMode);
    }

    /**
     * Transforms an input file into XHTML using the default Decorator.
     * 
     * @param file
     *            The File to process.
     * @param encoding
     *            The encoding to use.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file, final String encoding)
            throws IOException
    {
        return process(file, encoding, new DefaultDecorator());
    }

    /**
     * Transforms an input file into XHTML using the default Decorator.
     * 
     * @param file
     *            The File to process.
     * @param encoding
     *            The encoding to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file, final String encoding,
            final boolean safeMode) throws IOException
    {
        return process(file, encoding, new DefaultDecorator(), safeMode);
    }

    /**
     * Transforms an input file into XHTML.
     * 
     * @param file
     *            The File to process.
     * @param encoding
     *            The encoding to use.
     * @param decorator
     *            The decorator to use.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file, final String encoding,
            final Decorator decorator) throws IOException
    {
        final FileInputStream input = new FileInputStream(file);
        final String ret = process(input, encoding, decorator);
        input.close();
        return ret;
    }

    /**
     * Transforms an input file into XHTML.
     * 
     * @param file
     *            The File to process.
     * @param encoding
     *            The encoding to use.
     * @param decorator
     *            The decorator to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final File file, final String encoding,
            final Decorator decorator, final boolean safeMode)
            throws IOException
    {
        final FileInputStream input = new FileInputStream(file);
        final String ret = process(input, encoding, decorator, safeMode);
        input.close();
        return ret;
    }

    /**
     * Transforms an input stream into XHTML using UTF-8 encoding using the
     * default Decorator.
     * 
     * @param input
     *            The InputStream to process.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input) throws IOException
    {
        return process(input, "UTF-8", new DefaultDecorator());
    }

    /**
     * Transforms an input stream into XHTML using UTF-8 encoding using the
     * default Decorator.
     * 
     * @param input
     *            The InputStream to process.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input, final boolean safeMode)
            throws IOException
    {
        return process(input, "UTF-8", new DefaultDecorator(), safeMode);
    }

    /**
     * Transforms an input stream into XHTML using UTF-8 encoding.
     * 
     * @param input
     *            The InputStream to process.
     * @param decorator
     *            The decorator to use.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input,
            final Decorator decorator) throws IOException
    {
        return process(input, "UTF-8", decorator);
    }

    /**
     * Transforms an input stream into XHTML using UTF-8 encoding.
     * 
     * @param input
     *            The InputStream to process.
     * @param decorator
     *            The decorator to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input,
            final Decorator decorator, final boolean safeMode)
            throws IOException
    {
        return process(input, "UTF-8", decorator, safeMode);
    }

    /**
     * Transforms an input stream into XHTML using the default Decorator.
     * 
     * @param input
     *            The InputStream to process.
     * @param encoding
     *            The encoding to use.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input, final String encoding)
            throws IOException
    {
        final Processor p = new Processor(new BufferedReader(
                new InputStreamReader(input, encoding)),
                new DefaultDecorator(), false);
        return p.process();
    }

    /**
     * Transforms an input stream into XHTML using the default Decorator.
     * 
     * @param input
     *            The InputStream to process.
     * @param encoding
     *            The encoding to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input,
            final String encoding, final boolean safeMode) throws IOException
    {
        final Processor p = new Processor(new BufferedReader(
                new InputStreamReader(input, encoding)),
                new DefaultDecorator(), safeMode);
        return p.process();
    }

    /**
     * Transforms an input stream into XHTML.
     * 
     * @param input
     *            The InputStream to process.
     * @param encoding
     *            The encoding to use.
     * @param decorator
     *            The decorator to use.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input,
            final String encoding, final Decorator decorator)
            throws IOException
    {
        final Processor p = new Processor(new BufferedReader(
                new InputStreamReader(input, encoding)), decorator, false);
        return p.process();
    }

    /**
     * Transforms an input stream into XHTML.
     * 
     * @param input
     *            The InputStream to process.
     * @param encoding
     *            The encoding to use.
     * @param decorator
     *            The decorator to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final InputStream input,
            final String encoding, final Decorator decorator,
            final boolean safeMode) throws IOException
    {
        final Processor p = new Processor(new BufferedReader(
                new InputStreamReader(input, encoding)), decorator, safeMode);
        return p.process();
    }

    /**
     * Transforms an input stream into XHTML using the default Decorator.
     * 
     * @param reader
     *            The Reader to process.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final Reader reader) throws IOException
    {
        final Processor p = new Processor(
                !(reader instanceof BufferedReader) ? new BufferedReader(reader)
                        : reader, new DefaultDecorator(), false);
        return p.process();
    }

    /**
     * Transforms an input stream into XHTML using the default Decorator.
     * 
     * @param reader
     *            The Reader to process.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final Reader reader, final boolean safeMode)
            throws IOException
    {
        final Processor p = new Processor(
                !(reader instanceof BufferedReader) ? new BufferedReader(reader)
                        : reader, new DefaultDecorator(), safeMode);
        return p.process();
    }

    /**
     * Transforms an input stream into XHTML.
     * 
     * @param reader
     *            The Reader to process.
     * @param decorator
     *            The decorator to use.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final Reader reader, final Decorator decorator)
            throws IOException
    {
        final Processor p = new Processor(
                !(reader instanceof BufferedReader) ? new BufferedReader(reader)
                        : reader, decorator, false);
        return p.process();
    }

    /**
     * Transforms an input stream into XHTML.
     * 
     * @param reader
     *            The Reader to process.
     * @param decorator
     *            The decorator to use.
     * @param safeMode
     *            Set to <code>true</code> to escape unsafe HTML tags.
     * @return The processed String.
     * @throws IOException
     *             if an IO error occurs
     */
    public static String process(final Reader reader,
            final Decorator decorator, final boolean safeMode)
            throws IOException
    {
        final Processor p = new Processor(
                !(reader instanceof BufferedReader) ? new BufferedReader(reader)
                        : reader, decorator, safeMode);
        return p.process();
    }

    /**
     * Reads all lines from our reader.
     * <p>
     * Takes care of markdown link references.
     * </p>
     * 
     * @return A Block containing all lines.
     * @throws IOException
     *             If an IO error occurred.
     */
    private Block readLines() throws IOException
    {
        final Block block = new Block();
        final StringBuilder sb = new StringBuilder(80);
        int c = this.reader.read();
        LinkRef lastLinkRef = null;
        while (c != -1)
        {
            sb.setLength(0);
            int pos = 0;
            boolean eol = false;
            while (!eol)
            {
                switch (c)
                {
                case -1:
                    eol = true;
                    break;
                case '\n':
                    c = this.reader.read();
                    if (c == '\r')
                        c = this.reader.read();
                    eol = true;
                    break;
                case '\r':
                    c = this.reader.read();
                    if (c == '\n')
                        c = this.reader.read();
                    eol = true;
                    break;
                case '\t':
                {
                    final int np = pos + (4 - (pos & 3));
                    while (pos < np)
                    {
                        sb.append(' ');
                        pos++;
                    }
                    c = this.reader.read();
                }
                    break;
                default:
                    pos++;
                    sb.append((char) c);
                    c = this.reader.read();
                    break;
                }
            }

            final Line line = new Line();
            line.value = sb.toString();
            line.init();

            // Check for link definitions
            boolean isLinkRef = false;
            String id = null, link = null, comment = null;
            if (!line.isEmpty && line.leading < 4
                    && line.value.charAt(line.leading) == '[')
            {
                line.pos = line.leading + 1;
                // Read ID up to ']'
                id = line.readUntil(']');
                // Is ID valid and are there any more characters?
                if (id != null && line.pos + 2 < line.value.length())
                {
                    // Check for ':' ([...]:...)
                    if (line.value.charAt(line.pos + 1) == ':')
                    {
                        line.pos += 2;
                        line.skipSpaces();
                        // Check for link syntax
                        if (line.value.charAt(line.pos) == '<')
                        {
                            line.pos++;
                            link = line.readUntil('>');
                            line.pos++;
                        }
                        else
                            link = line.readUntil(' ', '\n');

                        // Is link valid?
                        if (link != null)
                        {
                            // Any non-whitespace characters following?
                            if (line.skipSpaces())
                            {
                                final char ch = line.value.charAt(line.pos);
                                // Read comment
                                if (ch == '\"' || ch == '\'' || ch == '(')
                                {
                                    line.pos++;
                                    comment = line.readUntil(ch == '(' ? ')'
                                            : ch);
                                    // Valid linkRef only if comment is valid
                                    if (comment != null)
                                        isLinkRef = true;
                                }
                            }
                            else
                                isLinkRef = true;
                        }
                    }
                }
            }

            // To make compiler happy: add != null checks
            if (isLinkRef && id != null && link != null)
            {
                if (id.toLowerCase().equals("$profile$"))
                {
                    this.emitter.useExtensions = this.useExtensions = link
                            .toLowerCase().equals("extended");
                    lastLinkRef = null;
                }
                else
                {
                    // Store linkRef and skip line
                    final LinkRef lr = new LinkRef(
                            link,
                            comment,
                            comment != null
                                    && (link.length() == 1 && link.charAt(0) == '*'));
                    this.emitter.addLinkRef(id, lr);
                    if (comment == null)
                        lastLinkRef = lr;
                }
            }
            else
            {
                comment = null;
                // Check for multi-line linkRef
                if (!line.isEmpty && lastLinkRef != null)
                {
                    line.pos = line.leading;
                    final char ch = line.value.charAt(line.pos);
                    if (ch == '\"' || ch == '\'' || ch == '(')
                    {
                        line.pos++;
                        comment = line.readUntil(ch == '(' ? ')' : ch);
                    }
                    if (comment != null)
                        lastLinkRef.title = comment;

                    lastLinkRef = null;
                }

                // No multi-line linkRef, store line
                if (comment == null)
                {
                    line.pos = 0;
                    block.appendLine(line);
                }
            }
        }

        return block;
    }

    /**
     * Initializes a list block by separating it into list item blocks.
     * 
     * @param root
     *            The Block to process.
     */
    private void initListBlock(final Block root)
    {
        Line line = root.lines;
        line = line.next;
        while (line != null)
        {
            final LineType t = line.getLineType();
            if ((t == LineType.OLIST || t == LineType.ULIST)
                    || (!line.isEmpty && (line.prevEmpty && line.leading == 0 && !(t == LineType.OLIST || t == LineType.ULIST))))
            {
                root.split(line.previous).type = BlockType.LIST_ITEM;
            }
            line = line.next;
        }
        root.split(root.lineTail).type = BlockType.LIST_ITEM;
    }

    /**
     * Recursively process the given Block.
     * 
     * @param root
     *            The Block to process.
     * @param listMode
     *            Flag indicating that we're in a list item block.
     */
    private void recurse(final Block root, boolean listMode)
    {
        Block block, list;
        Line line = root.lines;

        if (listMode)
        {
            root.removeListIndent();
            if (this.useExtensions && root.lines != null
                    && root.lines.getLineType() != LineType.CODE)
            {
                root.id = root.lines.stripIP();
            }
        }

        while (line != null && line.isEmpty)
            line = line.next;
        if (line == null)
            return;

        while (line != null)
        {
            final LineType type = line.getLineType();
            switch (type)
            {
            case OTHER:
            {
                final boolean wasEmpty = line.prevEmpty;
                while (line != null && !line.isEmpty)
                {
                    final LineType t = line.getLineType();
                    if ((listMode || this.useExtensions)
                            && (t == LineType.OLIST || t == LineType.ULIST))
                        break;
                    if (this.useExtensions && (t == LineType.CODE))
                        break;
                    if (t == LineType.HEADLINE || t == LineType.HEADLINE1
                            || t == LineType.HEADLINE2 || t == LineType.HR
                            || t == LineType.BQUOTE || t == LineType.XML)
                        break;
                    line = line.next;
                }
                final BlockType bt;
                if (line != null && !line.isEmpty)
                {
                    bt = (listMode && !wasEmpty) ? BlockType.NONE
                            : BlockType.PARAGRAPH;
                    root.split(line.previous).type = bt;
                    root.removeLeadingEmptyLines();
                }
                else
                {
                    bt = (listMode && (line == null || !line.isEmpty) && !wasEmpty) ? BlockType.NONE
                            : BlockType.PARAGRAPH;
                    root.split(line == null ? root.lineTail : line).type = bt;
                    root.removeLeadingEmptyLines();
                }
                line = root.lines;
            }
                break;
            case CODE:
                while (line != null && (line.isEmpty || line.leading > 3))
                {
                    line = line.next;
                }
                block = root
                        .split(line != null ? line.previous : root.lineTail);
                block.type = BlockType.CODE;
                block.removeSurroundingEmptyLines();
                break;
            case XML:
                if (line.previous != null)
                {
                    // FIXME ... this looks wrong
                    root.split(line.previous);
                }
                root.split(line.xmlEndLine).type = BlockType.XML;
                root.removeLeadingEmptyLines();
                line = root.lines;
                break;
            case BQUOTE:
                while (line != null)
                {
                    if (!line.isEmpty
                            && (line.prevEmpty && line.leading == 0 && line
                                    .getLineType() != LineType.BQUOTE))
                        break;
                    line = line.next;
                }
                block = root
                        .split(line != null ? line.previous : root.lineTail);
                block.type = BlockType.BLOCKQUOTE;
                block.removeSurroundingEmptyLines();
                block.removeBlockQuotePrefix();
                this.recurse(block, false);
                line = root.lines;
                break;
            case HR:
                if (line.previous != null)
                {
                    // FIXME ... this looks wrong
                    root.split(line.previous);
                }
                root.split(line).type = BlockType.RULER;
                root.removeLeadingEmptyLines();
                line = root.lines;
                break;
            case HEADLINE:
            case HEADLINE1:
            case HEADLINE2:
                if (line.previous != null)
                {
                    root.split(line.previous);
                }
                if (type != LineType.HEADLINE)
                {
                    line.next.setEmpty();
                }
                block = root.split(line);
                block.type = BlockType.HEADLINE;
                if (type != LineType.HEADLINE)
                    block.hlDepth = type == LineType.HEADLINE1 ? 1 : 2;
                if (this.useExtensions)
                    block.id = block.lines.stripIP();
                block.transfromHeadline();
                root.removeLeadingEmptyLines();
                line = root.lines;
                break;
            case OLIST:
            case ULIST:
                while (line != null)
                {
                    final LineType t = line.getLineType();
                    if (!line.isEmpty
                            && (line.prevEmpty && line.leading == 0 && !(t == LineType.OLIST || t == LineType.ULIST)))
                        break;
                    line = line.next;
                }
                list = root.split(line != null ? line.previous : root.lineTail);
                list.type = type == LineType.OLIST ? BlockType.ORDERED_LIST
                        : BlockType.UNORDERED_LIST;
                list.lines.prevEmpty = false;
                list.lineTail.nextEmpty = false;
                list.removeSurroundingEmptyLines();
                list.lines.prevEmpty = list.lineTail.nextEmpty = false;
                this.initListBlock(list);
                block = list.blocks;
                while (block != null)
                {
                    this.recurse(block, true);
                    block = block.next;
                }
                list.expandListParagraphs();
                break;
            default:
                line = line.next;
                break;
            }
        }
    }

    /**
     * Does all the processing.
     * 
     * @return The processed String.
     * @throws IOException
     *             If an IO error occurred.
     */
    private String process() throws IOException
    {
        final StringBuilder out = new StringBuilder();
        final Block parent = this.readLines();
        parent.removeSurroundingEmptyLines();

        this.recurse(parent, false);
        Block block = parent.blocks;
        while (block != null)
        {
            this.emitter.emit(out, block);
            block = block.next;
        }

        return out.toString();
    }
}
