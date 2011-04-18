/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

/**
 * Default Decorator implementation.
 * 
 * <p>Example for a user Decorator having a class attribute on &lt;p> tags.</p>
 * <pre><code>public class MyDecorator extends DefaultDecorator
 *{
 *    &#64;Override
 *    public void openParagraph(StringBuilder out)
 *    {
 *        out.append("&lt;p class=\"myclass\">");
 *    }
 *}
 *</code></pre>
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
public class DefaultDecorator implements Decorator
{
    /** Constructor. */
    public DefaultDecorator()
    {
        // empty
    }
    
    /** @see txtmark.Decorator#openParagraph(StringBuilder) */
    @Override
    public void openParagraph(StringBuilder out)
    {
        out.append("<p>");
    }

    /** @see txtmark.Decorator#closeParagraph(StringBuilder) */
    @Override
    public void closeParagraph(StringBuilder out)
    {
        out.append("</p>\n");
    }

    /** @see txtmark.Decorator#openBlockquote(StringBuilder) */
    @Override
    public void openBlockquote(StringBuilder out)
    {
        out.append("<blockquote>");
    }

    /** @see txtmark.Decorator#closeBlockquote(StringBuilder) */
    @Override
    public void closeBlockquote(StringBuilder out)
    {
        out.append("</blockquote>\n");
    }

    /** @see txtmark.Decorator#openCodeBlock(StringBuilder) */
    @Override
    public void openCodeBlock(StringBuilder out)
    {
        out.append("<pre><code>");
    }

    /** @see txtmark.Decorator#closeCodeBlock(StringBuilder) */
    @Override
    public void closeCodeBlock(StringBuilder out)
    {
        out.append("</code></pre>\n");
    }

    /** @see txtmark.Decorator#openCodeSpan(StringBuilder) */
    @Override
    public void openCodeSpan(StringBuilder out)
    {
        out.append("<code>");
    }

    /** @see txtmark.Decorator#closeCodeSpan(StringBuilder) */
    @Override
    public void closeCodeSpan(StringBuilder out)
    {
        out.append("</code>");
    }

    /** @see txtmark.Decorator#openHeadline(StringBuilder, int) */
    @Override
    public void openHeadline(StringBuilder out, int level)
    {
        out.append("<h");
        out.append(level);
        out.append('>');
    }

    /** @see txtmark.Decorator#closeHeadline(StringBuilder, int) */
    @Override
    public void closeHeadline(StringBuilder out, int level)
    {
        out.append("</h");
        out.append(level);
        out.append(">\n");
    }

    /** @see txtmark.Decorator#openStrong(StringBuilder) */
    @Override
    public void openStrong(StringBuilder out)
    {
        out.append("<strong>");
    }

    /** @see txtmark.Decorator#closeStrong(StringBuilder) */
    @Override
    public void closeStrong(StringBuilder out)
    {
        out.append("</strong>");
    }

    /** @see txtmark.Decorator#openEmphasis(StringBuilder) */
    @Override
    public void openEmphasis(StringBuilder out)
    {
        out.append("<em>");
    }

    /** @see txtmark.Decorator#closeEmphasis(StringBuilder) */
    @Override
    public void closeEmphasis(StringBuilder out)
    {
        out.append("</em>");
    }

    /** @see txtmark.Decorator#openOrderedList(StringBuilder)*/
    @Override
    public void openOrderedList(StringBuilder out)
    {
        out.append("<ol>\n");
    }

    /** @see txtmark.Decorator#closeOrderedList(StringBuilder) */
    @Override
    public void closeOrderedList(StringBuilder out)
    {
        out.append("</ol>\n");
    }

    /** @see txtmark.Decorator#openUnorderedList(StringBuilder) */
    @Override
    public void openUnorderedList(StringBuilder out)
    {
        out.append("<ul>\n");
    }

    /** @see txtmark.Decorator#closeUnorderedList(StringBuilder) */
    @Override
    public void closeUnorderedList(StringBuilder out)
    {
        out.append("</ul>\n");
    }

    /** @see txtmark.Decorator#openListItem(StringBuilder) */    
    @Override
    public void openListItem(StringBuilder out)
    {
        out.append("<li>");
    }

    /** @see txtmark.Decorator#closeListItem(StringBuilder) */
    @Override
    public void closeListItem(StringBuilder out)
    {
        out.append("</li>\n");
    }

    /** @see txtmark.Decorator#horizontalRuler(StringBuilder) */
    @Override
    public void horizontalRuler(StringBuilder out)
    {
        out.append("<hr />\n");
    }

    /** @see txtmark.Decorator#openLink(StringBuilder) */
    @Override
    public void openLink(StringBuilder out)
    {
        out.append("<a");
    }

    /** @see txtmark.Decorator#openImage(StringBuilder) */
    @Override
    public void openImage(StringBuilder out)
    {
        out.append("<img");
    }
}
