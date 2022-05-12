package com.group17.hifiprototype.scenes.base_scene;

import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseScene {

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
