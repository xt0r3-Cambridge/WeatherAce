package com.group17.hifiprototype.scenes.utils;

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
import java.util.stream.Collectors;

public class GridControls extends PaneControls {

    public static void newRowFromData(GridPane grid, String... data) {
        /*
        data: A list of stings the need to be added to the grid
         */

        int colCount = grid.getColumnCount();
        int rowCount = grid.getRowCount() + 1; // Because of a change in implementation

        if(data.length != colCount){
            throw new InvalidParameterException(String.format(
                    "The number of items in grid [%d] are not matched by the number of data points [%d]",
                    colCount,
                    data.length
            ));
        }



        grid.addRow(rowCount - 1, Arrays.stream(data).map(
                text -> {
                    Node label = new Label(text);
                    GridPane.setHalignment(label, HPos.CENTER);
                    GridPane.setValignment(label, VPos.CENTER);
                    return label;
                }
        ).toArray(value -> new Node[colCount]));

        Node first = new Label(data[0]);
        GridPane.setHalignment(first, HPos.CENTER);
        GridPane.setValignment(first, VPos.CENTER);
        first.getStyleClass().add("bold");
        grid.add(first, 0, rowCount - 1);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(30);
        rowConstraints.setVgrow(Priority.NEVER);
        grid.getRowConstraints().add(rowCount - 1, rowConstraints);
    }

}
