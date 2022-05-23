package com.group17.hifiprototype.scenes.fake_scene.FakeRace;

import com.group17.hifiprototype.MainApplication;
import com.group17.hifiprototype.backend.dataclasses.RaceGroups;
import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import com.group17.hifiprototype.scenes.fake_scene.LocalGenericController;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FakeRaceScene extends BaseScene {
    private static LocalGenericController controller;

    private FakeRaceScene(){}

    @Override
    public void resetScene() {
        controller.resetScene();
    }

    public static FakeRaceScene init(Stage stage, SceneId sceneId, RaceGroups group, String raceName) throws IOException {
        // LOAD SCENE

        //TODO: CHANGE SCENE
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fake_race.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // CREATE SCENE
        FakeRaceScene returnedScene = new FakeRaceScene();
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
