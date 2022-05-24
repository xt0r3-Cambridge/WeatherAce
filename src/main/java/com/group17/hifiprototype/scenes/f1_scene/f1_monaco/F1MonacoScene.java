package com.group17.hifiprototype.scenes.f1_scene.f1_monaco;

import com.group17.hifiprototype.MainApplication;
import com.group17.hifiprototype.backend.dataclasses.RaceGroups;
import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class F1MonacoScene extends BaseScene {
    private static F1MonacoController controller;

    private F1MonacoScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static F1MonacoScene init(Stage stage, SceneId sceneId, RaceGroups group, String raceName) throws IOException {
        // LOAD SCENE

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("f1_monaco.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        F1MonacoScene returnedScene = new F1MonacoScene();
        returnedScene.setSceneValues(sceneId, stage, scene);

        controller = fxmlLoader.getController();

        // SET ACTION VARIABLES
        controller.init(group, raceName);

        // ADD SCENE TO CONTROLLER
        SceneController.addScene(returnedScene);

        // ADD STYLESHEET
        returnedScene.getScene().getStylesheets().add("style.css");

        return returnedScene;
    }


}
