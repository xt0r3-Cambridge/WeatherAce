package com.group17.hifiprototype.scenes.nascar_scene.nascar_enjoyillinois;

import com.group17.hifiprototype.MainApplication;
import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class NascarEnjoyIllinoisScene extends BaseScene {
    private static NascarEnjoyIllinoisController controller;

    private NascarEnjoyIllinoisScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static NascarEnjoyIllinoisScene init(Stage stage, SceneId sceneId, String raceName) throws IOException {
        // LOAD SCENE

        //TODO: CHANGE SCENE
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("nascar_enjoyillinois.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        NascarEnjoyIllinoisScene returnedScene = new NascarEnjoyIllinoisScene();
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
