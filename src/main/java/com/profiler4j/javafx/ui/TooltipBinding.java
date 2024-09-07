package com.profiler4j.javafx.ui;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Tooltip;

public class TooltipBinding extends ObjectBinding<Tooltip> {

    private static final ObservableResourceFactory RESOURCE_FACTORY = ObservableResourceFactory.INSTANCE;
    private final String tooltipKey;

    public TooltipBinding(StringProperty text, String tooltipKey) {
        bind(text);
        this.tooltipKey = tooltipKey;
    }

    @Override
    protected Tooltip computeValue() {
        return new Tooltip(RESOURCE_FACTORY.getString(tooltipKey));
    }

}
