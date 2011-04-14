/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

import java.util.HashMap;

class Emitter
{
    private final HashMap<String, LinkRef> linkRefs = new HashMap<String, LinkRef>();

    public Emitter()
    {
        //
    }

    public void addLinkRef(final String key, final LinkRef linkRef)
    {
        this.linkRefs.put(key.toLowerCase(), linkRef);
    }

    public void emit(final StringBuilder out, final Block root)
    {
        root.removeSurroundingEmptyLines();

        switch(root.type)
        {
        case RULER:
            out.append("<hr />");
            return;
        case NONE:
        case XML:
            break;
        case HEADLINE:
            out.append("<h");
            out.append(root.hlDepth);
            out.append('>');
            break;
        case PARAGRAPH:
            out.append("<p>");
            break;
        case CODE:
            out.append("<pre><code>");
            break;
        case BLOCKQUOTE:
            out.append("<blockquote>");
            break;
        case UNORDERED_LIST:
            out.append("<ul>\n");
            break;
        case ORDERED_LIST:
            out.append("<ol>\n");
            break;
        case LIST_ITEM:
            out.append("<li>");
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
            out.append("</h");
            out.append(root.hlDepth);
            out.append(">\n");
            break;
        case PARAGRAPH:
            out.append("</p>\n");
            break;
        case CODE:
            out.append("</code></pre>\n");
            break;
        case BLOCKQUOTE:
            out.append("</blockquote>\n");
            break;
        case UNORDERED_LIST:
            out.append("</ul>\n");
            break;
        case ORDERED_LIST:
            out.append("</ol>\n");
            break;
        case LIST_ITEM:
            out.append("</li>\n");
            break;
        }
    }

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

    private void appendCode(final StringBuilder out, final String in, int start, int end)
    {
        for(int i = start; i < end; i++)
        {
            final char c;
            switch(c = in.charAt(i))
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

    private int checkLink(final StringBuilder out, final String in, int start, MarkToken token)
    {
        int pos = start + (token == MarkToken.LINK ? 1 : 2);
        final StringBuilder temp = new StringBuilder();

        temp.setLength(0);
        pos = Utils.readUntil(temp, in, pos, ']');
        if(pos < start)
            return -1;

        String name = temp.toString(), link = null, comment = null;

        pos++;
        pos = Utils.skipSpaces(in, pos);
        if(pos < start)
            return -1;
        if(in.charAt(pos) == '(')
        {
            pos++;
            pos = Utils.skipSpaces(in, pos);
            if(pos < start)
                return -1;
            temp.setLength(0);
            boolean useLt = in.charAt(pos) == '<';
            if(useLt)
                pos++;
            pos = Utils.readUntil(temp, in, pos, useLt ? '>' : ' ', ')');
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
            pos = Utils.readUntil(temp, in, pos, ']');
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

        if(link == null)
            return -1;

        if(token == MarkToken.LINK)
        {
            out.append("<a href=\"");
            this.appendCode(out, link, 0, link.length());
            out.append('"');
            if(comment != null)
            {
                out.append(" title=\"");
                this.appendCode(out, comment, 0, comment.length());
                out.append('"');
            }
            out.append('>');
            this.recursiveEmitLine(out, name, 0, MarkToken.NONE);
            out.append("</a>");
        }
        else
        {
            out.append("<img src=\"");
            this.appendCode(out, link, 0, link.length());
            out.append("\" alt=\"");
            this.appendCode(out, name, 0, name.length());
            out.append('"');
            if(comment != null)
            {
                out.append(" title=\"");
                this.appendCode(out, comment, 0, comment.length());
                out.append('"');
            }
            out.append(" />");
        }

        return pos;
    }

    private int checkEntity(final StringBuilder out, final String in, int start)
    {
        final StringBuilder temp = new StringBuilder();
        int pos = Utils.readUntil(temp, in, start, ';');
        if(pos < 0 || temp.length() < 3)
            return -1;
        out.append('&');
        if(temp.charAt(1) == '#')
        {
            out.append('#');
            if(temp.charAt(2) == 'x' || temp.charAt(2) == 'X')
            {
                if(temp.length() < 4)
                    return -1;
                out.append(temp.charAt(2));
                for(int i = 3; i < temp.length(); i++)
                {
                    final char c = temp.charAt(i);
                    if(!Character.isDigit(c) && !(Character.isLetter(c) && ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))))
                        return -1;
                    out.append(c);
                }
            }
            else
            {
                for(int i = 2; i < temp.length(); i++)
                {
                    final char c;
                    if(!Character.isDigit(c = temp.charAt(i)))
                        return -1;
                    out.append(c);
                }
            }
        }
        else
        {
            for(int i = 1; i < temp.length(); i++)
            {
                final char c;
                if(!Character.isLetter(c = temp.charAt(i)))
                    return -1;
                out.append(c);
            }
        }
        out.append(';');
        
        return pos;
    }
    
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
                    out.append("<em>");
                    out.append(temp);
                    out.append("</em>");
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
                    out.append("<strong>");
                    out.append(temp);
                    out.append("</strong>");
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
                    out.append("<code>");
                    this.appendCode(out, in, a, b);
                    out.append("</code>");
                    pos = b + (mt == MarkToken.CODE_DOUBLE ? 1 : 0);
                }
                else
                {
                    out.append(in.charAt(pos));
                }
                break;
            case HTML:
                out.append("&lt;");
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

    private void emitRawLines(final StringBuilder out, final Line lines)
    {
        Line line = lines;
        while(line != null)
        {
            if(!line.isEmpty)
            {
                out.append(line.value);
            }
            if(line.next != null)
                out.append('\n');
            line = line.next;
        }
    }

    private void emitCodeLines(final StringBuilder out, final Line lines)
    {
        Line line = lines;
        while(line != null)
        {
            if(!line.isEmpty)
            {
                for(int i = 4; i < line.value.length() - line.trailing; i++)
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
