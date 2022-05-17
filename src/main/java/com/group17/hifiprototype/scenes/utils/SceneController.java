package com.group17.hifiprototype.scenes.utils;

import com.group17.hifiprototype.scenes.base_scene.BaseScene;
import javafx.stage.Stage;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class SceneController {
    private static Stage stage;
    private static Map<SceneId, BaseScene> sceneMap;
    private static BaseScene scene;

    private SceneController() {
    }

    public static void init(Stage stage) {
        stage = stage;
        sceneMap = new HashMap<>();
    }

    public static void addScene(BaseScene scene) {
        sceneMap.put(scene.getId(), scene);
    }

    public static BaseScene currentScene() {
        return scene;
    }

    public static BaseScene getScene(SceneId id){
        /**
         * Returns the scene with the given id
         * Throws exception if it does not exist
         */
        BaseScene newScene = sceneMap.get(id);
        if (newScene == null) {
            throw new InvalidParameterException("That scene does not exist.");
        }
        return newScene;
    }

    public static void setScene(SceneId id) {
        /**
         * sets the displayed scene to another
         * throws exception if the requested scene does not exist
         */
        if(currentScene() != null){
            currentScene().resetScene();
        }
        BaseScene newScene = sceneMap.get(id);
        if (newScene == null) {
            throw new InvalidParameterException("That scene does not exist.");
        } else {
            newScene.getStage().setScene(newScene.getScene());
            newScene.getStage().setWidth(newScene.getStage().getWidth() + 0.01); // TODO: FIX THE BUG AND REMOVE
            newScene.getStage().show();
            scene = newScene;
        }
    }


}
