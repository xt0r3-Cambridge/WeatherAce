package com.group17.hifiprototype.scenes.f1_scene;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.Animations;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.VBoxControls;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class F1Controller extends BaseMotorsportController {

    public void loadWeatherData(){
        GridControls.newRowFromData(super.bottomContainer, "FRI", "", "", "", "", "");
        GridControls.newRowFromData(super.bottomContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.bottomContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.bottomContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.bottomContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.bottomContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.bottomContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");

    }

    public void loadRaceData(){
        VBoxControls.addBlock("Miami Grand Prix", "06-08 May", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("I am the best"));
        VBoxControls.addBlock("Fasz2", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click"));
        VBoxControls.addBlock("Fasz3", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click2"));
        VBoxControls.addBlock("Fasz4", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click3"));

    }
}

