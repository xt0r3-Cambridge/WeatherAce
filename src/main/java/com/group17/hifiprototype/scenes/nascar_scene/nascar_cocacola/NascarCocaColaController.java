package com.group17.hifiprototype.scenes.nascar_scene.nascar_cocacola;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class NascarCocaColaController extends BaseMotorsportController {

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
        VBoxControls.addBlock("NASCAR All-Star Open", "22 May", "images/flags/usa.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ALLSTAROPEN));
        VBoxControls.addBlock("NASCAR All-Star Race", "23 May", "images/flags/usa.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ALLSTARRACE));
        VBoxControls.addBlock("Coca-Cola 600", "29 May", "images/flags/usa.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_COCACOLA));
        VBoxControls.addBlock("Enjoy Illinois 300", "05 Jun", "images/flags/usa.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ENJOYILLINOIS));
        VBoxControls.addBlock("Toyota/Save Mart 350", "12 Jun", "images/flags/usa.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_TOYOTA));
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


