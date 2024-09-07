package com.profiler4j.javafx.application;

import javafx.event.Event;
import javafx.event.EventHandler;

public interface IButtonHandler<T extends Event> {

    EventHandler<T> handle(Object... params);

}
