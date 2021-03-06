package com.group17.hifiprototype.scenes.main_menu_scene;

import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainMenuController {
    @FXML
    private VBox contentWindow;
    @FXML
    private javafx.scene.control.Button F1Button;
    @FXML
    private javafx.scene.control.Button WECButton;
    @FXML
    private javafx.scene.control.Button NascarButton;
    @FXML
    private javafx.scene.control.Button LocalButton;
    @FXML
    private javafx.scene.control.Button FavouritesButton;

    public void init(){
        F1Button.setOnAction(actionEvent -> SceneController.setScene(SceneId.F1_SPAIN));
        WECButton.setOnAction(actionEvent -> SceneController.setScene(SceneId.WEC_LEMANS));
        NascarButton.setOnAction(actionEvent -> SceneController.setScene(SceneId.NASCAR_ALLSTAROPEN));
        LocalButton.setOnAction(actionEvent -> SceneController.setScene(SceneId.FAKE_RACE));
        // FavouritesButton.setOnAction(actionEvent -> SceneController.setScene(SceneId.FAVOURITES));
    }
}
