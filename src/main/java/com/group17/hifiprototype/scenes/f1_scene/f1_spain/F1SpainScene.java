package com.group17.hifiprototype.scenes.f1_scene.f1_spain;

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

public class F1SpainScene extends BaseScene {
    private static F1SpainController controller;

    private F1SpainScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static F1SpainScene init(Stage stage, SceneId sceneId, RaceGroups group, String raceName) throws IOException {
        // LOAD SCENE

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("f1_spain.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        F1SpainScene returnedScene = new F1SpainScene();
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
