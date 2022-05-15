package com.group17.hifiprototype.scenes.f1_scene;
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
import java.util.ArrayList;
import java.util.List;


public class F1Scene extends BaseScene {
    private static F1Controller controller;

    private F1Scene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static F1Scene init(Stage stage, SceneId sceneId) throws IOException {
        // LOAD SCENE
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("f1_scene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        F1Scene returnedScene = new F1Scene();
        returnedScene.setSceneValues(sceneId, stage, scene);

        controller = fxmlLoader.getController();

        // SET ACTION VARIABLES
        controller.init();

        // ADD SCENE TO CONTROLLER
        SceneController.addScene(returnedScene);

        // ADD STYLESHEET
        returnedScene.getScene().getStylesheets().add("style.css");

        return returnedScene;
    }


}
