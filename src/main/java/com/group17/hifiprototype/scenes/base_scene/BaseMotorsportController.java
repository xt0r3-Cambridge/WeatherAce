package com.group17.hifiprototype.scenes.base_scene;

import com.group17.hifiprototype.scenes.utils.Animations;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public abstract class BaseMotorsportController {

    @FXML
    protected ListView<String> scrollList;
    @FXML
    protected AnchorPane container;

    @FXML
    protected GridPane bottomContainer;

    @FXML
    protected AnchorPane myAnchorPane;

    @FXML
    protected ScrollPane myScrollPane;
    @FXML
    protected ScrollPane faScrollPane;

    @FXML
    protected AnchorPane root;
    @FXML
    protected GridPane scrollbar;
    @FXML
    protected AnchorPane scrollbar2;
    @FXML
    protected GridPane mainContent;
    @FXML
    protected AnchorPane overlay;
    @FXML
    protected ImageView overlayCloseButton;
    @FXML
    protected VBox popupvbox;
    @FXML
    protected ImageView popupFlag;
    @FXML
    protected GridPane cardGrid;

    public void resetScene() {
        myScrollPane.setVvalue(0);
        myScrollPane.setHvalue(0);
        this.closeOverlay();
    }

    public abstract void loadWeatherData();
    public abstract void loadRaceData();

    public void init() {

        // Creating the scrollbar for the event data
        final int scrollPaneOffset = 200;
        myScrollPane.setLayoutY(scrollPaneOffset);
        myScrollPane.prefWidthProperty().bind(root.widthProperty());
        myScrollPane.prefHeightProperty().bind(root.heightProperty().subtract(scrollPaneOffset));
        myScrollPane.minHeightProperty().bind(root.heightProperty().subtract(scrollPaneOffset));
        myScrollPane.maxHeightProperty().bind(root.heightProperty().subtract(scrollPaneOffset));
        myAnchorPane.prefWidthProperty().bind(root.widthProperty());
        // Add weather data to it
        this.loadWeatherData();
        // Change positioning to make everything fit
        bottomContainer.setPrefHeight((bottomContainer.getRowCount() + 1) * 30);
        final int offset = -60;
        myAnchorPane.minHeightProperty().bind(myScrollPane.heightProperty().add(bottomContainer.getPrefHeight() + offset));
        myAnchorPane.prefHeightProperty().bind(myScrollPane.heightProperty().add(bottomContainer.getPrefHeight() + offset));
        AnchorPane.setBottomAnchor(bottomContainer, 0d);


        //AnchorPane.setBottomAnchor(bottomContainer, bottomContainer.heightProperty().doubleValue());
        //AnchorPane.setTopAnchor(bottomContainer, 0d);
        //myAnchorPane.setLayoutY(root.getHeight() + offset);



        // Stop scrolling if we aren't holding that component
        myScrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) {
                if (!myScrollPane.pannableProperty().getValue()) {
                    event.consume();
                }
            }
        });
        bottomContainer.prefWidthProperty().bind(root.widthProperty());

        // Load race data for the overlay
        this.loadRaceData();
    }

    @FXML
    void backToMain() {
        SceneController.setScene(SceneId.MAIN_MENU);
    }

    @FXML
    void openOverlay() {
        System.out.println("Fasz");
        overlay.setVisible(true);
        overlayCloseButton.setVisible(true);
        overlay.setMouseTransparent(false);
        overlayCloseButton.setMouseTransparent(false);
    }

    @FXML
    void closeOverlay() {
        overlay.setVisible(false);
        overlayCloseButton.setVisible(false);
        overlay.setMouseTransparent(true);
        overlayCloseButton.setMouseTransparent(true);
        faScrollPane.setVvalue(0);
        faScrollPane.setHvalue(0);
    }

    @FXML
    void enableBottomScroll() {
        myScrollPane.setPannable(true);
    }

    @FXML
    void disableBottomScroll() {
        System.out.println("disabled");
        if (!myScrollPane.isPressed()) {
            myScrollPane.setPannable(false);
        }

    }

}

class BottomScrollbarController {

    public static double closedHeight(Pane content) {
        return content.getHeight() * 0.33;
    }

    public static double openHeight(Pane content) {
        return content.getHeight() * 0.66;
    }

    enum eventType {
        MOUSE_DRAG,
        DRAG,
        MOUSE_OVER
    }

    HashMap properties = new HashMap();


    boolean mouseDrag = false;
    boolean drag = false;
    boolean mouseOver = false;

    public static boolean isDragged() {
        return false;
    }

    public static boolean disableProperty(eventType type) {
        return false;
    }


}