# Txtmark - Java markdown processor
Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>  
See LICENSE.txt for licensing information.

***

Txtmark is yet another markdown processor for the JVM.  

*   It is easy to use:

        String result = txtmark.Processor.process("This is ***TXTMARK***");
    
*   It is fast (see below)  
    ... well, it is the fastest markdown processor on the JVM right now.

*   It does not depend on other libraries, so classpathing `txtmark.jar` is
    sufficient to use Txtmark in your project.

For an in-depth explanation of the markdown syntax have a look at [daringfireball.net](http://daringfireball.net/projects/markdown/syntax).

### Build instructions

1.  Clone the repo
2.  Do

        ant release

    and you will find everything you need inside the `release` folder.


### Where Txtmark is not like Markdown

***

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

### Txtmark extensions

***

To enable Txtmark's extended markdown parsing you can use the $PROFILE$ mechanism:

    [$PROFILE$]: extended

This seemed to me as the easiest and safest way to enable different behaviours.
(All other markdown processors will ignore this line.)

#### Behavior changes when using `[$PROFILE$]: extended`

*   Lists and code blocks end a paragraph (inspired by [Actuarius])

    In normal markdown the following:

        This is a paragraph
        * and this is not a list

    will produce:

        <p>This is a paragraph
        * and this is not a list</p>

    When using Txtmark extensions this changes to:

        <p>This is a paragraph</p>
        <ul>
        <li>and this is not a list</li>
        </ul>

*    More to come ...


### Markdown conformity

***

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


### Performance comparison of markdown processors for the JVM

---

Based on [this benchmark suite](http://henkelmann.eu/2011/01/10/performance_comparison_of_markdown_processor_for_the_jvm).  

<table>
  <tr><th>Test</th><th colspan="2">Actuarius</th><th colspan="2">PegDown</th><th colspan="2">Knockoff</th><th colspan="2">Txtmark</th></tr>
  <tr><td></td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td></tr>
  <tr><td>Plain Paragraphs</td><td>887</td><td>461</td><td>2455</td><td>2236</td><td>764</td><td>568</td><td>89</td><td>47</td></tr>
  <tr><td>Every Word Emphasized</td><td>2220</td><td>2077</td><td>3411</td><td>3406</td><td>30503</td><td>30514</td><td>72</td><td>66</td></tr>
  <tr><td>Every Word Strong</td><td>2384</td><td>2270</td><td>2456</td><td>2466</td><td>23639</td><td>23577</td><td>62</td><td>57</td></tr>
  <tr><td>Every Word Inline Code</td><td>824</td><td>804</td><td>2337</td><td>2237</td><td>23506</td><td>23622</td><td>54</td><td>55</td></tr>
  <tr><td>Every Word a Fast Link</td><td>3942</td><td>3738</td><td>1164</td><td>1159</td><td>8621</td><td>8595</td><td>89</td><td>68</td></tr>
  <tr><td>Every Word Consisting of Special XML Chars</td><td>9393</td><td>9312</td><td>7544</td><td>7314</td><td>801</td><td>608</td><td>3587</td><td>3614</td></tr>
  <tr><td>Every Word wrapped in manual HTML tags</td><td>6843</td><td>6828</td><td>1850</td><td>1859</td><td>8699</td><td>8692</td><td>1169</td><td>1154</td></tr>
  <tr><td>Every Line with a manual line break</td><td>859</td><td>724</td><td>2968</td><td>2946</td><td>2171</td><td>1990</td><td>58</td><td>56</td></tr>
  <tr><td>Every word with a full link</td><td>528</td><td>501</td><td>2252</td><td>2280</td><td>3513</td><td>3512</td><td>66</td><td>60</td></tr>
  <tr><td>Every word with a full image</td><td>395</td><td>374</td><td>2463</td><td>2569</td><td>3757</td><td>3726</td><td>56</td><td>55</td></tr>
  <tr><td>Every word with a reference link</td><td>19208</td><td>19035</td><td>39183</td><td>38710</td><td>243450</td><td>244943</td><td>1826</td><td>1798</td></tr>
  <tr><td>Every block a quote</td><td>465</td><td>449</td><td>2687</td><td>2684</td><td>978</td><td>977</td><td>48</td><td>48</td></tr>
  <tr><td>Every block a codeblock</td><td>151</td><td>134</td><td>597</td><td>601</td><td>270</td><td>262</td><td>36</td><td>27</td></tr>
  <tr><td>Every block a list</td><td>1209</td><td>1106</td><td>3448</td><td>3432</td><td>1411</td><td>1368</td><td>52</td><td>60</td></tr>
  <tr><td>All tests together</td><td>6062</td><td>6042</td><td>11556</td><td>11589</td><td>19827</td><td>19637</td><td>452</td><td>448</td></tr>
</table>

*   Q: Why is Txtmark so slow when it comes to XML entities?
*   A: Because Txtmark does some sanity checks on XML entities to make sure
    it outputs valid XML. For example:

        &cutie;

    will produce (when processed with Markdown and most other markdown processors):

        &cutie;

    and

        &amp;cutie;

    when processed with Txtmark.

Benchmarked versions:  
[Actuarius] version: 0.2  
[PegDown] version: 0.8.5.4  
[Knockoff] version: 0.7.3-15  

---

[Markdown] is copyright (c) 2004 by John Gruber  
[Actuarius] is copyright (c) 2010 by Christoph Henkelmann  
[Knockoff] is copyright (c) 2009-2011 by Tristan Juricek  
[PegDown] is copyright (c) 2010 by Mathias Doenitz  

***

[Markdown]: http://daringfireball.net/projects/markdown/
[Actuarius]: http://henkelmann.eu/projects/actuarius/
[Knockoff]: http://tristanhunt.com/projects/knockoff/
[PegDown]: https://github.com/sirthias/pegdown

Project link: <https://github.com/rjeschke/txtmark>
