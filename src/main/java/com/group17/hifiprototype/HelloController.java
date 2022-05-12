package com.group17.hifiprototype;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private VBox contentWindow;
    @FXML
    private javafx.scene.control.Button F1Button;
    @FXML
    private javafx.scene.control.Button WECButton;
    @FXML
    private javafx.scene.control.Button NascarButton;
    @FXML
    private javafx.scene.control.Button LocalButton;
    @FXML
    private javafx.scene.control.Button FavouritesButton;


    private Map<String, ButtonMethods> buttons;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(Double.toString(contentWindow.getWidth()));
    }

    @FXML
    protected void mainButtonHandler(Event evt) {
        String buttonID = ((Control)evt.getSource()).toString();
        System.out.println(buttonID);
        ButtonMethods button = this.buttons.get(buttonID);
        if (button == null) {
            throw new IllegalAccessError("The requested button does not exist");
        }
        button.handleClick();
    }

    private class ButtonClickHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {

        }
    }
}

interface ButtonMethods {
    void handleClick();
}

class F1Button implements ButtonMethods {
    public void handleClick(){

    }
}