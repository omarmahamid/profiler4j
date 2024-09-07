package com.profiler4j.javafx.application;

import com.profiler4j.async.AsyncProfilerRequest;
import com.profiler4j.async.CPUAsyncProfilerHandler;
import com.profiler4j.async.IAsyncProfilerHandler;
import com.profiler4j.async.StopAsyncProfilerRequest;
import com.profiler4j.async.exceptions.AsyncProfilerException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopButtonHandler implements IButtonHandler<ActionEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StopButtonHandler.class);

    private final IAsyncProfilerHandler cpuProfiling = new CPUAsyncProfilerHandler();
    private final IAsyncProfilerHandler allocProfiling = new CPUAsyncProfilerHandler();

    @Override
    public EventHandler<ActionEvent> handle(Object... params) {
        return event -> {

            String filePathToDump;
            String selectedOption = ((ComboBox<String>) params[0]).getValue();

            filePathToDump = ((FileState) params[1]).getFilePath();

            if (filePathToDump == null){
                LOGGER.info("File path to dump is not specified, using default path");
                filePathToDump = "profiling";
            }


            AsyncProfilerRequest asyncProfilerRequest = new StopAsyncProfilerRequest(selectedOption, filePathToDump);

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
