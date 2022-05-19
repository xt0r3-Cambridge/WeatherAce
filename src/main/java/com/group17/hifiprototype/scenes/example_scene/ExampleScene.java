package com.group17.hifiprototype.scenes.example_scene;

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


public class ExampleScene extends BaseScene {
    private static ExampleController controller;

    private ExampleScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static ExampleScene init(Stage stage, SceneId sceneId, RaceGroups group, String raceName) throws IOException {
        /**
         * Initializes the scene from its fxml file and the controller
         * Adds the stylesheet
         */

        // LOAD SCENE
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("example_scene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        ExampleScene returnedScene = new ExampleScene();
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
