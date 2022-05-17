package com.group17.hifiprototype.scenes.base_scene;

import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;

public abstract class BaseScene {

    private SceneId id;
    private Stage stage;
    private Scene scene;

    protected BaseScene(){
    }



    public void setSceneValues(SceneId id, Stage stage, Scene scene){
        // Generates a BaseScene. Done this way, so that we avoid issues from constructor chaining
        this.scene = scene;
        this.stage = stage;
        this.id = id;
    }

    public abstract void resetScene();

    public static BaseScene init(Stage stage, SceneId sceneId) throws IOException {
        throw new NotImplementedException("Init must be implemented individually for every scene");
    }

    public SceneId getId(){
        return this.id;
    }

    public Scene getScene(){
        return this.scene;
    }

    public Stage getStage(){
        return this.stage;
    }
}
