package com.mjc.school.service.exceptions;

public class SearchAuthorException extends RuntimeException {
    public SearchAuthorException() {
    }

    public SearchAuthorException(String message) {
        super(message);
    }

    public SearchAuthorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchAuthorException(Throwable cause) {
        super(cause);
    }
}
