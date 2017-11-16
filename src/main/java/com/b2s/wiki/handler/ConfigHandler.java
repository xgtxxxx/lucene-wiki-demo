package com.b2s.wiki.handler;


import com.b2s.wiki.model.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("config")
public class ConfigHandler implements Handler {

    @Autowired
    private AppConfig appConfig;

    @Override
    public AppConfig handle(final HttpServletRequest request) {
        return appConfig;
    }
}
