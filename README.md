# Txtmark - Java markdown processor
Copyright (C) 2011-2015 René Jeschke <rene_jeschke@yahoo.de>  
See LICENSE.txt for licensing information.

***

### Txtmark is yet another markdown processor for the JVM.  

*   It is easy to use:

        String result = txtmark.Processor.process("This is ***TXTMARK***");
    
*   It is fast (see below)  
    ... *well, it is the fastest markdown processor on the JVM right now.*
    (This might be outdated, but txtmark is still flippin' fast.)

*   It does not depend on other libraries, so classpathing `txtmark.jar` is
    sufficient to use Txtmark in your project.

For an in-depth explanation of markdown have a look at the original [Markdown Syntax].

***

### Maven repository

Txtmark is available on [maven central](http://search.maven.org/#search|ga|1|txtmark).

***

### Txtmark extensions

To enable Txtmark's extended markdown parsing you can use the $PROFILE$ mechanism:

    [$PROFILE$]: extended

This seemed to me as the easiest and safest way to enable different behaviours.
Just put this line into your Txtmark file like you would use reference links.

#### Behavior changes when using `[$PROFILE$]: extended`

*   Lists and code blocks end a paragraph

    In normal markdown the following:

        This is a paragraph
        * and this is not a list

    Will produce:

        <p>This is a paragraph
        * and this is not a list</p>

    When using Txtmark extensions this changes to:

        <p>This is a paragraph</p>
        <ul>
        <li>and this is not a list</li>
        </ul>

*   Text anchors

    Headlines and list items may recieve an ID which
    you can refer to using links.
    
        ## Headline with ID ##     {#headid}
        
        Another headline with ID   {#headid2}
        ------------------------
        
        * List with ID             {#listid}
        
        Links: [Foo] (#headid)
        
    this will produce:
    
        <h2 id="headid">Headline with ID</h2>
        <h2 id="headid2">Another headline with ID</h2>
        <ul>
        <li id="listid">List with ID</li>
        </ul>
        <p>Links: <a href="#headid">Foo</a></p>
    
    The ID _must_ be the last thing on the first line.
    
    All spaces before `{#` get removed, so you can't
    use an ID and a manual line break in the same line.
    
*   Auto HTML entities

    *   `(C)` becomes `&copy;` - &copy;
    *   `(R)` becomes `&reg;` - &reg;
    *   `(TM)` becomes `&trade;` - &trade;
    *   `--` becomes `&ndash;` - &ndash;
    *   `---` becomes `&mdash;` - &mdash;
    *   `...` becomes `&hellip;` - &hellip;
    *   `<<` becomes `&laquo;` - &laquo;
    *   `>>` becomes `&raquo;` - &raquo;
    *   `"Hello"` becomes `&ldquo;Hello&rdquo;` - &ldquo;Hello&rdquo;

*   Underscores (Emphasis)

    Underscores in the middle of a word don't result in emphasis.
    
        Con_cat_this
        
    normally produces this:
    
        Con<em>cat</em>this

*   Superscript

    You can use `^` to mark a span as superscript.
    
        2^2^ = 4
        
    turns into
    
        2<sup>2</sup> = 4

*   Abbreviations

    Abbreviations are defined like reference links, but using a `*`
    instead of a link and must be single-line only.
    
        [Git]: * "Fast distributed revision control system"
        
    and used like this
    
        This is [Git]!
        
    which will produce
    
        This is <abbr title="Fast distributed revision control system">Git</abbr>!

*   Fenced code blocks

        ```
        This is code!
        ```

        ~~~
        Another code block
        ~~~

        ~~~
        You can also mix flavours
        ```

    Fenced code block delimiter lines do start with at least three of `` or `~

    It is possible to add meta data to the beginning line. Everything trailing after `` or `~ is then considered meta data. These are all valid meta lines:

        ```python
        ~ ~ ~ ~ ~java
        ``` ``` ``` this is even more meta

    The meta information that you provide here can be used with a `BlockEmitter` to include e.g. syntax highlighted code blocks. Here's an example:

        public class CodeBlockEmitter implements BlockEmitter
        {
            private static void append(StringBuilder out, List<String> lines)
            {
                out.append("<pre class=\"pre_no_hl\">");
                for (final String l : lines)
                {
                    Utils.escapedAdd(out, l);
                    out.append('\n');
                }
                out.append("</pre>");
            }
        
            @Override
            public void emitBlock(StringBuilder out, List<String> lines, String meta)
            {
                if (Strings.isEmpty(meta))
                {
                    append(out, lines);
                }
                else
                {
                    try
                    {
                        // Utils#highlight(...) is not included with txtmark, it's sole purpose
                        // is to show what the meta can be used for
                        out.append(Utils.highlight(lines, meta));
                        out.append('\n');
                    }
                    catch (final IOException e)
                    {
                        // Ignore or do something, still, pump out the lines
                        append(out, lines);
                    }
                }
            }
        }

    You can then set the `BlockEmitter` in the txtmark `Configuration` using `Configuration.Builder#setCodeBlockEmitter(BlockEmitter emitter)`.

        
***

### Markdown conformity

Txtmark passes all tests inside [MarkdownTest\_1.0\_2007-05-09](http://daringfireball.net/projects/downloads/MarkdownTest_1.0_2007-05-09.tgz)
except of two:

1.  **Images.text**

    Fails because Txtmark doesn't produce empty 'title' image attributes.  
    (IMHO: Images ... OK)

2.  **Literal quotes in titles.text**

    What the frell ... this test will continue to FAIL.  
    Sorry, but using unescaped `"` in a title which should be surrounded
    by `"` is unacceptable for me ;)

    Change:

        Foo [bar](/url/ "Title with "quotes" inside").
        [bar]: /url/ "Title with "quotes" inside"

    to:

        Foo [bar](/url/ "Title with \"quotes\" inside").
        [bar]: /url/ "Title with \"quotes\" inside"

    and Txtmark will produce the correct result.  
    (IMHO: Literal quotes in titles ... OK)

***

### Where Txtmark is not like Markdown

*   Txtmark does not produce empty `title` attributes in link and image tags.

*   Unescaped `"` in link titles starting with `"` are not recognized and result
    in unexpected behaviour.
    
*   Due to a different list parsing approach some things get interpreted differently:

        * List
        > Quote

    will produce when processed with Markdown:

        <p><ul>
        <li>List</p>

        <blockquote>
         <p>Quote</li>
        </ul></p>
        </blockquote>

    and this when produced with Txtmark:

        <ul>
        <li>List<blockquote><p>Quote</p>
        </blockquote>
        </li>
        </ul>

    Another one:

        * List
        ====

    will produce when processed with Markdown:

        <h1>* List</h1>
    
    and this when produced with Txtmark:

        <ul>
        <li><h1>List</h1>
        </li>
        </ul>

*   List of escapeable characters:

        \   [   ]   (   )   {   }   #
        "   '   .   <   >   +   -   _
        !   `   ^
        

***

### Performance comparison of markdown processors for the JVM

**Remarks:** These benchmarks are too old to be of any value. I leave them here as a reference, though.

Based on [this benchmark suite](http://henkelmann.eu/2011/01/10/performance_comparison_of_markdown_processor_for_the_jvm).  

Excerpt from the original post concerning this benchmark suite:

>   Most of these tests are of course unrealistic: Who would write a 
>   text where each word is a link? Yet they serve an important use:
>   It makes it possible for the developer to pinpoint the parts of 
>   the parser where there is most room for improvement. Also, it 
>   explains why certain texts might render much faster in one 
>   Processor than in another.

Benchmark system:

*   Ubuntu Linux 10.04 32 Bit
*   Intel(R) Core(TM) 2 Duo T7500 @ 2.2GHz
*   Java(TM) SE Runtime Environment (build 1.6.0_24-b07)
*   Java HotSpot(TM) Server VM (build 19.1-b02, mixed mode)


<table>
  <tr><th>Test</th><th colspan="2">Actuarius</th><th colspan="2">PegDown</th><th colspan="2">Knockoff</th><th colspan="2">Txtmark</th></tr>
  <tr><td></td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td></tr>
  <tr><td>Plain Paragraphs</td><td>1127</td><td>577</td><td>1273</td><td>1037</td><td>740</td><td>400</td><td>157</td><td>64</td></tr>
  <tr><td>Every Word Emphasized</td><td>1562</td><td>1001</td><td>1523</td><td>1513</td><td>13982</td><td>13221</td><td>54</td><td>46</td></tr>
  <tr><td>Every Word Strong</td><td>1125</td><td>997</td><td>1115</td><td>1114</td><td>9543</td><td>9647</td><td>44</td><td>41</td></tr>
  <tr><td>Every Word Inline Code</td><td>382</td><td>277</td><td>1058</td><td>1052</td><td>9116</td><td>9074</td><td>51</td><td>39</td></tr>
  <tr><td>Every Word a Fast Link</td><td>2257</td><td>1600</td><td>537</td><td>531</td><td>3980</td><td>3410</td><td>109</td><td>55</td></tr>
  <tr><td>Every Word Consisting of Special XML Chars</td><td>4045</td><td>4270</td><td>2985</td><td>3044</td><td>312</td><td>377</td><td>778</td><td>775</td></tr>
  <tr><td>Every Word wrapped in manual HTML tags</td><td>3334</td><td>2919</td><td>901</td><td>896</td><td>3863</td><td>3736</td><td>73</td><td>62</td></tr>
  <tr><td>Every Line with a manual line break</td><td>510</td><td>588</td><td>1445</td><td>1440</td><td>1527</td><td>1130</td><td>56</td><td>56</td></tr>
  <tr><td>Every word with a full link</td><td>452</td><td>246</td><td>1045</td><td>996</td><td>1884</td><td>1819</td><td>86</td><td>55</td></tr>
  <tr><td>Every word with a full image</td><td>268</td><td>150</td><td>1140</td><td>1132</td><td>1985</td><td>1908</td><td>38</td><td>36</td></tr>
  <tr><td>Every word with a reference link</td><td>9847</td><td>9082</td><td>18956</td><td>18719</td><td>121136</td><td>115416</td><td>1525</td><td>1380</td></tr>
  <tr><td>Every block a quote</td><td>445</td><td>206</td><td>1312</td><td>1301</td><td>478</td><td>457</td><td>50</td><td>45</td></tr>
  <tr><td>Every block a codeblock</td><td>70</td><td>87</td><td>373</td><td>376</td><td>161</td><td>175</td><td>60</td><td>22</td></tr>
  <tr><td>Every block a list</td><td>920</td><td>912</td><td>1720</td><td>1725</td><td>622</td><td>651</td><td>55</td><td>55</td></tr>
  <tr><td>All tests together</td><td>3281</td><td>2885</td><td>5184</td><td>5196</td><td>10130</td><td>10460</td><td>206</td><td>196</td></tr>
</table>

##### Benchmarked versions:  
[Actuarius] version: 0.2  
[PegDown] version: 0.8.5.4  
[Knockoff] version: 0.7.3-15  

***

### Mentioned/related projects

[Markdown] is Copyright (C) 2004 by John Gruber  
[SmartyPants] is Copyright (C) 2003 by John Gruber  
[Actuarius] is Copyright (C) 2010 by Christoph Henkelmann  
[Knockoff] is Copyright (C) 2009-2011 by Tristan Juricek  
[PegDown] is Copyright (C) 2010 by Mathias Doenitz  
[PHP Markdown & Extra] is Copyright (C) 2009 Michel Fortin  

***

[Markdown Syntax]: http://daringfireball.net/projects/markdown/syntax/ "daringfireball.net"
[Markdown]: http://daringfireball.net/projects/markdown/
[SmartyPants]: http://daringfireball.net/projects/smartypants/
[Actuarius]: http://henkelmann.eu/projects/actuarius/
[Knockoff]: http://tristanhunt.com/projects/knockoff/
[PegDown]: https://github.com/sirthias/pegdown/
[PHP Markdown & Extra]: http://michelf.com/projects/php-markdown/
[Apache Ant(TM)]: http://ant.apache.org/

[repo]: https://github.com/rjeschke/txtmark/ "Txtmark at GitHub.com"
[tar]: https://github.com/rjeschke/txtmark/tarball/master "branch: master"
[zip]: https://github.com/rjeschke/txtmark/zipball/master "branch: master"

[$PROFILE$]: extended "Txtmark processing information."

Project link: <https://github.com/rjeschke/txtmark>
