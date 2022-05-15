package com.group17.hifiprototype.scenes.utils;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.security.InvalidParameterException;

public class VBoxControls {
    public static void addBlock(String name, String date, String url, AnchorPane anchorPane, VBox container, Runnable onClick) {
        /*
        Creates a new block where the name of the block is {name}, the date of the block is {date}, and the image of
        the block is loaded from the path to the image (e.g. @/images/logos/example.png for resources/images/...)
        When clicked, the pane will run the onClick() function. (That can have no arguments).
         */

        // Initialize container grid for the block
        GridPane cardGrid = new GridPane();
        cardGrid.setMaxHeight(1.7976931348623157E308);
        cardGrid.setMaxWidth(1.7976931348623157E308);
        VBox.setVgrow(cardGrid, Priority.SOMETIMES);
        VBox.setMargin(cardGrid, new Insets(0, 0,20, 0));
        cardGrid.getStyleClass().add("content-box");
        cardGrid.getStyleClass().add("depth-3");

        // Create grid column constraints
        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setMaxWidth(1.7976931348623157E308);
        columnConstraint.setPercentWidth(100);
        cardGrid.getColumnConstraints().add(0, columnConstraint);

        // Create grid row constraints
        RowConstraints[] rowConstraints = new RowConstraints[2];
        for (int i = 0; i < rowConstraints.length; i++) {
            rowConstraints[i] = new RowConstraints();
            rowConstraints[i].setFillHeight(false);
            rowConstraints[i].setVgrow(Priority.SOMETIMES);
            cardGrid.getRowConstraints().add(i, rowConstraints[i]);
        }

        // Create 1x2 grid layout
        cardGrid.addRow(0);
        cardGrid.addRow(1);
        cardGrid.addColumn(0);

        // Create flag and add to grid
        Image flag = ImageControls.createImage(url);
        ImageView popupFlag = new ImageView();
        popupFlag.setImage(flag);
        cardGrid.add(popupFlag, 0, 1);

        // Create grid for text
        GridPane labelGrid = new GridPane();
        labelGrid.setPadding(new Insets(15));

        // Create label grid row constraints
        RowConstraints label_rowConstraint = new RowConstraints();
        label_rowConstraint.setMinHeight(60);
        label_rowConstraint.setPrefHeight(60);
        label_rowConstraint.setVgrow(Priority.SOMETIMES);
        columnConstraint.setPercentWidth(100);
        labelGrid.getRowConstraints().add(0, label_rowConstraint);

        // Create label grid column constraints
        ColumnConstraints[] label_columnConstraints = new ColumnConstraints[2];
        for (int i = 0; i < rowConstraints.length; i++) {
            label_columnConstraints[i] = new ColumnConstraints();
            label_columnConstraints[i].setHgrow(Priority.SOMETIMES);
            if(i == 0){
                label_columnConstraints[i].setPercentWidth(75);
            }
            labelGrid.getColumnConstraints().add(i, label_columnConstraints[i]);
        }

        labelGrid.addColumn(0);
        labelGrid.addColumn(1);
        labelGrid.addRow(0);

        // Create text and add to grid
        Label nameLabel = new Label(name);
        Label dateLabel = new Label(date);

        nameLabel.setStyle("-fx-font-size: 24px;");
        dateLabel.setStyle("-fx-font-size: 24px;");

        nameLabel.getStyleClass().add("roboto");
        nameLabel.getStyleClass().add("bold");
        dateLabel.getStyleClass().add("roboto");

        nameLabel.setMaxWidth(1.7976931348623157E308);
        dateLabel.setMaxWidth(1.7976931348623157E308);

        GridPane.setHalignment(nameLabel, HPos.LEFT);
        GridPane.setHalignment(dateLabel, HPos.RIGHT);

        dateLabel.setTextAlignment(TextAlignment.CENTER);
        dateLabel.setWrapText(true);

        // Add labels to labelGrid
        labelGrid.add(nameLabel, 0, 0);
        labelGrid.add(dateLabel, 1, 0);

        //Add labelGrid to cardGrid
        cardGrid.add(labelGrid, 0, 0);

        cardGrid.setOnMouseClicked((event) -> onClick.run());

        // Add grid to VBox
        container.getChildren().add(cardGrid);

        // Create bindings
        popupFlag.fitWidthProperty().bind(anchorPane.widthProperty().subtract(30));
        popupFlag.fitHeightProperty().bind(popupFlag.fitWidthProperty().multiply(173.0 / 324.0));
        cardGrid.prefWidthProperty().bind(popupFlag.fitWidthProperty());
        cardGrid.prefHeightProperty().bind(popupFlag.fitHeightProperty().add(60));


    }
}

/*
<AnchorPane fx:id="overlay" maxWidth="-Infinity" AnchorPane.bottomAnchor="15.0" AnchorPane.leftAnchor="15.0" AnchorPane.rightAnchor="15.0" AnchorPane.topAnchor="15.0">
         <children>
              <ScrollPane hbarPolicy="NEVER" maxWidth="-Infinity" pannable="true" prefHeight="200.0" prefWidth="200.0" styleClass="transparent" vbarPolicy="NEVER" visible="true" AnchorPane.bottomAnchor="0" AnchorPane.leftAnchor="0" AnchorPane.rightAnchor="0" AnchorPane.topAnchor="62">
               <content>
                  <VBox fx:id="popupvbox" alignment="CENTER" maxWidth="-Infinity" prefHeight="168.0" prefWidth="343.0">
                     <children>
                        <GridPane fx:id="cardGrid" maxHeight="1.7976931348623157E308" maxWidth="1.7976931348623157E308" prefHeight="266.0" prefWidth="342.0" VBox.vgrow="SOMETIMES">
                          <columnConstraints>
                              <ColumnConstraints maxWidth="1.7976931348623157E308" percentWidth="100.0" />
                          </columnConstraints>
                          <rowConstraints>
                            <RowConstraints fillHeight="false" vgrow="SOMETIMES" />
                            <RowConstraints fillHeight="false" vgrow="SOMETIMES" />
                              <RowConstraints />
                          </rowConstraints>
                           <children>
                              <ImageView fx:id="popupFlag" GridPane.rowIndex="1">
                                 <image>
                                    <Image url="@images/flags/usa.png" />
                                 </image>
                              </ImageView>
                              <GridPane maxWidth="1.7976931348623157E308">
                                <columnConstraints>
                                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" percentWidth="75.0" prefWidth="100.0" />
                                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                                </columnConstraints>
                                <rowConstraints>
                                  <RowConstraints minHeight="60.0" prefHeight="60.0" vgrow="SOMETIMES" />
                                </rowConstraints>
                                 <children>
                                    <Label maxWidth="1.7976931348623157E308" text="Miami Grand Prix" GridPane.halignment="LEFT">
                                       <styleClass>
                                          <String fx:value="roboto" />
                                          <String fx:value="bold" />
                                       </styleClass>
                                       <font>
                                          <Font size="24.0" />
                                       </font>
                                    </Label>
                                    <Label maxWidth="1.7976931348623157E308" styleClass="roboto" text="06-08 May" textAlignment="CENTER" wrapText="true" GridPane.columnIndex="1" GridPane.halignment="RIGHT">
                                       <font>
                                          <Font size="24.0" />
                                       </font>
                                    </Label>
                                 </children>
                                 <padding>
                                    <Insets bottom="15.0" left="15.0" right="15.0" top="15.0" />
                                 </padding>
                              </GridPane>
                           </children>
                           <styleClass>
                              <String fx:value="depth-3" />
                              <String fx:value="content-box" />
                           </styleClass>
                           <VBox.margin>
                              <Insets bottom="20.0" />
                           </VBox.margin>
                        </GridPane>
 */