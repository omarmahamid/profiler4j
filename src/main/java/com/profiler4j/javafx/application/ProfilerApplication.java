package com.profiler4j.javafx.application;

import com.profiler4j.javafx.ui.ButtonCreator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfilerApplication extends Application {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilerApplication.class);


    private static final int WINDOW_WIDTH = 1024;

    private static final int WINDOW_HEIGHT = 550;

    private Button startButton;
    private Button stopButton;



    @Override
    public void start(Stage stage) throws Exception {

        startButton = ButtonCreator.createButton("START");
        stopButton = ButtonCreator.createButton("STOP");



        stage.show();
    }
}
