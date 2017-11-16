package com.b2s.wiki.controller;

import com.b2s.wiki.exception.WikiException;
import com.b2s.wiki.handler.Handler;
import com.b2s.wiki.model.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.AppConfigurationEntry;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("wiki-app")
public class RouteController {

    @Autowired
    private Map<String, Handler> handlers;

    @RequestMapping("/detect")
    @ResponseBody
    public Object doRoute(@RequestParam final String action, final HttpServletRequest request) {
        final Handler handler = handlers.get(action);
        if(Objects.isNull(handler)) {
            throw new WikiException("Can not find hander with action: " + action);
        }

        return handler.handle(request);
    }
}
