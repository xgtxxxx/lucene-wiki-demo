package com.b2s.wiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class WikiApp {
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(WikiApp.class, args);
    }
}
