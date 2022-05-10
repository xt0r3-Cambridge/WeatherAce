package com.group17.hifiprototype.scenes.base_scene;

import com.group17.hifiprototype.scenes.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class BaseMotorsportController {

    public abstract void loadWeatherData();
    public abstract void loadRaceData();

    public void resetScene() {
        detailedDataScrollPane.setVvalue(0);
        detailedDataScrollPane.setHvalue(0);
        this.closeOverlay();
    }

    public void init() {

        // BOTTOM SCROLLBAR
        {
            // SET SCROLLPANE OFFSET (so that the bottom bar is clickable)
            final int scrollPaneOffset = 200;
            detailedDataScrollPane.setLayoutY(scrollPaneOffset);
            // BIND HEIGHTS AND WIDTHS
            ScrollPaneControls.bindExactHeight(detailedDataScrollPane, root.heightProperty().subtract(scrollPaneOffset));
            detailedDataScrollPane.prefWidthProperty().bind(root.widthProperty());
            detailedDataAnchorPane.prefWidthProperty().bind(root.widthProperty());
            detailedDataContainer.prefWidthProperty().bind(root.widthProperty());

            // SETUP SCROLLABLE WEATHER DATA
            {
                // Load weather data
                this.loadWeatherData();
                // Set anchorPane height to have a height that overflows the scrollpane to the data height minus the offset
                detailedDataContainer.setPrefHeight((detailedDataContainer.getRowCount() + 1) * 30);
                final int offset = -60;  // The first row with the icons is 60px tall. We make that stick out.
                AnchorPaneControls.bindExactHeight(detailedDataAnchorPane,
                        detailedDataScrollPane.heightProperty().add(
                                detailedDataContainer.getPrefHeight() + offset
                        )
                );
                // Add the data to the bottom of the anchorPane. Now, only the offset is visible on screen.
                AnchorPane.setBottomAnchor(detailedDataContainer, 0d);

                // EventListener to stop scrolling if we aren't holding the scrollbar component
                detailedDataScrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
                    if (event.getDeltaY() != 0) {
                        if (!detailedDataScrollPane.pannableProperty().getValue()) {
                            event.consume();
                        }
                    }
                });
            }
        }

        // Load race data for the overlay
        this.loadRaceData();
    }

    @FXML
    protected GridPane detailedDataContainer;
    @FXML
    protected AnchorPane detailedDataAnchorPane;
    @FXML
    protected ScrollPane detailedDataScrollPane;
    @FXML
    protected ScrollPane faScrollPane;
    @FXML
    protected AnchorPane root;
    @FXML
    protected GridPane mainContent;
    @FXML
    protected AnchorPane overlay;
    @FXML
    protected ImageView overlayCloseButton;
    @FXML
    protected VBox popupvbox;

    @FXML
    void backToMain() {
        SceneController.setScene(SceneId.MAIN_MENU);
    }

    @FXML
    void openOverlay() {
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
        detailedDataScrollPane.setPannable(true);
    }

    @FXML
    void disableBottomScroll() {
        System.out.println("disabled");
        if (!detailedDataScrollPane.isPressed()) {
            detailedDataScrollPane.setPannable(false);
        }

    }

}