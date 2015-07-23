/*
 * Copyright (C) 2011-2015 René Jeschke <rene_jeschke@yahoo.de>
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

import java.util.Random;

import org.junit.Test;

public class Benchmark
{
    // random seed to use for text generation, >= 0 sets seed, < 0 uses random
    private static final long    SEED                = 0;
    // number of runs
    private static final int     RUNS                = 8;
    // perform an additional (hidden) warm-up run
    private static final boolean WARMUP_RUN          = true;
    private static final int     MIN_WORD_LENGTH     = 3;
    private static final int     MAX_WORD_LENGTH     = 10;
    private static final int     MAX_LINE_LENGTH     = 80;
    private static final int     LINES_PER_PARAGRAPH = 20;
    // number of paragraphs to create
    private static final int     NUM_PARAGRAPHS      = 30;

    // scratch area
    private static int REF_COUNTER = 0;

    private static String createWord(final Random rnd, final int minLength, final int maxLength)
    {
        final int len = rnd.nextInt(maxLength - minLength) + minLength;
        final char[] ret = new char[len];
        for (int i = 0; i < len; i++)
        {
            ret[i] = (char)('a' + rnd.nextInt(26));
        }
        return new String(ret);
    }

    private interface WLDecorator
    {
        public String decorate(String word);
    }

    private interface ParagraphDecorator
    {
        public void startParagraph(StringBuilder out);

        public void endParagraph(StringBuilder out);
    }

    private final static String createText(
            final WLDecorator wordDecorator,
            final WLDecorator lineDecorator,
            final ParagraphDecorator paragraphDecorator)
    {
        final Random rnd = new Random();
        if (SEED >= 0)
        {
            rnd.setSeed(SEED);
        }

        final StringBuilder sb = new StringBuilder();
        final StringBuilder line = new StringBuilder();

        boolean initParagraph = true;

        for (int lineLength = 0, lineCount = 0, paraCount = 0; paraCount < NUM_PARAGRAPHS;)
        {
            if (initParagraph)
            {
                if (paragraphDecorator != null)
                {
                    paragraphDecorator.startParagraph(sb);
                }
                initParagraph = false;
            }

            final String word = createWord(rnd, MIN_WORD_LENGTH, MAX_WORD_LENGTH);
            final String decorated = wordDecorator != null ? wordDecorator.decorate(word) : word;

            lineLength += word.length();
            line.append(decorated);

            if (lineLength < MAX_LINE_LENGTH)
            {
                lineLength++;
                line.append(' ');
            }
            else
            {
                if (lineDecorator != null)
                {
                    sb.append(lineDecorator.decorate(line.toString()));
                }
                else
                {
                    sb.append(line);
                }
                sb.append('\n');
                lineCount++;
                lineLength = 0;
                line.setLength(0);

                if (lineCount >= LINES_PER_PARAGRAPH)
                {
                    lineCount = 0;
                    sb.append('\n');
                    paraCount++;
                    if (paragraphDecorator != null)
                    {
                        paragraphDecorator.endParagraph(sb);
                    }
                    initParagraph = true;
                }
            }
        }

        return sb.toString();
    }

    private final static WLDecorator EMPHASIS = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "*" + word + "*";
        }
    };

    private final static WLDecorator STRONG = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "**" + word + "**";
        }
    };

    private final static WLDecorator EMPHASIS_STRONG = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "***" + word + "***";
        }
    };

    private final static WLDecorator INLINE_CODE = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "`" + word + "`";
        }
    };

    private final static WLDecorator LINKS = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "[" + word + "](http://www.example.com/" + word + ")";
        }
    };

    private final static WLDecorator REF_LINKS = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            final int id = REF_COUNTER++;
            return "[" + word + "][id" + id + "]";
        }
    };

    private final static ParagraphDecorator REF_LINKS_PARA = new ParagraphDecorator()
    {
        private int startId = 0;

        @Override
        public void startParagraph(final StringBuilder out)
        {
            this.startId = REF_COUNTER;
        }

        @Override
        public void endParagraph(final StringBuilder out)
        {
            for (int i = this.startId; i < REF_COUNTER; i++)
            {
                out.append("[id");
                out.append(i);
                out.append("]: http://www.example.com/");
                out.append(i);
                out.append('\n');
            }
        }
    };

    private final static WLDecorator HTML_WRAP = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "<span>" + word + "</span>";
        }
    };

    private final static WLDecorator MANUAL_LINE_BREAKS = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return word + "  ";
        }
    };

    private final static WLDecorator CODE_LINES = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "    " + word;
        }
    };

    private final static WLDecorator BLOCK_QUOTE = new WLDecorator()
    {
        @Override
        public String decorate(final String word)
        {
            return "> " + word;
        }
    };

    private static class Settings
    {
        private final WLDecorator        wordDecorator;
        private final WLDecorator        lineDecorator;
        private final ParagraphDecorator paragraphDecorator;
        private final String             name;

        private Settings(final String name)
        {
            this(name, null, null, null);
        }

        private Settings(final String name, final WLDecorator wordDecorator)
        {
            this(name, wordDecorator, null, null);
        }

        private Settings(final String name, final WLDecorator wordDecorator, final WLDecorator lineDecorator)
        {
            this(name, wordDecorator, lineDecorator, null);
        }

        private Settings(final String name, final WLDecorator wordDecorator, final WLDecorator lineDecorator,
                final ParagraphDecorator paragraphDecorator)
        {
            this.name = name;
            this.wordDecorator = wordDecorator;
            this.lineDecorator = lineDecorator;
            this.paragraphDecorator = paragraphDecorator;
        }
    }

    private final static Settings[] SETTINGS =
    {
            new Settings("default"),
            new Settings("manual linebreaks", null, MANUAL_LINE_BREAKS),
            new Settings("emphasis", EMPHASIS),
            new Settings("strong", STRONG),
            new Settings("emphasis+strong", EMPHASIS_STRONG),
            new Settings("inline code", INLINE_CODE),
            new Settings("full links", LINKS),
            new Settings("<span> wrap", HTML_WRAP),
            new Settings("code blocks", null, CODE_LINES),
            new Settings("blockquote", null, BLOCK_QUOTE),
            new Settings("ref links", REF_LINKS, null, REF_LINKS_PARA),
    };

    private static long timedProcess(final String text)
    {
        final long t0 = System.nanoTime();
        Processor.process(text);
        final long t1 = System.nanoTime();
        return t1 - t0;
    }

    private static void runTest(final Settings settings, final int runs)
    {
        final String testText = createText(settings.wordDecorator, settings.lineDecorator, settings.paragraphDecorator);

        if (WARMUP_RUN)
        {
            System.gc();
            timedProcess(testText);
        }

        final long[] times = new long[runs];
        for (int i = 0; i < runs; i++)
        {
            System.gc();
            times[i] = timedProcess(testText);
        }

        System.out.printf("%20s |", settings.name);
        long sum = 0;
        for (int i = 0; i < runs; i++)
        {
            System.out.printf(" %8d |", times[i] / 1000);
            sum += times[i];
        }
        System.out.printf(" %8d |", sum / (runs * 1000));
        System.out.println();
    }

    @Test
    public void benchmark()
    {
        System.out.printf("%20s |", "Performance test");
        for (int i = 0; i < RUNS; i++)
        {
            System.out.printf(" %8s |", "run #" + (i + 1));
        }
        System.out.printf(" %8s |", "average");
        System.out.println();

        System.out.print("---------------------|");
        for (int i = 0; i < RUNS + 1; i++)
        {
            System.out.print("----------|");
        }
        System.out.println();

        for (final Settings s : SETTINGS)
        {
            runTest(s, RUNS);
        }

        System.out.print("---------------------|");
        for (int i = 0; i < RUNS + 1; i++)
        {
            System.out.print("----------|");
        }
        System.out.println();
        System.out.println("All times are given in milliseconds");
    }
}
