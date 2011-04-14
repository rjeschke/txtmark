/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

class Line
{
    public int pos;
    public int leading = 0, trailing = 0;
    public boolean isEmpty = true;
    public String value =  null;
    public Line previous = null, next = null;
    public boolean prevEmpty, nextEmpty;

    public Line()
    {
        //
    }

    public void init()
    {
        this.leading = 0;
        while(this.leading < this.value.length() && this.value.charAt(this.leading) == ' ')
            this.leading++;

        if(this.leading == this.value.length())
        {
            this.setEmpty();
        }
        else
        {
            this.isEmpty = false;
            this.trailing = 0;
            while(this.value.charAt(this.value.length() - this.trailing - 1) == ' ')
                 this.trailing++;
        }
    }

    public void initLeading()
    {
        this.leading = 0;
        while(this.leading < this.value.length() && this.value.charAt(this.leading) == ' ')
            this.leading++;

        if(this.leading == this.value.length())
        {
            this.setEmpty();
        }
    }

    // TODO use Util#skipSpaces
    public boolean skipSpaces()
    {
        while(this.pos < this.value.length() && this.value.charAt(this.pos) == ' ')
            this.pos++;
        return this.pos < this.value.length();
    }

    // TODO use Util#readUntil
    public String readUntil(char... end)
    {
        final StringBuilder sb = new StringBuilder();
        int pos = this.pos;
        while(pos < this.value.length())
        {
            final char ch = this.value.charAt(pos);
            if(ch == '\\' && pos + 1 < this.value.length())
            {
                final char c;
                switch(c = this.value.charAt(pos + 1))
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
                    sb.append(c);
                    pos++;
                    break;
                default:
                    sb.append(ch);
                    break;
                }
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
                sb.append(ch);
            }
            pos++;
        }

        final char ch = pos < this.value.length() ? this.value.charAt(pos) : '\n';
        for(int n = 0; n < end.length; n++)
        {
            if(ch == end[n])
            {
                this.pos = pos;
                return sb.toString();
            }
        }
        return null;
    }

    public void setEmpty()
    {
        this.value = "";
        this.leading = this.trailing = 0;
        this.isEmpty = true;
        if(this.previous != null)
            this.previous.nextEmpty = true;
        if(this.next != null)
            this.next.prevEmpty = true;
    }

    private int countCharsWs(char ch)
    {
        int count = 0;
        for(int i = 0; i < this.value.length(); i++)
        {
            final char c = this.value.charAt(i);
            if(c == ' ')
                continue;
            if(c == ch)
            {
                count++;
                continue;
            }
            count = 0;
            break;
        }
        return count;
    }

    public LineType getLineType()
    {
        if(this.isEmpty)
            return LineType.EMPTY;

        if(this.leading > 3)
            return LineType.CODE;

        if(this.value.charAt(this.leading) == '#')
            return LineType.HEADLINE;

        if(this.value.charAt(this.leading) == '>')
            return LineType.BQUOTE;

        if(this.leading == 0 && this.value.length() > 2 && (this.value.charAt(0) == '*' || this.value.charAt(0) == '-'))
        {
            if(this.countCharsWs(this.value.charAt(0)) >= 3)
                return LineType.HR;
        }

        if(this.value.length() - this.leading >= 2 && this.value.charAt(this.leading + 1) == ' ')
        {
            switch(this.value.charAt(this.leading))
            {
            case '*':
            case '-':
            case '+':
                return LineType.ULIST;
            }
        }

        if(this.value.length() - this.leading >= 3 && Character.isDigit(this.value.charAt(this.leading)))
        {
            int i = this.leading + 1;
            while(i < this.value.length() && Character.isDigit(this.value.charAt(i)))
                i++;
            if(i + 1 < this.value.length() && this.value.charAt(i) == '.' && this.value.charAt(i + 1) == ' ')
                return LineType.OLIST;
        }

        if(this.next != null && !this.next.isEmpty)
        {
            if((this.next.value.charAt(0) == '-') && (this.next.countCharsWs('-') > 0))
                return LineType.HEADLINE2;
            if((this.next.value.charAt(0) == '=') && (this.next.countCharsWs('=') > 0))
                return LineType.HEADLINE1;
        }

        return LineType.OTHER;
    }
}
