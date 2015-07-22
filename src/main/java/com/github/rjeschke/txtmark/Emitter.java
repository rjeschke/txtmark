package com.github.rjeschke.txtmark;

public interface Emitter {
    void addLinkRef(String key, LinkRef linkRef);

    void emit(StringBuilder out, Block root);

    void useExtensions(boolean useExtensions);
}
