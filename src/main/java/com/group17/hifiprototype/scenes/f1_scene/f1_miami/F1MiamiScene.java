package com.group17.hifiprototype.scenes.f1_scene.f1_miami;

import com.group17.hifiprototype.MainApplication;
import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class F1MiamiScene extends BaseScene {
    private static F1MiamiController controller;

    private F1MiamiScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static F1MiamiScene init(Stage stage, SceneId sceneId, String raceName) throws IOException {
        // LOAD SCENE

        //TODO: CHANGE SCENE
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("f1_miami.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        F1MiamiScene returnedScene = new F1MiamiScene();
        returnedScene.setSceneValues(sceneId, stage, scene);

        controller = fxmlLoader.getController();

        // SET ACTION VARIABLES
        controller.init(raceName);

        // ADD SCENE TO CONTROLLER
        SceneController.addScene(returnedScene);

        // ADD STYLESHEET
        returnedScene.getScene().getStylesheets().add("style.css");

        return returnedScene;
    }


}
