package com.mlefevre.app.archiving.exception;


public class ArchiveException extends Exception {


    public ArchiveException(String message) {
        super(message);
    }

    public ArchiveException(String message, Throwable cause) {
        super(message, cause);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getMessage());
        builder.append("\n");
        for(StackTraceElement stackLine : this.getStackTrace()) {
            builder.append(stackLine.toString());
        }

        return builder.toString();
    }

}
