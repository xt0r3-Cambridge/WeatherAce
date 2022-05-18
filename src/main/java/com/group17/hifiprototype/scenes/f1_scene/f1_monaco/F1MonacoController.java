package com.group17.hifiprototype.scenes.f1_scene.f1_monaco;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class F1MonacoController extends BaseMotorsportController {

    public void loadWeatherData(){
        GridControls.newRowFromData(super.detailedDataContainer, "FRI", "", "", "", "", "");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");

    }

    public void loadRaceData(){
        VBoxControls.addBlock("Miami Grand Prix", "06-08 May", "images/f1/Miami.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_MIAMI));
        VBoxControls.addBlock("Spanish Grand Prix", "20-22 May", "images/f1/Spain.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_SPAIN));
        VBoxControls.addBlock("Monaco Grand Prix", "27-29 May", "images/f1/Monaco.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_MONACO));
        VBoxControls.addBlock("Azerbaijan Grand Prix", "10-12 Jun", "images/f1/Azerbaijan.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_AZERBAIJAN));
        VBoxControls.addBlock("Canadian Grand Prix", "17-19 Jun", "images/f1/Canada.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_CANADA));
        VBoxControls.addBlock("British Grand Prix", "01-03 Jul", "images/f1/Britain.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_BRITAIN));
    }

    public void setMainWeatherData(){
        /**
         * Sets the main weather data for the non-scrollable box in the middle of the screen
         * Needs to be implemented
         */
        super.mainWind.setText("75km/h ↗");
        super.mainRain.setText("99%");
        super.mainTemp.setText("658°C");
    }
}


