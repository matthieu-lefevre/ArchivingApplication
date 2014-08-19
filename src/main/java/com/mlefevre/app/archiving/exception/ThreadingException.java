package com.mlefevre.app.archiving.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Matthieu
 * Date: 18/08/14
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class ThreadingException extends Exception {


    public ThreadingException(String message) {
        super(message);
    }


    public ThreadingException(String message, Throwable cause) {
        super(message, cause);
    }

}
