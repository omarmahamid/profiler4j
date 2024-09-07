package com.profiler4j.javafx.application;

import com.profiler4j.javafx.ui.ButtonCreator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfilerApplication extends Application {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilerApplication.class);


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

        startButton.setOnAction(new StartButtonHandler().handle(durationField.getText(), profilerOptions.getValue()));
        stopButton.setOnAction(new StopButtonHandler().handle(profilerOptions.getValue()));

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(durationField, profilerOptions, startButton, stopButton);


        Scene scene = new Scene(hbox, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);

        stage.setTitle("Profiler Application");

        stage.show();
    }
}
