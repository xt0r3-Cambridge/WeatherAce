package com.group17.hifiprototype.scenes.base_scene;

import com.group17.hifiprototype.scenes.utils.Animations;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BaseMotorsportController {

    @FXML
    protected ListView<String> scrollList;
    @FXML
    protected AnchorPane container;
    @FXML
    protected GridPane scrollbar;
    @FXML
    protected AnchorPane scrollbar2;
    @FXML
    protected GridPane mainContent;
    @FXML
    protected ScrollPane overlay;
    @FXML
    protected ImageView overlayCloseButton;

    @FXML
    void openScrollbar(){
        System.out.println("itt");
        scrollbar.prefHeightProperty().unbind();
        Animations.changeSize(scrollbar, scrollbar.getWidth(), ScrollbarController.openHeight(mainContent));
    }

    @FXML
    void closeScrollbar(){
        scrollbar.prefHeightProperty().unbind();
        Animations.changeSize(scrollbar, scrollbar.getWidth(), ScrollbarController.closedHeight(mainContent));
    }

    public void resetScene(){
        init();
    }

    public void init() {
        closeOverlay();
        scrollbar.prefHeightProperty().unbind();
        scrollbar.prefHeightProperty().bind(mainContent.heightProperty().divide(3));
        // scrollbar2.prefWidthProperty().bind(mainContent.widthProperty());

    }

    @FXML
    void backToMain(){
        SceneController.setScene(SceneId.MAIN_MENU);
    }

    @FXML
    void openOverlay(){
        overlay.setVisible(true);
        overlayCloseButton.setVisible(true);
    }

    @FXML
    void closeOverlay(){
        overlay.setVisible(false);
        overlayCloseButton.setVisible(false);
    }

    public void addData(Iterable<String> data) {
        for (String dataPoint : data) {
            scrollList.getItems().add(dataPoint);
        }
    }

}

class ScrollbarController{

    public static double closedHeight(Pane content){
        return content.getHeight() * 0.33;
    }

    public static double openHeight(Pane content){
        return content.getHeight() * 0.66;
    }


}