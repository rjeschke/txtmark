package txtmark;

public class DefaultDecorator implements Decorator
{

    @Override
    public void openParagraph(StringBuilder out)
    {
        out.append("<p>");
    }

    @Override
    public void closeParagraph(StringBuilder out)
    {
        out.append("</p>\n");
    }

    @Override
    public void openBlockquote(StringBuilder out)
    {
        out.append("<blockquote>");
    }

    @Override
    public void closeBlockquote(StringBuilder out)
    {
        out.append("</blockquote>\n");
    }

    @Override
    public void openCodeBlock(StringBuilder out)
    {
        out.append("<pre><code>");
    }

    @Override
    public void closeCodeBlock(StringBuilder out)
    {
        out.append("</code></pre>\n");
    }

    @Override
    public void openCodeSpan(StringBuilder out)
    {
        out.append("<code>");
    }

    @Override
    public void closeCodeSpan(StringBuilder out)
    {
        out.append("</code>");
    }

    @Override
    public void openHeadline(StringBuilder out, int level)
    {
        out.append("<h");
        out.append(level);
        out.append('>');
    }

    @Override
    public void closeHeadline(StringBuilder out, int level)
    {
        out.append("</h");
        out.append(level);
        out.append(">\n");
    }

    @Override
    public void openStrong(StringBuilder out)
    {
        out.append("<strong>");
    }

    @Override
    public void closeStrong(StringBuilder out)
    {
        out.append("</strong>");
    }

    @Override
    public void openEmphasis(StringBuilder out)
    {
        out.append("<em>");
    }

    @Override
    public void closeEmphasis(StringBuilder out)
    {
        out.append("</em>");
    }

    @Override
    public void openOrderedList(StringBuilder out)
    {
        out.append("<ol>\n");
    }

    @Override
    public void closeOrderedList(StringBuilder out)
    {
        out.append("</ol>\n");
    }

    @Override
    public void openUnorderedList(StringBuilder out)
    {
        out.append("<ul>\n");
    }

    @Override
    public void closeUnorderedList(StringBuilder out)
    {
        out.append("</ul>\n");
    }

    @Override
    public void openListItem(StringBuilder out)
    {
        out.append("<li>");
    }

    @Override
    public void closeListItem(StringBuilder out)
    {
        out.append("</li>");
    }

    @Override
    public void horizontalRuler(StringBuilder out)
    {
        out.append("<hr />");
    }

    @Override
    public void openLink(StringBuilder out)
    {
        out.append("<a");
    }

    @Override
    public void openImage(StringBuilder out)
    {
        out.append("<img");
    }
}
