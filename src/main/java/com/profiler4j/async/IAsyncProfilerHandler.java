package com.profiler4j.async;

import com.profiler4j.async.exceptions.AsyncProfilerException;

public interface IAsyncProfilerHandler {

    void handle(AsyncProfilerRequest request) throws AsyncProfilerException;

}
