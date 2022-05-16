package com.group17.hifiprototype.scenes.main_menu_scene;
import com.group17.hifiprototype.MainApplication;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuScene extends BaseScene {
    private static MainMenuController controller;

    private MainMenuScene(){}

    @Override
    public void resetScene() {

    }

    public static MainMenuScene init(Stage stage, SceneId sceneId) throws IOException {
        // LOAD SCENE
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main_menu_scene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        MainMenuScene returnedScene = new MainMenuScene();
        returnedScene.setSceneValues(sceneId, stage, scene);

        controller = fxmlLoader.getController();

        // SET ACTION VARIABLES
        controller.init();

        // ADD STYLESHEET
        returnedScene.getScene().getStylesheets().add("style.css");


        // ADD SCENE TO CONTROLLER
        SceneController.addScene(returnedScene);

        return returnedScene;
    }


}
