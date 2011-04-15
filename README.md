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
Based on [MarkdownTest_1.0_2007-05-09](http://daringfireball.net/projects/downloads/MarkdownTest_1.0_2007-05-09.tgz)

* Amps and angle encoding ... OK
* Auto links ... OK
* Backslash escapes ... OK
* Blockquotes with code blocks ... OK
* Code Blocks ... OK
* Code Spans ... OK
* Hard-wrapped paragraphs with list-like lines ... OK
* Horizontal rules ... OK
* Images ... FAILED (see [Note 1](#note0))
* Inline HTML (Advanced) ... OK
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

18 passed; 5 failed.
Benchmark:  2 wallclock secs ( 0.02 usr  0.01 sys +  1.78 cusr  0.68 csys =  2.49 CPU)

***

1. <h4 id="note0">Note:</h4>
  Fails because Txtmark doesn't produce empty 'title' image attributes.
  (IMHO: Images ... OK)

2. <h4 id="note1">Note:</h4>
  Fails because of currently missing block-level HTML identification.

3. <h4 id="note2">Note:</h4>
  What the frell ... this test will continue to FAIL.
  Sorry, but using unescaped '"' in a link, which should be surrounded
  by '"' is unacceptable for me ;)

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
  <tr><th>Test</th><th colspan="2">Actuarius</th><th colspan="2">PegDown</th><th colspan="2">Knockoff</th><th colspan="2">Txtmark</th></tr>
  <tr><td></td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td><td>1st Run (ms)</td><td>2nd Run (ms)</td></tr>
  <tr><td>Plain Paragraphs</td><td>1213</td><td>301</td><td>1282</td><td>944</td><td>600</td><td>340</td><td>125</td><td>45</td></tr>
  <tr><td>Every Word Emphasized</td><td>1579</td><td>913</td><td>1482</td><td>1473</td><td>12840</td><td>12663</td><td>51</td><td>43</td></tr>
  <tr><td>Every Word Strong</td><td>1142</td><td>1004</td><td>1131</td><td>1110</td><td>9505</td><td>9615</td><td>39</td><td>39</td></tr>
  <tr><td>Every Word Inline Code</td><td>374</td><td>275</td><td>1064</td><td>1030</td><td>9118</td><td>9075</td><td>49</td><td>35</td></tr>
  <tr><td>Every Word a Fast Link</td><td>2172</td><td>1569</td><td>545</td><td>530</td><td>3951</td><td>3385</td><td>88</td><td>44</td></tr>
  <tr><td>Every Word Consisting of Special XML Chars</td><td>4008</td><td>4243</td><td>3029</td><td>3319</td><td>316</td><td>363</td><td>1303</td><td>1270</td></tr>
  <tr><td>Every Word wrapped in manual HTML tags</td><td>3041</td><td>2874</td><td>887</td><td>888</td><td>3776</td><td>3472</td><td>570</td><td>530</td></tr>
  <tr><td>Every Line with a manual line break</td><td>457</td><td>530</td><td>1325</td><td>1297</td><td>1340</td><td>981</td><td>46</td><td>43</td></tr>
  <tr><td>Every word with a full link</td><td>359</td><td>277</td><td>999</td><td>952</td><td>1713</td><td>1658</td><td>91</td><td>50</td></tr>
  <tr><td>Every word with a full image</td><td>209</td><td>143</td><td>1097</td><td>1068</td><td>1852</td><td>1756</td><td>33</td><td>33</td></tr>
  <tr><td>Every word with a reference link</td><td>9944</td><td>9098</td><td>18326</td><td>18318</td><td>116259</td><td>115617</td><td>1467</td><td>1313</td></tr>
  <tr><td>Every block a quote</td><td>431</td><td>210</td><td>1319</td><td>1328</td><td>477</td><td>469</td><td>37</td><td>37</td></tr>
  <tr><td>Every block a codeblock</td><td>67</td><td>95</td><td>374</td><td>378</td><td>166</td><td>174</td><td>62</td><td>22</td></tr>
  <tr><td>Every block a list</td><td>852</td><td>865</td><td>1706</td><td>1673</td><td>599</td><td>622</td><td>47</td><td>39</td></tr>
  <tr><td>All tests together</td><td>3313</td><td>2904</td><td>5273</td><td>5333</td><td>9732</td><td>9698</td><td>194</td><td>190</td></tr>
</table>


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
Project link: https://github.com/rjeschke/txtmark
