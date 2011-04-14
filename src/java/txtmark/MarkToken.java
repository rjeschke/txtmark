/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

enum MarkToken
{
    NONE,
    EM_STAR,            // x*x
    EM_UNDERSCORE,      // x_x
    STRONG_STAR,        // x**x
    STRONG_UNDERSCORE,  // x__x
    CODE_SINGLE,        // `
    CODE_DOUBLE,        // ``
    LINK,               // [
    HTML,               // <
    IMAGE,              // ![
    ENTITY,             // &
    ESCAPE              // \x
}
