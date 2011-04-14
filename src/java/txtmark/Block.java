/*
* Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
* See LICENSE.txt for licensing information.
*/
package txtmark;

class Block
{
    public BlockType type = BlockType.NONE;
    public Line lines = null, lineTail = null;
    public Block blocks = null, blockTail = null, blockParent = null;
    public Block next = null, previous = null;
    public int hlDepth = 0;

    public Block()
    {
        //
    }

    public boolean hasLines()
    {
        return this.lines != null;
    }

    public void removeSurroundingEmptyLines()
    {
        if(this.lines != null)
        {
            this.removeTrailingEmptyLines();
            this.removeLeadingEmptyLines();
        }
    }

    public void transfromHeadline()
    {
        if(this.hlDepth > 0)
            return;
        int level = 0;
        final Line line = this.lines;
        if(line.isEmpty)
            return;
        int start = line.leading;
        while(start < line.value.length() && line.value.charAt(start) == '#')
        {
            level++;
            start++;
        }
        while(start < line.value.length() && line.value.charAt(start) == ' ')
            start++;
        if(start >= line.value.length())
        {
            line.setEmpty();
        }
        else
        {
            int end = line.value.length() - line.trailing - 1;
            while(line.value.charAt(end) == '#')
                end--;
            while(line.value.charAt(end) == ' ')
                end--;
            line.value = line.value.substring(start, end + 1);
            line.leading = line.trailing = 0;
        }
        this.hlDepth = level;
    }

    public void removeListIndent()
    {
        Line line = this.lines;
        while(line != null)
        {
            if(!line.isEmpty)
            {
                switch(line.getLineType())
                {
                case ULIST:
                    line.value = line.value.substring(line.leading + 2);
                    break;
                case OLIST:
                    line.value = line.value.substring(line.value.indexOf('.') + 2);
                    break;
                default:
                    line.value = line.value.substring(Math.min(line.leading, 4));
                    break;
                }
                line.initLeading();
            }
            line = line.next;
        }
    }

    public void removeBlockQuotePrefix()
    {
        Line line = this.lines;
        while(line != null)
        {
            if(!line.isEmpty)
            {
                if(line.value.charAt(line.leading) == '>')
                {
                    int rem = line.leading + 1;
                    if(line.leading + 1 < line.value.length() && line.value.charAt(line.leading + 1) == ' ')
                        rem++;
                    line.value = line.value.substring(rem);
                    line.initLeading();
                }
            }
            line = line.next;
        }
    }

    public boolean removeLeadingEmptyLines()
    {
        boolean wasEmpty = false;
        Line line = this.lines;
        while(line != null && line.isEmpty)
        {
            this.removeLine(line);
            line = this.lines;
            wasEmpty = true;
        }
        return wasEmpty;
    }

    public void removeTrailingEmptyLines()
    {
        Line line = this.lineTail;
        while(line != null && line.isEmpty)
        {
            this.removeLine(line);
            line = this.lineTail;
        }
    }

    public Block split(final Line line)
    {
        final Block block = new Block();

        block.lines = this.lines;
        block.lineTail = line;
        this.lines = line.next;
        line.next = null;
        if(this.lines == null)
            this.lineTail = null;
        else
            this.lines.previous = null;

        block.blockParent = this;
        if(this.blocks == null)
            this.blocks = this.blockTail = block;
        else
        {
            block.previous = this.blockTail;
            this.blockTail.next = block;
            this.blockTail = block;
        }

        return block;
    }

    public void removeLine(final Line line)
    {
        if(line.previous == null)
            this.lines = line.next;
        else
            line.previous.next = line.next;
        if(line.next == null)
            this.lineTail = line.previous;
        else
            line.next.previous = line.previous;
        line.previous = line.next = null;
    }

    public void appendLine(final Line line)
    {
        if(this.lineTail == null)
            this.lines = this.lineTail = line;
        else
        {
            this.lineTail.nextEmpty = line.isEmpty;
            line.prevEmpty = this.lineTail.isEmpty;
            line.previous = this.lineTail;
            this.lineTail.next = line;
            this.lineTail = line;
        }
    }
}
