package com.ism.absences.util;

import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;

public class MultipartInputStreamFileResource extends InputStreamResource {

    private final String filename;
    private final long contentLength;
    private final String contentType;

    public MultipartInputStreamFileResource(InputStream inputStream, String filename, long contentLength, String contentType) {
        super(inputStream);
        this.filename = filename;
        this.contentLength = contentLength;
        this.contentType = contentType;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public long contentLength() {
        return this.contentLength;
    }

    public String getContentType() {
        return this.contentType;
    }
}
