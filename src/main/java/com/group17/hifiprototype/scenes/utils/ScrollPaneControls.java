package com.group17.hifiprototype.scenes.utils;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;

public class ScrollPaneControls {
    public static void setExactWidth(ScrollPane node, double width) {
        try {
            node.setMaxWidth(width);
            node.setMinWidth(width);
            node.setPrefWidth(width);
        } catch (NoSuchMethodError ignored) {
        }
    }

    public static void setExactHeight(ScrollPane node, double height) {
        try {
            node.setMaxHeight(height);
            node.setMinHeight(height);
            node.setPrefHeight(height);
        } catch (NoSuchMethodError ignored) {
        }
    }

    public static void bindExactWidth(ScrollPane node, ObservableValue<? extends Number> widthProperty){
        try {
            node.minWidthProperty().bind(widthProperty);
            node.maxWidthProperty().bind(widthProperty);
            node.prefWidthProperty().bind(widthProperty);
        } catch (NoSuchMethodError ignored) {
        }
    }

    public static void bindExactHeight(ScrollPane node, ObservableValue<? extends Number> heightProperty){
        try {
            node.minHeightProperty().bind(heightProperty);
            node.maxHeightProperty().bind(heightProperty);
            node.prefHeightProperty().bind(heightProperty);
        } catch (NoSuchMethodError ignored) {
        }
    }
}
