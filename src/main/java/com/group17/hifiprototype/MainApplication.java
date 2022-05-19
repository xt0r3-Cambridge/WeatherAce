package com.group17.hifiprototype;

import com.group17.hifiprototype.dataclasses.Group;
import com.group17.hifiprototype.dataclasses.Race;
import com.group17.hifiprototype.dataclasses.RaceGroups;
import com.group17.hifiprototype.dataclasses.RaceLoader;
import com.group17.hifiprototype.scenes.example_scene.ExampleScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_azerbaijan.F1AzerbaijanScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_britain.F1BritainScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_canada.F1CanadaScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_miami.F1MiamiScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_monaco.F1MonacoScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_spain.F1SpainScene;
import com.group17.hifiprototype.scenes.main_menu_scene.MainMenuScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_allstaropen.NascarAllStarOpenScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_allstarrace.NascarAllStarRaceScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_cocacola.NascarCocaColaScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_enjoyillinois.NascarEnjoyIllinoisScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_toyota.NascarToyotaScene;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.wec_scene.wec_bahrain.WecBahrainScene;
import com.group17.hifiprototype.scenes.wec_scene.wec_fuji.WecFujiScene;
import com.group17.hifiprototype.scenes.wec_scene.wec_lemans.WecLeMansScene;
import com.group17.hifiprototype.scenes.wec_scene.wec_monza.WecMonzaScene;
import javafx.application.Application;
import javafx.scene.Scene;
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

        // INIT RACES
        RaceLoader.initialise("src/main/resources/com/group17/hifiprototype/races");


        // LOAD APPLICATION PAGES (and add them to the controller)
        MainMenuScene.init(stage, SceneId.MAIN_MENU);
        ExampleScene.init(stage, SceneId.EXAMPLE);

        F1AzerbaijanScene.init(stage, SceneId.F1_AZERBAIJAN);
        F1BritainScene.init(stage, SceneId.F1_BRITAIN);
        F1CanadaScene.init(stage, SceneId.F1_CANADA);
        F1MiamiScene.init(stage, SceneId.F1_MIAMI);
        F1MonacoScene.init(stage, SceneId.F1_MONACO);
        F1SpainScene.init(stage, SceneId.F1_SPAIN);

        NascarAllStarOpenScene.init(stage, SceneId.NASCAR_ALLSTAROPEN);
        NascarAllStarRaceScene.init(stage, SceneId.NASCAR_ALLSTARRACE);
        NascarCocaColaScene.init(stage, SceneId.NASCAR_COCACOLA);
        NascarEnjoyIllinoisScene.init(stage, SceneId.NASCAR_ENJOYILLINOIS);
        NascarToyotaScene.init(stage, SceneId.NASCAR_TOYOTA);

        WecBahrainScene.init(stage, SceneId.WEC_BAHRAIN);
        WecFujiScene.init(stage, SceneId.WEC_FUJI);
        WecLeMansScene.init(stage, SceneId.WEC_LEMANS);
        WecMonzaScene.init(stage, SceneId.WEC_MONZA);

        SceneController.setScene(SceneId.F1_SPAIN);
    }

    public static void main(String[] args) {
        launch();
    }
}