package txtmark;

class Utils
{
    public static int skipSpaces(final String in, int start)
    {
        int pos = start;
        while(pos < in.length() && in.charAt(pos) == ' ' && in.charAt(pos) != '\n')
            pos++;
        return pos < in.length() && in.charAt(pos) != '\n' ? pos : -1;
    }

    public static int readUntil(final StringBuilder out, final String in, int start, char... end)
    {
        int pos = start;
        while(pos < in.length() && in.charAt(pos) != '\n')
        {
            final char ch = in.charAt(pos);
            if(ch == '\\' && pos + 1 < in.length())
            {
                final char c;
                switch(c = in.charAt(pos + 1))
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
                    out.append(c);
                    pos++;
                    break;
                default:
                    out.append(ch);
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
                out.append(ch);
            }
            pos++;
        }

        final char ch = pos < in.length() ? in.charAt(pos) : '\n';
        for(int n = 0; n < end.length; n++)
        {
            if(ch == end[n])
                return pos;
        }
        return -1;
    }
}
