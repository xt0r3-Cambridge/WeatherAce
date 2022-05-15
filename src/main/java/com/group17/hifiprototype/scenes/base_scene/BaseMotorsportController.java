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

public class BaseMotorsportController {

    @FXML
    protected ListView<String> scrollList;
    @FXML
    protected AnchorPane container;

    @FXML
    GridPane bottomContainer;

    @FXML
    AnchorPane myAnchorPane;

    @FXML
    ScrollPane myScrollPane;
    @FXML
    ScrollPane faScrollPane;

    @FXML
    public AnchorPane root;
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
        init();
    }

    public void init() {
        myScrollPane.setLayoutY(200);
        myScrollPane.prefWidthProperty().bind(root.widthProperty());
        myScrollPane.prefHeightProperty().bind(root.heightProperty().subtract(200));
        myAnchorPane.prefWidthProperty().bind(root.widthProperty());

        final int offset = -300;
        AnchorPane.setTopAnchor(bottomContainer, root.getHeight() + offset);
        root.heightProperty().addListener((observableValue, number, t1) -> {
            AnchorPane.setTopAnchor(bottomContainer, observableValue.getValue().doubleValue() + offset);
        });

        GridControls.newRowFromData(bottomContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");

        myScrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) {
                if (!myScrollPane.pannableProperty().getValue()) {
                    event.consume();
                }
            }
        });
        bottomContainer.prefWidthProperty().bind(root.widthProperty());
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