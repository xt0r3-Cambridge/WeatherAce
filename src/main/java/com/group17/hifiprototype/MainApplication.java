package com.group17.hifiprototype;

import com.group17.hifiprototype.backend.dataclasses.RaceGroups;
import com.group17.hifiprototype.backend.dataclasses.RaceLoader;
import com.group17.hifiprototype.scenes.example_scene.ExampleScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_azerbaijan.F1AzerbaijanScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_britain.F1BritainScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_canada.F1CanadaScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_miami.F1MiamiScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_monaco.F1MonacoScene;
import com.group17.hifiprototype.scenes.f1_scene.f1_spain.F1SpainScene;
import com.group17.hifiprototype.scenes.fake_scene.FakeRace.FakeRaceScene;
import com.group17.hifiprototype.scenes.main_menu_scene.MainMenuScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_allstaropen.NascarAllStarOpenScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_allstarrace.NascarAllStarRaceScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_cocacola.NascarCocaColaScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_enjoyillinois.NascarEnjoyIllinoisScene;
import com.group17.hifiprototype.scenes.nascar_scene.nascar_toyota.NascarToyotaScene;
import com.group17.hifiprototype.scenes.utils.ImageControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.wec_scene.wec_bahrain.WecBahrainScene;
import com.group17.hifiprototype.scenes.wec_scene.wec_fuji.WecFujiScene;
import com.group17.hifiprototype.scenes.wec_scene.wec_lemans.WecLeMansScene;
import com.group17.hifiprototype.scenes.wec_scene.wec_monza.WecMonzaScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // SET STAGE PARAMETERS
        stage.setTitle("WeatherAce");
         Image icon = ImageControls.createImage("icon.png");
         stage.getIcons().add(icon);

         stage.setWidth(480);  // can be resized during runtime
         stage.setHeight(800);  // can be resized during runtime

        // INIT SCENE CONTROLLER
        SceneController.init(stage);

        // INIT RACES
        RaceLoader.initialise("src/main/resources/com/group17/hifiprototype/races");


        // LOAD APPLICATION PAGES (and add them to the controller)
        MainMenuScene.init(stage, SceneId.MAIN_MENU);
        ExampleScene.init(stage, SceneId.EXAMPLE, RaceGroups.F1, "Monaco Grand Prix");
        F1AzerbaijanScene.init(stage, SceneId.F1_AZERBAIJAN, RaceGroups.F1,  "Azerbaijan Grand Prix");
        F1BritainScene.init(stage, SceneId.F1_BRITAIN, RaceGroups.F1, "Britain Grand Prix");
        F1CanadaScene.init(stage, SceneId.F1_CANADA, RaceGroups.F1, "Canada Grand Prix");


        F1MiamiScene.init(stage, SceneId.F1_MIAMI, RaceGroups.F1, "Miami Grand Prix");
        F1MonacoScene.init(stage, SceneId.F1_MONACO, RaceGroups.F1, "Monaco Grand Prix");
        F1SpainScene.init(stage, SceneId.F1_SPAIN, RaceGroups.F1, "Spanish Grand Prix");


        NascarAllStarOpenScene.init(stage, SceneId.NASCAR_ALLSTAROPEN, RaceGroups.NASCAR, "All-Star Open");
        NascarAllStarRaceScene.init(stage, SceneId.NASCAR_ALLSTARRACE, RaceGroups.NASCAR, "All-Star Race");


        NascarCocaColaScene.init(stage, SceneId.NASCAR_COCACOLA, RaceGroups.NASCAR, "Coca-Cola 600");
        NascarEnjoyIllinoisScene.init(stage, SceneId.NASCAR_ENJOYILLINOIS, RaceGroups.NASCAR, "Enjoy Illinois 300");
        NascarToyotaScene.init(stage, SceneId.NASCAR_TOYOTA, RaceGroups.NASCAR, "Toyota/Save Mart 350");


        WecBahrainScene.init(stage, SceneId.WEC_BAHRAIN, RaceGroups.WEC, "Bahrain Race");
        WecFujiScene.init(stage, SceneId.WEC_FUJI, RaceGroups.WEC, "6 Hours of Fuji");
        WecLeMansScene.init(stage, SceneId.WEC_LEMANS, RaceGroups.WEC, "24 Hours of Le Mans");
        WecMonzaScene.init(stage, SceneId.WEC_MONZA, RaceGroups.WEC, "6 Hours of Monza");

        FakeRaceScene.init(stage, SceneId.FAKE_RACE, RaceGroups.Local, "Example race");

        NascarAllStarOpenScene.init(stage, SceneId.NASCAR_ALLSTAROPEN, RaceGroups.NASCAR, "All-Star Open");

        SceneController.setScene(SceneId.MAIN_MENU);
    }

    public static void main(String[] args) {
        launch();
    }
}