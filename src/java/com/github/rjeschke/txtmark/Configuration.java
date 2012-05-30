package com.github.rjeschke.txtmark;

/**
 * Txtmark configuration.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 * @since 0.7
 */
public class Configuration
{
    final boolean safeMode;
    final String encoding;
    final Decorator decorator;

    /**
     * <p>This is the default configuration for txtmark's <code>process</code> methods</p>
     * 
     * <ul>
     * <li><code>safeMode = false</code></li>
     * <li><code>encoding = UTF-8</code></li>
     * <li><code>decorator = DefaultDecorator</code></li>
     * </ul>
     */
    public final static Configuration DEFAULT = new Configuration(false, "UTF-8", new DefaultDecorator());

    /**
     * <p>Default safe configuration</p>
     * 
     * <ul>
     * <li><code>safeMode = true</code></li>
     * <li><code>encoding = UTF-8</code></li>
     * <li><code>decorator = DefaultDecorator</code></li>
     * </ul>
     */
    public final static Configuration DEFAULT_SAFE = Configuration.builder().enableSafeMode().build();

    /**
     * Constructor.
     * 
     * @param safeMode
     * @param encoding
     * @param decorator
     */
    Configuration(boolean safeMode, String encoding, Decorator decorator)
    {
        this.safeMode = safeMode;
        this.encoding = encoding;
        this.decorator = decorator;
    }

    /**
     * Creates a new Builder instance.
     * 
     * @return A new Builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }
    
    /**
     * Configuration builder.
     * 
     * @author René Jeschke <rene_jeschke@yahoo.de>
     * @since 0.7
     */
    public static class Builder
    {
        private boolean safeMode = false;
        private String encoding = "UTF-8";
        private Decorator decorator = new DefaultDecorator();

        /**
         * Constructor.
         * 
         */
        Builder()
        {
            // empty
        }

        /**
         * Enables HTML safe mode.
         * 
         * Default: <code>false</code>
         * 
         * @return This builder
         * @since 0.7
         */
        public Builder enableSafeMode()
        {
            this.safeMode = true;
            return this;
        }

        /**
         * Sets the HTML safe mode flag.
         * 
         * Default: <code>false</code>
         * 
         * @param flag
         *            <code>true</code> to enable safe mode
         * @return This builder
         * @since 0.7
         */
        public Builder setSafeMode(boolean flag)
        {
            this.safeMode = flag;
            return this;
        }

        /**
         * Sets the character encoding for txtmark.
         * 
         * Default: <code>&quot;UTF-8&quot;</code>
         * 
         * @param encoding
         *            The encoding
         * @return This builder
         * @since 0.7
         */
        public Builder setEncoding(String encoding)
        {
            this.encoding = encoding;
            return this;
        }

        /**
         * Sets the decorator for txtmark.
         * 
         * Default: <code>DefaultDecorator()</code>
         * 
         * @param decorator
         *            The decorator
         * @return This builder
         * @see DefaultDecorator
         * @since 0.7
         */
        public Builder setDecorator(Decorator decorator)
        {
            this.decorator = decorator;
            return this;
        }

        /**
         * Builds a configuration instance.
         * 
         * @return a Configuration instance
         * @since 0.7
         */
        public Configuration build()
        {
            return new Configuration(this.safeMode, this.encoding, this.decorator);
        }
    }
}
