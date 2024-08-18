package com.profiler4j.async;

public class AsyncProfilerRequest {

    private final long duration;
    private final String event;
    private final String action;
    private final String outputFile;

    public AsyncProfilerRequest(long duration, String event, String action, String outputFile) {
        this.duration = duration;
        this.event = event;
        this.action = action;
        this.outputFile = outputFile;
    }

    public long getDuration() {
        return duration;
    }

    public String getEvent() {
        return event;
    }

    public String getAction() {
        return action;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
