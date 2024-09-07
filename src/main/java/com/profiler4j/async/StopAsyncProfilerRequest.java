package com.profiler4j.async;

import static com.profiler4j.async.AsyncProfileActions.STOP;

public class StopAsyncProfilerRequest extends AsyncProfilerRequest{

    public StopAsyncProfilerRequest(String event, String outputFile) {
        super(event, STOP.name(), outputFile);
    }
}
