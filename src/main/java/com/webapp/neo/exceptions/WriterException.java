package com.webapp.neo.exceptions;

public final class WriterException extends Exception {
    public WriterException() {
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(Throwable cause) {
        super(cause);
    }


}
