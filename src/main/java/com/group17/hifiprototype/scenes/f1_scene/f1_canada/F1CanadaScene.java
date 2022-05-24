package com.group17.hifiprototype.scenes.f1_scene.f1_canada;

import com.group17.hifiprototype.MainApplication;
import com.group17.hifiprototype.backend.dataclasses.Race;
import com.group17.hifiprototype.backend.dataclasses.RaceGroups;
import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class F1CanadaScene extends BaseScene {
    private static F1CanadaController controller;

    private F1CanadaScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static F1CanadaScene init(Stage stage, SceneId sceneId, RaceGroups group, String raceName) throws IOException {
        // LOAD SCENE

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("f1_canada.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        F1CanadaScene returnedScene = new F1CanadaScene();
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
