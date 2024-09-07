package com.profiler4j.javafx.ui;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import java.util.Optional;
import java.util.ResourceBundle;

public class ObservableResourceFactory {

    public static final ObservableResourceFactory INSTANCE = new ObservableResourceFactory();

    private ObservableResourceFactory(){}

    private final ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();

    public ObjectProperty<ResourceBundle> resourcesProperty() {
        return resources;
    }

    public final ResourceBundle getResources() {
        return resourcesProperty().get();
    }

    public final void setResources(ResourceBundle resources) {
        resourcesProperty().set(resources);
    }

    public boolean containsKey(String key) {
        return getResources().containsKey(key);
    }

    public String getString(String key) {
        return getResources().getString(key);
    }

    public StringBinding getStringBinding(String key, ObservableValue<?>... observables) {
        return new StringBinding() {
            {
                bind(resourcesProperty());
                bind(observables);
            }

            @Override
            public String computeValue() {
                String text = getResources().getString(key);
                int index = 0;
                for (ObservableValue<?> observable: observables) {
                    text = text.replace("{" + index + "}", Optional.ofNullable(observable.getValue()).map(Object::toString).orElse(""));
                    index++;
                }
                return text;
            }
        };
    }
}
