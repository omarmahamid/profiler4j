package com.profiler4j.javafx.application;

import com.profiler4j.async.AllocationAsyncProfiler;
import com.profiler4j.async.AsyncProfilerRequest;
import com.profiler4j.async.CPUAsyncProfilerHandler;
import com.profiler4j.async.IAsyncProfilerHandler;
import com.profiler4j.async.exceptions.AsyncProfilerException;
import com.profiler4j.javafx.ui.ButtonCreator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.profiler4j.async.AsyncProfileActions.START;

public class ProfilerApplication extends Application {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilerApplication.class);

    private final IAsyncProfilerHandler cpuProfiling = new CPUAsyncProfilerHandler();
    private final IAsyncProfilerHandler allocProfiling = new AllocationAsyncProfiler();


    private static final int WINDOW_WIDTH = 1024;

    private static final int WINDOW_HEIGHT = 550;

    private Button startButton;
    private Button stopButton;
    private TextField durationField;
    private ComboBox<String> profilerOptions;



    @Override
    public void start(Stage stage) throws Exception {

        startButton = ButtonCreator.createButton("START");

        stopButton = ButtonCreator.createButton("STOP");

        durationField = new TextField();
        durationField.setPromptText("Enter duration");

        profilerOptions = new ComboBox<>();
        profilerOptions.getItems().addAll("CPU", "ALLOC");
        profilerOptions.setValue("CPU");

        startButton.setOnAction(event -> {
            long duration;
            try {
                duration = Long.parseLong(durationField.getText());
                if (duration <= 0) {
                    throw new IllegalArgumentException("duration should be greater than zero");
                }
            }catch (Exception e){
                throw new RuntimeException("please, enter a valid integer number",e);
            }

            String selectedOption = profilerOptions.getValue();

            AsyncProfilerRequest asyncProfilerRequest = new AsyncProfilerRequest(duration, selectedOption, START.name(), "");

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
        });

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(durationField, profilerOptions, startButton, stopButton);


        Scene scene = new Scene(hbox, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);

        stage.setTitle("Profiler Application");

        stage.show();
    }
}
