package com.group17.hifiprototype.scenes.utils;

import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import org.w3c.dom.Node;

public class PaneControls {
    public static void setExactWidth(Pane node, double width) {
        try {
            node.setMaxWidth(width);
            node.setMinWidth(width);
            node.setPrefWidth(width);
        } catch (NoSuchMethodError ignored) {
        }
    }

    public static void setExactHeight(Pane node, double height) {
        try {
            node.setMaxHeight(height);
            node.setMinHeight(height);
            node.setPrefHeight(height);
        } catch (NoSuchMethodError ignored) {
        }
    }

    public static void bindExactWidth(Pane node, ObservableValue<? extends Number> widthProperty){
        try {
            node.minWidthProperty().bind(widthProperty);
            node.maxWidthProperty().bind(widthProperty);
            node.prefWidthProperty().bind(widthProperty);
        } catch (NoSuchMethodError ignored) {
        }
    }

    public static void bindExactHeight(Pane node, ObservableValue<? extends Number> heightProperty){
        try {
            node.minHeightProperty().bind(heightProperty);
            node.maxHeightProperty().bind(heightProperty);
            node.prefHeightProperty().bind(heightProperty);
        } catch (NoSuchMethodError ignored) {
        }
    }
}
