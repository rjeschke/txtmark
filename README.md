# txtmark - Java markdown processor
Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>  
See LICENSE.txt for licensing information.

***

txtmark is yet another markdown processor for the JVM.  
... and is *damn* fast^^

Again this is a WIP release.

TODO:

- block-level HTML element processing
- code clean-ups
- see below (markdown test suite)

### MarkdownTest results so far

***

Based on [MarkdownTest\_1.0\_2007-05-09](http://daringfireball.net/projects/downloads/MarkdownTest_1.0_2007-05-09.tgz)

* Amps and angle encoding ... OK
* Auto links ... OK
* Backslash escapes ... OK
* Blockquotes with code blocks ... OK
* Code Blocks ... OK
* Code Spans ... OK
* Hard-wrapped paragraphs with list-like lines ... OK
* Horizontal rules ... OK
* Images ... FAILED (see [Note 1](#note0))
* Inline HTML (Advanced) ... FAILED (see [Note 2](#note1))
* Inline HTML (Simple) ... FAILED (see [Note 2](#note1))
* Inline HTML comments ... FAILED (see [Note 2](#note1))
* Links, inline style ... OK
* Links, reference style ... OK
* Links, shortcut references ... OK
* Literal quotes in titles ... FAILED (see [Note 3](#note2))
* Markdown Documentation - Basics ... OK
* Markdown Documentation - Syntax ... FAILED (see [Note 2](#note1))
* Nested blockquotes ... OK
* Ordered and unordered lists ... OK
* Strong and em together ... OK
* Tabs ... OK
* Tidyness ... OK

17 passed; 6 failed.

***

1. <h4 id="note0">Note:</h4>
    Fails because Txtmark doesn't produce empty 'title' image attributes.  
    (IMHO: Images ... OK)

2. <h4 id="note1">Note:</h4>
    Fails because of currently missing block-level HTML identification.

3. <h4 id="note2">Note:</h4>
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

***

Based on [this](http://henkelmann.eu/2011/01/10/performance_comparison_of_markdown_processor_for_the_jvm).  
Txtmark's results should not be considered final, they may change in either direction
during the upcoming releases.  
But I think you get the point.  

<table>
  <tr>
    <th>Test</th>
    <th colspan="2">Actuarius</th>
    <th colspan="2">PegDown</th>
    <th colspan="2">Knockoff</th>
    <th colspan="2">Txtmark</th>
  </tr>
  <tr>
    <td></td>
    <td>1st Run (ms)</td><td>2nd Run (ms)</td>
    <td>1st Run (ms)</td><td>2nd Run (ms)</td>
    <td>1st Run (ms)</td><td>2nd Run (ms)</td>
    <td>1st Run (ms)</td><td>2nd Run (ms)</td>
  </tr>
  <tr>
    <td>Plain Paragraphs</td>
    <td>969</td><td>300</td>
    <td>1468</td><td>956</td>
    <td>564</td><td>362</td>
    <td>114</td><td>45</td>
  </tr>
  <tr>
    <td>Every Word Emphasized</td>
    <td>1409</td><td>884</td>
    <td>1435</td><td>1417</td>
    <td>13161</td><td>12921</td>
    <td>52</td><td>44</td>
  </tr>
  <tr>
    <td>Every Word Strong</td>
    <td>1087</td><td>978</td>
    <td>1125</td><td>1100</td>
    <td>9717</td><td>9586</td>
    <td>40</td><td>46</td>
  </tr>
  <tr>
    <td>Every Word Inline Code</td>
    <td>351</td><td>278</td>
    <td>1047</td><td>1037</td>
    <td>9499</td><td>9245</td>
    <td>45</td><td>35</td>
  </tr>
  <tr>
    <td>Every Word a Fast Link</td>
    <td>2123</td><td>1580</td>
    <td>523</td><td>512</td>
    <td>4086</td><td>3470</td>
    <td>78</td><td>50</td>
  </tr>
  <tr>
    <td>Every Word Consisting of Special XML Chars</td>
    <td>3981</td><td>3973</td>
    <td>3341</td><td>3055</td>
    <td>372</td><td>319</td>
    <td>1842</td><td>1841</td>
  </tr>
  <tr>
    <td>Every Word wrapped in manual HTML tags</td>
    <td>3073</td><td>2907</td>
    <td>901</td><td>888</td>
    <td>3826</td><td>3529</td>
    <td>492</td><td>453</td>
  </tr>
  <tr>
    <td>Every Line with a manual line break</td>
    <td>437</td><td>583</td>
    <td>1370</td><td>1363</td>
    <td>1352</td><td>957</td>
    <td>42</td><td>44</td>
  </tr>
  <tr>
    <td>Every word with a full link</td>
    <td>398</td><td>266</td>
    <td>1057</td><td>1014</td>
    <td>1755</td><td>1689</td>
    <td>88</td><td>47</td>
  </tr>
  <tr>
    <td>Every word with a full image</td>
    <td>228</td><td>139</td>
    <td>1110</td><td>1101</td>
    <td>1917</td><td>1773</td>
    <td>37</td><td>33</td>
  </tr>
  <tr>
    <td>Every word with a reference link</td>
    <td>9726</td><td>9146</td>
    <td>19019</td><td>20044</td>
    <td>117632</td><td>118306</td>
    <td>1431</td><td>1240</td>
  </tr>
  <tr>
    <td>Every block a quote</td>
    <td>431</td><td>205</td>
    <td>1366</td><td>1328</td>
    <td>474</td><td>464</td>
    <td>35</td><td>36</td>
  </tr>
  <tr>
    <td>Every block a codeblock</td>
    <td>68</td><td>84</td>
    <td>387</td><td>377</td>
    <td>161</td><td>169</td>
    <td>61</td><td>19</td>
  </tr>
  <tr>
    <td>Every block a list</td>
    <td>863</td><td>912</td>
    <td>1735</td><td>1762</td>
    <td>602</td><td>686</td>
    <td>46</td><td>36</td>
  </tr>
  <tr>
    <td>All tests together</td>
    <td>3319</td><td>2959</td>
    <td>5245</td><td>5305</td>
    <td>10252</td><td>9751</td>
    <td>222</td><td>173</td>
  </tr>
</table>

[Actuarius] version: 0.2  
[PegDown] version: 0.8.5.4  
[Knockoff] version: 0.7.3-15  

***

[Markdown] is copyright (c) 2004 by John Gruber  
   [Markdown]: http://daringfireball.net/projects/markdown/
[Actuarius] is copyright (c) 2010 by Christoph Henkelmann  
   [Actuarius]: http://henkelmann.eu/projects/actuarius/
[Knockoff] is copyright (c) 2009-2011 by Tristan Juricek  
   [Knockoff]: http://tristanhunt.com/projects/knockoff/
[PegDown] is copyright (c) 2010 by Mathias Doenitz  
   [PegDown]: https://github.com/sirthias/pegdown

***

Project link: <https://github.com/rjeschke/txtmark>
