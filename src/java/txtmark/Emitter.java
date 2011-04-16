/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

import java.util.HashMap;

/**
 * Emitter class responsible for generating HTML output.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
class Emitter
{
    /** Link references. */
    private final HashMap<String, LinkRef> linkRefs = new HashMap<String, LinkRef>();
    /** The Decorator. */
    private Decorator decorator;
    
    /** Constructor. */
    public Emitter(final Decorator decorator)
    {
        this.decorator = decorator;
    }

    /**
     * Adds a LinkRef to this set of LinkRefs.
     * 
     * @param key The key/id.
     * @param linkRef The LinkRef.
     */
    public void addLinkRef(final String key, final LinkRef linkRef)
    {
        this.linkRefs.put(key.toLowerCase(), linkRef);
    }

    /**
     * Transforms the given block recursively into HTML.
     * 
     * @param out The StringBuilder to write to.
     * @param root The Block to process.
     */
    public void emit(final StringBuilder out, final Block root)
    {
        root.removeSurroundingEmptyLines();

        switch(root.type)
        {
        case RULER:
            this.decorator.horizontalRuler(out);
            return;
        case NONE:
        case XML:
            break;
        case HEADLINE:
            this.decorator.openHeadline(out, root.hlDepth);
            break;
        case PARAGRAPH:
            this.decorator.openParagraph(out);
            break;
        case CODE:
            this.decorator.openCodeBlock(out);
            break;
        case BLOCKQUOTE:
            this.decorator.openBlockquote(out);
            break;
        case UNORDERED_LIST:
            this.decorator.openUnorderedList(out);
            break;
        case ORDERED_LIST:
            this.decorator.openOrderedList(out);
            break;
        case LIST_ITEM:
            this.decorator.openListItem(out);
            break;
        }

        if(root.hasLines())
        {
            this.emitLines(out, root);
        }
        else
        {
            Block block = root.blocks;
            while(block != null)
            {
                this.emit(out, block);
                block = block.next;
            }
        }

        switch(root.type)
        {
        case RULER:
        case NONE:
        case XML:
            break;
        case HEADLINE:
            this.decorator.closeHeadline(out, root.hlDepth);
            break;
        case PARAGRAPH:
            this.decorator.closeParagraph(out);
            break;
        case CODE:
            this.decorator.closeCodeBlock(out);
            break;
        case BLOCKQUOTE:
            this.decorator.closeBlockquote(out);
            break;
        case UNORDERED_LIST:
            this.decorator.closeUnorderedList(out);
            break;
        case ORDERED_LIST:
            this.decorator.closeOrderedList(out);
            break;
        case LIST_ITEM:
            this.decorator.closeListItem(out);
            break;
        }
    }

    /**
     * Transforms lines into HTML.
     * 
     * @param out The StringBuilder to write to.
     * @param block The Block to process.
     */
    private void emitLines(final StringBuilder out, final Block block)
    {
        switch(block.type)
        {
        case CODE:
            this.emitCodeLines(out, block.lines);
            break;
        case XML:
            this.emitRawLines(out, block.lines);
            break;
        default:
            this.emitMarkedLines(out, block.lines);
            break;
        }
    }

    /**
     * Finds the position of the given Token in the given String.
     * 
     * @param in The String to search on.
     * @param start The starting character position.
     * @param token The token to find.
     * @return The position of the token or -1 if none could be found.
     */
    private int findToken(final String in, int start, MarkToken token)
    {
        int pos = start;
        while(pos < in.length())
        {
            if(this.getToken(in, pos) == token)
                return pos;
            pos++;
        }
        return -1;
    }

    /**
     * Checks if there is a valid markdown link definition.
     * 
     * @param out The StringBuilder containing the generated output.
     * @param in Input String.
     * @param start Starting position.
     * @param token Either LINK or IMAGE.
     * @return The new position or -1 if there is no valid markdown link.
     */
    private int checkLink(final StringBuilder out, final String in, int start, MarkToken token)
    {
        int pos = start + (token == MarkToken.LINK ? 1 : 2);
        final StringBuilder temp = new StringBuilder();

        temp.setLength(0);
        pos = Utils.readMdLinkId(temp, in, pos);
        if(pos < start)
            return -1;

        String name = temp.toString(), link = null, comment = null;
        final int oldPos = pos++;
        pos = Utils.skipSpaces(in, pos);
        if(pos < start)
        {
            final LinkRef lr = this.linkRefs.get(name.toLowerCase());
            if(lr != null)
            {
                link = lr.link;
                comment = lr.title;
                pos = oldPos;
            }
            else
            {
                return -1;
            }
        }
        else if(in.charAt(pos) == '(')
        {
            pos++;
            pos = Utils.skipSpaces(in, pos);
            if(pos < start)
                return -1;
            temp.setLength(0);
            boolean useLt = in.charAt(pos) == '<';
            pos = useLt ? Utils.readUntil(temp, in, pos + 1, '>') : Utils.readMdLink(temp, in, pos);
            if(pos < start)
                return -1;
            if(useLt)
                pos++;
            link = temp.toString();

            if(in.charAt(pos) == ' ')
            {
                pos = Utils.skipSpaces(in, pos);
                if(pos > start && in.charAt(pos) == '"')
                {
                    pos++;
                    temp.setLength(0);
                    pos = Utils.readUntil(temp, in, pos, '"');
                    if(pos < start)
                        return -1;
                    comment = temp.toString();
                    pos++;
                    pos = Utils.skipSpaces(in, pos);
                    if(pos == -1)
                        return -1;
                }
            }
            if(in.charAt(pos) != ')')
                return -1;
        }
        else if(in.charAt(pos) == '[')
        {
            pos++;
            temp.setLength(0);
            pos = Utils.readRawUntil(temp, in, pos, ']');
            if(pos < start)
                return -1;
            final String id = temp.length() > 0 ? temp.toString() : name;
            final LinkRef lr = this.linkRefs.get(id.toLowerCase());
            if(lr != null)
            {
                link = lr.link;
                comment = lr.title;
            }
        }
        else
        {
            final LinkRef lr = this.linkRefs.get(name.toLowerCase());
            if(lr != null)
            {
                link = lr.link;
                comment = lr.title;
                pos = oldPos;
            }
            else
            {
                return -1;
            }
        }

        if(link == null)
            return -1;

        if(token == MarkToken.LINK)
        {
            this.decorator.openLink(out);
            out.append(" href=\"");
            Utils.appendValue(out, link, 0, link.length());
            out.append('"');
            if(comment != null)
            {
                out.append(" title=\"");
                Utils.appendValue(out, comment, 0, comment.length());
                out.append('"');
            }
            out.append('>');
            this.recursiveEmitLine(out, name, 0, MarkToken.NONE);
            out.append("</a>");
        }
        else
        {
            this.decorator.openImage(out);
            out.append(" src=\"");
            Utils.appendValue(out, link, 0, link.length());
            out.append("\" alt=\"");
            Utils.appendValue(out, name, 0, name.length());
            out.append('"');
            if(comment != null)
            {
                out.append(" title=\"");
                Utils.appendValue(out, comment, 0, comment.length());
                out.append('"');
            }
            out.append(" />");
        }

        return pos;
    }

    /**
     * Check if there is a valid HTML tag here. 
     * This method also transforms auto links and mailto auto links.
     * 
     * @param out The StringBuilder to write to.
     * @param in Input String. 
     * @param start Starting position.
     * @return The new position or -1 if nothing valid has been found.
     */
    // TODO ... hm ... refactor this
    private int checkHtml(final StringBuilder out, final String in, int start)
    {
        final StringBuilder temp = new StringBuilder();
        int pos;

        // Check for auto links
        temp.setLength(0);
        pos = Utils.readUntil(temp, in, start + 1, ':');
        if(pos != -1 && HTML.isLinkPrefix(temp.toString()))
        {
            pos = Utils.readUntil(temp, in, pos, '>');
            if(pos != -1)
            {
                final String link = temp.toString();
                this.decorator.openLink(out);
                out.append(" href=\"");
                Utils.appendValue(out, link, 0, link.length());
                out.append("\">");
                Utils.appendValue(out, link, 0, link.length());
                out.append("</a>");
                return pos;
            }
        }
        
        // Check for mailto auto link
        temp.setLength(0);
        pos = Utils.readUntil(temp, in, start + 1, '@');
        if(pos != -1)
        {
            pos = Utils.readUntil(temp, in, pos, '>');
            if(pos != -1)
            {
                final String link = temp.toString();
                this.decorator.openLink(out);
                out.append(" href=\"");
                Utils.appendMailto(out, "mailto:", 0, 7);
                Utils.appendMailto(out, link, 0, link.length());
                out.append("\">");
                Utils.appendMailto(out, link, 0, link.length());
                out.append("</a>");
                return pos;
            }
        }
        
        // Check for inline html
        if(start + 2 < in.length())
        {
            temp.setLength(0);
            return Utils.readXML(out, in, start);
        }        
    
        return -1;
    }
    
    /**
     * Check if this is a valid XML/HTML entity.
     * 
     * @param out The StringBuilder to write to.
     * @param in Input String.
     * @param start Starting position
     * @return The new position or -1 if this entity in invalid.
     */
    private int checkEntity(final StringBuilder out, final String in, int start)
    {
        int pos = Utils.readUntil(out, in, start, ';');
        if(pos < 0 || out.length() < 3)
            return -1;
        if(out.charAt(1) == '#')
        {
            if(out.charAt(2) == 'x' || out.charAt(2) == 'X')
            {
                if(out.length() < 4)
                    return -1;
                for(int i = 3; i < out.length(); i++)
                {
                    final char c = out.charAt(i);
                    if((c < '0' || c > '9') && ((c < 'a' || c > 'f') && (c < 'A' || c > 'F')))
                        return -1;
                }
            }
            else
            {
                for(int i = 2; i < out.length(); i++)
                {
                    final char c = out.charAt(i);
                    if(c < '0' || c > '9')
                        return -1;
                }
            }
            out.append(';');
        }
        else
        {
            for(int i = 1; i < out.length(); i++)
            {
                final char c = out.charAt(i);
                if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
                    return -1;
            }
            out.append(';');
            return HTML.isEntity(out.toString()) ? pos : -1;
        }
        
        return pos;
    }
    
    /**
     * Recursively scans through the given line, taking care of any markdown stuff.
     * 
     * @param out The StringBuilder to write to.
     * @param in Input String.
     * @param start Start position.
     * @param token The matching Token (for e.g. '*')
     * @return The position of the matching Token or -1 if token was NONE or no Token could be found.
     */
    private int recursiveEmitLine(final StringBuilder out, final String in, int start, MarkToken token)
    {
        int pos = start, a, b;
        final StringBuilder temp = new StringBuilder();
        while(pos < in.length())
        {
            final MarkToken mt = this.getToken(in, pos);
            if(token != MarkToken.NONE && (mt == token || token == MarkToken.EM_STAR && mt == MarkToken.STRONG_STAR || token == MarkToken.EM_UNDERSCORE && mt == MarkToken.STRONG_UNDERSCORE))
                return pos;

            switch(mt)
            {
            case IMAGE:
            case LINK:
                temp.setLength(0);
                b = this.checkLink(temp, in, pos, mt);
                if(b > 0)
                {
                    out.append(temp);
                    pos = b;
                }
                else
                {
                    out.append(in.charAt(pos));
                }
                break;
            case EM_STAR:
            case EM_UNDERSCORE:
                temp.setLength(0);
                b = this.recursiveEmitLine(temp, in, pos + 1, mt);
                if(b > 0)
                {
                    this.decorator.openEmphasis(out);
                    out.append(temp);
                    this.decorator.closeEmphasis(out);
                    pos = b;
                }
                else
                {
                    out.append(in.charAt(pos));
                }
                break;
            case STRONG_STAR:
            case STRONG_UNDERSCORE:
                temp.setLength(0);
                b = this.recursiveEmitLine(temp, in, pos + 2, mt);
                if(b > 0)
                {
                    this.decorator.openStrong(out);
                    out.append(temp);
                    this.decorator.closeStrong(out);
                    pos = b + 1;
                }
                else
                {
                    out.append(in.charAt(pos));
                }
                break;
            case CODE_SINGLE:
            case CODE_DOUBLE:
                a = pos + (mt == MarkToken.CODE_DOUBLE ? 2 : 1);
                b = this.findToken(in, a, mt);
                if(b > 0)
                {
                    pos = b + (mt == MarkToken.CODE_DOUBLE ? 1 : 0);
                    while(a < b && in.charAt(a) == ' ')
                        a++;
                    if(a < b)
                    {
                        while(in.charAt(b - 1) == ' ')
                            b--;
                        this.decorator.openCodeSpan(out);
                        Utils.appendCode(out, in, a, b);
                        this.decorator.closeCodeSpan(out);
                    }
                }
                else
                {
                    out.append(in.charAt(pos));
                }
                break;
            case HTML:
                temp.setLength(0);
                b = this.checkHtml(temp, in, pos);
                if(b > 0)
                {
                    out.append(temp);
                    pos = b;
                }
                else
                {
                    out.append("&lt;");
                }
                break;
            case ENTITY:
                temp.setLength(0);
                b = this.checkEntity(temp, in, pos);
                if(b > 0)
                {
                    out.append(temp);
                    pos = b;
                }
                else
                {
                    out.append("&amp;");
                }
                break;
            case ESCAPE:
                pos++;
                //$FALL-THROUGH$
            default:
                out.append(in.charAt(pos));
                break;
            }
            pos++;
        }
        return -1;
    }

    /**
     * Check if there is any markdown Token.
     * 
     * @param in Input String.
     * @param pos Starting position.
     * @return The Token.
     */
    private MarkToken getToken(final String in, final int pos)
    {
        final char c0 = pos > 0 ? in.charAt(pos - 1) : ' ';
        final char c  = in.charAt(pos);
        final char c1 = pos + 1 < in.length() ? in.charAt(pos + 1) : ' ';
        final char c2 = pos + 2 < in.length() ? in.charAt(pos + 2) : ' ';

        switch(c)
        {
        case '*':
            if(c1 == '*')
            {
                return c0 != ' ' || c2 != ' ' ? MarkToken.STRONG_STAR : MarkToken.EM_STAR;
            }
            return c0 != ' ' || c1 != ' ' ? MarkToken.EM_STAR : MarkToken.NONE;
        case '_':
            if(c1 == '_')
            {
                return c0 != ' ' || c2 != ' ' ? MarkToken.STRONG_UNDERSCORE : MarkToken.EM_UNDERSCORE;
            }
            return c0 != ' ' || c1 != ' ' ? MarkToken.EM_UNDERSCORE : MarkToken.NONE;
        case '!':
            if(c1 == '[')
                return MarkToken.IMAGE;
            return MarkToken.NONE;
        case '[':
            return MarkToken.LINK;
        case '`':
            return c1 == '`' ? MarkToken.CODE_DOUBLE : MarkToken.CODE_SINGLE;
        case '\\':
            switch(c1)
            {
            case '\\':
            case '[':
            case ']':
            case '(':
            case ')':
            case '{':
            case '}':
            case '#':
            case '"':
            case '\'':
            case '.':
            case '>':
            case '*':
            case '+':
            case '-':
            case '_':
            case '!':
            case '`':
                return MarkToken.ESCAPE;
            default:
                return MarkToken.NONE;
            }
        case '<':
            return MarkToken.HTML;
        case '&':
            return MarkToken.ENTITY;
        default:
            return MarkToken.NONE;
        }
    }

    /**
     * Writes a set of markdown lines into the StringBuilder.
     * 
     * @param out The StringBuilder to write to.
     * @param lines The lines to write.
     */
    private void emitMarkedLines(final StringBuilder out, final Line lines)
    {
        final StringBuilder in = new StringBuilder();
        Line line = lines;
        while(line != null)
        {
            if(!line.isEmpty)
            {
                in.append(line.value.substring(line.leading, line.value.length() - line.trailing));
                if(line.trailing >= 2)
                    in.append("<br />");
            }
            if(line.next != null)
                in.append('\n');
            line = line.next;
        }

        this.recursiveEmitLine(out, in.toString(), 0, MarkToken.NONE);
    }

    /**
     * Writes a set of raw lines into the StringBuilder.
     * 
     * @param out The StringBuilder to write to.
     * @param lines The lines to write.
     */
    private void emitRawLines(final StringBuilder out, final Line lines)
    {
        Line line = lines;
        while(line != null)
        {
            if(!line.isEmpty)
            {
                out.append(line.value);
            }
            out.append('\n');
            line = line.next;
        }
    }

    /**
     * Writes a code block into the StringBuilder.
     * 
     * @param out The StringBuilder to write to.
     * @param lines The lines to write.
     */
    private void emitCodeLines(final StringBuilder out, final Line lines)
    {
        Line line = lines;
        while(line != null)
        {
            if(!line.isEmpty)
            {
                for(int i = 4; i < line.value.length(); i++)
                {
                    final char c;
                    switch(c = line.value.charAt(i))
                    {
                    case '&':
                        out.append("&amp;");
                        break;
                    case '<':
                        out.append("&lt;");
                        break;
                    case '>':
                        out.append("&gt;");
                        break;
                    default:
                        out.append(c);
                        break;
                    }
                }
            }
            out.append('\n');
            line = line.next;
        }
    }
}
