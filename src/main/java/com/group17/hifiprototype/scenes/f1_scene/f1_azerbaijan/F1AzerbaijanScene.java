package com.group17.hifiprototype.scenes.f1_scene.f1_azerbaijan;

import com.group17.hifiprototype.MainApplication;
import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class F1AzerbaijanScene extends BaseScene {
    private static F1AzerbaijanController controller;

    private F1AzerbaijanScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static F1AzerbaijanScene init(Stage stage, SceneId sceneId) throws IOException {
        // LOAD SCENE

        //TODO: CHANGE SCENE
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("f1_azerbaijan.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        F1AzerbaijanScene returnedScene = new F1AzerbaijanScene();
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
