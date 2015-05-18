/*
 * Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.rjeschke.txtmark;

/**
 * Default Decorator implementation.
 * 
 * <p>
 * Example for a user Decorator having a class attribute on &lt;p&gt; tags.
 * </p>
 * 
 * <pre>
 * <code>public class MyDecorator extends DefaultDecorator
 * {
 *     &#64;Override
 *     public void openParagraph(StringBuilder out)
 *     {
 *         out.append("&lt;p class=\"myclass\"&gt;");
 *     }
 * }
 * </code>
 * </pre>
 * 
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
public class NullDecorator implements Decorator
{
    /** Constructor. */
    public NullDecorator()
    {
        // empty
    }

    /** @see Decorator#openParagraph(StringBuilder) */
    @Override
    public void openParagraph(StringBuilder out)
    {

    }

    /** @see Decorator#closeParagraph(StringBuilder) */
    @Override
    public void closeParagraph(StringBuilder out)
    {

    }

    /** @see Decorator#openBlockquote(StringBuilder) */
    @Override
    public void openBlockquote(StringBuilder out)
    {
            }

    /** @see Decorator#closeBlockquote(StringBuilder) */
    @Override
    public void closeBlockquote(StringBuilder out)
    {

    }

    /** @see Decorator#openCodeBlock(StringBuilder) */
    @Override
    public void openCodeBlock(StringBuilder out)
    {

    }

    /** @see Decorator#closeCodeBlock(StringBuilder) */
    @Override
    public void closeCodeBlock(StringBuilder out)
    {

    }

    /** @see Decorator#openCodeSpan(StringBuilder) */
    @Override
    public void openCodeSpan(StringBuilder out)
    {

    }

    /** @see Decorator#closeCodeSpan(StringBuilder) */
    @Override
    public void closeCodeSpan(StringBuilder out)
    {

    }

    /**
     * @see Decorator#openHeadline(StringBuilder,
     *      int)
     */
    @Override
    public void openHeadline(StringBuilder out, int level)
    {
    }

    /**
     * @see Decorator#closeHeadline(StringBuilder,
     *      int)
     */
    @Override
    public void closeHeadline(StringBuilder out, int level)
    {

    }

    /** @see Decorator#openStrong(StringBuilder) */
    @Override
    public void openStrong(StringBuilder out)
    {

    }

    /** @see Decorator#closeStrong(StringBuilder) */
    @Override
    public void closeStrong(StringBuilder out)
    {

    }

    /** @see Decorator#openEmphasis(StringBuilder) */
    @Override
    public void openEmphasis(StringBuilder out){
    }

    /** @see Decorator#closeEmphasis(StringBuilder) */
    @Override
    public void closeEmphasis(StringBuilder out)
    {
            }

    /** @see Decorator#openSuper(StringBuilder) */
    @Override
    public void openSuper(StringBuilder out)
    {

    }

    /** @see Decorator#closeSuper(StringBuilder) */
    @Override
    public void closeSuper(StringBuilder out)
    {

    }

    /** @see Decorator#openOrderedList(StringBuilder,String) */
    @Override
    public void openOrderedList(StringBuilder out, String start)
    {

    }

    /** @see Decorator#closeOrderedList(StringBuilder) */
    @Override
    public void closeOrderedList(StringBuilder out)
    {

    }

    /** @see Decorator#openUnorderedList(StringBuilder) */
    @Override
    public void openUnorderedList(StringBuilder out)
    {

    }

    /** @see Decorator#closeUnorderedList(StringBuilder) */
    @Override
    public void closeUnorderedList(StringBuilder out)
    {

    }

    /** @see Decorator#openListItem(StringBuilder) */
    @Override
    public void openListItem(StringBuilder out)
    {

    }

    /** @see Decorator#closeListItem(StringBuilder) */
    @Override
    public void closeListItem(StringBuilder out)
    {

    }

    /** @see Decorator#horizontalRuler(StringBuilder) */
    @Override
    public void horizontalRuler(StringBuilder out)
    {

    }

    /** @see Decorator#openLink(StringBuilder) */
    @Override
    public void openLink(StringBuilder out)
    {

    }

    /** @see Decorator#closeLink(StringBuilder) */
    @Override
    public void closeLink(StringBuilder out)
    {

    }

    /** @see Decorator#openImage(StringBuilder) */
    @Override
    public void openImage(StringBuilder out)
    {

    }

    /** @see Decorator#closeImage(StringBuilder) */
    @Override
    public void closeImage(StringBuilder out)
    {

    }
}
