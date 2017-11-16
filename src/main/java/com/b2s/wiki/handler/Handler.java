package com.b2s.wiki.handler;

import javax.servlet.http.HttpServletRequest;

public interface Handler {
    Object handle(final HttpServletRequest request);
}
