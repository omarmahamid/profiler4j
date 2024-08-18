package com.profiler4j.javafx.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonsEventsInitializer {

    public void setButtonEvents(Button button){

        // currently buttons name keys are coupled with the implementors of the set on action.

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

    }



}
