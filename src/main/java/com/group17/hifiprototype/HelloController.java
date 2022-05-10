package com.group17.hifiprototype;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private VBox contentWindow;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(Double.toString(contentWindow.getWidth()));
    }
}