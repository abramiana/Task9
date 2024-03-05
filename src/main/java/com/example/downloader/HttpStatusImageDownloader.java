package com.example.downloader;

import com.example.checker.HttpStatusChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class HttpStatusImageDownloader implements ImageDownloader {
    private static final Logger logger = LogManager.getLogger(HttpStatusImageDownloader.class);
    private final HttpStatusChecker checker;

    public HttpStatusImageDownloader(HttpStatusChecker checker) {
        this.checker = checker;
    }

    @Override
    public void downloadStatusImage(int code) throws IOException {
        String imageUrl = checker.getStatusImage(code);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try (InputStream in = new BufferedInputStream(new java.net.URL(imageUrl).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(code + ".jpg")) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                logger.info("Image for status " + code + " successfully downloaded");
            } catch (IOException e) {
                logger.error("Error downloading image for status " + code, e);
                throw e;
            }
        } else {
            throw new FileNotFoundException("Image for status " + code + " not found");
        }
    }
}