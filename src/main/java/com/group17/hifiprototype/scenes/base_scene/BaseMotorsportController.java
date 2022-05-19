package com.group17.hifiprototype.scenes.base_scene;

import com.group17.hifiprototype.backend.dataclasses.*;
import com.group17.hifiprototype.scenes.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMotorsportController {
    /**
     * Generic controller for functionality that is the same across all race scenes
     */

    public abstract void loadRaceData();

    public void resetScene() {
        detailedDataScrollPane.setVvalue(0);
        detailedDataScrollPane.setHvalue(0);
        this.closeOverlay();
    }

    public void init(RaceGroups group, String raceName){
        // Load weather data
        this.loadWeatherData(group, raceName);

        // Set the main component's text
        this.setMainWeatherData(group, raceName);

        // Add other races to the overlay (hard coded)
        this.loadRaceData();
        // Setup generic methods
        this.init();
    }

    public void init() {
        /**
         * Initializes the base motorsport scene that will be the same for all race scenes (e.g. F1 Grand Prix in Miami)
         */

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
    }

    public void setMainWeatherData(RaceGroups group, String raceName) {
        /**
         * Attempts to load the weather data for the race with name raceName and set the main weather content box
         * If it fails, it defaults to loading mock data
         */
        try {
            // Get the race
            Race currentRace = RaceLoader.getGroup(group).getRace(raceName);
            // Load the weather data
            currentRace.loadWeatherData();
            // If successful, get the main datapoints and load them to the main content box
            DataPoint mainDataPoint = currentRace.getMainDataPoint();
            mainWind.setText(mainDataPoint.prettyWind());
            mainRain.setText(mainDataPoint.prettyAirTemperature());
            mainTemp.setText(mainDataPoint.prettyGroundTemperature());
        } catch (Exception ignored) {
            // Otherwise add default data
            mainWind.setText("4 m/s " + Direction.NE);
            mainRain.setText("36°C");
            mainTemp.setText("42°C");
        }
    }

    public void loadWeatherData(RaceGroups group, String raceName) {
        /**
         * Attempts to load the weather data for the race with name raceName into the scrollable panel
         * If it fails, it defaults to loading mock data
         */
        try {
            // Get the race
            Race currentRace = RaceLoader.getGroup(group).getRace(raceName);
            // Load the weather data
            currentRace.loadWeatherData();

            // If it is successful, add the datapoints from the weather data to the scrollbar
            ArrayList<Session> sessions = currentRace.getSessions();
            for (Session session : sessions) {
                GridControls.newRowFromData(detailedDataContainer, session.getName(), "", "", "", "", "");
                System.out.println(session.getName());
                List<DataPoint> dataPointList = session.getDataPoints();
                for (DataPoint dataPoint : dataPointList) {
                    GridControls.newRowFromData(
                            detailedDataContainer,
                            dataPoint.prettyTime(),
                            dataPoint.prettyPrecipitation(),
                            dataPoint.prettyAirTemperature(),
                            dataPoint.prettyGroundTemperature(),
                            dataPoint.prettyWind(),
                            dataPoint.prettyVisibility()
                    );
                }
                GridControls.newRowFromData(detailedDataContainer, "", "", "", "", "", "");
            }
        } catch (Exception ignored) {
            // Otherwise fill rows with mock data
            GridControls.newRowFromData(detailedDataContainer, "ERROR:", "Race at", "an invalid", "time", "Filled with", "mock data");
            GridControls.fillWithMock(detailedDataContainer);
        }
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
    protected Text mainWind;
    @FXML
    protected Text mainRain;
    @FXML
    protected Text mainTemp;

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
        if (!detailedDataScrollPane.isPressed()) {
            detailedDataScrollPane.setPannable(false);
        }

    }

}