package com.profiler4j.javafx.application;

import com.profiler4j.async.AsyncProfilerRequest;
import com.profiler4j.async.CPUAsyncProfilerHandler;
import com.profiler4j.async.IAsyncProfilerHandler;
import com.profiler4j.async.StartCPUAsyncProfilerRequest;
import com.profiler4j.async.exceptions.AsyncProfilerException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class StartButtonHandler implements IButtonHandler<ActionEvent>{

    private final IAsyncProfilerHandler cpuProfiling = new CPUAsyncProfilerHandler();
    private final IAsyncProfilerHandler allocProfiling = new CPUAsyncProfilerHandler();

    @Override
    public EventHandler<ActionEvent> handle(Object... params) {
        return event -> {
            long duration;
            try {
                duration = Long.parseLong(((TextField) params[0]).getText());
                if (duration <= 0) {
                    throw new IllegalArgumentException("duration should be greater than zero");
                }
            }catch (Exception e){
                throw new RuntimeException("please, enter a valid integer number",e);
            }

            String selectedOption = ((ComboBox<String>) params[1]).getValue();

            AsyncProfilerRequest asyncProfilerRequest = new StartCPUAsyncProfilerRequest(duration, selectedOption, "/Users/omarmahamid/Desktop/profiling.html");

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
