package com.bobocode.se;

public class FileReaderException extends RuntimeException {
    public FileReaderException(String message, Exception e) {
        super(message, e);
    }
}
