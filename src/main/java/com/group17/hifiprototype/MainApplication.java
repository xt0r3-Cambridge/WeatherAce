package com.group17.hifiprototype;

import com.group17.hifiprototype.scenes.f1_scene.F1Scene;
import com.group17.hifiprototype.scenes.main_menu_scene.MainMenuScene;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // SET STAGE PARAMETERS
        stage.setTitle("WeatherAce");
        /*
         TODO: add icon to resources folder and uncomment
         Image icon = new Image("icon.png");
         stage.getIcons().add(icon);
         */

         stage.setWidth(480);  // can be resized during runtime
         stage.setHeight(800);  // can be resized during runtime
        // INIT SCENE CONTROLLER
        SceneController.init(stage);

        // LOAD APPLICATION PAGES (and add them to the controller)
        MainMenuScene mainMenuScene = MainMenuScene.init(stage, SceneId.MAIN_MENU);
        F1Scene f1Scene = F1Scene.init(stage, SceneId.FORMULA_1);


        SceneController.setScene(SceneId.MAIN_MENU);
    }

    public static void main(String[] args) {
        launch();
    }
}