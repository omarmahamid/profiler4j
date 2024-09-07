package com.profiler4j.javafx.ui;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

import java.util.ResourceBundle;

public class ButtonCreator {

    private ButtonCreator(){
        throw new IllegalStateException("UTILITY CLASS");
    }

    private static final String RESOURCE_NAME = "i18n.lang";

    private static final ObservableResourceFactory RESOURCE_FACTORY = ObservableResourceFactory.INSTANCE;

    static
    {
        RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME));
    }

    public static Button createButton(String langKey, ObservableValue<?>... observables) {

        Button button = new Button();
        button.textProperty().bind(RESOURCE_FACTORY.getStringBinding(langKey, observables));

        String tooltipKey = langKey + "_tt";

        if (RESOURCE_FACTORY.containsKey(tooltipKey)) {
            button.tooltipProperty().bind(new TooltipBinding(button.textProperty(), tooltipKey));
        }

        return button;

    }

}
