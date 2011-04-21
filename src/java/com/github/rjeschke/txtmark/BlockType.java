/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package com.github.rjeschke.txtmark;

/**
 * Block type enum.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
enum BlockType
{
    /** Unspecified. Used for root block and list items without paragraphs. */
    NONE,
    /** A block quote. */
    BLOCKQUOTE,
    /** A code block. */
    CODE,
    /** A headline. */
    HEADLINE,
    /** A list item. */
    LIST_ITEM,
    /** An ordered list. */
    ORDERED_LIST,
    /** A paragraph. */
    PARAGRAPH,
    /** A horizontal ruler. */
    RULER,
    /** An unordered list. */
    UNORDERED_LIST,
    /** A XML block. */
    XML
}
