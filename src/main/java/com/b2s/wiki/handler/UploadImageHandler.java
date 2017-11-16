package com.b2s.wiki.handler;

import com.b2s.wiki.exception.WikiException;
import com.b2s.wiki.model.AppConfig;
import com.b2s.wiki.model.AppConstants;
import com.b2s.wiki.model.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component("uploadimage")
public class UploadImageHandler implements Handler {

    @Value("${wiki.config.path.images}")
    private String imageSavedPath;

    @Autowired
    private AppConfig appConfig;

    @Override
    public Object handle(final HttpServletRequest request) {
        if (request instanceof AbstractMultipartHttpServletRequest) {
            final AbstractMultipartHttpServletRequest multipartHttpServletRequest =
                (AbstractMultipartHttpServletRequest) request;
            final MultipartFile file = multipartHttpServletRequest.getFile(appConfig.getImageFieldName());
            final String filePath = populateFileName(file.getOriginalFilename());
            final File image = new File(filePath);
            try (final FileOutputStream fos = new FileOutputStream(image)) {
                //TODO: Could be replaced by BufferOutputStream
                fos.write(file.getBytes());
            } catch (final IOException e) {
                throw new WikiException(e.getMessage(), e);
            }

            return new UploadResponse(
                "SUCCESS",
                file.getSize(),
                file.getName(),
                "/image/" + Base64.getEncoder().encodeToString(filePath.getBytes()),
                filePath.substring(filePath.lastIndexOf('.')),
                file.getOriginalFilename());
        } else {
            throw new WikiException("No file found!");
        }
    }

    private String populateFileName(final String originalName) {
        final long now = System.currentTimeMillis();
        final StringBuffer sb = new StringBuffer();

        return sb.append(imageSavedPath)
            .append(now)
            .append('_')
            .append(originalName)
            .toString();
    }

}
