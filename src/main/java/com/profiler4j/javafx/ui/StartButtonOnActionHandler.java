package com.profiler4j.javafx.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class StartButtonOnActionHandler implements IButtonOnActionHandler{



    @Override
    public void handle(Button button) {

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

    }
}
