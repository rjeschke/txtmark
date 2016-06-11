package com.github.rjeschke.txtmark;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by caosh on 2016/6/11.
 */
public class LineReader {
    private final Reader reader;
    private final Configuration config;
    private int c;

    public LineReader(Reader reader, Configuration config) throws IOException {
        this.reader = reader;
        this.config = config;
        this.c = reader.read();
    }

    public boolean eof() {
        return c == -1;
    }

    public String read() throws IOException {
        final StringBuilder sb = new StringBuilder(80);
        int pos = 0;
        boolean eol = false;
        while (!eol) {
            switch (c) {
                case -1:
                    eol = true;
                    break;
                case '\n':
                    c = reader.read();
                    if (c == '\r') {
                        c = reader.read();
                    }
                    eol = true;
                    break;
                case '\r':
                    c = reader.read();
                    if (c == '\n') {
                        c = reader.read();
                    }
                    eol = true;
                    break;
                case '\t': {
                    final int np = pos + (4 - (pos & 3));
                    while (pos < np) {
                        sb.append(' ');
                        pos++;
                    }
                    c = reader.read();
                    break;
                }
                default:
                    if (c != '<' || !config.panicMode) {
                        pos++;
                        sb.append((char) c);
                    } else {
                        pos += 4;
                        sb.append("&lt;");
                    }
                    c = reader.read();
                    break;
            }
        }
        return sb.toString();
    }
}
