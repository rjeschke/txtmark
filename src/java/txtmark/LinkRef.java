/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

class LinkRef
{
    public final String link;
    public String title;

    public LinkRef(final String link, final String title)
    {
        this.link = link;
        this.title = title;
    }

    @Override
    public String toString()
    {
        return this.link + " \"" + this.title + "\"";
    }
}
