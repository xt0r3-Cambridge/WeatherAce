package com.group17.hifiprototype.scenes.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.security.InvalidParameterException;

public class VBoxControls {
    public static void addBlock(String name, String date, String url, VBox container) {
        /*
        Creates a new block where the name of the block is {name}, the date of the block is {date}, and the image of
        the block is loaded from the path to the image (e.g. @/images/logos/example.png for resources/images/...)
         */
        if(url.charAt(0) != '@'){
            throw new InvalidParameterException("Url must start with @ to start looking in the resources folder");
        }

        // Initialize container grid for the block
        GridPane cardGrid = new GridPane();
        cardGrid.setMaxHeight(1.7976931348623157E308);
        cardGrid.setMaxWidth(1.7976931348623157E308);

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

        // Create flag
        Image flag = new Image(url);
        ImageView popupFlag = new ImageView();
        popupFlag.setPickOnBounds(true);
        popupFlag.setPreserveRatio(true);
        popupFlag.setImage(flag);
        GridPane.setRowSpan(popupFlag, 2);
        // Add to grid
        cardGrid.add(popupFlag, 0, 1);

                popupFlag.fitWidthProperty().bind(container.widthProperty().subtract(30));
        popupFlag.fitHeightProperty().bind(popupFlag.fitWidthProperty().multiply(173.0 / 324.0));
        cardGrid.prefWidthProperty().bind(popupFlag.fitWidthProperty());
        cardGrid.prefHeightProperty().bind(popupFlag.fitHeightProperty().add(60));
    }
}

/*
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
                              <ImageView fx:id="popupFlag" fitHeight="174.0" fitWidth="324.0" pickOnBounds="true" preserveRatio="true" styleClass="content-box" GridPane.halignment="CENTER" GridPane.rowIndex="1" GridPane.valignment="BOTTOM">
                                 <image>
                                    <Image url="@images/flags/usa.png" />
                                 </image>
                              </ImageView>
                              <GridPane>
                                <columnConstraints>
                                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" percentWidth="75.0" prefWidth="100.0" />
                                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                                </columnConstraints>
                                <rowConstraints>
                                  <RowConstraints minHeight="60.0" prefHeight="60.0" vgrow="SOMETIMES" />
                                </rowConstraints>
                                 <children>
                                    <Label style="-fx-font-size: 24px;" text="Miami Grand Prix" GridPane.halignment="LEFT">
                                       <styleClass>
                                          <String fx:value="roboto" />
                                          <String fx:value="bold" />
                                       </styleClass>
                                       <font>
                                          <Font size="24.0" />
                                       </font>
                                    </Label>
                                    <Label style="-fx-font-size: 24px;" styleClass="roboto" text="06-08 May" textAlignment="CENTER" wrapText="true" GridPane.columnIndex="1" GridPane.halignment="RIGHT">
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