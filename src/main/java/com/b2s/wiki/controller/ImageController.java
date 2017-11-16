package com.b2s.wiki.controller;

import com.b2s.wiki.exception.WikiException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@Controller
public class ImageController {
    @RequestMapping("/image/{id}")
    public void getImage(@PathVariable final String id, final HttpServletResponse response) {
        final String filePath = new String(Base64.getDecoder().decode(id.getBytes()));
        try (final FileInputStream fileInputStream = new FileInputStream(filePath); final OutputStream os = response.getOutputStream()) {
            response.setContentType("image/jpeg");
            final byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            os.write(bytes);
            response.flushBuffer();
        } catch (final IOException e) {
            throw new WikiException(e.getMessage(), e);
        }
    }
}
