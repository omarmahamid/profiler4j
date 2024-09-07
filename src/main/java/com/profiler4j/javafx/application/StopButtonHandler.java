package com.profiler4j.javafx.application;

import com.profiler4j.async.AsyncProfilerRequest;
import com.profiler4j.async.CPUAsyncProfilerHandler;
import com.profiler4j.async.IAsyncProfilerHandler;
import com.profiler4j.async.StopAsyncProfilerRequest;
import com.profiler4j.async.exceptions.AsyncProfilerException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StopButtonHandler implements IButtonHandler<ActionEvent> {


    private final IAsyncProfilerHandler cpuProfiling = new CPUAsyncProfilerHandler();
    private final IAsyncProfilerHandler allocProfiling = new CPUAsyncProfilerHandler();

    @Override
    public EventHandler<ActionEvent> handle(Object... params) {
        return event -> {

            String selectedOption = params[0].toString();

            AsyncProfilerRequest asyncProfilerRequest = new StopAsyncProfilerRequest(selectedOption, "/Users/omarmahamid/Desktop/profiling");

            if (selectedOption.equals("CPU")){
                try {
                    cpuProfiling.handle(asyncProfilerRequest);
                } catch (AsyncProfilerException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    allocProfiling.handle(asyncProfilerRequest);
                } catch (AsyncProfilerException e) {
                    throw new RuntimeException(e);
                }
            }
        };

    }
}
