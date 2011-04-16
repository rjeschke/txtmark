/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

/**
 * Utilities.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
class Utils
{
    /**
     * Skips spaces in the given String.
     * 
     * @param in Input String.
     * @param start Starting position.
     * @return The new position or -1 if EOL has been reached.
     */
    public static int skipSpaces(final String in, final int start)
    {
        int pos = start;
        while(pos < in.length() && (in.charAt(pos) == ' ' || in.charAt(pos) == '\n'))
            pos++;
        return pos < in.length() ? pos : -1;
    }

    /**
     * Processed the given escape sequence.
     * 
     * @param out The StringBuilder to write to.
     * @param ch The character.
     * @param pos Current parsing position.
     * @return The new position.
     */
    public static int escape(final StringBuilder out, final char ch, final int pos)
    {
        switch(ch)
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
            out.append(ch);
            return pos + 1;
        default:
            out.append('\\');
            return pos;
        }
    }
    
    /**
     * Reads characters until any 'end' character is encountered.
     * 
     * @param out The StringBuilder to write to.
     * @param in The Input String.
     * @param start Starting position.
     * @param end End characters.
     * @return The new position or -1 if no 'end' char was found.
     */
    public static int readUntil(final StringBuilder out, final String in, final int start, final char... end)
    {
        int pos = start;
        while(pos < in.length())
        {
            final char ch = in.charAt(pos);
            if(ch == '\\' && pos + 1 < in.length())
            {
                pos = escape(out, in.charAt(pos + 1), pos);
            }
            else
            {
                boolean endReached = false;
                for(int n = 0; n < end.length; n++)
                {
                    if(ch == end[n])
                    {
                        endReached = true;
                        break;
                    }
                }
                if(endReached)
                    break;
                out.append(ch);
            }
            pos++;
        }

        return (pos == in.length()) ? -1 : pos;
    }

    /**
     * Reads characters until the 'end' character is encountered.
     * 
     * @param out The StringBuilder to write to.
     * @param in The Input String.
     * @param start Starting position.
     * @param end End characters.
     * @return The new position or -1 if no 'end' char was found.
     */
    public static int readUntil(final StringBuilder out, final String in, final int start, final char end)
    {
        int pos = start;
        while(pos < in.length())
        {
            final char ch = in.charAt(pos);
            if(ch == '\\' && pos + 1 < in.length())
            {
                pos = escape(out, in.charAt(pos + 1), pos);
            }
            else
            {
                if(ch == end)
                    break;
                out.append(ch);
            }
            pos++;
        }

        return (pos == in.length()) ? -1 : pos;
    }

    /**
     * Reads a markdown link.
     * 
     * @param out The StringBuilder to write to.
     * @param in Input String.
     * @param start Starting position.
     * @return The new position or -1 if this is no valid markdown link.
     */
    public static int readMdLink(final StringBuilder out, final String in, final int start)
    {
        int pos = start;
        int counter = 1;
        while(pos < in.length())
        {
            final char ch = in.charAt(pos);
            if(ch == '\\' && pos + 1 < in.length())
            {
                pos = escape(out, in.charAt(pos + 1), pos);
            }
            else
            {
                boolean endReached = false;
                switch(ch)
                {
                case '(':
                    counter++;
                    break;
                case ' ':
                    if(counter == 1)
                        endReached = true;
                    break;
                case ')':
                    counter--;
                    if(counter == 0)
                        endReached = true;
                    break;
                }
                if(endReached)
                    break;
                out.append(ch);
            }
            pos++;
        }

        return (pos == in.length()) ? -1 : pos;
    }

    /**
     * Reads a markdown link ID.
     * 
     * @param out The StringBuilder to write to.
     * @param in Input String.
     * @param start Starting position.
     * @return The new position or -1 if this is no valid markdown link ID.
     */
    public static int readMdLinkId(final StringBuilder out, final String in, final int start)
    {
        int pos = start;
        int counter = 1;
        while(pos < in.length())
        {
            final char ch = in.charAt(pos);
            if(ch == '\\' && pos + 1 < in.length())
            {
                pos = escape(out, in.charAt(pos + 1), pos);
            }
            else
            {
                boolean endReached = false;
                switch(ch)
                {
                case '\n':
                    out.append(' ');
                    break;
                case '[':
                    counter++;
                    out.append(ch);
                    break;
                case ']':
                    counter--;
                    if(counter == 0)
                        endReached = true;
                    else
                        out.append(ch);
                    break;
                default:
                    out.append(ch);
                    break;
                }
                if(endReached)
                    break;
            }
            pos++;
        }

        return (pos == in.length()) ? -1 : pos;
    }

    /**
     * Reads characters until any 'end' character is encountered, ignoring escape sequences.
     * 
     * @param out The StringBuilder to write to.
     * @param in The Input String.
     * @param start Starting position.
     * @param end End characters.
     * @return The new position or -1 if no 'end' char was found.
     */
    public static int readRawUntil(final StringBuilder out, final String in, final int start, final char... end)
    {
        int pos = start;
        while(pos < in.length())
        {
            final char ch = in.charAt(pos);
            boolean endReached = false;
            for(int n = 0; n < end.length; n++)
            {
                if(ch == end[n])
                {
                    endReached = true;
                    break;
                }
            }
            if(endReached)
                break;
            out.append(ch);
            pos++;
        }

        return (pos == in.length()) ? -1 : pos;
    }

    /**
     * Reads characters until the end character is encountered, ignoring escape sequences.
     * 
     * @param out The StringBuilder to write to.
     * @param in The Input String.
     * @param start Starting position.
     * @param end End characters.
     * @return The new position or -1 if no 'end' char was found.
     */
    public static int readRawUntil(final StringBuilder out, final String in, final int start, final char end)
    {
        int pos = start;
        while(pos < in.length())
        {
            final char ch = in.charAt(pos);
            if(ch == end)
                break;
            out.append(ch);
            pos++;
        }

        return (pos == in.length()) ? -1 : pos;
    }

    /**
     * Appends the given string encoding special HTML characters.
     * 
     * @param out The StringBuilder to write to.
     * @param in Input String.
     * @param start Input String starting position.
     * @param end Input String end position.
     */
    public static void appendCode(final StringBuilder out, final String in, final int start, final int end)
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

    /**
     * Appends the given string encoding special HTML characters (used in HTML attribute values).
     * 
     * @param out The StringBuilder to write to.
     * @param in Input String.
     * @param start Input String starting position.
     * @param end Input String end position.
     */
    public static void appendValue(final StringBuilder out, final String in, final int start, final int end)
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
            case '"':
                out.append("&quot;");
                break;
            case '\'':
                out.append("&apos;");
                break;
            default:
                out.append(c);
                break;
            }
        }
    }
}
