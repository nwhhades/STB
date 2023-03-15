package com.whiner.stblib.net;

public enum UrlUtils {
    INSTANCE;

    public interface Impl {
        String actionUrl(String url, String tag);
    }

    private Impl impl;

    public void setImpl(Impl impl) {
        this.impl = impl;
    }

    public String useUrl(String url, String tag) {
        if (impl != null) {
            return impl.actionUrl(url, tag);
        }
        return url;
    }

}
