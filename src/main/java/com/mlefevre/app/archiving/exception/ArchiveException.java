package com.mlefevre.app.archiving.exception;


public class ArchiveException extends Exception {

    public ArchiveException(String message) {
        super(message);
    }

    public ArchiveException(String message, Throwable cause) {
        super(message, cause);
    }

}
