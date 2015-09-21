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

/**
 * Emitter interface responsible for generating output.
 *
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
public interface Emitter
{
    /**
     * Sets whether to use the extended profile.
     *
     * @param useExtensions
     *            True to use extensions.
     */
    void setUseExtensions(boolean useExtensions);

    /**
     * Adds a LinkRef to this set of LinkRefs.
     *
     * @param key
     *            The key/id.
     * @param linkRef
     *            The LinkRef.
     */
    void addLinkRef(final String key, final LinkRef linkRef);

    /**
     * Transforms the given block recursively into HTML.
     *
     * @param out
     *            The StringBuilder to write to.
     * @param root
     *            The Block to process.
     */
    void emit(final StringBuilder out, final Block root);
}
