/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package com.github.rjeschke.txtmark;

/**
 * A markdown link reference.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
class LinkRef
{
    /** The link. */
    public final String link;
    /** The optional comment/title. */
    public String title;
    /** Flag indicating that this is an abbreviation. */
    public final boolean isAbbrev;

    /**
     * Constructor.
     * 
     * @param link The link.
     * @param title The title (may be <code>null</code>).
     */
    public LinkRef(final String link, final String title, final boolean isAbbrev)
    {
        this.link = link;
        this.title = title;
        this.isAbbrev = isAbbrev;
    }

    /** @see java.lang.Object#toString() */
    @Override
    public String toString()
    {
        return this.link + " \"" + this.title + "\"";
    }
}
