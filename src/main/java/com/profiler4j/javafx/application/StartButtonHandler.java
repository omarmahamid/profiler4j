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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartButtonHandler implements IButtonHandler<ActionEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartButtonHandler.class);

    private final IAsyncProfilerHandler cpuProfiling = new CPUAsyncProfilerHandler();
    private final IAsyncProfilerHandler allocProfiling = new CPUAsyncProfilerHandler();

    @Override
    public EventHandler<ActionEvent> handle(Object... params) {
        return event -> {
            long duration;
            String filePathToDump;
            try {
                duration = Long.parseLong(((TextField) params[0]).getText());
                if (duration <= 0) {
                    throw new IllegalArgumentException("duration should be greater than zero");
                }
            } catch (Exception e) {
                throw new RuntimeException("please, enter a valid integer number", e);
            }

            String selectedOption = ((ComboBox<String>) params[1]).getValue();

            filePathToDump = ((FileState) params[2]).getFilePath();

            if (filePathToDump == null){
                LOGGER.info("File path to dump is not specified, using default path");
                filePathToDump = "profiling.html";
            }

            AsyncProfilerRequest asyncProfilerRequest = new StartCPUAsyncProfilerRequest(duration, selectedOption, filePathToDump);

            if (selectedOption.equals("CPU")) {
                try {
                    cpuProfiling.handle(asyncProfilerRequest);
                } catch (AsyncProfilerException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    allocProfiling.handle(asyncProfilerRequest);
                } catch (AsyncProfilerException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
