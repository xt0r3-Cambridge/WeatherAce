package com.group17.hifiprototype.scenes.utils;

import com.group17.hifiprototype.backend.dataclasses.Direction;
import eu.hansolo.tilesfx.Tile;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GridControls extends PaneControls {

    public static void newRowFromData(GridPane grid, String... data) {
        /**
         * @param grid: The grid containing the items (detailedDataContainer in /scenes/base_scene/BaseMotorsportController.java)
         * @param data: A list of stings the need to be added to the grid.
         *              For our purposes, it needs to have 5 elements, that are
         *              (time, rain probability, temperature, road temperature, visibility)
         *              Example:
         *              ("14:20", "12%", "36°C", "200°C", "700km/h", "1.5m")
         */

        int colCount = grid.getColumnCount();
        int rowCount = grid.getRowCount() + 1; // Ugly modification because of a change in implementation

        // Check whether we have the correct number of data points
        if(data.length != colCount){
            throw new InvalidParameterException(String.format(
                    "The number of items in grid [%d] are not matched by the number of data points [%d]",
                    colCount,
                    data.length
            ));
        }

        // CREATING ROW DATA
        {
            // Create data for a row consisting of all the labels
            Node[] rowData = Arrays.stream(data).map(
                    text -> {
                        Node label = new Label(text);
                        GridPane.setHalignment(label, HPos.CENTER);
                        GridPane.setValignment(label, VPos.CENTER);
                        return label;
                    }
            ).toArray(value -> new Node[colCount]);
            // Add bold style to the first column
            rowData[0].getStyleClass().add("bold");
            // Add row to grid
            grid.addRow(rowCount - 1, rowData);
        }

        // CREATING ROW CONSTRAINTS
        {
            // Set the row height to be 30 pixels
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(30);
            rowConstraints.setVgrow(Priority.NEVER);
            grid.getRowConstraints().add(rowCount - 1, rowConstraints);
        }

    }

    // FILL WITH MOCK DATA
    public static void fillWithMock(GridPane grid){
        Direction[] directions = {Direction.E, Direction.N, Direction.NE, Direction.NW, Direction.S, Direction.W, Direction.SE, Direction.SW};
        GridControls.newRowFromData(grid, "FRI", "", "", "", "", "");
        for(int i = 0; i < 10; i++){
            addMockRow(grid, directions, i * 5);
        }
        GridControls.newRowFromData(grid, "", "", "", "", "", "");
        GridControls.newRowFromData(grid, "SAT", "", "", "", "", "");
        for(int i = 0; i < 10; i++){
            addMockRow(grid, directions, i * 5);
        }
    }

    public static void addMockRow(GridPane grid, Direction[] directions, int time){
        GridControls.newRowFromData(
                grid,
                "14:" + time,
                ThreadLocalRandom.current().nextInt(0, 101) + "%",
                ThreadLocalRandom.current().nextInt(0, 40) + "°C",
                ThreadLocalRandom.current().nextInt(0, 40) + "°C",
                ThreadLocalRandom.current().nextInt(0, 15) + "m/s " + directions[ThreadLocalRandom.current().nextInt(0, directions.length)],
                ThreadLocalRandom.current().nextInt(1, 20) +" km"
        );
    }

}
