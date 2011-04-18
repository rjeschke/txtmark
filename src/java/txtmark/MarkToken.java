/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

/**
 * Markdown token enumeration.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
enum MarkToken
{
    /** No token. */
    NONE,
    /** &#x2a; */
    EM_STAR,            // x*x
    /** _ */
    EM_UNDERSCORE,      // x_x
    /** &#x2a;&#x2a; */
    STRONG_STAR,        // x**x
    /** __ */
    STRONG_UNDERSCORE,  // x__x
    /** ` */
    CODE_SINGLE,        // `
    /** `` */
    CODE_DOUBLE,        // ``
    /** [ */
    LINK,               // [
    /** &lt; */
    HTML,               // <
    /** ![ */
    IMAGE,              // ![
    /** &amp; */
    ENTITY,             // &
    /** \ */
    ESCAPE,             // \x
    /** Extended: &copy; */
    X_COPY,             // (C)
    /** Extended: &reg; */
    X_REG,              // (R)
    /** Extended: &trade; */
    X_TRADE,            // (TM)
    /** Extended: &laquo; */
    X_LAQUO,            // <<
    /** Extended: &raquo; */
    X_RAQUO,            // >>
    /** Extended: &mdash; */
    X_NDASH,            // --
    /** Extended: &ndash; */
    X_MDASH,            // ---
    /** Extended: &hellip; */
    X_HELLIP,           // ...
    /** Extended: &rdquo; */
    X_RDQUO,            // "
    /** Extended: &ldquo; */
    X_LDQUO            // "
}
