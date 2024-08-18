package com.profiler4j.async.exceptions;

public class AsyncProfilerException extends Exception{

    public AsyncProfilerException(String msg, Throwable t){
        super(msg, t);
    }
}
