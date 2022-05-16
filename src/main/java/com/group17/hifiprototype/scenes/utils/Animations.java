package com.group17.hifiprototype.scenes.utils;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animations {

    public static void changeSize(final Pane pane, double width, double height) {
        Duration cycleDuration = Duration.millis(500);
        Timeline timeline = new Timeline(
                new KeyFrame(cycleDuration,
                        new KeyValue(pane.prefWidthProperty(), width, Interpolator.EASE_BOTH)),
                new KeyFrame(cycleDuration,
                        new KeyValue(pane.prefHeightProperty(), height, Interpolator.EASE_BOTH))
        );

        timeline.play();
        timeline.setOnFinished(event -> {
            // Sounds good, doesn't work
        });
    }
}
