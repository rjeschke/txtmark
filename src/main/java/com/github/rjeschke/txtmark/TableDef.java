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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * A GFM table definition
 * @author bogdan@quandora.com
 *
 */
public class TableDef {

	/**
	 *
	 * @param text the text to process
	 * @param start from where to start skiping spaces
	 * @param end is exclusive
	 * @return the offset of the first non space character after 'start' or -1 if none was found.
	 */
	public final static int skipSpaces(String text, int start, int end) {
		int i = start;
		while (i < end && Character.isWhitespace(text.charAt(i))) i++;
		return i >= end ? -1 : i;
	}

	public static int skip(char c, String text, int start, int end) {
		int i = start;
		while (i < end && text.charAt(i) == c) i++;
		return i >= end ? -1 : i;
	}

	/**
	 * Skip spaces backward: starting at end until start is reached.
	 * the end offset is exclusive and the start offset is inclusive
	 * @param text
	 * @param start
	 * @param end
	 * @return the first offset with a non space character. the offset is >= start
	 */
	public final static int skipSpacesBackward(String text, int start, int end) {
		int i = end-1;
		while (i >= start && Character.isWhitespace(text.charAt(i))) i--;
		return i < start ? -1 : i;
	}

	public static TableDef parse(String headerLineText, String dividerLineText) {
		TableDef table = parseTableDividerLine(dividerLineText);
		if (table != null) {
			table.header = table.parseRow(headerLineText);
			if (table.header == null) {
				table = null;
			}
		}
		return table;
	}

	private static TableDef parseTableDividerLine(String lineText) {
		int len = lineText.length();
		int s = skipSpaces(lineText, 0, len);
		if (s == -1) {
			return null; // not a table divider
		}
		int border = 0;
		// skip first | if any
		char c = lineText.charAt(s);
		if (c == '|') {
			border |= 1;
			s++;
		} else if (c != '-' && c != ':') {
			return null; // not a table
		}
		int e = skipSpacesBackward(lineText, 0, len);
		c = lineText.charAt(e);
		if (c == '|') {
			border |= 2;
			e--;
		} else if (c != '-' && c != ':') {
			return null; // not a table
		}

		// e is an exclusive offset in skipCellDivider
		TableDef table = readTableDividerCols(lineText, s, e+1);
		if (table != null) {
			table.border = border;
		}
		return table;
	}


	private static TableDef readTableDividerCols(final String text, final int start, final int end) {
		TableDef table = null;

		int align = 0;
		int i = start;
		while (true) {
			// at beginning of a cell after |
			i = skipSpaces(text, i, end);
			if (i == -1) {
				return null; // invalid table def
			}
			char c = text.charAt(i);
			if (c == ':') {
				align = 1;
				i = skipSpaces(text, i+1, end);
				if (i == -1) {
					return null;
				}
				c = text.charAt(i);
				if (c != '-') {
					return null;
				}
			} else if (c == '-') {
				align = 0;
			} else {
				return null;
			}

			i = skip('-', text, i+1, end);
			if (i == -1) {
				break; // last cell
			}

			i = skipSpaces(text, i, end);
			if (i == -1) {
				break; // last cell
			}
			c = text.charAt(i);
			if (c == ':') {
				align |= 2;
				i = skipSpaces(text, i+1, end);
				if (i == -1) {
					break; // last cell
				}
				c = text.charAt(i);
				if (c != '|') {
					return null;
				}
			} else if (c != '|') {
				return null;
			}

			if (table == null) {
				table = new TableDef();
				table.defineColumn(align);
			} else {
				table.defineColumn(align);
			}
			i++;
		}

		if (table == null) {
			// a single column - not a table
			return null;
		} else {
			table.defineColumn(align);
		}

		return table;
	}

    private static LinkedList<String> splitColumns(String text, char delimiter) {
        if (text == null || text.length() == 0) {
            return null;
        }
        int s = 0;
        int e = text.indexOf(delimiter, s);
        if (e == -1) {
        	return null; // not a column line - we require at least 2 columns
        }
        LinkedList<String> ar = new LinkedList<String>();
        do {
            String segment = text.substring(s, e).trim();
            ar.add(segment);
            s = e + 1;
            e = text.indexOf(delimiter, s);
        } while (e != -1);

        int len = text.length();
        if (s < len) {
            String segment = text.substring(s).trim();
            ar.add(segment);
        } else {
            ar.add("");
        }
        return ar;
    }


	protected int[] aligns = new int[4];
	protected int width = 0;
	protected int border = 0;
	public LinkedList<String> header;
	public List<LinkedList<String>> rows = new ArrayList<LinkedList<String>>();

	public String getAlign(int i) {
		if (i >= width) {
			return null;
		}
		switch(aligns[i]) {
		case 0: return null;
		case 1: return "left";
		case 2: return "right";
		case 3: return "center";
		}
		return null;
	}

	private void defineColumn(int align) {
		int newWidth = width+1;
		if (newWidth > aligns.length) {
			int[] tmp = new int[newWidth];
			System.arraycopy(aligns, 0, tmp, 0, width);
			tmp[width] = align;
			aligns = tmp;
			width = newWidth;
		} else {
			aligns[width++] = align;
		}
	}

	/**
	 * Parse and a add a table row if successful. If not a table row do nothing and return false;
	 * @param lineText
	 * @return
	 */
	public boolean addRow(String lineText) {
		LinkedList<String> row = parseRow(lineText);
		if (row != null) {
			this.rows.add(row);
			return true;
		}
		return false;
	}

	private LinkedList<String> parseRow(String lineText) {
		LinkedList<String> cols = splitColumns(lineText, '|');
		if (cols == null || cols.size() < 2) { // not a table row
			return null;
		}
		boolean hasRightBorder = (border & 1) != 0;
		boolean hasLeftBorder = (border & 2) != 0;
		if (hasRightBorder && cols.getFirst().isEmpty()) {
			cols.removeFirst();
		} else if (hasRightBorder) {
			// data line no right padded with | as in divider line
			// we consider this an error
			return null;
		}
		if (hasLeftBorder && cols.getLast().isEmpty()) {
			cols.removeLast();
		}

		// complete tr with spaces if needed
		int width = this.width;
		int dw = width - cols.size();
		if (dw > 0) {
			while (dw > 0) {
				cols.addLast("&nbsp;");
				dw--;
			}
		} else if (dw < 0) { // overflow - cut
			//TODO: should we accept overflow cells?
			while (dw < 0) {
				cols.removeLast();
				dw++;
			}
		}
		return cols;
	}

}
