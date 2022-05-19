package com.group17.hifiprototype.scenes.utils;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class VBoxControls {
    public static void addBlock(String name, String date, String url, AnchorPane anchorPane, VBox container, Runnable onClick) {
        /**
         * Creates a new block where the name of the block is {name}, the date of the block is {date}, and the image of
         * the block is loaded from the path to the image (e.g. /images/logos/example.png for resources/images/...)
         * When clicked, the pane will run the onClick() function. (The function can have no arguments).
         * @param name: The name of the competition on the overlay
         * @param: date: The date when the competition will take place
         * @param url: The url of the background image
         * @param anchorPane: anchorPane to contain the VBox container of the block
         * @param container: The VBox container which contains all the boxes
         * @param onClick: The function that gets called when the block is clicked.
         */

        final double labelPadding = 15;
        final double labelSize = 90;

        GridPane cardGrid = new GridPane();
        // INITIALIZE CONTAINER GRID
        {
            cardGrid.setMaxHeight(1.7976931348623157E308);
            cardGrid.setMaxWidth(1.7976931348623157E308);
            VBox.setVgrow(cardGrid, Priority.SOMETIMES);
            VBox.setMargin(cardGrid, new Insets(0, 0, 20, 0));
            cardGrid.getStyleClass().add("content-box");
            cardGrid.getStyleClass().add("straight-bottom");
            cardGrid.getStyleClass().add("depth-3");
            cardGrid.setOnMouseClicked((event) -> onClick.run());

            // Create and add grid column constraints
            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setMaxWidth(1.7976931348623157E308);
            cardGrid.getColumnConstraints().add(0, columnConstraint);

            // Create and add grid row constraints
            RowConstraints[] rowConstraints = new RowConstraints[2];
            for (int i = 0; i < rowConstraints.length; i++) {
                rowConstraints[i] = new RowConstraints();
                if (i == 0) {
                    rowConstraints[i].setMaxHeight(2 * labelPadding + labelSize);
                    rowConstraints[i].setMinHeight(2 * labelPadding + labelSize);
                    rowConstraints[i].setPrefHeight(2 * labelPadding + labelSize);
                }
                rowConstraints[i].setFillHeight(false);
                rowConstraints[i].setVgrow(Priority.SOMETIMES);
                cardGrid.getRowConstraints().add(i, rowConstraints[i]);
            }

            // Create 1x2 grid layout
            cardGrid.addRow(0);
            cardGrid.addRow(1);
            cardGrid.addColumn(0);
        }

        // CREATE FLAG
        ImageView popupFlag = new ImageView();
        Image flag = ImageControls.createImage(url);
        {
            popupFlag.setImage(flag);
            GridPane.setValignment(popupFlag, VPos.BOTTOM);
            cardGrid.add(popupFlag, 0, 1);
            // popupFlag keeps its aspect ratio
            popupFlag.fitWidthProperty().bind(anchorPane.widthProperty().subtract(30));
            popupFlag.fitHeightProperty().bind(popupFlag.fitWidthProperty().multiply(flag.getHeight() / flag.getWidth()));
        }


        // CREATE LABEL GRID
        GridPane labelGrid = new GridPane();
        {
            labelGrid.setPadding(new Insets(labelPadding));

            GridControls.setExactHeight(labelGrid, 2 * labelPadding + labelSize);

            // Create label grid row constraints
            RowConstraints rowConstraint = new RowConstraints();
            ConstraintControls.setExactHeight(rowConstraint, labelSize);
            rowConstraint.setMaxHeight(labelSize);
            labelGrid.getRowConstraints().add(0, rowConstraint);
            rowConstraint.setValignment(VPos.CENTER);

            // Create label grid column constraints
            ColumnConstraints[] columnConstraints = new ColumnConstraints[2];
            for (int i = 0; i < columnConstraints.length; i++) {
                columnConstraints[i] = new ColumnConstraints();
                columnConstraints[i].setHgrow(Priority.SOMETIMES);
                if (i == 0) {
                    columnConstraints[i].setPercentWidth(75);
                }
                labelGrid.getColumnConstraints().add(i, columnConstraints[i]);
            }

            labelGrid.addColumn(0);
            labelGrid.addColumn(1);
            labelGrid.addRow(0);

            // Create text fields in the grid
            Label nameLabel = new Label(name);
            Label dateLabel = new Label(date);
            {
                // Set max height
                nameLabel.setMaxHeight(labelSize);
                dateLabel.setMaxHeight(labelSize);

                // Add styling
                nameLabel.setStyle("-fx-font-size: 24px;");
                dateLabel.setStyle("-fx-font-size: 24px;");
                nameLabel.getStyleClass().add("roboto");
                nameLabel.getStyleClass().add("bold");
                dateLabel.getStyleClass().add("roboto");

                // Set text and row alignments
                GridPane.setHalignment(nameLabel, HPos.LEFT);
                GridPane.setHalignment(dateLabel, HPos.RIGHT);
                dateLabel.setTextAlignment(TextAlignment.CENTER);
                nameLabel.setTextAlignment(TextAlignment.CENTER);
                dateLabel.setWrapText(true);
                nameLabel.setWrapText(true);
            }
            // Add labels to labelGrid
            labelGrid.add(nameLabel, 0, 0);
            labelGrid.add(dateLabel, 1, 0);
        }

        //Add labelGrid to cardGrid
        cardGrid.add(labelGrid, 0, 0);

        // Add grid to VBox
        container.getChildren().add(cardGrid);

        // CREATE BINDINGS
        {
            // cardGrid has exact width of popupFlag and exact height of popupFlag + labels
            GridControls.bindExactWidth(cardGrid, popupFlag.fitWidthProperty());
            GridControls.bindExactHeight(cardGrid, popupFlag.fitHeightProperty().add(labelGrid.heightProperty()));
        }
    }
}