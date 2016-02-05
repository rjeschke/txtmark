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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;

public class GFMTest
{
    private static final Charset  UTF_8 = Charset.forName("UTF-8");
    private static final String   RES   = "/com/github/rjeschke/txtmark/testsuite/gfm/";
    private static final String[] TESTS =
        {
        "GFM strikeout", "GFM Autolink", "GFM Tables"
        };

    private final static String readTextUTF_8(final InputStream in) throws IOException
    {
        final Reader r = new BufferedReader(new InputStreamReader(in, UTF_8));
        final StringBuilder sb = new StringBuilder();
        try
        {
            for (;;)
            {
                final int ch = r.read();
                if (ch > -1)
                {
                    sb.append((char)ch);
                }
                else
                {
                    break;
                }
            }
            return sb.toString();
        }
        finally
        {
            in.close();
        }
    }

    public final static String collapseWhitespace(final String str)
    {
        boolean wasWs = false;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
        {
            final char ch = str.charAt(i);
            if (Character.isWhitespace(ch) || Character.isSpaceChar(ch))
            {
                if (!wasWs)
                {
                    sb.append(' ');
                    wasWs = true;
                }
            }
            else
            {
                wasWs = false;
                sb.append(ch);
            }
        }
        return sb.toString().trim();
    }

    public final static String replaceHacks(final String str)
    {
        return str
                .replace(" />", "/>")
                .replace(" <", "<");
    }

    public final static String tidy(final String str)
    {
        return replaceHacks(collapseWhitespace(str));
    }

    @Test
    public void test() throws IOException
    {
        for (final String name : TESTS)
        {
            final InputStream txtIn = GFMTest.class.getResourceAsStream(RES + name + ".text");
            final InputStream cmpIn = GFMTest.class.getResourceAsStream(RES + name + ".html");
            if (txtIn == null || cmpIn == null)
            {
                StringBuilder details = new StringBuilder();
                details.append("Resource(s) not found: ");
                if (txtIn == null) {
                    details.append(RES + name + ".text");
                }
                if (cmpIn == null) {
                    if (details.length() > 0) {
                        details.append(", ");
                    }
                    details.append(RES + name + ".html");
                }
                Assert.fail("Unmatched test resources: '"+name+"' - "+details.toString());
            }

            final String text = readTextUTF_8(txtIn);
            final String compare = readTextUTF_8(cmpIn);

            final String processed = Processor.process(text, Configuration.builder().forceExtentedProfile().build());

            final String tCompare = tidy(compare);
            final String tProcessed = tidy(processed);

            if (!tCompare.equals(tProcessed))
            {
                System.out.println("Test: " + name);
                System.out.println("=============================================");
                System.out.println(tProcessed);
                System.out.println("---------------------------------------------");
                System.out.println(tCompare);
                System.out.println("=============================================");
                System.out.println(processed);
                System.out.println("---------------------------------------------");
                System.out.println(compare);
                System.out.println("=============================================");
                Assert.fail("Test '" + name + "' failed");
            }
        }
    }
}
