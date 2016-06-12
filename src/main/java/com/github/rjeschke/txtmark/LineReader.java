package com.github.rjeschke.txtmark;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by caosh on 2016/6/11.
 */
class LineReader {
    private final Reader reader;
    private final Configuration config;
    private int nextChar;

    public LineReader(Reader reader, Configuration config) throws IOException {
        this.reader = reader;
        this.config = config;
        this.nextChar = reader.read();
    }

    public boolean eof() {
        return nextChar == -1;
    }

    public Line read() throws IOException {
        final StringBuilder sb = new StringBuilder(80);
        int pos = 0;
        boolean eol = false;
        while (!eol) {
            switch (nextChar) {
                case -1:
                    eol = true;
                    break;
                case '\n':
                    nextChar = reader.read();
                    if (nextChar == '\r') {
                        nextChar = reader.read();
                    }
                    eol = true;
                    break;
                case '\r':
                    nextChar = reader.read();
                    if (nextChar == '\n') {
                        nextChar = reader.read();
                    }
                    eol = true;
                    break;
                case '\t': {
                    final int np = pos + (4 - (pos & 3));
                    while (pos < np) {
                        sb.append(' ');
                        pos++;
                    }
                    nextChar = reader.read();
                    break;
                }
                default:
                    if (nextChar != '<' || !config.panicMode) {
                        pos++;
                        sb.append((char) nextChar);
                    } else {
                        pos += 4;
                        sb.append("&lt;");
                    }
                    nextChar = reader.read();
                    break;
            }
        }
        return new Line(sb.toString());

    }
}
