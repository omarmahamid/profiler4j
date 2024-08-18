package com.profiler4j.async;

import com.profiler4j.async.exceptions.AsyncProfilerException;
import com.profiler4j.async.exceptions.EventNotSupportedException;
import one.profiler.AsyncProfiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPUAsyncProfilerHandler implements IAsyncProfilerHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(CPUAsyncProfilerHandler.class);

    @Override
    public void handle(AsyncProfilerRequest request) throws AsyncProfilerException {

        String action = request.getAction();
        String outputFile = request.getOutputFile();
        AsyncProfiler asyncProfiler = AsyncProfiler.getInstance();
        LOGGER.info("Handling async CPU profiler {}, action {}", request.getEvent(), action);
        try {
            if (AsyncProfileActions.START.name().equals(action)) {
                asyncProfiler.execute(String.format("start,event=cpu,file=%s", outputFile));
            } else if (AsyncProfileActions.STOP.name().equals(action)) {
                asyncProfiler.execute(String.format("stop,file=%s.html", outputFile));
            } else {
                throw new EventNotSupportedException(String.format("event %s not supported", request.getEvent()));
            }
        } catch (Exception e) {
            throw new AsyncProfilerException("Exception while profiling", e);
        }

    }
}
