package txtmark;

public interface Decorator
{
    public void openParagraph(final StringBuilder out);
    public void closeParagraph(final StringBuilder out);

    public void openBlockquote(final StringBuilder out);
    public void closeBlockquote(final StringBuilder out);
    
    public void openCodeBlock(final StringBuilder out);
    public void closeCodeBlock(final StringBuilder out);
    
    public void openCodeSpan(final StringBuilder out);
    public void closeCodeSpan(final StringBuilder out);
    
    public void openHeadline(final StringBuilder out, int level);
    public void closeHeadline(final StringBuilder out, int level);

    public void openStrong(final StringBuilder out);
    public void closeStrong(final StringBuilder out);
    
    public void openEmphasis(final StringBuilder out);
    public void closeEmphasis(final StringBuilder out);

    public void openOrderedList(final StringBuilder out);
    public void closeOrderedList(final StringBuilder out);

    public void openUnorderedList(final StringBuilder out);
    public void closeUnorderedList(final StringBuilder out);
    
    public void openListItem(final StringBuilder out);
    public void closeListItem(final StringBuilder out);
    
    public void horizontalRuler(final StringBuilder out);
    
    public void openLink(final StringBuilder out);
    
    public void openImage(final StringBuilder out);
}
